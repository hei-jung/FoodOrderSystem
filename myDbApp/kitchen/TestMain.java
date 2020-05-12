package myDbApp.kitchen;

import java.util.Scanner;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		KitchenMenu menu = new KitchenMenu();
		menu.run(sc);
	}

}
