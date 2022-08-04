package kyumin;

public class Wiper {
	private boolean wiper_state = false;
	
	public void on() {
		wiper_state = true;
		System.out.println("와이퍼를 켰습니다.");
	}
	public void off() {
		wiper_state = false;
		System.out.println("와이퍼를 껏습니다.");
	}
	
	public boolean isWiper_state() {
		return wiper_state;
	}
	
	public void print_state() {
		if(wiper_state == true) {
			System.out.println("와이퍼가 켜져있습니다.");
		}else {
			System.out.println("와이퍼가 꺼져있습니다.");
		}
	}
	
}
