package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


import dbutil.SQLHelper;

public class LoginDao {


	
	public int addlogs(String id, String iden, String logtime,String opt) {
		String sql = "insert into syslogs values(?,?,?,?)";
		return SQLHelper.executeUpdate(sql,id,iden,logtime,opt);
	}
	
	
}
