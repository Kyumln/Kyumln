package oracle_db;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustInfoSys_Runnable {

	public static void main(String[] args) throws ParseException {
		
		Scanner sc = new Scanner(System.in);
		UserInterface ui = new UserInterface();
		
		int num = 0;
				
		while(true) {
			try{
			System.out.println();
			System.out.println("메뉴를 선택하세요.");
			System.out.println("------------------------------------------------------------------------");
			System.out.println("1. 고객정보 검색 | 2. 고객정보 추가 | 3. 회원 삭제 | 4. 생일자 확인 | 5. 프로그램 종료");
			System.out.println("------------------------------------------------------------------------");
			System.out.print(">>>");
			num = sc.nextInt();
			if(num == 1) {
				ui.search_info();
			}else if(num == 2) {
				ui.add_customer();
			}else if(num == 3) {
				ui.remove_customer();
			}else if(num == 4) {
				ui.print_birthday();
			}else if(num == 5) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}else {
				System.out.println("메뉴에 적혀있는 적절한 숫자를 입력하세요.\n");
			}
			
			}catch(InputMismatchException e) {
				sc.reset();
				sc = new Scanner(System.in);
				System.out.println("메뉴에 적혀있는 적절한 숫자를 입력하세요.\n");
			}
			
		}

	}

}
