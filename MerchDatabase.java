package minkyu;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class MerchDatabase {
	Scanner sc = new Scanner(System.in);
	private int merch_total;
	private MerchInfo[] merchinfo  = new MerchInfo[100];
	
	public void preset_data() {
		merchinfo[0] = new MerchInfo("콜라", "대전", "아니오", 1, 1500, "2025-08-10", 100);
		merchinfo[1] = new MerchInfo("사이다", "대전", "아니오", 2, 1500, "2025-08-10", 40);
		merchinfo[2] = new MerchInfo("환타", "대전", "아니오", 3, 1500, "2025-08-10", 20);
		merchinfo[3] = new MerchInfo("참치통조림", "대전", "아니오", 4, 3000, "2024-08-10", 20);
		merchinfo[4] = new MerchInfo("고추참치통조림", "대전", "아니오", 5, 3500, "2024-08-10", 20);
		merchinfo[5] = new MerchInfo("빵", "대전", "아니오", 6, 2000, "2022-08-20", 5);
		merchinfo[6] = new MerchInfo("포카칩", "대전", "아니오", 7, 1500, "2024-08-10", 30);
		merchinfo[7] = new MerchInfo("스윙칩", "대전", "아니오", 8, 1500, "2024-08-10", 30);
		merchinfo[8] = new MerchInfo("신라면", "대전", "아니오", 9, 1000, "2025-08-10", 50);
		merchinfo[9] = new MerchInfo("진라면", "대전", "아니오", 10, 1000, "2025-08-10", 50);
		merchinfo[10] = new MerchInfo("팔도비빔면", "대전", "아니오", 11, 1100, "2025-08-10", 20);
		merchinfo[11] = new MerchInfo("담배", "대전", "예", 12, 4000, "2025-08-10", 30);
		merchinfo[12] = new MerchInfo("우유", "대전", "아니오", 13, 2000, "2025-08-16", 35);
		merchinfo[13] = new MerchInfo("월드콘", "대전", "아니오", 14, 2000, "2030-08-10", 15);
		merchinfo[14] = new MerchInfo("감동란", "대전", "아니오", 15, 2000, "2022-08-25", 15);
		merchinfo[15] = new MerchInfo("훈제란", "대전", "아니오", 16, 1500, "2022-09-10", 10);
		merchinfo[16] = new MerchInfo("육포", "대전", "아니오", 17, 1500, "2024-08-10", 20);
		merchinfo[17] = new MerchInfo("진로", "대전", "예", 18, 3000, "2025-08-10", 50);
		merchinfo[18] = new MerchInfo("테라", "대전", "예", 19, 2500, "2025-08-10", 50);
		merchinfo[19] = new MerchInfo("너구리", "대전", "아니오", 20, 1000, "2024-08-10", 70);
	}
	
	public void delete_Merch()	{
		
		String name = null;
		int barcode;
		
		System.out.println("삭제할 상품 정보를 입력하세요.");
		
		System.out.println("상품 이름 ");
		System.out.print(">> ");
		name = sc.next();
		
		System.out.println("상품 바코드");
		System.out.print(">> ");

		barcode = sc.nextInt();
		
		for(int i = 1; i <= merchinfo.length; ++i) {
			if(merchinfo[i-1] != null && merchinfo[i-1].getMerch_name().equals(name) && merchinfo[i-1].getMerch_barcode() == barcode) {
				System.out.println("선택하신 상품 "+merchinfo[i-1].getMerch_name()+"을(를) 제거합니다.");
				merchinfo[i-1] = null;
				break;
			}
		}
		
		
	}
	
	public void new_Merch() {
		
		String production = null;
		String expirydate = null;
		String isadult = null;
		String name = null;
		int stock = 0;
		int num = 0;
		int barcode = 0;
		int price = 0;
		
		for(int i = 1; i <= merchinfo.length; ++i) {
			if(merchinfo[i-1] == null) {
				num = i-1;
				break;
			}
		}
		
		System.out.println("상품 정보를 입력하세요.");
		System.out.println("상품 이름 ");
		System.out.print(">> ");
		name = sc.next();
		
		System.out.println("상품 바코드");
		System.out.print(">> ");

		barcode = sc.nextInt();
		
		System.out.println("상품 가격");
		System.out.print(">> ");

		price = sc.nextInt();
		
		System.out.println("상품 생산지");
		System.out.print(">> ");
		
		production = sc.next();
		
		System.out.println("상품 파기날짜");
		System.out.println("yyyy-MM-dd 형식으로 입력하세요.");
		System.out.print(">> ");

		expirydate = sc.next();
		
		System.out.println("상품 성인용 유무");
		System.out.println("성인용이면 예 성인용이 아니면 아니오 를 입력하세요.");
		System.out.print(">> ");
		
		isadult = sc.next();
		
		System.out.println("상품 개수");
		System.out.print(">> ");

		stock = sc.nextInt();
		
		merchinfo[num] = new MerchInfo(name, production, isadult, barcode, price, expirydate, stock);
		
	}
	
	public void find_Merch() {
		String name = null;
		int barcode = 0;
		boolean isfound = false;
		
		System.out.println("찾고자 하는 상품의 이름을 입력하세요.");
		System.out.print(">> ");
		
		name = sc.next();
		
		System.out.println("찾고자 하는 상품의 바코드를 입력하세요.");
		System.out.print(">> ");
		
		barcode = sc.nextInt();
		
		try {
		for(int i = 1; i <= merchinfo.length; ++i) {
			if(merchinfo[i-1].getMerch_name() != null && merchinfo[i-1].getMerch_name().equals(name) && merchinfo[i-1].getMerch_barcode() == barcode) {
				System.out.print("상품 이름: "+merchinfo[i-1].getMerch_name()+" | 상품 바코드: "+merchinfo[i-1].getMerch_barcode()+" | 상품 가격: "+merchinfo[i-1].getMerch_price()+" | 생산지: "+merchinfo[i-1].getMerch_production());
				System.out.print(" | 상품 재고: "+merchinfo[i-1].getMerch_stock()+" | 상품 파기날짜: "+merchinfo[i-1].getExpiry_date()+" | 상품 성인용 유무: ");
				if(merchinfo[i-1].isMerch_isadult() == true) {
					System.out.println("예");
				}else {
					System.out.println("아니오");
				}
				isfound = true;
			}
		}
		}catch(NullPointerException e) {
			if(isfound == false) {
				System.out.println(name+"라는 상품을 찾을 수 없습니다.");
			}
		}
		
	}
	
	public void sell_Merch() {
		int merch_array_num = 0;
		int purch_amount = 0;
		int stock = 0;
		int day = 0;
		int month = 0;
		int year = 0;
		int barcode = 0;
		String age = null;
		String name = null;
		boolean isfound = false;
		Period period;
		
		LocalDate now = LocalDate.now();
		LocalDate targetdate;
		
		System.out.println("구매 하고자 하는 상품의 이름을 입력하세요.");
		System.out.print(">> ");
		
		name = sc.next();
		
		System.out.println("구매 하고자 하는 상품의 바코드를 입력하세요.");
		System.out.print(">> ");
		
		barcode = sc.nextInt();
		
		
		try {
		for(int i = 1; i <= merchinfo.length; ++i) {
			if(merchinfo[i-1].getMerch_name() != null && merchinfo[i-1].getMerch_name().equals(name) && merchinfo[i-1].getMerch_barcode() == barcode) {
				System.out.println("선택하신 상품 "+merchinfo[i-1].getMerch_name()+"은(는) "+merchinfo[i-1].getMerch_price()+"원 이고 "
									+merchinfo[i-1].getMerch_stock()+"개의 재고가 남아 있습니다.");
				isfound = true;
				merch_array_num = i-1;
				break;
			}
		}
		}catch(NullPointerException e) {
			if(isfound == false) {
				System.out.println(name+"라는 상품을 찾을 수 없습니다.");
				return;
			}
		}
		
		if(merchinfo[merch_array_num].isMerch_isadult() == true) {
			
				System.out.println("성인용 제품입니다.");
				System.out.println("미성년자는 구매할 수 없습니다.");
				
				System.out.println("제품을 구매하실 고객님의 생년월일을 다음의 형식에 맞게 입력해 주세요.");
				System.out.println("yyyy-MM-dd");
				System.out.print(">> ");
				
				age = sc.next();
				
				targetdate = LocalDate.parse(age);
				
				period = Period.between(now, targetdate);
				
				year = period.getYears();
				
				if(-year>=19) {
					System.out.println("고객님은 만 19세가 지났기 때문에 제품을 구매하실 수 있습니다.");
				}else {
					System.out.println("고객님은 미성년자이기 때문에 제품을 구매하실 수 없습니다.");
					return;
				}
				
		}
		
		String expirydate = merchinfo[merch_array_num].getExpiry_date();
		
		targetdate = LocalDate.parse(expirydate);
		
		period = Period.between(now, targetdate);
		
		day = period.getMonths();
		month = period.getMonths();
		year = period.getYears();
		
		if(period.getDays() < 0) {
			System.out.println("해당 물품의 유통기한은 "+merchinfo[merch_array_num].getExpiry_date()+"로 유통기한이 "+-year+"년 "+-month+"개월 "+-day+"일 지나서 판매할 수 없습니다.");
			return;
		}
		
		
		while(true) {
			System.out.println("몇 개 구매하시겠습니까?");
			System.out.print(">> ");
			
			purch_amount = sc.nextInt();
			
			stock = merchinfo[merch_array_num].getMerch_stock();
			
			stock -= purch_amount;
			
			if(stock < 0) {
				System.out.println("현재 재고보다 많은 상품은 구매할 수 없습니다.");
				System.out.println("구매할 상품의 개수를 다시 입력해 주세요.");
			}else {
				break;
			}
		}
		
		System.out.println(merchinfo[merch_array_num].getMerch_name()+" "+purch_amount+"개는 "+purch_amount*merchinfo[merch_array_num].getMerch_price()+"원 입니다.");
		System.out.println("구매해주셔서 감사합니다.");
		merchinfo[merch_array_num].setMerch_stock(stock);
		
		
	}

	
	public void sum_TotalPrice() {
		int sum = 0;
		try {
		for(int i = 1; i <= merchinfo.length; ++i) {
			if(merchinfo[i-1] != null) {
				sum += merchinfo[i-1].getMerch_price() * merchinfo[i-1].getMerch_stock();
			}
		}
		}catch(NullPointerException e) {}
		
		System.out.println("총 상품의 가격은 "+sum+"원 입니다.");
		
	}

	
	public void getMerch_total() {
		merch_total = 0;
		for(int i = 1; i <= merchinfo.length; ++i) {
			if(merchinfo[i-1] != null) {
				merch_total++;
				System.out.print("상품 이름: "+merchinfo[i-1].getMerch_name()+" | 상품 바코드: "+merchinfo[i-1].getMerch_barcode()+" | 상품 가격: "+merchinfo[i-1].getMerch_price()+" | 생산지: "+merchinfo[i-1].getMerch_production());
				System.out.print(" | 상품 재고: "+merchinfo[i-1].getMerch_stock()+" | 상품 파기날짜: "+merchinfo[i-1].getExpiry_date()+" | 상품 성인용 유무: ");
				if(merchinfo[i-1].isMerch_isadult() == true) {
					System.out.println("예");
				}else {
					System.out.println("아니오");
				}
				System.out.println();
			}
		}
		
		System.out.println();
		System.out.println("총 상품의 종류는 "+merch_total+"개 입니다.");
		
		
		
	}
	
	
	
	
}
