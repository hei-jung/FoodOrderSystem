package myDbApp.post;

import java.util.Scanner;

public interface Service {

	// 회원가입
	public void signUp(Scanner sc);

	// 새글 쓰기
	public void writePost(Scanner sc);

	// 글 삭제
	public void deletePost(Scanner sc);

	// 전체글 보기
	public void showAll();

	// 내글 보기
	public void showMine(Scanner sc);

	// 로그인
	public String signIn(Scanner sc);

	// 별점 계산
	public String pointsToStar(Scanner sc);
}
