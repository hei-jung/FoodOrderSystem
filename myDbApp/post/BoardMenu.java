package myDbApp.post;

import java.util.Scanner;

public class BoardMenu {
	
	private Service service;
	
	public BoardMenu() {
		service = new ServiceImpl();
	}
	
	public void run(Scanner sc) {
		String str = "1.회원가입 2.새글쓰기 3.글삭제 4.전체글보기 5.내글보기 6.종료";
		boolean flag = true;
		while(flag) {
			System.out.println(str);
			int menu = sc.nextInt();
			switch(menu) {
			case 1:
				service.signUp(sc);
				break;
			case 2:
				service.writePost(sc);
				break;
			case 3:
				service.deletePost(sc);
				break;
			case 4:
				service.showAll();
				break;
			case 5:
				service.showMine(sc);
				break;
			case 6:
				flag = false;
				System.out.println("종료합니다.");
				break;
			}
		}
	}

}
