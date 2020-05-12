package myDbApp;

import java.util.Scanner;

import myDbApp.kitchen.KitchenMenu;
import myDbApp.order.OrderMenu;
import myDbApp.post.BoardMenu;

public class Menu {

	private BoardMenu bm = new BoardMenu();
	private OrderMenu om = new OrderMenu();
	private KitchenMenu km = new KitchenMenu();

	public Menu() {

	}

	public void run(Scanner sc) {
		String str = "1.회원게시판 2.주문 3.주방 4.종료";
		boolean flag = true;
		while (flag) {
			System.out.println(str);
			int menu = sc.nextInt();
			switch (menu) {
			case 1:
				bm.run(sc);
				break;
			case 2:
				om.run(sc);
				break;
			case 3:
				km.run(sc);
				break;
			case 4:
				System.out.println("프로그램을 종료합니다.");
				flag = false;
				break;
			}
		}
	}
}
