package entity;

public class NametoBenefit {
	Depositor user=null;
	Benefitrecord brecord=null;
	public NametoBenefit() {
		super();
	}
	public NametoBenefit(Depositor user, Benefitrecord brecord) {
		super();
		this.user = user;
		this.brecord = brecord;
	}
	public Depositor getUser() {
		return user;
	}
	public void setUser(Depositor user) {
		this.user = user;
	}
	public Benefitrecord getBrecord() {
		return brecord;
	}
	public void setBrecord(Benefitrecord brecord) {
		this.brecord = brecord;
	}
	
}
