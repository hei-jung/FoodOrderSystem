package myDbApp.post;

import java.util.ArrayList;
import java.util.Scanner;

import myDbApp.order.Order;

public class ServiceImpl implements Service {

	private Dao dao;

	public ServiceImpl() {
		dao = new DaoImpl();
	}

	@Override
	public void signUp(Scanner sc) {
		// 회원가입
		System.out.println("--회원가입--");
		System.out.print("이메일을 입력하세요>>");
		String email = sc.next();
		while (dao.checkEmail(email)) {
			System.out.println("이미 가입된 메일입니다.");
			System.out.print("다른 이메일을 입력하세요>>");
			email = sc.next();
		}
		System.out.print("이름>>");
		String name = sc.next();
		System.out.print("아이디>>");
		String id = sc.next();
		System.out.print("비밀번호>>");
		String pwd = sc.next();
		System.out.print("일반?학생?>>");
		String type = sc.next();
		Member m = new Member(id, pwd, name, type, email);
		dao.insert(m);
	}

	@Override
	public void writePost(Scanner sc) {
		// 새글 쓰기
		String id = signIn(sc);
		// System.out.print("제목>>");
		// String title = sc.next();
		ArrayList<Order> list = dao.selectAllOrder(id);
//		if (list.isEmpty()) {
//			System.out.println("주문 기록이 없습니다. 후기를 작성하시려면 주문을 먼저 이용해주세요.");
//			return;
//		}
		for (Order o : list) {
			System.out.println(o);
		}
		System.out.print("후기를 작성할 주문번호를 입력하세요>>");
		int num = sc.nextInt();
		String title = dao.rtFoodName(id, num);
		System.out.print("내용을 입력하세요\n>>");
		String content = sc.next();
		String stars = pointsToStar(sc);
		dao.write(new Board(id, title, content, stars));
	}

	@Override
	public void deletePost(Scanner sc) {
		// 글 삭제
		String id = showMine(sc);// 내가 쓴 글 보여주기
		System.out.print("삭제할 글번호를 입력하세요>>");
		int num = sc.nextInt();
		dao.delete(new Board(id, num));
	}

	@Override
	public void showAll() {
		// 전체글 보기
		ArrayList<Board> list = dao.selectAllPosts();
		for (Board b : list) {
			System.out.println(b);
		}
	}

	@Override
	public String showMine(Scanner sc) {
		// 내글 보기
		String id = signIn(sc);
		ArrayList<Board> list = dao.selectMyPosts(id);
		for (Board b : list) {
			System.out.println(b);
		}
		return id;
	}

	@Override
	public String signIn(Scanner sc) {
		// 로그인
		System.out.println("--로그인--");
		System.out.print("id:");
		String id = sc.next();
		System.out.print("pw:");
		String pwd = sc.next();
		while (dao.login(id, pwd)) {
			System.out.println("다시 시도하세요.");
			System.out.print("id:");
			id = sc.next();
			System.out.print("pw:");
			pwd = sc.next();
		}
		return id;
	}

	@Override
	public String pointsToStar(Scanner sc) {
		System.out.print("별점(1~5)>>");
		String point = sc.next();
		String stars = "";
		switch (point) {
		case "1":
			stars = "★";
			break;
		case "1.5":
			stars = "★☆";
			break;
		case "2":
			stars = "★★";
			break;
		case "2.5":
			stars = "★★☆";
			break;
		case "3":
			stars = "★★★";
			break;
		case "3.5":
			stars = "★★★☆";
			break;
		case "4":
			stars = "★★★★";
			break;
		case "4.5":
			stars = "★★★★☆";
			break;
		default:
			stars = "★★★★★";
			break;
		}
		return stars;
	}

}
