package myDbApp.order;

import java.util.ArrayList;
import java.util.Scanner;

import myDbApp.kitchen.Food;

public interface Dao {

	// �޴� ��ȸ(kitchen DB)
	public ArrayList<Food> selectAllMenu();

	// ���� �ҷ�����(kitchen DB)
	public int selectPrice(Order o);

	// �ֹ���ٱ���
	public void insert(String id, Order o);

	// �� �ֹ���� Ȯ��
	public ArrayList<Order> selectAllOrder(String id);

	// �� �����ݾ� ���
	public int calTotalPrice(String id);

	// ����
	public void pay(String id, Scanner sc);

	// ���� ó��
	public void setPaid(String id);

	// ����Ʈ ����
	public void savePoint(String id, int point);

}
