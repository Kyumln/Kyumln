package kyumin;

public class Window_RightBack implements Window {
	private boolean window_state = ITS_CLOSE;

	
	@Override
	public void open() {
		// TODO Auto-generated method stub
		setWindow_state(ITS_OPEN);
		System.out.println("우측후방 창문을 열었습니다.");
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		setWindow_state(ITS_CLOSE);
		System.out.println("우측후방 창문을 닫았습니다.");
	}

	@Override
	public void check_state() {
		// TODO Auto-generated method stub
		if(isWindow_state() == true) {
			System.out.println("현재 우측후방 창문은 열려 있습니다.");
		}else {
			System.out.println("현재 우측후방 창문은 닫혀 있습니다.");
		}
	}

	public boolean isWindow_state() {
		return window_state;
	}

	public boolean setWindow_state(boolean window_state) {
		this.window_state = window_state;
		return window_state;
	}

}
