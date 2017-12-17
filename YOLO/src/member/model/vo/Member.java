package member.model.vo;

public class Member {
	
	private String id;
	//아이디 - 이메일주소
	private String pwd;
	//비밀번호
	private String name;
	//닉네임 -중복 x
	
	
	public Member(){}
	
	
	public Member(String id, String pwd, String name, String mailing) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
	
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



	
	

}
