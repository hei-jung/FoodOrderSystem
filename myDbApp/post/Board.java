package myDbApp.post;

public class Board {

	private int num;// 글번호
	private String writer;// 글작성자
	private String title;// 글제목
	private String date;// 글작성일
	private String content;// 글내용
	private String stars;// 별점

	public Board() {

	}

	public Board(String writer, String title, String content, String stars) {
		// 글쓰기용
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.stars = stars;
	}

	public Board(String writer, int num) {
		// 삭제용
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
		return "[" + num + "]작성자:" + writer + "_제목:" + title + "_작성된 날짜:" + date + "\n_내용:" + content + "_별점:" + stars;
	}

}
