package myDbApp.kitchen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import myDbApp.dbconn.DBConn;
import myDbApp.order.Order;

public class DaoImpl implements Dao {

	private DBConn db;

	public DaoImpl() {
		db = DBConn.getInstance();
	}

	@Override
	public ArrayList<Order> checkOrder() {
		// �ֹ� Ȯ��(users_log DB)
		// (������ �ƴµ� ������ �� �� �͸�)
		String sql = "select ordernum, foodnum, qty, to_char(pay_date,'yy/mm/dd') from users_log where paid='Y' and served='N' order by ordernum";
		ArrayList<Order> list = new ArrayList<Order>();
		ResultSet rs = null;
		Connection conn = db.getConnect();// db ����
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();// db���� �о���� ����
			while (rs.next()) {
				int orderNum = rs.getInt(1);
				int foodNum = rs.getInt(2);
				int qty = rs.getInt(3);
				String pdate = rs.getString(4);
				Order o = new Order(orderNum, foodNum, qty, pdate);
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
	public void setServed(int num) {
		// ���� �Ϸ� �� ����(users_log DB)
		String sql = "update users_log set served='Y' where ordernum=?";
		Connection conn = db.getConnect();// db ����
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);// ? ����
			pstmt.setInt(1, num);
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
	public int todaySales() {
		// ������ ����(users_log DB)
		int sales = 0;
		String sql = "select sum(amt) from users_log where to_char(pay_date,'yy/mm/dd')=to_char(sysdate,'yy/mm/dd')";
		ResultSet rs = null;
		Connection conn = db.getConnect();// db ����
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();// db���� �о���� ����
			if (rs.next()) {
				sales = rs.getInt(1);// sales�� �� ��������
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
		return sales;
	}

}
