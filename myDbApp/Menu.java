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
		String str = "1.ȸ���Խ��� 2.�ֹ� 3.�ֹ� 4.����";
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
				System.out.println("���α׷��� �����մϴ�.");
				flag = false;
				break;
			}
		}
	}
}
