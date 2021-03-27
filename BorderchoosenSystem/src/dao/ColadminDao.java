package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbutil.SQLHelper;
import entity.ColAdmin;
import entity.Teacher;

public class ColadminDao {
	public List<ColAdmin> queryAllcoadmin() {
		List<ColAdmin> clist = new ArrayList<ColAdmin>();
		String sql = "select * from coadmin";
		ResultSet rs = SQLHelper.executeQuery(sql);
		try {
			while (rs.next()) {
				ColAdmin tea = new ColAdmin();
				tea.setTid(rs.getString(1));
				tea.setCno(rs.getString(2));
				tea.setTpwd(rs.getString(3));
				tea.setTname(rs.getString(4));
				tea.setTgender(rs.getString(5));
				tea.setTbirth(rs.getString(6));
				tea.setTjob(rs.getString(7));
				tea.setTedu(rs.getString(8));
				tea.setTsub(rs.getString(9));
				tea.setTtype(rs.getString(10));
				tea.setTtele(rs.getString(11));
				tea.setTmail(rs.getString(12));
				tea.setTfield(rs.getString(13));
				tea.setLimit(rs.getInt(14));
				clist.add(tea);
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return clist;
	}
	public int countColAdmin() {
		int r=0;
		String sql = "select count(*) from coadmin";
		Object obj=SQLHelper.executeSingleValue(sql);
		if(obj!=null)
			r=new Integer(obj.toString());
		return r;
	}
	
	public int addColAdmin(String tid, String cno, String tpwd, String tname, String tgender, String tbirth, String tjob, String tedu,
			String tsub, String ttype, String ttele, String tmail, String tfield, int limit) {
		String sql = "insert into coadmin values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return SQLHelper.executeUpdate(sql,tid,cno,tpwd,tname,tgender,tbirth,tjob,tedu,tsub,ttype,ttele,tmail,tfield,limit);
	}

	public int addColAdminByTeacher(Teacher tea) {
		String tid=tea.getTid();
		String cno=tea.getCno();
		String tpwd=tea.getTpwd();
		String tname=tea.getTname();
		String tgender=tea.getTgender();
		String tbirth=tea.getTbirth();
		String tjob=tea.getTjob();
		String tedu=tea.getTedu();
		String tsub=tea.getTsub();
		String ttype=tea.getTtype();
		String ttele=tea.getTtele();
		String tmail=tea.getTmail();
		String tfield=tea.getTfield();
		int limit=tea.getLimit();
		String sql = "insert into coadmin values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return SQLHelper.executeUpdate(sql,tid,cno,tpwd,tname,tgender,tbirth,tjob,tedu,tsub,ttype,ttele,tmail,tfield,limit);
	}
	
	
	
	public int updateColAdmin(String id, String pwd,String name, String gender,String tbirth, String tjob, String tedu,
			String tsub, String ttype, String ttele, String tmail, String tfield) {
		String sql = "update coadmin set tpwd=?,tname=?,tgender=?,tbirth=?,tjob=?,tedu=?,tsub=?,ttype=?,ttele=?,tmail=?,tfield=? where sid=?";
		return SQLHelper.executeUpdate(sql,pwd,name,gender,tbirth,tjob,tedu,tsub,ttype,ttele,tmail,tfield,id);
	}
	public ColAdmin queryColAdminById(String id) {
		ColAdmin tea = null;
		try {
			String sql = "select * from coadmin where tid=?";
			ResultSet rs = SQLHelper.executeQuery(sql,id);
			if(rs!=null && rs.next()) {
				tea=new ColAdmin();
				tea.setTid(rs.getString(1));
				tea.setCno(rs.getString(2));
				tea.setTpwd(rs.getString(3));
				tea.setTname(rs.getString(4));
				tea.setTgender(rs.getString(5));
				tea.setTbirth(rs.getString(6));
				tea.setTjob(rs.getString(7));
				tea.setTedu(rs.getString(8));
				tea.setTsub(rs.getString(9));
				tea.setTtype(rs.getString(10));
				tea.setTtele(rs.getString(11));
				tea.setTmail(rs.getString(12));
				tea.setTfield(rs.getString(13));
				tea.setLimit(rs.getInt(14));
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tea;
	}
	public String queryCnameById(String id) {
		
		String cno="";
		try {
			String sql = "select cno from coadmin where tid=?";
			ResultSet rs = SQLHelper.executeQuery(sql,id);
			if(rs!=null && rs.next()) {
				cno=rs.getString(1);
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		String cname = "";
		try {
			String sql = "select cname from college where cno=?";
			ResultSet rs = SQLHelper.executeQuery(sql,cno);
			if(rs!=null && rs.next()) {	
				cname=rs.getString(1);
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cname;
	}
	public int updateColadminpwd(String id, String pwd) {
		String sql = "update coadmin set tpwd=? where tid=?";
		return SQLHelper.executeUpdate(sql,pwd,id);
	}
	public int deleteColAdminById(String id) {
		String sql="delete from coadmin where tid=?";
		return SQLHelper.executeUpdate(sql, id);
	}

}