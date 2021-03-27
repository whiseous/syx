package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import entity.Student;
import entity.users;
import dbutil.SQLHelper;

public class LoginDao {

	
	
	/*  判断登陆时间（是否在规定时间内）  */
	public int judgelogtime(String tartime) 
	{
		int flag=0;
		String sql = "select * from systime where diduse=?";
		String stime="";
		String etime="";
		ResultSet rs =null;
		int res=0;
		rs = SQLHelper.executeQuery(sql,1);
		try {
			while (rs.next()) {
				stime=rs.getString(1);
				etime=rs.getString(2);
				
				res=stime.compareTo(tartime);
				//System.out.println(res);
		        if(res>0||res==0)
		        {
		        	flag=-1;//未开始
		        }
		        else
		        {
		        	res=etime.compareTo(tartime);
		        	if(res<0||res==0)
		        	{
		        		flag=-2;//已结束
		        	}
		        	else 
		        	{
		        		flag=1;//在期限内
		        	}
		        }
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		return flag;
	}
	
	
	/*  录入日志   */
	public int addlogs(String id, String iden, String opt,String logtime) {
		String sql = "insert into syslogs values(?,?,?,?)";
		return SQLHelper.executeUpdate(sql,id,iden,opt,logtime);
	}
	
	
}
