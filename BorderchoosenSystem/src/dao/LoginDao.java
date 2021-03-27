package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import entity.Student;
import entity.users;
import dbutil.SQLHelper;

public class LoginDao {

	
	
	/*  �жϵ�½ʱ�䣨�Ƿ��ڹ涨ʱ���ڣ�  */
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
		        	flag=-1;//δ��ʼ
		        }
		        else
		        {
		        	res=etime.compareTo(tartime);
		        	if(res<0||res==0)
		        	{
		        		flag=-2;//�ѽ���
		        	}
		        	else 
		        	{
		        		flag=1;//��������
		        	}
		        }
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		return flag;
	}
	
	
	/*  ¼����־   */
	public int addlogs(String id, String iden, String opt,String logtime) {
		String sql = "insert into syslogs values(?,?,?,?)";
		return SQLHelper.executeUpdate(sql,id,iden,opt,logtime);
	}
	
	
}
