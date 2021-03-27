package entity;

public class Bankteller {
	String eid;
	String employeename;
	String account;
	String password;
	
	public Bankteller() {
		super();
	}

	public Bankteller(String eid, String employeename, String account, String password) {
		super();
		this.eid = eid;
		this.employeename = employeename;
		this.account = account;
		this.password = password;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
