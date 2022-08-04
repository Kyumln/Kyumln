package kyumin;

public class Tire_Continetal implements Tire {
	private final int tire_num = 4;

	@Override
	public void Change_Tire() {
		// TODO Auto-generated method stub
		System.out.println("컨티넨탈 타이어로 교체합니다.");
	}

	public int getTire_num() {
		return tire_num;
	}
}
