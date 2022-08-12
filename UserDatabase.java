package minkyu;

import java.util.Scanner;

public class UserDatabase {
	Scanner sc = new Scanner(System.in);
	public static final int WAGE = 9800;
	private long work_start_time;
	private long work_end_time;
	private int current_user = -1;
	private String current_user_name = null;
	private boolean is_changed = false;
	private UserInfo[] userinfo = new UserInfo[40];
	
	public void sign_Up() {
		int num = 0;
		String id = null;
		String name = null;
		String password = null;
		
		for(int i = 1; i <= userinfo.length; ++i) {
			if(userinfo[i-1] == null) {
				num = i-1;
				break;
			}
		}
		
		System.out.println("회원가입을 시작합니다.");
		
		System.out.println("ID를 입력하세요");
		System.out.print(">> ");
		
		id = sc.next();
		
		System.out.println("비밀번호를 입력하세요");
		System.out.print(">> ");
		
		password = sc.next();
		
		System.out.println("이름을 입력하세요");
		System.out.print(">> ");
		
		name = sc.next();
		
		userinfo[num] = new UserInfo(id, password, name);
	}
	
	public void delete_User() {
		String id = null;
		String password = null;
		
		System.out.println("회원삭제를 시작합니다.");
		
		System.out.println("ID를 입력하세요");
		System.out.print(">> ");
		
		id = sc.next();
		
		System.out.println("비밀번호를 입력하세요");
		System.out.print(">> ");
		
		password = sc.next();
		
		for(int i = 1; i <= userinfo.length; ++i) {
			if(userinfo[i-1] != null && userinfo[i-1].getId().equals(id) && userinfo[i-1].getPassword().equals(password)) {
				userinfo[i-1] = null;
				System.out.println("삭제 되었습니다.");
				setIs_Changed(true);
				return;
			}
		}
		
		System.out.println("회원 정보가 다릅니다.");
		setIs_Changed(false);
		return;
		
	}
	

	public void change_Password() {
		String temp_password = null;
		String temp_name = null;

		
		System.out.println("현재 비밀번호를 입력하세요");
		System.out.print(">> ");
		
		temp_password = sc.next();
		
		if(userinfo[current_user].getPassword().equals(temp_password)) {
			
			System.out.println(userinfo[current_user].getUser_name()+"님 변경할 비밀번호를 입력하세요.");
			System.out.print(">> ");
			temp_password = sc.next();
			
			userinfo[current_user].setPassword(temp_password);
			System.out.println("변경 되었습니다.");
			
			setIs_Changed(true);
			
		}else {
			System.out.println("비밀번호가 다릅니다.");
			setIs_Changed(false);
			return;
		}
		
	}
	
	public void login() {
		String temp_id = null;
		String temp_password = null;
		
		System.out.println("ID를 입력하세요");
		System.out.print(">> ");
		
		temp_id = sc.next();
		
		System.out.println("비밀번호를 입력하세요");
		System.out.print(">> ");
		
		temp_password = sc.next();
		
		try {
		for(int i = 1; i <= userinfo.length; ++i) {
			if(userinfo[i-1] != null && userinfo[i-1].getId().equals(temp_id) && userinfo[i-1].getPassword().equals(temp_password)){
				
					setCurrent_user(i-1);
					System.out.println(userinfo[getCurrent_user()].getUser_name()+"님 로그인 되었습니다.");
					current_user_name = userinfo[getCurrent_user()].getUser_name();
					return;
			}
		}
		}catch(NullPointerException e) {}
		
		System.out.println("존재하지 않는 계정입니다.");
		
	}
	
	public void logout() {
		System.out.println(userinfo[getCurrent_user()].getUser_name()+"님 로그아웃 되었습니다.");
		
		setCurrent_user(-1);
	}
	
	public void worktime_cal() {

		long workedTime = System.currentTimeMillis();
		long hour = (work_end_time-work_start_time) / (1000 * 60 * 60);
		long minute = ((work_end_time-work_start_time) % (1000 * 60 * 60)) / (1000 * 60);
		long sec = (((work_end_time-work_start_time) % (1000 * 60 * 60)) % (1000 * 60)) / 1000;
		System.out.print(hour + "시간 ");
		System.out.print(minute + "분 ");
		System.out.print(sec + "초");
		
		System.out.println(" 근무하셨으며 오늘 일당은 " + WAGE *( (work_end_time-work_start_time) / (1000 * 60 * 60) ) + "원 입니다.");
		
	}

	public int getCurrent_user() {
		return current_user;
	}

	public void setCurrent_user(int current_user) {
		this.current_user = current_user;
	}

	public boolean is_Changed() {
		return is_changed;
	}

	public void setIs_Changed(boolean is_changed) {
		this.is_changed = is_changed;
	}

	public String getCurrent_user_name() {
		return current_user_name;
	}

	public long getWork_start_time() {
		return work_start_time;
	}

	public void setWork_start_time(long work_start_time) {
		this.work_start_time = work_start_time;
	}

	public long getWork_end_time() {
		return work_end_time;
	}

	public void setWork_end_time(long work_end_time) {
		this.work_end_time = work_end_time;
	}
	
	
	
}
