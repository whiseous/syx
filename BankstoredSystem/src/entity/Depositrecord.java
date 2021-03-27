package entity;

public class Depositrecord {
	String uid;
	String eid;
	String depositype;
	String depositdate;
	double rate;
	double amount;
	double benefit;
	String prodate;
	int active;
	
	public Depositrecord() {
		super();
	}

	public Depositrecord(String uid, String eid, String depositype, String depositdate, double rate, double amount,
			double benefit, String prodate, int active) {
		super();
		this.uid = uid;
		this.eid = eid;
		this.depositype = depositype;
		this.depositdate = depositdate;
		this.rate = rate;
		this.amount = amount;
		this.benefit = benefit;
		this.prodate = prodate;
		this.active = active;
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

	public String getDepositype() {
		return depositype;
	}

	public void setDepositype(String depositype) {
		this.depositype = depositype;
	}

	public String getDepositdate() {
		return depositdate;
	}

	public void setDepositdate(String depositdate) {
		this.depositdate = depositdate;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getBenefit() {
		return benefit;
	}

	public void setBenefit(double benefit) {
		this.benefit = benefit;
	}

	public String getProdate() {
		return prodate;
	}

	public void setProdate(String prodate) {
		this.prodate = prodate;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	
}
