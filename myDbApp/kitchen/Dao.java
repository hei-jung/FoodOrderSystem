package myDbApp.kitchen;

import java.util.ArrayList;

import myDbApp.order.Order;

public interface Dao {

	// 주문 확인(users_log DB)
	public ArrayList<Order> checkOrder();

	// 조리 완료 후 서빙(users_log DB)
	public void setServed(int num);
	
	// 오늘의 매출(users_log DB)
	public int todaySales();
}
