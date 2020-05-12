package myDbApp.kitchen;

public class Food {

	private int num; // �޴���ȣ. DB���� �ڵ� �Ҵ�
	private String name; // �޴��̸�
	private int price; // �޴��� ����

	public Food() {

	}

	public Food(int num, String name, int price) {
		this.num = num;
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return "[" + num + "]" + name + "\t--- " + price + "��";
	}

}
