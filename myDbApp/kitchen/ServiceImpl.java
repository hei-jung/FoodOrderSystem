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
		// 주문 조회
		ArrayList<Order> list = dao.checkOrder();
		for (Order o : list) {
			System.out.println(o.forKitchen());
		}
	}

	@Override
	public void cook(Scanner sc) {
		// 음식 준비
		System.out.println("--아직 서빙 안 한 주문--");
		showOrder();
		System.out.print("서빙 완료한 주문 번호>>");
		dao.setServed(sc.nextInt());
	}

}
