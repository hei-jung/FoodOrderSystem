package myDbApp.order;

import java.util.Scanner;

public interface Service {

	public void lookMenu();// 1.�޴� ����

	public void addOrder(Scanner sc);// 2.�ֹ� ��ٱ���

	public void checkOrder(Scanner sc);// 3.�ֹ���� ����

	public void payAll(Scanner sc);// 4.����

}
