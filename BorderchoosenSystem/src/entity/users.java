package entity;

public class users {
	private String id;//编号
	private String identity;//身份
	private String opty;//操作
	private String logtime;//登陆时间
	public users(String id, String identity, String opty, String logtime) {
		super();
		this.id = id;
		this.identity = identity;
		this.opty = opty;
		this.logtime = logtime;
	}
	public users() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getOpty() {
		return opty;
	}
	public void setOpty(String opty) {
		this.opty = opty;
	}
	public String getLogtime() {
		return logtime;
	}
	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}
	
}
