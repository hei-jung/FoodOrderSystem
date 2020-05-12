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
		String id = signIn(sc);
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
		String id = signIn(sc);
		ArrayList<Order> list = dao.selectAllOrder(id);
		for (Order o : list) {
			System.out.println(o);
		}
	}

	@Override
	public void payAll(Scanner sc) {
		// ����
		String id = signIn(sc);
		dao.pay(id, sc);
		dao.setPaid(id);
	}

	@Override
	public String signIn(Scanner sc) {
		// �α���
		System.out.println("--�α���--");
		System.out.print("id:");
		String id = sc.next();
		System.out.print("pw:");
		String pwd = sc.next();
		while (dao.login(id, pwd)) {
			System.out.println("�ٽ� �õ��ϼ���.");
			System.out.print("id:");
			id = sc.next();
			System.out.print("pw:");
			pwd = sc.next();
		}
		return id;
	}

}
