package myDbApp.order;

import java.util.ArrayList;
import java.util.Scanner;

import myDbApp.kitchen.Food;

public interface Dao {

	// 메뉴 조회(Foods DB)
	public ArrayList<Food> selectAllMenu();

	// 가격 불러오기(Foods DB)
	public int selectPrice(Order o);

	// 주문장바구니(users_log DB)
	public void insert(String id, Order o);

	// 내 주문목록 확인(users_log DB)
	public ArrayList<Order> selectAllOrder(String id);

	// 총 결제금액 계산(users_log DB)
	public int calTotalPrice(String id);

	// 결제(users DB)
	public void pay(String id, Scanner sc);

	// 결제 처리(users_log DB)
	public void setPaid(String id);

	// 포인트 적립(users DB)
	public void savePoint(String id, int point);

	// 로그인(users DB)
	public boolean login(String id, String pwd);

}
