package myDbApp.test;

import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleEx01 {

	public static void main(String[] args) {
		// jdbc 드라이버가 잘 연결됐는지 확인하기 위한 테스트 코드
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		try {
			Class.forName(driver);
			System.out.println("jdbc driver 로딩 성공");
			DriverManager.getConnection(url, user, password);
			System.out.println("오라클 연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		}
	}
}
