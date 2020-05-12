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
	private int total_price;// �����ݾ�.�޴���ȣ�� �޴�DB�� �����ؼ� ���

	public DaoImpl() {
		db = DBConn.getInstance();
	}

	@Override
	public ArrayList<Food> selectAllMenu() {
		// �޴� ��ȸ(Foods DB)
		String sql = "select * from Foods order by num";
		ArrayList<Food> list = new ArrayList<Food>();
		ResultSet rs = null;
		Connection conn = db.getConnect();// db ����
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();// db���� �о���� ����
			while (rs.next()) {
				int num = rs.getInt(1);
				String name = rs.getString(2);
				int price = rs.getInt(3);
				Food f = new Food(num, name, price);
				list.add(f);// db���� �о�� �����͸� list�� �߰�
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
		// ���� �ҷ�����(Foods DB)
		String sql = "select price from Foods where num=?";
		int price = 0;
		ResultSet rs = null;
		Connection conn = db.getConnect();// db ����
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, o.getMenuNum());// ? ����
			rs = pstmt.executeQuery();// db���� �о���� ����
			if (rs.next()) {
				price = rs.getInt(1);// db price�� ��������
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
		// �ֹ���ٱ���(users_log DB)
		String sql = "insert into users_log(ordernum, id, foodnum, qty, amt) values(seq_order.nextval,?,?,?,?)";
		// �ֹ���ȣ(�ڵ�:seq_order.nextval),�޴���ȣ(�ֹ����Է�),����(�ֹ����Է�),�����ݾ�(�Ĵ�)
		Connection conn = db.getConnect();// db ����
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);// ? ����
			pstmt.setString(1, id);
			pstmt.setInt(2, o.getMenuNum());
			pstmt.setInt(3, o.getQty());
			pstmt.setInt(4, o.getPrice());
			pstmt.executeUpdate();// db�� ���� ����
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
		// �� �ֹ���� Ȯ��(users_log DB)
		String sql = "select ordernum,id,foodnum,qty,amt,paid,served,to_char(sysdate,'yy/mm/dd') from users_log where id=?";
		// �ֹ���ȣ,id,�޴���ȣ,����,�����ݾ�,����Ȯ��,�޾Ҵ���,������¥
		ArrayList<Order> list = new ArrayList<Order>();
		ResultSet rs = null;
		Connection conn = db.getConnect();// db ����
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);// ? ����
			rs = pstmt.executeQuery();// db���� �о���� ����
			while (rs.next()) {
				int orderNum = rs.getInt(1);
				int menuNum = rs.getInt(3);
				int qty = rs.getInt(4);
				int price = rs.getInt(5);
				String paid = rs.getString(6);
				String served = rs.getString(7);
				String pdate = rs.getString(8);
				Order o = new Order(orderNum, menuNum, qty, price, paid, served, pdate);
				list.add(o);// db���� �о�� �����͸� list�� �߰�
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
		// �� �����ݾ� ���(users_log DB)
		// ����� �ڹٿ��� �� �ϰ�, DB���� �׷��Լ��� �̿��ؼ� ����ϰ� ����� ��������
		// �����ͼ� ���(��, ���α׷� �����(�Һ���)���� �����ݾ��� �����ֵ���)
		String sql = "select sum(amt) from users_log where id=? and paid='N'";
		ResultSet rs = null;
		Connection conn = db.getConnect();// db ����
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);// ? ����
			rs = pstmt.executeQuery();// db���� �о���� ����
			if (rs.next()) {
				total_price = rs.getInt(1);// total_price�� db���� ���� �� ����
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
		// ����: ����Ʈ ���� �Ǵ� �л� ����(30%) --- ȸ��(users)db���� �����;���
		total_price = calTotalPrice(id);// �� �����ݾ�
		String sql = "select type, points from users where id=?";
		String type = "";
		int point = 0;
		ResultSet rs = null;
		Connection conn = db.getConnect();// db ����
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);// ? ����
			rs = pstmt.executeQuery();// db���� �о���� ����
			while (rs.next()) {
				type = rs.getString(1);// ȸ��Ÿ���� �޾ƿ�
				point = rs.getInt(2);// ȸ������Ʈ�� �޾ƿ�
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

		if (type.equals("�л�")) {
			total_price *= 0.7; // 30% ���� ����
			System.out.println(total_price + "�� �����Ǿ����ϴ�.");
		} else if (type.equals("�Ϲ�")) {
			System.out.print("����Ʈ�� ����Ͻ÷��� 0�� ��������.\n>>");
			int num = sc.nextInt();
			if ((num == 0) && (point >= 100)) {
				total_price -= point;
				System.out.println(total_price + "�� �����Ǿ����ϴ�.");
				savePoint(id, -point);
			} else {
				if ((num == 0) && (point < 100)) {
					System.out.println("����Ʈ�� �����մϴ�.");
				}
				point = (int) (total_price / 10);// ����Ʈ�� �����ݾ��� 10%
				savePoint(id, point);// ����Ʈ ��� �� �ϰ� ������
				System.out.println(point + "�� �����Ͽ����ϴ�.");
				System.out.println(total_price + "�� �����Ǿ����ϴ�.");
			}
		}
	}

	@Override
	public void setPaid(String id) {
		// ���� ó��(users_log DB)
		String sql = "update users_log set paid='Y', pay_date=sysdate where id=?";
		Connection conn = db.getConnect();// db ����
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql); // ? ����
			pstmt.setString(1, id);
			pstmt.executeUpdate();// db�� ���� ����
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
		// ����Ʈ ����(users DB)
		String sql = "select points from users where id=?";
		int userPoint = 0;
		ResultSet rs = null;
		Connection conn = db.getConnect();// db ����
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);// ? ����
			rs = pstmt.executeQuery();// db���� �о���� ����
			if (rs.next()) {
				userPoint = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sql = "update users set points=? where id=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);// ? ����
			pstmt.setInt(1, userPoint + point);// ����Ʈ ������Ʈ
			pstmt.setString(2, id);
			pstmt.executeUpdate();// db�� ���� ����
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
		// �α���(users DB)
		String sql = "select id, pwd from users";
		ArrayList<Member> list = new ArrayList<Member>();
		ResultSet rs = null;
		Connection conn = db.getConnect();// db ����
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();// db���� �о���� ����
			while (rs.next()) {
				String _id = rs.getString(1);
				String _pwd = rs.getString(2);
				list.add(new Member(_id, _pwd));// db���� �о�� �����͸� list�� �߰�
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
					System.out.print("��й�ȣ�� Ʋ�Ƚ��ϴ�. ");
					return true;
				}
			}
		}
		System.out.print("�������� �ʴ� ���̵��Դϴ�. ");
		return true;
	}

}
