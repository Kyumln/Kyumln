package kyumin;

public class SignalLight {
	public final static boolean ON = true;
	public final static boolean OFF = false;

	private boolean left_signallight = false;
	private boolean right_signallight = false;
	
	public void LeftLight_On() {
		setLeft_signallight(ON);
	}
	
	public void LeftLight_OFF() {
		setLeft_signallight(OFF);
	}
	
	public void RightLight_On() {
		setRight_signallight(ON);
	}
	
	public void RightLight_OFF() {
		setRight_signallight(OFF);
	}
	
	public void EmergencyLight_Push() {
		LeftLight_OFF();
		RightLight_OFF();
		
		RightLight_On();
		LeftLight_On();
	}
	
	public void Light_State_check() {
		if(isLeft_signallight() == true && isRight_signallight() == true ) {
			System.out.println("비상 점멸등이 켜져 있습니다.");
		}else if(isLeft_signallight() == true && isRight_signallight() == false){
			System.out.println("좌측 점멸등이 켜져 있습니다.");
		}else if(isLeft_signallight() == false && isRight_signallight() == true){
			System.out.println("우측 점멸등이 켜져 있습니다.");
		}else if(isLeft_signallight() == false && isRight_signallight() == false){
			System.out.println("점멸등이 모두 꺼져 있습니다.");
		}
	}
	

	public boolean isLeft_signallight() {
		return left_signallight;
	}

	public void setLeft_signallight(boolean left_signallight) {
		this.left_signallight = left_signallight;
	}

	public boolean isRight_signallight() {
		return right_signallight;
	}

	public void setRight_signallight(boolean right_signallight) {
		this.right_signallight = right_signallight;
	}
	
	
}
