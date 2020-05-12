package myDbApp.post;

public class Board {

	private int num;// �۹�ȣ
	private String writer;// ���ۼ���
	private String title;// ������
	private String date;// ���ۼ���
	private String content;// �۳���
	private String stars;// ����

	public Board() {

	}

	public Board(String writer, String title, String content, String stars) {
		// �۾����
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.stars = stars;
	}

	public Board(String writer, int num) {
		// ������
		this.writer = writer;
		this.num = num;
	}

	public Board(int num, String writer, String title, String date, String content, String stars) {
		this.num = num;
		this.writer = writer;
		this.title = title;
		this.date = date;
		this.content = content;
		this.stars = stars;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}

	@Override
	public String toString() {
		return "[" + num + "]�ۼ���:" + writer + "_����:" + title + "_�ۼ��� ��¥:" + date + "\n_����:" + content + "_����:" + stars;
	}

}
