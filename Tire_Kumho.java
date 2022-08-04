package kyumin;

public class Tire_Kumho implements Tire {
	private final int tire_num = 2;
	
	@Override
	public void Change_Tire() {
		// TODO Auto-generated method stub
		System.out.println("금호 타이어로 교체합니다.");
	}

	public int getTire_num() {
		return tire_num;
	}
}
