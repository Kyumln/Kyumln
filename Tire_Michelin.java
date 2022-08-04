package kyumin;

public class Tire_Michelin implements Tire {
	private final int tire_num = 1;
	
	@Override
	public void Change_Tire() {
		// TODO Auto-generated method stub
		System.out.println("미쉐린 타이어로 교체합니다.");
	}

	public int getTire_num() {
		return tire_num;
	}

}
