package myDbApp.kitchen;

public class Food {

	private int num; // 메뉴번호. DB에서 자동 할당
	private String name; // 메뉴이름
	private int price; // 메뉴의 가격

	public Food() {

	}

	public Food(int num, String name, int price) {
		this.num = num;
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return "[" + num + "]" + name + "\t--- " + price + "원";
	}

}
