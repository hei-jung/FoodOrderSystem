package myDbApp.post;

import java.util.ArrayList;
import java.util.Scanner;

import myDbApp.order.Order;

public class ServiceImpl implements Service {

	private Dao dao;

	public ServiceImpl() {
		dao = new DaoImpl();
	}

	@Override
	public void signUp(Scanner sc) {
		// ȸ������
		System.out.println("--ȸ������--");
		System.out.print("�̸����� �Է��ϼ���>>");
		String email = sc.next();
		while (dao.checkEmail(email)) {
			System.out.println("�̹� ���Ե� �����Դϴ�.");
			System.out.print("�ٸ� �̸����� �Է��ϼ���>>");
			email = sc.next();
		}
		System.out.print("�̸�>>");
		String name = sc.next();
		System.out.print("���̵�>>");
		String id = sc.next();
		System.out.print("��й�ȣ>>");
		String pwd = sc.next();
		System.out.print("�Ϲ�?�л�?>>");
		String type = sc.next();
		Member m = new Member(id, pwd, name, type, email);
		dao.insert(m);
	}

	@Override
	public void writePost(Scanner sc) {
		// ���� ����
		String id = signIn(sc);
		// System.out.print("����>>");
		// String title = sc.next();
		ArrayList<Order> list = dao.selectAllOrder(id);
//		if (list.isEmpty()) {
//			System.out.println("�ֹ� ����� �����ϴ�. �ı⸦ �ۼ��Ͻ÷��� �ֹ��� ���� �̿����ּ���.");
//			return;
//		}
		for (Order o : list) {
			System.out.println(o);
		}
		System.out.print("�ı⸦ �ۼ��� �ֹ���ȣ�� �Է��ϼ���>>");
		int num = sc.nextInt();
		String title = dao.rtFoodName(id, num);
		System.out.print("������ �Է��ϼ���\n>>");
		String content = sc.next();
		String stars = pointsToStar(sc);
		dao.write(new Board(id, title, content, stars));
	}

	@Override
	public void deletePost(Scanner sc) {
		// �� ����
		String id = showMine(sc);// ���� �� �� �����ֱ�
		System.out.print("������ �۹�ȣ�� �Է��ϼ���>>");
		int num = sc.nextInt();
		dao.delete(new Board(id, num));
	}

	@Override
	public void showAll() {
		// ��ü�� ����
		ArrayList<Board> list = dao.selectAllPosts();
		for (Board b : list) {
			System.out.println(b);
		}
	}

	@Override
	public String showMine(Scanner sc) {
		// ���� ����
		String id = signIn(sc);
		ArrayList<Board> list = dao.selectMyPosts(id);
		for (Board b : list) {
			System.out.println(b);
		}
		return id;
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

	@Override
	public String pointsToStar(Scanner sc) {
		System.out.print("����(1~5)>>");
		String point = sc.next();
		String stars = "";
		switch (point) {
		case "1":
			stars = "��";
			break;
		case "1.5":
			stars = "�ڡ�";
			break;
		case "2":
			stars = "�ڡ�";
			break;
		case "2.5":
			stars = "�ڡڡ�";
			break;
		case "3":
			stars = "�ڡڡ�";
			break;
		case "3.5":
			stars = "�ڡڡڡ�";
			break;
		case "4":
			stars = "�ڡڡڡ�";
			break;
		case "4.5":
			stars = "�ڡڡڡڡ�";
			break;
		default:
			stars = "�ڡڡڡڡ�";
			break;
		}
		return stars;
	}

}
