package myDbApp.order;

public class Order {

	private int orderNum;// 주문번호
	private int menuNum;// 메뉴번호
	private int qty;// 주문수량
	private int price;// 선택 메뉴 가격
	private String paid;// 결제확인
	private String served;// 음식나옴확인

	public Order() {

	}

	public Order(int menuNum, int qty) {
		// 주문 추가용
		this.menuNum = menuNum;
		this.qty = qty;
	}

	public Order(int menuNum, int qty, int price) {
		// 주문 수정용
		this.menuNum = menuNum;
		this.qty = qty;
		this.price = price;
	}

	public Order(int orderNum, int menuNum, int qty, int price, String paid, String served) {
		this.orderNum = orderNum;
		this.menuNum = menuNum;
		this.qty = qty;
		this.price = price;
		this.paid = paid;
		this.served = served;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public int getMenuNum() {
		return menuNum;
	}

	public void setMenuNum(int menuNum) {
		this.menuNum = menuNum;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPaid() {
		return paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}

	public String getServed() {
		return served;
	}

	public void setServed(String served) {
		this.served = served;
	}

	@Override
	public String toString() {
		return "주문 [주문번호=" + orderNum + ", 메뉴번호=" + menuNum + ", 주문수량=" + qty + ", 결제금액=" + price + ", 결제처리=" + paid
				+ "]";
	}

}
