package myDbApp.order;

import java.util.ArrayList;
import java.util.Scanner;

import myDbApp.kitchen.Food;

public interface Dao {

	// �޴� ��ȸ(Foods DB)
	public ArrayList<Food> selectAllMenu();

	// ���� �ҷ�����(Foods DB)
	public int selectPrice(Order o);

	// �ֹ���ٱ���(users_log DB)
	public void insert(String id, Order o);

	// �� �ֹ���� Ȯ��(users_log DB)
	public ArrayList<Order> selectAllOrder(String id);

	// �� �����ݾ� ���(users_log DB)
	public int calTotalPrice(String id);

	// ����(users DB)
	public void pay(String id, Scanner sc);

	// ���� ó��(users_log DB)
	public void setPaid(String id);

	// ����Ʈ ����(users DB)
	public void savePoint(String id, int point);

	// �α���(users DB)
	public boolean login(String id, String pwd);

}
