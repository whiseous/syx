package entity;

public class NametoDeposit {
	Depositor user=null;
	Depositrecord drecord=null;
	public NametoDeposit() {
		super();
	}
	public NametoDeposit(Depositor user, Depositrecord drecord) {
		super();
		this.user = user;
		this.drecord = drecord;
	}
	public Depositor getUser() {
		return user;
	}
	public void setUser(Depositor user) {
		this.user = user;
	}
	public Depositrecord getDrecord() {
		return drecord;
	}
	public void setDrecord(Depositrecord drecord) {
		this.drecord = drecord;
	}

	
}
