package entity;

public class log {
String id;
String iden;
String time;
String opty;
public log() {
	super();
}
public log(String id, String iden, String time, String opty) {
	super();
	this.id = id;
	this.iden = iden;
	this.time = time;
	this.opty = opty;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getIden() {
	return iden;
}
public void setIden(String iden) {
	this.iden = iden;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public String getOpty() {
	return opty;
}
public void setOpty(String opty) {
	this.opty = opty;
}

}
