package myDbApp.order;

import java.util.ArrayList;
import java.util.Scanner;

import myDbApp.kitchen.Food;

public class ServiceImpl implements Service {

	private Dao dao;

	public ServiceImpl() {
		dao = new DaoImpl();
	}

	@Override
	public void lookMenu() {
		// �޴� ����
		ArrayList<Food> list = dao.selectAllMenu();
		for (Food f : list) {
			System.out.println(f);
		}
	}

	@Override
	public void addOrder(Scanner sc) {
		// �ֹ� ��ٱ���
		lookMenu();// ��ü �޴� �����ֱ�
		System.out.print("���̵� �Է��ϼ���\n>>");
		String id = sc.next();
		System.out.print("�ֹ��� ���� ��ȣ�� ������\n>>");
		int menuNum = sc.nextInt();
		System.out.print("�� ���� �ֹ��Ͻðڽ��ϱ�?\n>>");
		int qty = sc.nextInt();
		Order o = new Order(menuNum, qty);
		o.setPrice(dao.selectPrice(o) * qty);// ������:����*�� ����,����:kitchen���� ��������.
		dao.insert(id, o);
	}

	@Override
	public void checkOrder(Scanner sc) {
		// �ֹ���� ����
		System.out.print("���̵� �Է��ϼ���\n>>");
		ArrayList<Order> list = dao.selectAllOrder(sc.next());
		for (Order o : list) {
			System.out.println(o);
		}
	}

	@Override
	public void payAll(Scanner sc) {
		// ����
		System.out.print("���̵� �Է��ϼ���\n>>");
		String id = sc.next();
		dao.pay(id, sc);
		dao.setPaid(id);
	}

}
