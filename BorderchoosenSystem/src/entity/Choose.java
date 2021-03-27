package entity;

public class Choose {
	private Student s=new Student();
	private String first;
	private String second;
	private String third;
	private String fintutor;
	public Choose(Student s, String first, String second, String third,String fintutor) {
		super();
		this.s = s;
		this.first = first;
		this.second = second;
		this.third = third;
		this.fintutor = fintutor;
	}
	public String getFintutor() {
		return fintutor;
	}
	public void setFintutor(String fintutor) {
		this.fintutor = fintutor;
	}
	public Choose() {
		super();
	}
	public Student getS() {
		return s;
	}
	public void setS(Student s) {
		this.s = s;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
	public String getThird() {
		return third;
	}
	public void setThird(String third) {
		this.third = third;
	}
	
}
