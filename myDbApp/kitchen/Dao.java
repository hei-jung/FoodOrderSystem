package myDbApp.kitchen;

import java.util.ArrayList;

import myDbApp.order.Order;

public interface Dao {

	// �ֹ� Ȯ��(users_log DB)
	public ArrayList<Order> checkOrder();

	// ���� �Ϸ� �� ����(users_log DB)
	public void setServed(int num);
	
	// ������ ����(users_log DB)
	public int todaySales();
}
