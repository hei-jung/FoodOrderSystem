package myDbApp.post;

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
	public ArrayList<Board> selectAllPosts() {
		// ��ü �� ��ȸ(board DB)
		String sql = "select num, writer, title, to_char(w_date, 'yyyy/mm/dd'), content, stars from board order by num";
		ArrayList<Board> list = new ArrayList<Board>();
		ResultSet rs = null;
		Connection conn = db.getConnect();// db ����
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();// db���� �о���� ����
			while (rs.next()) {
				int num = rs.getInt(1);
				String writer = rs.getString(2);
				String title = rs.getString(3);
				String date = rs.getString(4);
				String content = rs.getString(5);
				String stars = rs.getString(6);
				Board b = new Board(num, writer, title, date, content, stars);
				list.add(b);// db���� �о�� �����͸� list�� �߰�
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
	public void insert(Member m) {
		// ȸ������(users DB)
		String sql = "insert into users(id,pwd,type,name,email) values(?,?,?,?,?)";
		Connection conn = db.getConnect();// db ����
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);// ? ����
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPwd());
			pstmt.setString(3, m.getType());
			pstmt.setString(4, m.getName());
			pstmt.setString(5, m.getEmail());
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
	public boolean checkEmail(String email) {
		// ȸ������ �� �̸��� �ߺ�üũ(users DB)
		String sql = "select email from users";
		ArrayList<String> list = new ArrayList<String>();
		ResultSet rs = null;
		Connection conn = db.getConnect();// db ����
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();// db���� �о���� ����
			while (rs.next()) {
				list.add(rs.getString(1));// db���� �о�� �����͸� list�� �߰�
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
		return list.contains(email);
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

	@Override
	public void write(Board b) {
		// �� ����(board DB)
		String sql = "insert into board values(seq_board.nextval,?,?,sysdate,?,?)";
		Connection conn = db.getConnect();// db ����
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);// ? ����
			pstmt.setString(1, b.getWriter());
			pstmt.setString(2, b.getTitle());
			pstmt.setString(3, b.getContent());
			pstmt.setString(4, b.getStars());
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
	public void delete(Board b) {
		// �� �����(board DB)
		String sql = "delete from board where num=?";
		Connection conn = db.getConnect();// db ����
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getNum());// ? ����
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
	public ArrayList<Board> selectMyPosts(String id) {
		// ���� �� �۸� ����(board DB)
		ArrayList<Board> list = new ArrayList<Board>();
		String sql = "select num, writer, title, to_char(w_date, 'yyyy/mm/dd'), content, stars from board where writer=? order by num";
		ResultSet rs = null;
		Connection conn = db.getConnect();// db ����
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);// ? ����
			rs = pstmt.executeQuery();// db���� �о���� ����
			while (rs.next()) {
				int num = rs.getInt(1);
				String writer = rs.getString(2);
				String title = rs.getString(3);
				String date = rs.getString(4);
				String content = rs.getString(5);
				String stars = rs.getString(6);
				Board b = new Board(num, writer, title, date, content, stars);
				list.add(b);// db���� �о�� �����͸� list�� �߰�
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
	public String rtFoodName(String id, int num) {
		String sql = "select food from foods where num in(select foodnum from users_log where id=? and ordernum=?)";
		ResultSet rs = null;
		Connection conn = db.getConnect();// db ����
		String food = "";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);// ? ����
			pstmt.setString(1, id);
			pstmt.setInt(2, num);
			rs = pstmt.executeQuery();// db���� �о���� ����
			if (rs.next()) {
				food = rs.getString(1);
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
		return food;
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

}
