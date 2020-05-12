package myDbApp.post;

import java.util.ArrayList;

import myDbApp.order.Order;

public interface Dao {

	// ��ü �� ��ȸ(board DB)
	public ArrayList<Board> selectAllPosts();

	// ȸ������(users DB)
	public void insert(Member m);

	// ȸ������ �� �̸��� �ߺ�üũ(users DB)
	public boolean checkEmail(String email);

	// �α���(users DB)
	public boolean login(String id, String pwd);

	// �� ����(board DB)
	public void write(Board b);

	// �� �����(board DB)
	public void delete(Board b);

	// ���� �� �۸� ����(board DB)
	public ArrayList<Board> selectMyPosts(String id);

	// ������: �ֹ��ߴ� ���� �̸�����!(foods DB)
	public String rtFoodName(String id, int num);

	// �� �ֹ���� Ȯ��(users_log DB)
	public ArrayList<Order> selectAllOrder(String id);
}
