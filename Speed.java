package kyumin;

public class Speed {
	private int current_speed;
	private int gear_state;
	
	public Speed() {
		setCurrent_speed(0);
		gear_state = 0;
	}
	
	public void View_Speed(String gear) {
		
		if(gear == null) {
			return;
		}
		
		if(gear.equals("R")) {
			System.out.println("현재 자동차는 후진 중입니다.");
			return;
		}else if(gear != "R" && Integer.parseInt(gear) > 0 && Integer.parseInt(gear) < 7) {
			gear_state = Integer.parseInt(gear);
			for(int i = 0; i < 5; ++i) {
				if(gear_state == i+1) {
					System.out.println("자동차의 현재 속도는 "+(i+1)*10+"Km 입니다.");
				}
			}
		
			if(gear_state == 6) {
				System.out.println("자동차의 현재 속도는 100Km 이상 입니다.");
			}
		}else {
			System.out.println("오류발생 잘못된 기어를 입력했습니다.");
		}
		
		
		
	}

	public int getCurrent_speed() {
		return current_speed;
	}

	public void setCurrent_speed(int current_speed) {
		this.current_speed = current_speed;
	}
	
}
