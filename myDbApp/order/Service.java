package myDbApp.order;

import java.util.Scanner;

public interface Service {

	public void lookMenu();// 1.메뉴 보기

	public void addOrder(Scanner sc);// 2.주문 장바구니

	public void checkOrder(Scanner sc);// 3.주문목록 보기

	public void payAll(Scanner sc);// 4.결제

}
