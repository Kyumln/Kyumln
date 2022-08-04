package kyumin;

import java.util.Scanner;

public class Gear {
	private String current_gear;
	
	public Gear() {
		current_gear = null;
	}
	
	public void Change_Gear() {
		System.out.println("기어를 몇 단으로 바꾸시겠습니까?");
		System.out.print("입력> ");
		
		Scanner scanner = new Scanner(System.in);
		String input_gear = scanner.next();
		setCurrent_gear(input_gear);
		
		if(input_gear.equals("R")) {
			this.current_gear = input_gear;
			System.out.println("기어 변경됨");
			return;
		}else if(input_gear != "R" && (Integer.parseInt(input_gear) > 0 && Integer.parseInt(input_gear) < 7)) {
			this.current_gear = String.valueOf(input_gear);
			System.out.println("기어 변경됨");
			return;
		}else {
			System.out.println("오류발생 잘못된 기어를 입력했습니다.");
			return;
		}
	}

	public String getCurrent_gear() {
		return current_gear;
	}

	public void setCurrent_gear(String current_gear) {
		this.current_gear = current_gear;
	}
	
}
