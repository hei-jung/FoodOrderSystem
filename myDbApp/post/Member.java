package myDbApp.post;

public class Member {

	private String id;// 아이디
	private String pwd;// 비밀번호
	private String name;// 이름
	private String type;// 회원유형(일반/학생)
	private String email;// 이메일

	public Member() {

	}

	public Member(String id, String pwd) {
		// 로그인용
		this.id = id;
		this.pwd = pwd;
	}

	public Member(String id, String pwd, String name, String type, String email) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.type = type;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
