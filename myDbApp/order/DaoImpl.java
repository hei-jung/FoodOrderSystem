package myDbApp.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import myDbApp.dbconn.DBConn;
import myDbApp.kitchen.Food;
import myDbApp.post.Member;

public class DaoImpl implements Dao {

	private DBConn db;
	private int total_price;// 결제금액.메뉴번호로 메뉴DB에 접근해서 계산

	public DaoImpl() {
		db = DBConn.getInstance();
	}

	@Override
	public ArrayList<Food> selectAllMenu() {
		// 메뉴 조회(Foods DB)
		String sql = "select * from Foods order by num";
		ArrayList<Food> list = new ArrayList<Food>();
		ResultSet rs = null;
		Connection conn = db.getConnect();// db 연결
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();// db에서 읽어오기 실행
			while (rs.next()) {
				int num = rs.getInt(1);
				String name = rs.getString(2);
				int price = rs.getInt(3);
				Food f = new Food(num, name, price);
				list.add(f);// db에서 읽어온 데이터를 list에 추가
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public int selectPrice(Order o) {
		// 가격 불러오기(Foods DB)
		String sql = "select price from Foods where num=?";
		int price = 0;
		ResultSet rs = null;
		Connection conn = db.getConnect();// db 연결
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, o.getMenuNum());// ? 맵핑
			rs = pstmt.executeQuery();// db에서 읽어오기 실행
			if (rs.next()) {
				price = rs.getInt(1);// db price값 가져오기
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return price;
	}

	@Override
	public void insert(String id, Order o) {
		// 주문장바구니(users_log DB)
		String sql = "insert into users_log(ordernum, id, foodnum, qty, amt) values(seq_order.nextval,?,?,?,?)";
		// 주문번호(자동:seq_order.nextval),메뉴번호(주문자입력),수량(주문자입력),결제금액(식당)
		Connection conn = db.getConnect();// db 연결
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);// ? 맵핑
			pstmt.setString(1, id);
			pstmt.setInt(2, o.getMenuNum());
			pstmt.setInt(3, o.getQty());
			pstmt.setInt(4, o.getPrice());
			pstmt.executeUpdate();// db에 쓰기 실행
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<Order> selectAllOrder(String id) {
		// 내 주문목록 확인(users_log DB)
		String sql = "select ordernum,id,foodnum,qty,amt,paid,served,to_char(sysdate,'yy/mm/dd') from users_log where id=?";
		// 주문번호,id,메뉴번호,수량,결제금액,결제확인,받았는지,결제날짜
		ArrayList<Order> list = new ArrayList<Order>();
		ResultSet rs = null;
		Connection conn = db.getConnect();// db 연결
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);// ? 맵핑
			rs = pstmt.executeQuery();// db에서 읽어오기 실행
			while (rs.next()) {
				int orderNum = rs.getInt(1);
				int menuNum = rs.getInt(3);
				int qty = rs.getInt(4);
				int price = rs.getInt(5);
				String paid = rs.getString(6);
				String served = rs.getString(7);
				String pdate = rs.getString(8);
				Order o = new Order(orderNum, menuNum, qty, price, paid, served, pdate);
				list.add(o);// db에서 읽어온 데이터를 list에 추가
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public int calTotalPrice(String id) {
		// 총 결제금액 계산(users_log DB)
		// 계산을 자바에서 안 하고, DB에서 그룹함수를 이용해서 계산하고 여기로 가져오기
		// 가져와서 출력(즉, 프로그램 사용자(소비자)에게 결제금액을 보여주도록)
		String sql = "select sum(amt) from users_log where id=? and paid='N'";
		ResultSet rs = null;
		Connection conn = db.getConnect();// db 연결
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);// ? 맵핑
			rs = pstmt.executeQuery();// db에서 읽어오기 실행
			if (rs.next()) {
				total_price = rs.getInt(1);// total_price에 db에서 읽은 값 저장
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (total_price > 0) {
			return total_price;
		} else {
			return -1;
		}
	}

	@Override
	public void pay(String id, Scanner sc) {
		// 결제: 포인트 차감 또는 학생 할인(30%) --- 회원(users)db에서 가져와야함
		total_price = calTotalPrice(id);// 총 결제금액
		String sql = "select type, points from users where id=?";
		String type = "";
		int point = 0;
		ResultSet rs = null;
		Connection conn = db.getConnect();// db 연결
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);// ? 맵핑
			rs = pstmt.executeQuery();// db에서 읽어오기 실행
			while (rs.next()) {
				type = rs.getString(1);// 회원타입을 받아옴
				point = rs.getInt(2);// 회원포인트를 받아옴
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (type.equals("학생")) {
			total_price *= 0.7; // 30% 할인 적용
			System.out.println(total_price + "원 결제되었습니다.");
		} else if (type.equals("일반")) {
			System.out.print("포인트를 사용하시려면 0을 누르세요.\n>>");
			int num = sc.nextInt();
			if ((num == 0) && (point >= 100)) {
				total_price -= point;
				System.out.println(total_price + "원 결제되었습니다.");
				savePoint(id, -point);
			} else {
				if ((num == 0) && (point < 100)) {
					System.out.println("포인트가 부족합니다.");
				}
				point = (int) (total_price / 10);// 포인트는 결제금액의 10%
				savePoint(id, point);// 포인트 사용 안 하고 적립만
				System.out.println(point + "점 적립하였습니다.");
				System.out.println(total_price + "원 결제되었습니다.");
			}
		}
	}

	@Override
	public void setPaid(String id) {
		// 결제 처리(users_log DB)
		String sql = "update users_log set paid='Y', pay_date=sysdate where id=?";
		Connection conn = db.getConnect();// db 연결
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql); // ? 맵핑
			pstmt.setString(1, id);
			pstmt.executeUpdate();// db에 쓰기 실행
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void savePoint(String id, int point) {
		// 포인트 적립(users DB)
		String sql = "select points from users where id=?";
		int userPoint = 0;
		ResultSet rs = null;
		Connection conn = db.getConnect();// db 연결
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);// ? 맵핑
			rs = pstmt.executeQuery();// db에서 읽어오기 실행
			if (rs.next()) {
				userPoint = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sql = "update users set points=? where id=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);// ? 맵핑
			pstmt.setInt(1, userPoint + point);// 포인트 업데이트
			pstmt.setString(2, id);
			pstmt.executeUpdate();// db에 쓰기 실행
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public boolean login(String id, String pwd) {
		// 로그인(users DB)
		String sql = "select id, pwd from users";
		ArrayList<Member> list = new ArrayList<Member>();
		ResultSet rs = null;
		Connection conn = db.getConnect();// db 연결
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();// db에서 읽어오기 실행
			while (rs.next()) {
				String _id = rs.getString(1);
				String _pwd = rs.getString(2);
				list.add(new Member(_id, _pwd));// db에서 읽어온 데이터를 list에 추가
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (Member m : list) {
			if (id.equals(m.getId())) {
				if (pwd.equals(m.getPwd())) {
					return false;
				} else {
					System.out.print("비밀번호가 틀렸습니다. ");
					return true;
				}
			}
		}
		System.out.print("존재하지 않는 아이디입니다. ");
		return true;
	}

}
