package entity;

public class Benefitrecord {
	String uid;
	String eid;
	double withdraw;
	String withdrawdate;
	double wdbenefit;
	
	public Benefitrecord() {
		super();
	}

	public Benefitrecord(String uid, String eid, double withdraw, String withdrawdate, double wdbenefit) {
		super();
		this.uid = uid;
		this.eid = eid;
		this.withdraw = withdraw;
		this.withdrawdate = withdrawdate;
		this.wdbenefit = wdbenefit;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public double getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(double withdraw) {
		this.withdraw = withdraw;
	}

	public String getWithdrawdate() {
		return withdrawdate;
	}

	public void setWithdrawdate(String withdrawdate) {
		this.withdrawdate = withdrawdate;
	}

	public double getWdbenefit() {
		return wdbenefit;
	}

	public void setWdbenefit(double wdbenefit) {
		this.wdbenefit = wdbenefit;
	}
	
}
