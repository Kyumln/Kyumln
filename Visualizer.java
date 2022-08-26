package hadoop;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;

public class Visualizer {
	public void avgVisualizer() {
		double[] min_xData = new double[] {2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021};
		double[] min_yData = new double[] {-17.1, -16.4, -13.2, -13, -18, -12.6, -17.8, -10.9, -12.9, -18.6};
		
		double[] avg_xData = new double[] {2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021};
		double[] avg_yData = new double[] {12.2, 12.5, 13.4, 13.6, 13.6, 13, 12.9, 13.5, 13.2, 13.7};
		
		double[] max_xData = new double[] {2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021};
		double[] max_yData = new double[] {36.7, 33.9, 35.8, 36, 36.6, 35.4, 39.6, 36.8, 35.4, 36.5};
		
		
		final XYChart chart = new XYChartBuilder().width(600).height(400).title("2012 ~ 2021 서울 연평균기온").xAxisTitle("년도(년)").yAxisTitle("단위(℃)").build();

		chart.getStyler().setLegendPosition(LegendPosition.InsideNE);
		chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);

		chart.addSeries("최저기온(℃)", min_xData, min_yData);
		chart.addSeries("평균기온(℃)", avg_xData, avg_yData);
		chart.addSeries("최대기온(℃)", max_xData, max_yData);
		
		JFrame frame = new JFrame("2012 ~ 2021 서울 연평균기온");
	    frame.setLayout(new BorderLayout());
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    JPanel chartPanel = new XChartPanel<XYChart>(chart);
	    frame.add(chartPanel, BorderLayout.CENTER);
	    
	    frame.pack();
	    frame.setVisible(true);
	}
}
