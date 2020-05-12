package myDbApp.kitchen;

import java.util.ArrayList;
import java.util.Scanner;

import myDbApp.order.Order;

public class ServiceImpl implements Service {

	private Dao dao;

	public ServiceImpl() {
		dao = new DaoImpl();
	}

	@Override
	public void showOrder() {
		// �ֹ� ��ȸ
		ArrayList<Order> list = dao.checkOrder();
		for (Order o : list) {
			System.out.println(o.forKitchen());
		}
	}

	@Override
	public void cook(Scanner sc) {
		// ���� �غ�
		System.out.println("--���� ���� �� �� �ֹ�--");
		showOrder();
		System.out.print("���� �Ϸ��� �ֹ� ��ȣ>>");
		dao.setServed(sc.nextInt());
	}

}
