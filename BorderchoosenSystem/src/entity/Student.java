package entity;

public class Student {
	private String Sid,Spwd;//ѧ�š�����
	private String Sname;//����
	private String Spro;//רҵ
	private String Sgender;//�Ա�
	private String Sscore;//����
	private String Cno;//ѧԺ��
	
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
