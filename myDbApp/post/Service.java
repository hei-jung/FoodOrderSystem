package myDbApp.post;

import java.util.Scanner;

public interface Service {

	// ȸ������
	public void signUp(Scanner sc);

	// ���� ����
	public void writePost(Scanner sc);

	// �� ����
	public void deletePost(Scanner sc);

	// ��ü�� ����
	public void showAll();

	// ���� ����
	public void showMine(Scanner sc);

	// �α���
	public String signIn(Scanner sc);

	// ���� ���
	public String pointsToStar(Scanner sc);
}
