package myDbApp.order;

import java.util.Scanner;

public class Menu {

	private Service service;

	public Menu() {
		service = new ServiceImpl();
	}

	public void run(Scanner sc) {
		String str = "1.�޴����� 2.�ֹ���ٱ��� 3.�ֹ���Ϻ��� 4.���� 5.����";
		boolean flag = true;
		while (flag) {
			System.out.println(str);
			int menu = sc.nextInt();
			switch (menu) {
			case 1:
				service.lookMenu();
				break;
			case 2:
				service.addOrder(sc);
				break;
			case 3:
				service.checkOrder(sc);
				break;
			case 4:
				service.payAll(sc);
				break;
			case 5:
				flag = false;
				System.out.println("�����մϴ�.");
				break;
			}
		}
	}
}
