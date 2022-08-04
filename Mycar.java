package kyumin;

public class Mycar implements Car {
	private String current_gear = null;
	private int current_speed = 0;
	private int tire_leftfront = 1;
	private int tire_rightfront = 1;
	private int tire_leftback = 1;
	private int tire_rightback = 1;
	public long beforeTime = 0;

	@Override
	public void car_on() {
		// TODO Auto-generated method stub
		System.out.println("자동차가출발합니다. 안전운전하세요.");
		
		beforeTime = System.currentTimeMillis();
	}

	@Override
	public void car_off() {
		// TODO Auto-generated method stub
		    
		long afterTime = System.currentTimeMillis(); 
		long secDiffTime = (afterTime - beforeTime)/1000;
		System.out.println("주행한 시간은 "+secDiffTime+"초입니다.");
		System.out.println("안녕히계세요.");

	}
	
	public void car_tirestate() {
		
		if(getTire_leftfront() == 1) {
			System.out.print("왼쪽 앞 타이어는 미쉐린 타이어 입니다. ");
		}else if(getTire_leftfront() == 2) {
			System.out.print("왼쪽 앞 타이어는 금호 타이어 입니다. ");
		}else if(getTire_leftfront() == 3) {
			System.out.print("왼쪽 앞 타이어는 한국 타이어 입니다. ");
		}else if(getTire_leftfront() == 4) {
			System.out.print("왼쪽 앞 타이어는 컨티넨탈 타이어 입니다. ");
		}else {
			System.out.println("오류발생");
		}
		
		if(getTire_rightfront() == 1) {
			System.out.print("오른쪽 앞 타이어는 미쉐린 타이어 입니다. ");
		}else if(getTire_rightfront() == 2) {
			System.out.print("오른쪽 앞 타이어는 금호 타이어 입니다. ");
		}else if(getTire_rightfront() == 3) {
			System.out.print("오른쪽 앞 타이어는 한국 타이어 입니다. ");
		}else if(getTire_rightfront() == 4) {
			System.out.print("오른쪽 앞 타이어는 컨티넨탈 타이어 입니다. ");
		}else {
			System.out.println("오류발생");
		}
		
		System.out.println();
		
		if(getTire_leftback() == 1) {
			System.out.print("왼쪽 뒤 타이어는 미쉐린 타이어 입니다. ");
		}else if(getTire_leftback() == 2) {
			System.out.print("왼쪽 뒤 타이어는 금호 타이어 입니다. ");
		}else if(getTire_leftback() == 3) {
			System.out.print("왼쪽 뒤 타이어는 한국 타이어 입니다. ");
		}else if(getTire_leftback() == 4) {
			System.out.print("왼쪽 뒤 타이어는 컨티넨탈 타이어 입니다. ");
		}else {
			System.out.println("오류발생");
		}
		
		if(getTire_rightback() == 1) {
			System.out.print("오른쪽 뒤 타이어는 미쉐린 타이어 입니다. ");
		}else if(getTire_rightback() == 2) {
			System.out.print("오른쪽 뒤 타이어는 금호 타이어 입니다. ");
		}else if(getTire_rightback() == 3) {
			System.out.print("오른쪽 뒤 타이어는 한국 타이어 입니다. ");
		}else if(getTire_rightback() == 4) {
			System.out.print("오른쪽 뒤 타이어는 컨티넨탈 타이어 입니다. ");
		}else {
			System.out.println("오류발생");
		}
	}

	@Override
	public void car_break() {
		// TODO Auto-generated method stub
		Gear gear = new Gear();
		gear.setCurrent_gear(null);
	}

	public String getCurrent_gear() {
		return current_gear;
	}

	public void setCurrent_gear(String current_gear) {
		this.current_gear = current_gear;
	}

	public int getCurrent_speed() {
		return current_speed;
	}

	public void setCurrent_speed(int current_speed) {
		this.current_speed = current_speed;
	}

	public int getTire_leftfront() {
		return tire_leftfront;
	}

	public void setTire_leftfront(int tire_leftfront) {
		this.tire_leftfront = tire_leftfront;
		System.out.println("왼쪽앞 타이어 교체됨");
	}

	public int getTire_rightfront() {
		return tire_rightfront;
	}

	public void setTire_rightfront(int tire_rightfront) {
		this.tire_rightfront = tire_rightfront;
		System.out.println("오른쪽앞 타이어 교체됨");
	}

	public int getTire_leftback() {
		return tire_leftback;
	}

	public void setTire_leftback(int tire_leftback) {
		this.tire_leftback = tire_leftback;
		System.out.println("왼쪽 뒤 타이어 교체됨");
	}

	public int getTire_rightback() {
		return tire_rightback;
	}

	public void setTire_rightback(int tire_rightback) {
		this.tire_rightback = tire_rightback;
		System.out.println("오른쪽 뒤 타이어 교체됨");
	}
}
