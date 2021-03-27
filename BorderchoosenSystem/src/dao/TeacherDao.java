package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import entity.Choose;
import entity.Student;
import entity.Teacher;
import entity.Uniadmin;
import dbutil.SQLHelper;

public class TeacherDao{

	
	public List<Teacher> queryAllteacher() {
		List<Teacher> clist = new ArrayList<Teacher>();
		String sql = "select * from teacher";
		ResultSet rs = SQLHelper.executeQuery(sql);
		try {
			while (rs.next()) {
				Teacher tea = new Teacher();
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
	
	public List<Teacher> queryAllcolteacher(String cno) {
		List<Teacher> clist = new ArrayList<Teacher>();
		String sql = "select * from teacher where cno=?";
		ResultSet rs = SQLHelper.executeQuery(sql,cno);
		try {
			while (rs.next()) {
				Teacher tea = new Teacher();
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
	
	public int countTeacher() {
		int r=0;
		String sql = "select count(*) from teacher";
		Object obj=SQLHelper.executeSingleValue(sql);
		if(obj!=null)
			r=new Integer(obj.toString());
		return r;
	}
	
	public int addTeacher(String tid, String cno, String tpwd, String tname, String tgender, String tbirth, String tjob, String tedu,
			String tsub, String ttype, String ttele, String tmail, String tfield, int limit) {
		String sql = "insert into teacher values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return SQLHelper.executeUpdate(sql,tid,cno,tpwd,tname,tgender,tbirth,tjob,tedu,tsub,ttype,ttele,tmail,tfield,limit);
	}

	public int exupTeacher(String tid, String tname, String tgender, String tbirth, String tjob, String tedu,
			String tsub, String ttype, String ttele, String tmail, String tfield, int limit) {
		String sql = "update teacher set tname=?,tgender=?,tbirth=?,tjob=?,tedu=?,tsub=?,ttype=?,ttele=?,tmail=?,tfield=?,limstu=? where tid=?";
		return SQLHelper.executeUpdate(sql,tname,tgender,tbirth,tjob,tedu,tsub,ttype,ttele,tmail,tfield,limit,tid);
	}
	
	
	public int updateTeacher(String id, String pwd,String name, String gender,String tbirth, String tjob, String tedu,
			String tsub, String ttype, String ttele, String tmail, String tfield) {
		String sql = "update teacher set tpwd=?,tname=?,tgender=?,tbirth=?,tjob=?,tedu=?,tsub=?,ttype=?,ttele=?,tmail=?,tfield=? where tid=?";
		return SQLHelper.executeUpdate(sql,pwd,name,gender,tbirth,tjob,tedu,tsub,ttype,ttele,tmail,tfield,id);
	}
	public Teacher queryTeacherById(String id) {
		Teacher tea = null;
		try {
			String sql = "select * from teacher where tid=?";
			ResultSet rs = SQLHelper.executeQuery(sql,id);
			if(rs!=null && rs.next()) {
				tea=new Teacher();
				//System.out.println("导师编号"+rs.getString(1));
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
			else 
			{
				SQLHelper.closeConnection();
				return tea;
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tea;
	}
	
	public String queryTeacherByname(String name) {
		String tid = null;
		try {
			String sql = "select tid from teacher where tname=?";
			ResultSet rs = SQLHelper.executeQuery(sql,name);
			if(rs!=null && rs.next()) {
				//System.out.println("导师编号"+rs.getString(1));
				tid=rs.getString(1);
			}
			else 
			{
				SQLHelper.closeConnection();
				return tid;
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tid;
	}
	
	
	
	public String queryCnameById(String id) {
		
		String cno="";
		try {
			String sql = "select cno from teacher where tid=?";
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
	
	public List<Teacher> queryChooseteacher(Choose cho) {
		List<Teacher> clist = null;
		clist =new ArrayList<Teacher>();
		if(cho.getFirst()==null||cho.getFirst().equals(""))
		{
			return clist;
		}
		else if(cho.getSecond()==null||cho.getSecond().equals("")) 
		{
			clist.add(queryTeacherById(cho.getFirst()));
			return clist;
		}
		else if(cho.getThird()==null||cho.getThird().equals("")) 
		{
			clist.add(queryTeacherById(cho.getFirst()));
			clist.add(queryTeacherById(cho.getSecond()));
			return clist;
		}
		else 
		{
			clist.add(queryTeacherById(cho.getFirst()));
			clist.add(queryTeacherById(cho.getSecond()));
			clist.add(queryTeacherById(cho.getThird()));
			return clist;
		}
		
	}
	
	public int updateteaChoiceById(String tid,String sid) {
		
		int r=0;//计数，用于判断限制教师学生数
		String sql="";
		ResultSet rs =null;
		try {
			sql = "select count(*) from teachoose where tid=?";
			rs = SQLHelper.executeQuery(sql,tid);
			if(rs.next())
				r=Integer.valueOf(rs.getString(1));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		sql="";
		//System.out.println("tid:"+tid);
		Teacher tea=queryTeacherById(tid);
		//System.out.println("limit:"+tea.getLimit());
		//System.out.println("tsub:"+tea.getTsub());
		//System.out.println("r:"+r);
		if(tea.getLimit()>r)
		{
			try {
				sql = "select * from teachoose where tid=? and sid=?";
				rs = SQLHelper.executeQuery(sql,tid,sid);
				//System.out.println("判断rs得到前");
				if(rs!=null && rs.next()) {//有该导师选择的学生
					//System.out.println("判断rs,确定有");	
					SQLHelper.closeConnection();
					return -2;//-2表示已经有了
				}
				else 
				{
					sql = "insert into teachoose values(?,?)";
					SQLHelper.executeUpdate(sql,tid,sid);
					SQLHelper.closeConnection();
					return 1;//1表示成功插入了
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		else 
		{
			SQLHelper.closeConnection();
			return 0;//0表示已选满
		}
		return 5;//未设想的情况
		
		
	}
	
	
	
	public List<Student> queryAllselestudent(String tid)
	{
		List<Student> slist = null;
		ResultSet rs=null;
		String sql = "";
		slist =new ArrayList<Student>();
		Student gra=new Student();
		StudentDao sdao=new StudentDao();
		try {
			sql = "select * from choose where first=?";//先找一志愿
			rs = SQLHelper.executeQuery(sql,tid);
			while (rs.next()) {
				gra=sdao.queryStudentById(rs.getString(1));
				slist.add(gra);
			}
			
			sql = "select * from choose where second=?";//找二志愿
			rs = SQLHelper.executeQuery(sql,tid);
			while (rs.next()) {
				gra=sdao.queryStudentById(rs.getString(1));
				slist.add(gra);
			}
			
			sql = "select * from choose where third=?";//找三志愿
			rs = SQLHelper.executeQuery(sql,tid);
			while (rs.next()) {
				gra=sdao.queryStudentById(rs.getString(1));
				slist.add(gra);
			}
			
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		return slist;
		
	}
	public int updateTeacherpwd(String id, String pwd) {
		String sql = "update teacher set tpwd=? where tid=?";
		return SQLHelper.executeUpdate(sql,pwd,id);
	}
	
	public int deleteStudentintechoById(String tid,String sid) {//删除导师选择表中的学生（即删除该行）
		String sql="";
		try {
			sql = "select * from choose where tid=? and sid=?";
			ResultSet rs = SQLHelper.executeQuery(sql,tid,sid);
			if(rs!=null && rs.next()) {//first有导师编号
				sql="delete from teachoose where tid=? and sid=?";
				SQLHelper.executeQuery(sql,tid,sid);
				String third="";
				
			}
			else 
			{
				SQLHelper.closeConnection();
				return -1;//表示走到了不对的地方（意图删除不存在的导师）
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		SQLHelper.closeConnection();
		return 1;
	}
	
	public List<Teacher> queryTargettutor(String text) {
		List<Teacher> tlist = new ArrayList<Teacher>();
		String target="%"+text+"%";
		//System.out.println("target"+target);
		/*学院名需要另外取值
		 * */
		String sql = "select * from  teacher INNER JOIN college ON teacher.cno=college.cno where tid like ? or tname like ? or tgender like ? or tbirth like ? or tjob like ? or tedu like ? or tsub like ? or ttype like ? or ttele like ? or tmail like ? or tfield like ? or cname like ?";
		ResultSet rs = SQLHelper.executeQuery(sql,target,target,target,target,target,target,target,target,target,target,target,target);
		try {
			while (rs.next()) {
				//System.out.println("dao里有");
				Teacher tea = new Teacher();
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
				//System.out.println("tea的name"+tea.getTname());
				tlist.add(tea);
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return tlist;
	}
	
	
	public int deleteTeacherById(String id) {
		String sql="delete from teacher where tid=?";
		return SQLHelper.executeUpdate(sql, id);
	}

}