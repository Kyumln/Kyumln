package kyumin;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final boolean run = true;
		
		Mycar car = new Mycar();
		
		Gear gear = new Gear();
		
		Speed speed = new Speed();
		
		Wiper wiper = new Wiper();
		
		SignalLight signalLight = new SignalLight();
		
		Tire_Continetal tire_continetal = new Tire_Continetal();
		Tire_Hankook tire_hankook = new Tire_Hankook();
		Tire_Kumho tire_kumho = new Tire_Kumho();
		Tire_Michelin tire_michelin = new Tire_Michelin();
		
		Window_LeftBack window_leftback = new Window_LeftBack();
		Window_LeftFront window_leftfront = new Window_LeftFront();
		Window_RightBack window_rightback = new Window_RightBack();
		Window_RightFront window_rightfront = new Window_RightFront();
		
		car.car_on();
		
		while(run) {
			
			try {
			
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			System.out.println("1. 기어변속 | 2. 현재속도 | 3. 타이어 교체 | 4. 창문 | 5. 와이퍼 선택 | 6. 깜빡이 선택 | 7. 자동차 상태 | 8. 급브레이크 | 9. 종료");
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			System.out.print("입력하세요> ");
			
			Scanner scanner = new Scanner(System.in);
			int num = scanner.nextInt();
			
			
			
			if(num == 1) {
				gear.Change_Gear();
				car.setCurrent_gear(gear.getCurrent_gear());
			}else if(num == 2) {
				if(car.getCurrent_gear() == null){
					System.out.println("오류 발생! 기어가 선택되지 않았습니다.");
					continue;
				}else {
					speed.View_Speed(car.getCurrent_gear());
					car.setCurrent_speed(speed.getCurrent_speed());	
				}
			}else if(num == 3) {
				System.out.println("교체할 타이어를 선택하세요.");
				System.out.println("------------------------------------------");
				System.out.println("1. 미쉐린 | 2. 금호 | 3. 한국타이어 | 4. 컨티넨탈");
				System.out.println("------------------------------------------");
				System.out.print("입력하세요> ");
				int temp = scanner.nextInt();
				int temp_tiernum = 0;
				if(temp == 1) {
					tire_michelin.Change_Tire();
					temp_tiernum = tire_michelin.getTire_num();
				}else if(temp == 2) {
					tire_kumho.Change_Tire();
					temp_tiernum = tire_kumho.getTire_num();
				}else if(temp == 3) {
					tire_hankook.Change_Tire();
					temp_tiernum = tire_hankook.getTire_num();
				}else if(temp == 4) {
					tire_continetal.Change_Tire();
					temp_tiernum = tire_continetal.getTire_num();
				}else {
					System.out.println("오류발생");
				}
				
				System.out.println("교체할 타이어 위치를 선택하세요.");
				System.out.println("------------------------------------------");
				System.out.println("1. 왼쪽앞 | 2. 왼쪽뒤 | 3. 오른쪽앞 | 4. 오른쪽뒤");
				System.out.println("------------------------------------------");
				System.out.print("입력하세요> ");
				temp = scanner.nextInt();

				if(temp == 1) {
					car.setTire_leftfront(temp_tiernum);
				}else if(temp == 2) {
					car.setTire_leftback(temp_tiernum);
				}else if(temp == 3) {
					car.setTire_rightfront(temp_tiernum);
				}else if(temp == 4) {
					car.setTire_rightback(temp_tiernum);
				}else {
					System.out.println("오류발생");
				}
				
			}else if(num == 4) {
				System.out.println("---------------------");
				System.out.println("1. 창문열기 | 2. 창문닫기");
				System.out.println("---------------------");
				System.out.print("입력하세요> ");
				int temp = scanner.nextInt();
				
				System.out.println("열고싶은 창문의 위치를 선택하세요.");
				System.out.println("------------------------------------------------");
				System.out.println("1. 운전석 | 2. 조수석 | 3. 뒷창문 오른쪽 | 4. 뒷창문 왼쪽");
				System.out.println("------------------------------------------------");
				System.out.print("입력하세요> ");
				int temp1 = scanner.nextInt();
				
				if(temp1 == 1) {
					if(temp == 1) {
						window_leftfront.open();
					}else if(temp == 2) {
						window_leftfront.close();
					}else {
						System.out.println("오류발생");
					}
				}else if(temp1 == 2) {
					if(temp == 1) {
						window_rightfront.open();
					}else if(temp == 2) {
						window_rightfront.close();
					}else {
						System.out.println("오류발생");
					}
				}else if(temp1 == 3) {
					if(temp == 1) {
						window_rightback.open();
					}else if(temp == 2){
						window_rightback.close();
					}else {
						System.out.println("오류발생");
					}
				}else if(temp1 == 4) {
					if(temp == 1) {
						window_leftback.open();
					}else if(temp == 2) {
						window_leftback.close();
					}else {
						System.out.println("오류발생");
					}
				}
				
			}else if(num == 5) {
				System.out.println("와이퍼 조작을 선택하세요.");
				System.out.println("---------------");
				System.out.println("1. 켜기 | 2. 끄기");
				System.out.println("---------------");
				System.out.print("입력하세요> ");
				int temp = scanner.nextInt();
				
				if(temp == 1) {
					wiper.on();
				}else if(temp == 2) {
					wiper.off();
				}else {
					System.out.println("오류발생");
				}
			
			}else if(num == 6) {
				System.out.println("비상등 조작을 선택하세요.");
				System.out.println("----------------------------------------------------------");
				System.out.println("1. 왼쪽켜기/끄기 | 2. 오른쪽켜기/끄기 | 3. 비상등 켜기 | 4. 비상등 끄기");
				System.out.println("----------------------------------------------------------");
				System.out.print("입력하세요> ");
				int temp = scanner.nextInt();

				if(temp == 1) {
					if(signalLight.isLeft_signallight()==true) {
						signalLight.LeftLight_OFF();
					}else {
						signalLight.LeftLight_On();
					}
				}else if(temp == 2) {
					if(signalLight.isRight_signallight()==true) {
						signalLight.RightLight_OFF();
					}else {
						signalLight.RightLight_On();
					}
				}else if(temp == 3) {
					signalLight.EmergencyLight_Push();
				}else if(temp == 4) {
					signalLight.LeftLight_OFF();
					signalLight.RightLight_OFF();
				}else {
					System.out.println("오류발생");
				}
				
			}else if(num == 7) {
				
				speed.View_Speed(car.getCurrent_gear());
				
				System.out.println();
				
				car.car_tirestate();
				
				System.out.println();
				System.out.println();
				
				window_leftfront.check_state();
				window_rightfront.check_state();
				window_leftback.check_state();
				window_rightback.check_state();
				
				System.out.println();

				if(car.getCurrent_gear() == null) {
					System.out.println("오류 발생! 기어가 선택되지 않았습니다.");
				}else {
					System.out.println("현재 기어는 "+car.getCurrent_gear()+"단 입니다.");
				}
				
				System.out.println();
				
				wiper.print_state();
				
				System.out.println();

				signalLight.Light_State_check();
				
			}else if(num == 8) {
				System.out.println("브레이크 작동");
				car.setCurrent_gear(null);
				car.setCurrent_speed(0);
			}else if(num == 9) {
				car.car_off();
				break;
			}else {
				System.out.println("잘못된 숫자를 입력했습니다.");
			}
			
			}catch(InputMismatchException e){
				System.out.println("잘못된 입력을 받았습니다.");
			}
		}
		

		
	}

}
