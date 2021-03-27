package entity;

public class Student {
	private String Sid,Spwd;//学号、密码
	private String Sname;//姓名
	private String Spro;//专业
	private String Sgender;//性别
	private String Sscore;//分数
	private String Cno;//学院号
	
	public Student() {
		super();
	}

	public Student(String sid, String spwd, String sname, String spro, String sgender, String sscore, String cno) {
		super();
		Sid = sid;
		Spwd = spwd;
		Sname = sname;
		Spro = spro;
		Sgender = sgender;
		Sscore = sscore;
		Cno = cno;
	}

	public String getSid() {
		return Sid;
	}

	public void setSid(String sid) {
		Sid = sid;
	}

	public String getSpwd() {
		return Spwd;
	}

	public void setSpwd(String spwd) {
		Spwd = spwd;
	}

	public String getSname() {
		return Sname;
	}

	public void setSname(String sname) {
		Sname = sname;
	}

	public String getSpro() {
		return Spro;
	}

	public void setSpro(String spro) {
		Spro = spro;
	}

	public String getSgender() {
		return Sgender;
	}

	public void setSgender(String sgender) {
		Sgender = sgender;
	}

	public String getSscore() {
		return Sscore;
	}

	public void setSscore(String sscore) {
		Sscore = sscore;
	}

	public String getCno() {
		return Cno;
	}

	public void setCno(String cno) {
		Cno = cno;
	}
	
	
	
}
