package entity;

public class College {
	private String cno;//学院号
	private String cname;//学院名
	public College(String cno, String cname) {
		super();
		this.cno = cno;
		this.cname = cname;
	}
	public College() {
		super();
	}
	public String getCno() {
		return cno;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	
}
