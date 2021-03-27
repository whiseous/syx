package entity;

public class Depositor {
	String username;
	String address;
	String uid;
	public Depositor(String username, String address, String uid) {
		super();
		this.username = username;
		this.address = address;
		this.uid = uid;
	}
	public Depositor() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	
}
