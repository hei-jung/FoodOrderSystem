package myDbApp.kitchen;

import java.util.Scanner;

public interface Service {

	// 주문 조회
	public void showOrder();

	// 음식 준비
	public void cook(Scanner sc);
}
