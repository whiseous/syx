package entity;

public class Record {
	String eid;
	String uid;
	String optiontype;
	double num;
	String date;
	double rate;
	public Record() {
		super();
	}
	public Record(String eid, String uid, String optiontype, double num, String date, double rate) {
		super();
		this.eid = eid;
		this.uid = uid;
		this.optiontype = optiontype;
		this.num = num;
		this.date = date;
		this.rate = rate;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getOptiontype() {
		return optiontype;
	}
	public void setOptiontype(String optiontype) {
		this.optiontype = optiontype;
	}
	public double getNum() {
		return num;
	}
	public void setNum(double num) {
		this.num = num;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	
}
