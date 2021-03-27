package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import entity.Choose;
import entity.Student;
import entity.Teacher;
import dbutil.SQLHelper;

public class StudentDao{

	
	public List<Student> queryAllStudent() {
		List<Student> clist = new ArrayList<Student>();
		String sql = "select * from student";
		ResultSet rs = SQLHelper.executeQuery(sql);
		try {
			while (rs.next()) {
				Student stu = new Student();
				stu.setSid(rs.getString(1));
				stu.setCno(rs.getString(2));
				stu.setSpwd(rs.getString(3));
				stu.setSname(rs.getString(4));
				stu.setSpro(rs.getString(5));
				stu.setSgender(rs.getString(6));
				stu.setSscore(rs.getString(7));
				clist.add(stu);
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return clist;
	}
	public int countStudent() {
		int r=0;
		String sql = "select count(*) from student";
		Object obj=SQLHelper.executeSingleValue(sql);
		if(obj!=null)
			r=new Integer(obj.toString());
		return r;
	}
	
	public int addStudent(String id, String cno, String pwd,String name, String pro, String gender,String score) {
		String sql = "insert into student values(?,?,?,?,?,?,?)";
		return SQLHelper.executeUpdate(sql,id,cno,pwd,name,pro,gender,score);
	}

	public int addStuchoose(String id) {
		String sql = "insert into choose values(?,?,?,?,?)";
		return SQLHelper.executeUpdate(sql,id,"","","","");
	}
	
	
	public int exupStudent(String id,String name, String pro, String gender,String score) {
		String sql = "update student set sname=?,spro=?,sgender=?,sscore=? where sid=?";
		return SQLHelper.executeUpdate(sql,name,pro,gender,score,id);
	}
	
	
	public int updateStudent(String id, String pwd,String name, String gender) {
		String sql = "update student set spwd=?,sname=?,sgender=? where sid=?";
		return SQLHelper.executeUpdate(sql,pwd,name,gender,id);
	}
	
	public int updateStudentpwd(String id, String pwd) {
		String sql = "update student set spwd=? where sid=?";
		return SQLHelper.executeUpdate(sql,pwd,id);
	}
	
	
	public Student queryStudentById(String id) {
		Student stu = null;
		try {
			String sql = "select * from student where sid=?";
			
			ResultSet rs = SQLHelper.executeQuery(sql,id);
			
			if(rs!=null && rs.next()) {
				stu=new Student();
				stu.setSid(rs.getString(1));
				stu.setCno(rs.getString(2));
				stu.setSpwd(rs.getString(3));
				stu.setSname(rs.getString(4));
				stu.setSpro(rs.getString(5));
				stu.setSgender(rs.getString(6));
				stu.setSscore(rs.getString(7));
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return stu;
	}

	public Choose queryChoiceById(String id) {
		Choose cho = null;
		Student s=queryStudentById(id);
		try {
			String sql = "select * from choose where sid=?";
			ResultSet rs = SQLHelper.executeQuery(sql,id);
			if(rs!=null && rs.next()) {
				cho=new Choose();
				cho.setS(s);
				cho.setFirst(rs.getString(2));
				cho.setSecond(rs.getString(3));
				cho.setThird(rs.getString(4));
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cho;
	}
	
	public String queryfinalById(String id) {
		String teaid = "";
		try {
			String sql = "select final from choose where sid=?";
			ResultSet rs = SQLHelper.executeQuery(sql,id);
			if(rs!=null && rs.next()) {	
				teaid=rs.getString(1);
			}else 
			{
				SQLHelper.closeConnection();
				return teaid;
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return teaid;
	}
	
	public int updatefinalById(String sid,String tid) {
		//System.out.println(sid);
		//System.out.println(tid);
		try {
			String sql = "update choose set final=? where sid=?";
			int rs = SQLHelper.executeUpdate(sql,tid,sid);
			
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 1;
	}
	
	
	public String queryCnameById(String id) {
		
		String cno="";
		try {
			String sql = "select cno from student where sid=?";
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
	
	public String queryCnoByCname(String name) {
		
		String cno="";
		try {
			String sql = "select cno from college where cname=?";
			ResultSet rs = SQLHelper.executeQuery(sql,name);
			if(rs!=null && rs.next()) {
				cno=rs.getString(1);
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cno;
	}
	
	
	public int updateChoiceById(String sid,String tid) {
		String sql="";
		try {
			sql = "select first from choose where sid=?";
			ResultSet rs = SQLHelper.executeQuery(sql,sid);
			if(rs!=null && rs.next()&&rs.getString(1).equals("")==false) {//first�е�ʦ���
				if(rs.getString(1).equals(tid))//�ж��Ƿ��ǵ�ǰ���
				{
					SQLHelper.closeConnection();
					return -2;//-2��ʾ�Ѿ�����
				}
				sql = "select second from choose where sid=?";
				rs = SQLHelper.executeQuery(sql,sid);
				if(rs!=null && rs.next()&&rs.getString(1).equals("")==false) {//second�е�ʦ���
					if(rs.getString(1).equals(tid))//�ж��Ƿ��ǵ�ǰ���
					{
						SQLHelper.closeConnection();
						return -2;//-2��ʾ�Ѿ�����
					}
					sql = "select third from choose where sid=?";
					rs = SQLHelper.executeQuery(sql,sid);
					if(rs!=null && rs.next()&&rs.getString(1).equals("")==false) {//third�е�ʦ��ţ�ȫ���ˣ�
						if(rs.getString(1).equals(tid))//�ж��Ƿ��ǵ�ǰ���
						{
							SQLHelper.closeConnection();
							return -2;//-2��ʾ�Ѿ�����
						}
						SQLHelper.closeConnection();
						return -1;//-1��ʾ������
					}
					else 
					{
						sql = "update choose set third=? where sid=?";
					}
				}
				else 
				{
					sql = "update choose set second=? where sid=?";
				}
			}
			else 
			{
				sql = "update choose set first=? where sid=?";
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return SQLHelper.executeUpdate(sql,tid,sid);
	}
	
	public int deleteTutorById(String sid,String tid) {//ɾ��־Ը���еĵ�ʦ
			String sql="";
			Choose choice=queryChoiceById(sid);
			try {
				sql = "select first from choose where sid=?";
				ResultSet rs = SQLHelper.executeQuery(sql,sid);
				if(rs!=null && rs.next()&&rs.getString(1).equals("")==false) {//first�е�ʦ���
					//System.out.println(rs.getString(1));
					//System.out.println("1hao"+tid);
				if(rs.getString(1).equals(tid))//�ж��Ƿ��ǵ�ǰ���
				{
					sql="update choose set first=? where sid=?";
					SQLHelper.executeUpdate(sql,choice.getSecond(),sid);
					sql="update choose set second=? where sid=?";
					SQLHelper.executeUpdate(sql,choice.getThird(),sid);
					sql="update choose set third=? where sid=?";
					String third="";
					SQLHelper.executeUpdate(sql,third,sid);
					return 1;
				}
				else {
					sql = "select second from choose where sid=?";
					rs = SQLHelper.executeQuery(sql,sid);
					if(rs!=null && rs.next()&&rs.getString(1).equals("")==false) {//second�е�ʦ���
						//System.out.println(rs.getString(1));
						//System.out.println("2hao"+tid);
					if(rs.getString(1).equals(tid))//�ж��Ƿ��ǵ�ǰ���
					{
						sql="update choose set second=? where sid=?";
						SQLHelper.executeUpdate(sql,choice.getThird(),sid);
						sql="update choose set third=? where sid=?";
						String third="";
						SQLHelper.executeUpdate(sql,third,sid);
					}
					else {
						sql = "select third from choose where sid=?";
						rs = SQLHelper.executeQuery(sql,sid);
						if(rs!=null && rs.next()&&rs.getString(1).equals("")==false) {//third�е�ʦ��ţ������жϣ�
							//System.out.println(rs.getString(1));
							//System.out.println("3hao"+tid);
							if(rs.getString(1).equals(tid))//�ж��Ƿ��ǵ�ǰ���
							{
								sql="update choose set third=? where sid=?";
								String third="";
								return SQLHelper.executeUpdate(sql,third,sid);
							}
							else {
								SQLHelper.closeConnection();
								return -1;//��ʾ�ߵ��˲��Եĵط�����ͼɾ�������ڵĵ�ʦ��
							}
						}
						}
					}
					}
				}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 1;
	}
	
	
	public int rechangeChoiceById(String sid,String test,String ter,String t) {//����־Ը����ȷ������־Ը
		String sql="";
		try {
			sql="update choose set first=?,second=?,third=? where sid=?";
			SQLHelper.executeUpdate(sql,test,ter,t,sid);
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 1;
	}
	
	public List<Student> queryteaChoosestudent(String tid) {
		List<Student> slist = null;
		slist =new ArrayList<Student>();
		/* �ȸ���tid�ҵ����е�ѧ��sid
		 * �ٸ���sid�鵽ѧ����Ϣ�����slist
		 * �ٰ�slist����
		 * */
		String sql="";
		ResultSet rs =null;
		try {
			sql = "select * from teachoose where tid=?";
			rs = SQLHelper.executeQuery(sql,tid);
			while (rs.next()) {
				Student stu = new Student();
				stu=queryStudentById(rs.getString(2));
				slist.add(stu);
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return slist;
	}
	public List<Student> queryTargetgrad(String text) {
		List<Student> slist = new ArrayList<Student>();
		String target="%"+text+"%";
		//System.out.println("target"+target);
		/*ѧԺ����Ҫ����ȡֵ
		 * */
		String sql = "select * from  student INNER JOIN college ON student.cno=college.cno where sid like ? or sname like ? or sgender like ? or spro like ? or cname like ? or sscore like ?";
		ResultSet rs = SQLHelper.executeQuery(sql,target,target,target,target,target,target);
		try {
			while (rs.next()) {
				//System.out.println("dao����");
				Student stu = new Student();
				stu.setSid(rs.getString(1));
				stu.setCno(rs.getString(2));
				stu.setSpwd(rs.getString(3));
				stu.setSname(rs.getString(4));
				stu.setSpro(rs.getString(5));
				stu.setSgender(rs.getString(6));
				stu.setSscore(rs.getString(7));
				//System.out.println("stu��name"+stu.getTname());
				slist.add(stu);
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return slist;
	}
	
	
	public int deleteStudentById(String id) {
		String sql="delete from student where sid=?";
		return SQLHelper.executeUpdate(sql, id);
	}

}