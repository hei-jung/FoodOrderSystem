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
		// 메뉴 보기
		ArrayList<Food> list = dao.selectAllMenu();
		for (Food f : list) {
			System.out.println(f);
		}
	}

	@Override
	public void addOrder(Scanner sc) {
		// 주문 장바구니
		lookMenu();// 전체 메뉴 보여주기
		String id = signIn(sc);
		System.out.print("주문할 음식 번호를 고르세요\n>>");
		int menuNum = sc.nextInt();
		System.out.print("몇 개를 주문하시겠습니까?\n>>");
		int qty = sc.nextInt();
		Order o = new Order(menuNum, qty);
		o.setPrice(dao.selectPrice(o) * qty);// 결제액:가격*새 수량,가격:kitchen에서 가져오기.
		dao.insert(id, o);
	}

	@Override
	public void checkOrder(Scanner sc) {
		// 주문목록 보기
		String id = signIn(sc);
		ArrayList<Order> list = dao.selectAllOrder(id);
		for (Order o : list) {
			System.out.println(o);
		}
	}

	@Override
	public void payAll(Scanner sc) {
		// 결제
		String id = signIn(sc);
		dao.pay(id, sc);
		dao.setPaid(id);
	}

	@Override
	public String signIn(Scanner sc) {
		// 로그인
		System.out.println("--로그인--");
		System.out.print("id:");
		String id = sc.next();
		System.out.print("pw:");
		String pwd = sc.next();
		while (dao.login(id, pwd)) {
			System.out.println("다시 시도하세요.");
			System.out.print("id:");
			id = sc.next();
			System.out.print("pw:");
			pwd = sc.next();
		}
		return id;
	}

}
