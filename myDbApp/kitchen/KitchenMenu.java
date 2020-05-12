package myDbApp.kitchen;

import java.util.Scanner;

public class KitchenMenu {

	private Service service;

	public KitchenMenu() {
		service = new ServiceImpl();
	}

	public void run(Scanner sc) {
		String str = "1.�����Ϸ� 2.����";
		boolean flag = true;
		while (flag) {
			System.out.println(str);
			int menu = sc.nextInt();
			switch (menu) {
			case 1:
				service.cook(sc);
				break;
			case 2:
				flag = false;
				System.out.println("�����մϴ�.");
				break;
			}
		}
	}

}
