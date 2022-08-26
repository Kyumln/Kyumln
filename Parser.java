package hadoop;

import java.awt.BorderLayout;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilderFactory;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Parser {

	public String get(String date){
		
		HttpURLConnection con = null;
		String s = null;
		String[] v = new String[5];
		double[] xData = new double[24];
		double[] yData = new double[24];
		double[] rn_xData = new double[24];
		double[] rn_yData = new double[24];
		double[] ws_xData = new double[24];
		double[] ws_yData = new double[24];
		
		System.out.println("기후정보를 출력할 지역을 선택하세요");
		System.out.println("지원하는 지역: 서울, 인천, 세종, 청주, 대전, 포항, 안동, 대구, 전주, 울산, 창원, 광주, 부산");
		System.out.print(">>");
		
		String local_num = null;
		int localnum = 108;
		Scanner sc = new Scanner(System.in);
		local_num = sc.next();
		
		if(local_num.equals("서울")) {
			localnum = 108;
		}else if(local_num.equals("인천")) {
			localnum = 112;
		}else if(local_num.equals("세종")) {
			localnum = 239;
		}else if(local_num.equals("청주")) {
			localnum = 131;
		}else if(local_num.equals("대전")) {
			localnum = 133;
		}else if(local_num.equals("안동")) {
			localnum = 136;
		}else if(local_num.equals("포항")) {
			localnum = 138;
		}else if(local_num.equals("대구")) {
			localnum = 143;
		}else if(local_num.equals("울산")) {
			localnum = 152;
		}else if(local_num.equals("창원")) {
			localnum = 155;
		}else if(local_num.equals("전주")) {
			localnum = 146;
		}else if(local_num.equals("광주")) {
			localnum = 156;
		}else if(local_num.equals("부산")) {
			localnum = 159;
		}
		
		
		try
		{
			URL url = new URL(
				"http://apis.data.go.kr/1360000/AsosHourlyInfoService/getWthrDataList"
				+ "?ServiceKey=ELowM%2FG1NBdsGOuoNcriBAinJ8hVUqq4kdMjc%2FbcgMUJtRemTeC7ldvdvawFTdLJiGID2Mh4o6TJEBJvU8KbcQ%3D%3D"
				+ "&numOfRows=120"
				+ "&pageNo=1"
				+ "&dataCd=ASOS"
				+ "&dateCd=HR"
				+ "&stnIds=" + localnum
				+ "&endDt=" + date
				+ "&endHh=23"
				+ "&startHh=00"
				+ "&startDt=" + date
			);
			
			con = (HttpURLConnection)url.openConnection();
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(con.getInputStream());

			boolean ok = false; 
			
			Element e;
			NodeList ns = doc.getElementsByTagName("header");
			if (ns.getLength() > 0)
			{
				e = (Element)ns.item(0);
				if ("00".equals(e.getElementsByTagName("resultCode").item(0).getTextContent()))
					ok = true; 
				else 
					s = e.getElementsByTagName("resultMsg").item(0).getTextContent();
			}
				
			if (ok)
			{
				ns = doc.getElementsByTagName("item");
				
				for (int i = 0; i < ns.getLength(); i++)
				{
					e = (Element)ns.item(i);
					
					String ta = null;
					String stnNm = null;
					String rn = null;
					String ws = null;
					String dsnw = null;
					
					ta = e.getElementsByTagName("ta").item(0).getTextContent();
					stnNm = e.getElementsByTagName("stnNm").item(0).getTextContent();
					rn = e.getElementsByTagName("rn").item(0).getTextContent();
					ws = e.getElementsByTagName("ws").item(0).getTextContent();
					dsnw = e.getElementsByTagName("dsnw").item(0).getTextContent();
					
					v[0] = ta;
					v[1] = stnNm;
					v[2] = rn;
					v[3] = ws;
					v[4] = dsnw;
					
					double temp = Double.parseDouble(ta);
					xData[i] = i;
					yData[i] = temp;
					
					if(rn.isBlank()) {
					rn_xData[i] = i;
					rn_yData[i] = 0.0;
					}else {
						temp = Double.parseDouble(rn);
						rn_xData[i] = i;
						rn_yData[i] = temp;
					}
					
					if(rn.isBlank()) {
						ws_xData[i] = i;
						ws_yData[i] = 0.0;
						}else {
							temp = Double.parseDouble(ws);
							ws_xData[i] = i;
							ws_yData[i] = temp;
						}
					
					System.out.println("-------"+i+"시-------");
					System.out.println("기온 : " + v[0] + "℃");
					System.out.println("지역 : " + v[1]);
					if(v[2].isBlank()) {
						System.out.println("강우량 : 해당없음");
					}else {
						System.out.println("강우량 : " + v[2]+"mm");
					}
					System.out.println("풍량 : " + v[3] + "m/s");
					if(v[4].isBlank()) {
						System.out.println("적설량: 해당없음");
					}else {
						System.out.println("적설량: " + v[4] + "cm");
					}
					System.out.println("------------------");
					System.out.println();
					
				}
				
				final XYChart chart = new XYChartBuilder().width(600).height(400).title(local_num+"의 어제 날씨").xAxisTitle("시간(시)").yAxisTitle("단위(℃, mm, m/s)").build();

				chart.getStyler().setLegendPosition(LegendPosition.InsideNE);
				chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Area);

				chart.addSeries("기온(℃)", xData, yData);
				chart.addSeries("강수량(mm)", rn_xData, rn_yData);
				chart.addSeries("풍량(m/s)", ws_xData, ws_yData);
				
				JFrame frame = new JFrame(local_num+"의 어제 날씨");
			    frame.setLayout(new BorderLayout());
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			    JPanel chartPanel = new XChartPanel<XYChart>(chart);
			    frame.add(chartPanel, BorderLayout.CENTER);
			    
			    frame.pack();
			    frame.setVisible(true);
				
	        }
		}
		catch (Exception e)
		{
			s = e.getMessage();
		}
		
		if (con != null)
			con.disconnect();
		return s;
	}
	
	public String getSpecificData(){
		
			HttpURLConnection con = null;
			String s = null;
			String[] v = new String[5];
			Scanner sc = new Scanner(System.in);
			String date = null;
			double[] xData = new double[24];
			double[] yData = new double[24];
			double[] rn_xData = new double[24];
			double[] rn_yData = new double[24];
			double[] ws_xData = new double[24];
			double[] ws_yData = new double[24];
			
			System.out.println("정보를 찾으실 날짜를 입력해 주세요. | 입력 형식: 20220801");
			System.out.print(">>");
			date = sc.next();
			
			System.out.println("기후정보를 출력할 지역을 선택하세요");
			System.out.println("지원하는 지역: 서울, 인천, 세종, 청주, 대전, 포항, 안동, 대구, 전주, 울산, 창원, 광주, 부산");
			System.out.print(">>");
			String local_num = null;
			int localnum = 108;
			local_num = sc.next();
			
			if(local_num.equals("서울")) {
				localnum = 108;
			}else if(local_num.equals("인천")) {
				localnum = 112;
			}else if(local_num.equals("세종")) {
				localnum = 239;
			}else if(local_num.equals("청주")) {
				localnum = 131;
			}else if(local_num.equals("대전")) {
				localnum = 133;
			}else if(local_num.equals("안동")) {
				localnum = 136;
			}else if(local_num.equals("포항")) {
				localnum = 138;
			}else if(local_num.equals("대구")) {
				localnum = 143;
			}else if(local_num.equals("울산")) {
				localnum = 152;
			}else if(local_num.equals("창원")) {
				localnum = 155;
			}else if(local_num.equals("전주")) {
				localnum = 146;
			}else if(local_num.equals("광주")) {
				localnum = 156;
			}else if(local_num.equals("부산")) {
				localnum = 159;
			}
			
			try
			{
				URL url = new URL(
					"http://apis.data.go.kr/1360000/AsosHourlyInfoService/getWthrDataList"
					+ "?ServiceKey=ELowM%2FG1NBdsGOuoNcriBAinJ8hVUqq4kdMjc%2FbcgMUJtRemTeC7ldvdvawFTdLJiGID2Mh4o6TJEBJvU8KbcQ%3D%3D"
					+ "&numOfRows=120"
					+ "&pageNo=1"
					+ "&dataCd=ASOS"
					+ "&dateCd=HR"
					+ "&stnIds=" + localnum
					+ "&endDt=" + date
					+ "&endHh=23"
					+ "&startHh=00"
					+ "&startDt=" + date
				);
				
				con = (HttpURLConnection)url.openConnection();
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(con.getInputStream());

				boolean ok = false; 
				
				Element e;
				NodeList ns = doc.getElementsByTagName("header");
				if (ns.getLength() > 0)
				{
					e = (Element)ns.item(0);
					if ("00".equals(e.getElementsByTagName("resultCode").item(0).getTextContent()))
						ok = true; 
					else 
						s = e.getElementsByTagName("resultMsg").item(0).getTextContent();
				}
					
				if (ok)
				{
					ns = doc.getElementsByTagName("item");
					
					for (int i = 0; i < ns.getLength(); i++)
					{
						e = (Element)ns.item(i);
						
						String ta = null;
						String stnNm = null;
						String rn = null;
						String ws = null;
						String dsnw = null;
						
						ta = e.getElementsByTagName("ta").item(0).getTextContent();
						stnNm = e.getElementsByTagName("stnNm").item(0).getTextContent();
						rn = e.getElementsByTagName("rn").item(0).getTextContent();
						ws = e.getElementsByTagName("ws").item(0).getTextContent();
						dsnw = e.getElementsByTagName("dsnw").item(0).getTextContent();
						
						v[0] = ta;
						v[1] = stnNm;
						v[2] = rn;
						v[3] = ws;
						v[4] = dsnw;
						
						double temp = Double.parseDouble(ta);
						xData[i] = i;
						yData[i] = temp;
						
						if(rn.isBlank()) {
						rn_xData[i] = i;
						rn_yData[i] = 0.0;
						}else {
							temp = Double.parseDouble(rn);
							rn_xData[i] = i;
							rn_yData[i] = temp;
						}
						
						if(rn.isBlank()) {
							ws_xData[i] = i;
							ws_yData[i] = 0.0;
							}else {
								temp = Double.parseDouble(ws);
								ws_xData[i] = i;
								ws_yData[i] = temp;
							}
						
						System.out.println("-------"+i+"시-------");
						System.out.println("기온 : " + v[0] + "℃");
						System.out.println("지역 : " + v[1]);
						if(v[2].isBlank()) {
							System.out.println("강우량 : 해당없음");
						}else {
							System.out.println("강우량 : " + v[2]+"mm");
						}
						System.out.println("풍량 : " + v[3] + "m/s");
						if(v[4].isBlank()) {
							System.out.println("적설량: 해당없음");
						}else {
							System.out.println("적설량: " + v[4] + "cm");
						}
						System.out.println("------------------");
						System.out.println();
						
					}
					
					SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
					SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
					Date formatDate = dtFormat.parse(date);
					String format_date = newDtFormat.format(formatDate);
					

					final XYChart chart = new XYChartBuilder().width(600).height(400).title(local_num+"의 "+format_date+" 날씨").xAxisTitle("시간(시)").yAxisTitle("단위(℃, mm, m/s)").build();

					chart.getStyler().setLegendPosition(LegendPosition.InsideNE);
					chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Area);

					chart.addSeries("기온(℃)", xData, yData);
					chart.addSeries("강수량(mm)", rn_xData, rn_yData);
					chart.addSeries("풍량(m/s)", ws_xData, ws_yData);
					
					JFrame frame = new JFrame(local_num+"의 "+format_date+" 날씨");
				    frame.setLayout(new BorderLayout());
				    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				    JPanel chartPanel = new XChartPanel<XYChart>(chart);
				    frame.add(chartPanel, BorderLayout.CENTER);
				    
				    frame.pack();
				    frame.setVisible(true);
					
		        }
			}
			catch (Exception e)
			{
				s = e.getMessage();
			}
			
			if (con != null)
				con.disconnect();
			return s;
	}

}
