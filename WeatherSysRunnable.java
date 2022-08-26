package hadoop;

import java.time.LocalDate;
import java.util.Scanner;

public class WeatherSysRunnable {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int num = 0;
		int temp = 0;
		boolean run = true;
		LocalDate now = LocalDate.now();
		
		Visualizer visualizer = new Visualizer();
		Parser parser = new Parser();

		temp = now.getDayOfMonth();
		String day = Integer.toString(temp-1);
		if(Integer.parseInt(day) <= 9) {
			day = "0"+day;
		}

		temp = now.getMonthValue();
		String month = Integer.toString(temp);
		if(Integer.parseInt(month) <= 9) {
			month = "0"+month;
		}
		
		temp = now.getYear();
		String year = Integer.toString(temp);
		
		String date = year+month+day;
		
		System.out.println("---------------------------------------------------");
		System.out.println("----------------Weather Data System----------------");
		System.out.println("---------------------------------------------------");
	
		System.out.println();
		
		while(run) {
		
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("1. 어제의 날씨 | 2. 과거 날씨 | 3. 연평균기온 | 4. 정리된 데이터 보기 | 5. 프로그램 종료");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.print(">> ");
		
		num = sc.nextInt();
		
		if(num == 1) {
			parser.get(date);
		}else if(num == 2){
			parser.getSpecificData();
		}else if(num == 3){
			System.out.println("2012 ~ 2021 년도 서울의 연평균기온이 출력 됩니다.");
			visualizer.avgVisualizer();
		}else if(num == 4){
			Connecter.connect_hadoop();
			System.out.println("해당하는 값이 없으면 9999가 출력됩니다.");
		}else if(num == 5) {
			System.out.println("프로그램 종료");
			break;
		}
		
		}
	}

}
