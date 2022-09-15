package oracle_db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UserInterface {
	
	void search_info(){
		Query query = new Query();
		Scanner sc = new Scanner(System.in);
		int num = 0;
		String temp = null;
		Date tempdate = null;
		
		System.out.println("----------------------------------------------------");
		System.out.println("회원 정보 검색 기능입니다.");
		System.out.println("----------------------------------------------------");
		System.out.println("찾고자 하는 회원의 정보를 선택하여 입력하세요.");
		System.out.println("----------------------------------------------------");
		System.out.println("1. 이름 | 2. 생년월일 | 3. 핸드폰번호 | 4. 이메일 | 5. 닉네임");
		System.out.println("----------------------------------------------------");
		System.out.print(">>>");
		num = sc.nextInt();
		if(num == 1) {
			System.out.println("-----------------------------");
			System.out.println("찾고자 하는 회원의 이름을 입력하세요.");
			System.out.println("-----------------------------");
			System.out.print(">>>");
			temp = sc.next();
			query.find_data(num, temp);
		}else if(num == 2) {
			System.out.println("-----------------------------------------------");
			System.out.println("찾고자 하는 회원의 생년월일을 입력하세요. EX) 2020-01-01");
			System.out.println("-----------------------------------------------");
			System.out.print(">>>");
			temp = sc.next();
			query.find_data(num, temp);
		}else if(num == 3) {
			System.out.println("---------------------------------------------------");
			System.out.println("찾고자 하는 회원의 핸드폰번호를 입력하세요. EX) 123-4567-8901");
			System.out.println("---------------------------------------------------");
			System.out.print(">>>");
			temp = sc.next();
			query.find_data(num, temp);
		}else if(num == 4) {
			System.out.println("------------------------------");
			System.out.println("찾고자 하는 회원의 이메일을 입력하세요.");
			System.out.println("------------------------------");
			System.out.print(">>>");
			temp = sc.next();
			query.find_data(num, temp);
		}else if(num == 5) {
			System.out.println("------------------------------");
			System.out.println("찾고자 하는 회원의 닉네임을 입력하세요.");
			System.out.println("------------------------------");
			System.out.print(">>>");
			temp = sc.next();
			query.find_data(num, temp);
		}
		
	}
	
	void add_customer() throws ParseException {
		Query query = new Query();
		Scanner sc = new Scanner(System.in);
		
		int cust_num = 0;
		String name = null;
		String gender = null;
		String birthday = null;
		String address = null;
		String phone = null;
		String email = null;
		String nickname = null;
		
		System.out.println("--------------------------------");
		System.out.println("회원 추가 기능입니다.");
		System.out.println("--------------------------------");
		
		System.out.println("추가하고자 하는 회원의 정보를 입력하세요.");
		
		while(true) {
		System.out.println("--------------------------------");
		System.out.println("회원번호를 입력하세요.");
		System.out.println("--------------------------------");
		System.out.print(">>>");
		cust_num = sc.nextInt();
			if(query.check_custid_isalreadyexist(cust_num) == true) {
				break;
			}
		}
		
		System.out.println("--------------");
		System.out.println("이름을 입력하세요.");
		System.out.println("--------------");
		System.out.print(">>>");
		name = sc.next();
		
		System.out.println("---------------------------");
		System.out.println("성별을 입력하세요. EX) 남자, 여자");
		System.out.println("---------------------------");
		System.out.print(">>>");
		gender = sc.next();
		
		System.out.println("--------------------------------");
		System.out.println("생년월일을 입력하세요. EX) 1999-01-01");
		System.out.println("--------------------------------");
		System.out.print(">>>");
		birthday = sc.next();
        java.sql.Date date = java.sql.Date.valueOf(birthday);
		
        sc.nextLine();
        
		System.out.println("-----------------------------");
		System.out.println("주소를 입력하세요. EX) 대한민국 서울");
		System.out.println("-----------------------------");
		System.out.print(">>>");
		address = sc.nextLine();
		
		while(true) {
		System.out.println("-------------------------------------");
		System.out.println("핸드폰 번호를 입력하세요. EX) 123-4567-8901");
		System.out.println("-------------------------------------");
		System.out.print(">>>");
		phone = sc.next();
			if(query.check_phone_isalreadyexist(phone) == true) {
				break;
			}
		}
		
		while(true) {
		System.out.println("---------------");
		System.out.println("이메일을 입력하세요.");
		System.out.println("---------------");
		System.out.print(">>>");
		email = sc.next();
			if(query.check_email_isalreadyexist(email) == true) {
				break;
			}
		}
		
		while(true) {
			System.out.println("---------------");
			System.out.println("닉네임을 입력하세요.");
			System.out.println("---------------");
			System.out.print(">>>");
			nickname = sc.next();
			
			if(query.check_nickname_isalreadyexist(nickname) == true) {
				break;
			}
		}
		
		query.insert_data(cust_num, name, gender, date, phone, address, email, nickname);
	}
	
	public void remove_customer() {
		Query query = new Query();
		Scanner sc = new Scanner(System.in);
		System.out.println("----------------");
		System.out.println("회원 삭제 기능입니다.");
		System.out.println("----------------");
		
		System.out.println("삭제하고자 하는 회원의 회원번호를 입력하세요.");
		System.out.print(">>>");
		
		int num = sc.nextInt();
		query.delete_data(num);
	}
	
	public void print_birthday() {
		Query query = new Query();
		query.check_birthday();
	}
	
}
