package entity;

public class Teacher {
	private String Tid,Tpwd;//ְ���š�����
	private String Tname,Tgender,Tbirth,Tjob,Tedu,Tsub,Ttype;//�������Ա𡢳������¡�ְ�ơ�ѧ����ѧ�Ʒ��򡢵�ʦ���
	private String Cno;//����ѧԺ��
	private String Ttele;//�칫�绰
	private String Tmail;//�����ʼ�
	private String Tfield;//�о���������
	private int limit=0;//��ʦ��ѡ�������ѧ����
	
	public Teacher() {
		super();
	}

	public Teacher(String tid, String tpwd, String tname, String tgender, String tbirth, String tjob, String tedu,
			String tsub, String ttype, String cno, String ttele, String tmail, String tfield, int limit) {
		super();
		Tid = tid;
		Tpwd = tpwd;
		Tname = tname;
		Tgender = tgender;
		Tbirth = tbirth;
		Tjob = tjob;
		Tedu = tedu;
		Tsub = tsub;
		Ttype = ttype;
		Cno = cno;
		Ttele = ttele;
		Tmail = tmail;
		Tfield = tfield;
		this.limit = limit;
	}

	public String getTid() {
		return Tid;
	}

	public void setTid(String tid) {
		Tid = tid;
	}

	public String getTpwd() {
		return Tpwd;
	}

	public void setTpwd(String tpwd) {
		Tpwd = tpwd;
	}

	public String getTname() {
		return Tname;
	}

	public void setTname(String tname) {
		Tname = tname;
	}

	public String getTgender() {
		return Tgender;
	}

	public void setTgender(String tgender) {
		Tgender = tgender;
	}

	public String getTbirth() {
		return Tbirth;
	}

	public void setTbirth(String tbirth) {
		Tbirth = tbirth;
	}

	public String getTjob() {
		return Tjob;
	}

	public void setTjob(String tjob) {
		Tjob = tjob;
	}

	public String getTedu() {
		return Tedu;
	}

	public void setTedu(String tedu) {
		Tedu = tedu;
	}

	public String getTsub() {
		return Tsub;
	}

	public void setTsub(String tsub) {
		Tsub = tsub;
	}

	public String getTtype() {
		return Ttype;
	}

	public void setTtype(String ttype) {
		Ttype = ttype;
	}

	public String getCno() {
		return Cno;
	}

	public void setCno(String cno) {
		Cno = cno;
	}

	public String getTtele() {
		return Ttele;
	}

	public void setTtele(String ttele) {
		Ttele = ttele;
	}

	public String getTmail() {
		return Tmail;
	}

	public void setTmail(String tmail) {
		Tmail = tmail;
	}

	public String getTfield() {
		return Tfield;
	}

	public void setTfield(String tfield) {
		Tfield = tfield;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	
}
