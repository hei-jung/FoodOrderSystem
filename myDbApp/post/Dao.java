package myDbApp.post;

import java.util.ArrayList;

import myDbApp.order.Order;

public interface Dao {

	// 전체 글 조회(board DB)
	public ArrayList<Board> selectAllPosts();

	// 회원가입(users DB)
	public void insert(Member m);

	// 회원가입 시 이메일 중복체크(users DB)
	public boolean checkEmail(String email);

	// 로그인(users DB)
	public boolean login(String id, String pwd);

	// 글 쓰기(board DB)
	public void write(Board b);

	// 글 지우기(board DB)
	public void delete(Board b);

	// 내가 쓴 글만 보기(board DB)
	public ArrayList<Board> selectMyPosts(String id);

	// 글제목: 주문했던 음식 이름으로!(foods DB)
	public String rtFoodName(String id, int num);

	// 내 주문목록 확인(users_log DB)
	public ArrayList<Order> selectAllOrder(String id);
}
