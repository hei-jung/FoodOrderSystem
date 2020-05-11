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
		System.out.print("아이디를 입력하세요\n>>");
		String id = sc.next();
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
		System.out.print("아이디를 입력하세요\n>>");
		ArrayList<Order> list = dao.selectAllOrder(sc.next());
		for (Order o : list) {
			System.out.println(o);
		}
	}

	@Override
	public void payAll(Scanner sc) {
		// 결제
		System.out.print("아이디를 입력하세요\n>>");
		String id = sc.next();
		dao.pay(id, sc);
		dao.setPaid(id);
	}

}
