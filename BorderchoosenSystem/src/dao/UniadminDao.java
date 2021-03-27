package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbutil.SQLHelper;
import entity.Uniadmin;
import entity.users;

public class UniadminDao {
	public List<Uniadmin> queryAllUniadmin() {
		List<Uniadmin> clist = new ArrayList<Uniadmin>();
		String sql = "select * from uniadmin";
		ResultSet rs = SQLHelper.executeQuery(sql);
		try {
			while (rs.next()) {
				Uniadmin uadmin = new Uniadmin();
				uadmin.setUid(rs.getString(1));
				uadmin.setUpwd(rs.getString(2));

				clist.add(uadmin);
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return clist;
	}
	public int countUniadmin() {
		int r=0;
		String sql = "select count(*) from uniadmin";
		Object obj=SQLHelper.executeSingleValue(sql);
		if(obj!=null)
			r=new Integer(obj.toString());
		return r;
	}
	
	public int addUniadmin(String id,String pwd) {
		String sql = "insert into uniadmin values(?,?)";
		return SQLHelper.executeUpdate(sql,id,pwd);
	}

	public int updateUniadmin(String id, String pwd) {
		String sql = "update uniadmin set uadpwd=? where uadid=?";
		return SQLHelper.executeUpdate(sql,pwd,id);
	}
	public Uniadmin queryUniadminById(String id) {
		Uniadmin uadmin = null;
		try {
			String sql = "select * from uniadmin where uadid=?";
			ResultSet rs = SQLHelper.executeQuery(sql,id);
			if(rs!=null && rs.next()) {
				uadmin=new Uniadmin();
				uadmin.setUid(rs.getString(1));
				uadmin.setUpwd(rs.getString(2));

			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return uadmin;
	}

	
	public int closetime() {
		String sql = "update systime set diduse=? where diduse=?";
		return SQLHelper.executeUpdate(sql,0,1);
	}
	public int updatetime(String stime, String etime) {
		String sql = "insert into systime values(?,?,?)";
		return SQLHelper.executeUpdate(sql,stime,etime,1);
	}
	
	public int updateUadminpwd(String id, String pwd) {
		String sql = "update uniadmin set uadpwd=? where uadid=?";
		return SQLHelper.executeUpdate(sql,pwd,id);
	}
	
	public List<users> queryAllusers() {//≤È—Ø»’÷æ
		List<users> clist = new ArrayList<users>();
		String sql = "select * from syslogs";
		ResultSet rs = SQLHelper.executeQuery(sql);
		try {
			while (rs.next()) {
				users user = new users();
				user.setId(rs.getString(1));
				user.setIdentity(rs.getString(2));
				user.setOpty(rs.getString(3));
				user.setLogtime(rs.getString(4));
				clist.add(user);
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return clist;
	}
	public int deleteUniadminById(String id) {
		String sql="delete from uniadmin where uadid=?";
		return SQLHelper.executeUpdate(sql, id);
	}

}