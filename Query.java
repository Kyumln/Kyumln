package oracle_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Query {
	private Connection connection;
	private String orcale_url;
	private String user_id;
	private String user_password;
	
	public Query() {
		this.orcale_url = "jdbc:oracle:thin:@localhost:1521:xe";
		this.user_id = "CUSTINFO";
		this.user_password = "CUSTINFO";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("JDBC driver is not found!");
		}
		System.out.println("DB connection is ready!");
		try {
			this.connection = DriverManager.getConnection(this.orcale_url,this.user_id,this.user_password);
			System.out.println("DB connection is successful!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB connection is failed!");
			e.printStackTrace();
		}
	}
	
	public void find_data(int num, String data) {
		String query = "SELECT * FROM CUSTOMERINFORMATION";
		int cnt = 0;
		try {
			Statement statement = this.connection.createStatement();
			ResultSet result_set = statement.executeQuery(query);
			if(num == 1) {
				while(result_set.next()) {
					if(data.equals(result_set.getString(2))) {
						++cnt;
						print_custinfo(result_set);
						result_set.close();
						statement.close();
						this.connection.close();
						break;
					}
				}
				if(cnt == 0){
					System.out.println("없는 고객입니다!");
				}
			}else if(num == 2) {
				
				while(result_set.next()) {
					SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
					String strdate = transFormat.format(result_set.getDate(4));
					if(data.equals(strdate)) {
						++cnt;
						print_custinfo(result_set);
						
						result_set.close();
						statement.close();
						this.connection.close();
						break;
					}
				}
				
				if(cnt == 0){
					System.out.println("없는 고객입니다!");
				}
				
			}else if(num == 3) {
				while(result_set.next()) {
					if(data.equals(result_set.getString(5))) {
						++cnt;
						print_custinfo(result_set);
						
						result_set.close();
						statement.close();
						this.connection.close();
						break;
					}
				}
				
				if(cnt == 0){
					System.out.println("없는 고객입니다!");
				}
				
			}else if(num == 4) {
				while(result_set.next()) {
					if(data.equals(result_set.getString(7))) {
						++cnt;
						print_custinfo(result_set);
						
						result_set.close();
						statement.close();
						this.connection.close();
						break;
					} 
				}
				
				if(cnt == 0){
					System.out.println("없는 고객입니다!");
				}
				
			}else if(num == 5) {
				while(result_set.next()) {
					if(data.equals(result_set.getString(8))) {
						++cnt;
						print_custinfo(result_set);
						
						result_set.close();
						statement.close();
						this.connection.close();
						break;
					} 
				}
				
				if(cnt == 0){
					System.out.println("없는 고객입니다!");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void insert_data(int cust_id, String name, String gender, java.sql.Date birthday, String phone, String address, String email, String nickname) {
		String query = "INSERT INTO CUSTOMERINFORMATION(CUSTID, NAME, GENDER, BIRTHDAY, PHONE, ADDRESS, EMAIL, NICKNAME) VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement pstm = null;
		try {
			this.connection = DriverManager.getConnection(this.orcale_url,this.user_id, this.user_password);
			System.out.println("DB connection is successes!");
			pstm = this.connection.prepareStatement(query);

			pstm.setInt(1, cust_id);
			pstm.setString(2, name);
			pstm.setString(3, gender);
			pstm.setDate(4, birthday);
			pstm.setString(5, phone);
			pstm.setString(6, address);
			pstm.setString(7, email);
			pstm.setString(8, nickname);
			pstm.executeUpdate();
			System.out.println("Data insert is success!");
			pstm.close();
			this.connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("CUSTID 는 PRIMARY KEY이기 때문에 중복이 안됩니다.");
			e.printStackTrace();
		}
		
	}
	
	public void delete_data(int cust_id) {
		String query = "DELETE FROM CUSTOMERINFORMATION WHERE CUSTID = ?";
		PreparedStatement pstm = null;
		try {
			this.connection = DriverManager.getConnection(this.orcale_url,this.user_id, this.user_password);
			pstm = connection.prepareStatement(query);
			pstm.setInt(1, cust_id);

			int res = pstm.executeUpdate();
			if(res > 0) {
				System.out.println("삭제 성공");
			}else{
				System.out.println("삭제 실패");
			}
			
			pstm.close();
			this.connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void check_birthday() {
		String query = "SELECT * FROM CUSTOMERINFORMATION";
		int cnt = 0;
		
		try {
			Statement statement = this.connection.createStatement();
			ResultSet result_set = statement.executeQuery(query);
			while(result_set.next()) {
				
				LocalDate localdate = LocalDate.now();
				
				DateTimeFormatter transFormat_localdate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String currentdate = transFormat_localdate.format(localdate);

				Date date = result_set.getDate(4);
				SimpleDateFormat transFormat_date = new SimpleDateFormat("yyyy-MM-dd");
				String birthday = transFormat_date.format(date);
				
				if(currentdate.equals(birthday)) {
					System.out.println("\r\n");
					System.out.println("------------------------");
					System.out.println(result_set.getString(2)+"님 생일 축하드립니다 !!");
					System.out.println("------------------------");
					System.out.println("\r\n");
					++cnt;
				}
			}
			
			if(cnt == 0) {
				System.out.println("\r\n");
				System.out.println("------------------");
				System.out.println("생일인 고객이 없습니다!");
				System.out.println("------------------");
				System.out.println("\r\n");
			}
			
			result_set.close();
			statement.close();
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Query Error occur!");
			e.printStackTrace();
		}

	}
	
	public boolean check_custid_isalreadyexist(int custid) {
		String query = "SELECT * FROM CUSTOMERINFORMATION";
		try {
			Statement statement = this.connection.createStatement();
			ResultSet result_set = statement.executeQuery(query);
			while(result_set.next()) {
				
				if(custid == result_set.getInt(1)) {
					System.out.println("중복되는 회원번호 입니다. 다시 입력해 주세요");
					result_set.close();
					statement.close();
					return false;
				}
			}
			result_set.close();
			statement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Query Error occur!");
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean check_phone_isalreadyexist(String phone) {
		String query = "SELECT * FROM CUSTOMERINFORMATION";
		try {
			Statement statement = this.connection.createStatement();
			ResultSet result_set = statement.executeQuery(query);
			while(result_set.next()) {
				
				if(phone.equals(result_set.getString(5))) {
					System.out.println("중복되는 전화번호 입니다. 다시 입력해 주세요");
					result_set.close();
					statement.close();
					return false;
				}
			}
			result_set.close();
			statement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Query Error occur!");
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean check_email_isalreadyexist(String email) {
		String query = "SELECT * FROM CUSTOMERINFORMATION";
		try {
			Statement statement = this.connection.createStatement();
			ResultSet result_set = statement.executeQuery(query);
			while(result_set.next()) {
				
				if(email.equals(result_set.getString(7))) {
					System.out.println("중복되는 이메일 입니다. 다시 입력해 주세요");
					result_set.close();
					statement.close();
					return false;
				}
			}
			result_set.close();
			statement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Query Error occur!");
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean check_nickname_isalreadyexist(String nickname) {
		String query = "SELECT * FROM CUSTOMERINFORMATION";
		
		try {
			Statement statement = this.connection.createStatement();
			ResultSet result_set = statement.executeQuery(query);
			while(result_set.next()) {
				
				if(nickname.equals(result_set.getString(8))) {
					System.out.println("중복되는 닉네임 입니다. 다시 입력해 주세요");
					result_set.close();
					statement.close();
					return false;
				}
			}
			result_set.close();
			statement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Query Error occur!");
			e.printStackTrace();
		}
		
		return true;
	}
	
	private void print_custinfo(ResultSet result_set) throws SQLException {
		System.out.println("\r\n");
		System.out.println("--------------------------------------------------");
		System.out.println("----------------------고객정보----------------------");
		System.out.println("--------------------------------------------------");
		System.out.printf("%s %d\n","고객번호:",result_set.getInt(1));
		System.out.printf("%s %s\n","이름:", result_set.getString(2));
		System.out.printf("%s %s\n","성별:", result_set.getString(3));
		System.out.printf("%s %s\n","생년월일:", result_set.getDate(4));
		System.out.printf("%s %s\n","핸드폰번호:", result_set.getString(5));
		System.out.printf("%s %s\n","주소:", result_set.getString(6));
		System.out.printf("%s %s\n","이메일:", result_set.getString(7));
		System.out.printf("%s %s\n","닉네임:", result_set.getString(8));
		System.out.println("--------------------------------------------------");
		System.out.println("\r\n");
	}
	
}
