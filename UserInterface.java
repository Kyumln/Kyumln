package minkyu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
	MerchDatabase merchdatabase = new MerchDatabase();
	UserDatabase userdatabase = new UserDatabase();
	Scanner sc = new Scanner(System.in);
	
	
	public void ui_main(){
		merchdatabase.preset_data();
		int num = 0;
		
		while(true) {
			try	{
			System.out.println("--------------------------------------------------");
			System.out.println("--------------------POS SYSTEM--------------------");
			System.out.println("--------------------------------------------------");
		
			System.out.println();
		
			System.out.println("--------------------------------------------");
			System.out.println("1. 로그인 | 2. 회원가입 | 3. 회원삭제 | 4. 시스템 종료");
			System.out.println("--------------------------------------------");
			System.out.print(">> ");
			
			num = sc.nextInt();
		
			if(num == 1) {
				userdatabase.login();
				if(userdatabase.getCurrent_user() != -1) {
					while(true) {
						
						try	{
						
						System.out.println("-------------------------------------");
						System.out.println("1. 업무 시작 | 2. 비밀번호 변경 | 3. 로그아웃");
						System.out.println("-------------------------------------");
						System.out.print(">> ");
							
						num = sc.nextInt();
						
						if(num == 1) {
							
							System.out.println(userdatabase.getCurrent_user_name()+"님 안녕하세요.");
							userdatabase.setWork_start_time(System.currentTimeMillis());
							
							while(true) {
								try	{
								System.out.println("------------------------------------------------------------------------------------------");
								System.out.println("1. 물품 판매 | 2. 재고 파악 | 3. 새로운 상품 등록 | 4. 등록된 상품 삭제 | 5. 현재 예상 급료 확인 | 6. 업무 종료");
								System.out.println("------------------------------------------------------------------------------------------");
								System.out.print(">> ");
								
								num = sc.nextInt();
								
								if(num == 1) {
									ui_Selling();
								}else if(num == 2) {
									ui_Stocking();
								}else if(num == 3) {
									ui_InputMerch();
								}else if(num == 4) {
									merchdatabase.delete_Merch();
								}else if(num == 5) {
									ui_PredictWAGE();
								}else if(num == 6) {
									userdatabase.setWork_end_time(System.currentTimeMillis());
									
									userdatabase.worktime_cal();
									
									System.out.println("오늘 하루도 수고하셨습니다.");
									break;
								}
								
								}catch(InputMismatchException e) {
									System.out.println("잘못된 입력입니다.");
									sc.reset();
									sc.next();
								}
								
							}
						
						
						}else if(num == 2) {
							userdatabase.change_Password();
							if(userdatabase.is_Changed() == true) {
								System.out.println("비밀번호가 변경되었습니다. 다시 로그인 하세요.");
								userdatabase.logout();
								break;
							}else {
								System.out.println("비밀번호가 변경되지 않았습니다.");
							}
							
						}else if(num == 3) {
							userdatabase.logout();
							break;
						}else {
							System.out.println("잘못된 숫자를 입력하셨습니다.");
						}
					
						}catch(InputMismatchException e) {
							System.out.println("잘못된 입력입니다.");
							sc.reset();
							sc.next();
						}
					}
			}
				
			}else if(num == 2) {
				userdatabase.sign_Up();
				System.out.println("회원가입 되었습니다.");
				System.out.println();
			}else if(num == 3) {
				userdatabase.delete_User();
				if(userdatabase.is_Changed() == true) {
					System.out.println("정상적으로 계정이 삭제되었습니다.");
				}else {
					System.out.println("계정이 삭제되지 않았습니다.");
				}
			}else if(num == 4) {
				System.out.println("시스템을 종료합니다.");
				break;
			}else {
				System.out.println("잘못된 숫자를 입력하셨습니다.");
			}
			
			}catch(InputMismatchException e) {
				System.out.println("잘못된 입력입니다.");
				sc.reset();
				sc.next();
			}
			
		}
		
	}
		
	

	public void ui_InputMerch(){
		System.out.println("신상품 등록 메뉴입니다.");
		merchdatabase.new_Merch();
		
	}
	
	public void ui_Stocking() {
		int num = 0;
		
		System.out.println("재고 파악 메뉴입니다.");
		System.out.println("기능을 선택하세요.");
		System.out.println("------------------------------------------");
		System.out.println("1. 총 물품 종류 | 2. 총 물품가격 | 3. 물품 찾기 기능");
		System.out.println("------------------------------------------");
		System.out.print(">> ");
		
		num = sc.nextInt();
		
		if(num == 1) {
			merchdatabase.getMerch_total();
		}else if(num == 2) {
			merchdatabase.sum_TotalPrice();
		}else if(num == 3) {
			merchdatabase.find_Merch();
		}
		
	}
	
	public void ui_Selling() {
		
		int num = 0;
		boolean cash = false;
		boolean card = false;
		String merch_name = null;
		
		System.out.println("판매 메뉴 입니다.");
		System.out.println("결제 방법을 선택해 주세요.");
		System.out.println("---------------");
		System.out.println("1. 카드 | 2. 현금");
		System.out.println("---------------");
		System.out.print(">> ");
		
		num = sc.nextInt();
		
		if(num == 1) {
			card = true;
			cash = false;
		}else if(num == 2) {
			cash = true;
			card = false;
		}
		
		merchdatabase.sell_Merch();
		
		
	}
	
	public void ui_PredictWAGE() {
		
		System.out.println("현재 예상 급료를 계산합니다.");
		
		userdatabase.setWork_end_time(System.currentTimeMillis());
		
		userdatabase.worktime_cal();
		
	}
	
	
}
