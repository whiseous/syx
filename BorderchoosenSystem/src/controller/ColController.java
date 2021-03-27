package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ColadminDao;
import dao.StudentDao;
import dao.TeacherDao;
import dao.UniadminDao;
import entity.Choose;
import entity.ColAdmin;
import entity.Student;
import entity.Teacher;
import entity.Uniadmin;
import entity.users;


@WebServlet("/Coladmin.action")
public class ColController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao sdao=new StudentDao();
	private TeacherDao tdao=new TeacherDao();
	private UniadminDao udao=new UniadminDao();
	private ColadminDao cdao=new ColadminDao();
	private ColAdmin c=null;
	private Choose choice=null;
    public ColController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");//院管理
		String opttype=request.getParameter("opttype");
		String sid="";//学生
		String seartext="";
		String cname="";
		switch(opttype) 
		{
			/*导师信息维护*/
			case "tutorin"://导师信息录入界面
				c=cdao.queryColAdminById(id);
				response.sendRedirect("Maincadtutorin.jsp");
				break;
				
			case "inserttutor"://录入导师信息
				c=cdao.queryColAdminById(id);
				/*得导师信息*/
				String tid=request.getParameter("cadtid");
				String tname=request.getParameter("cadtname");
				tname = new String(tname.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				String tgender=request.getParameter("cadtgender");
				tgender = new String(tgender.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				String tbirth=request.getParameter("cadtbirth");
				String tedu=request.getParameter("cadtedu");
				tedu = new String(tedu.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				String tjob=request.getParameter("cadtjob");
				tjob = new String(tjob.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				String tsub=request.getParameter("cadtsub");
				tsub = new String(tsub.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				String ttype=request.getParameter("cadttype");
				ttype = new String(ttype.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				String tcname=request.getParameter("cadtcname");
				tcname = new String(tcname.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				String ttele=request.getParameter("cadttele");
				String tmail=request.getParameter("cadtmail");
				String tfield=request.getParameter("cadtfield");
				tfield = new String(tfield.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				String limit=request.getParameter("cadtlimit");
				int  tlimit=Integer.valueOf(limit);
				String tcno=sdao.queryCnoByCname(tcname);
				
				//System.out.println("gender值"+tgender);
				
				
				tdao.addTeacher(tid, tcno,"111111", tname, tgender, tbirth, tjob, tedu, tsub, ttype, ttele, tmail, tfield, tlimit);
				response.sendRedirect("Maincadtutorin.jsp?k=1");
				break;
				
			case "updatetutor":
				c=cdao.queryColAdminById(id);
				/*得导师信息*/
				tid=request.getParameter("cadtid");
				tname=request.getParameter("cadtname");
				tname = new String(tname.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				tgender=request.getParameter("cadtgender");
				tgender = new String(tgender.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				tbirth=request.getParameter("cadtbirth");
				tedu=request.getParameter("cadtedu");
				tedu = new String(tedu.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				tjob=request.getParameter("cadtjob");
				tjob = new String(tjob.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				tsub=request.getParameter("cadtsub");
				tsub = new String(tsub.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				ttype=request.getParameter("cadttype");
				ttype = new String(ttype.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				tcname=request.getParameter("cadtcname");
				tcname = new String(tcname.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				ttele=request.getParameter("cadttele");
				tmail=request.getParameter("cadtmail");
				tfield=request.getParameter("cadtfield");
				tfield = new String(tfield.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				limit=request.getParameter("cadtlimit");
				tlimit=Integer.valueOf(limit);
				tcno=sdao.queryCnoByCname(tcname);
				
				//System.out.println("gender值"+tgender);
				
				
				tdao.exupTeacher(tid, tname, tgender, tbirth, tjob, tedu, tsub, ttype, ttele, tmail, tfield, tlimit);
				request.getSession().setAttribute("t", tdao.queryTeacherById(tid));
				response.sendRedirect("Cadseetutorinfo.jsp?k=1");
				
				break;
				
				
			case "selectshowtutor"://导师信息维护（搜索）

				request.getSession().setAttribute("tlist", tdao.queryAllteacher());

				response.sendRedirect("Maincolsearchtutor.jsp");
				
				break;
				
			case "reuptutor"://导师信息维护（执行查找）
				
				//request.setCharacterEncoding("UTF-8");
				//response.setContentType("text/html;charset=UTF-8");
				seartext=request.getParameter("searchtext");//取搜索框内容
				seartext = new String(seartext.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				//System.out.println("值"+seartext);
				/* 先取值
				 * 再以该值调用dao语句
				 * dao返回查询的导师列表
				 * 思考后因为与导师相关还是放在导师dao
				 * */
				List<Teacher> tli=null;
				tli=tdao.queryTargettutor(seartext);
				if(tli==null) 
				{
					//System.out.println("没有值");
					response.sendRedirect("Maincolsearchtutor.jsp");
				}else {
					//System.out.println("有值");
					tli=tdao.queryTargettutor(seartext);
					//Teacher a=tli.get(0);
					//System.out.println(a.getTname());
					request.getSession().setAttribute("tlist", tli);
					response.sendRedirect("Maincolsearchtutor.jsp");
				}
				
				break;
				
			case "viewtutor"://查看导师详细信息页面
				tid=request.getParameter("tid");
				cname=tdao.queryCnameById(tid);
				//System.out.println("cname:"+cname);
				//System.out.println("导师编号"+tid);
				request.getSession().setAttribute("cname", cname);
				request.getSession().setAttribute("t", tdao.queryTeacherById(tid));
				response.sendRedirect("Cadseetutorinfo.jsp?");
				break;
				
			case "deletutor"://删除导师
				tid=request.getParameter("tid");
				int p=tdao.deleteTeacherById(tid);
				//System.out.println("p的值:"+p);
				response.sendRedirect("Coladmin.action?id="+id+"&opttype=selectshowtutor");//删除应该不必要（传参说明成不成功）
				break;
				
				
				/*学生信息维护*/

			case "graduin"://学生信息录入界面
				c=cdao.queryColAdminById(id);
				response.sendRedirect("Maincadgraduin.jsp");
				break;
				
			case "insertgrad"://录入学生信息
				c=cdao.queryColAdminById(id);
				/*得学生信息*/
				sid=request.getParameter("cadsid");//id
				String sname=request.getParameter("cadsname");//name
				sname = new String(sname.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				String sgender=request.getParameter("cadsgender");//gender
				sgender = new String(sgender.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				String spro=request.getParameter("cadspro");//profession
				spro = new String(spro.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法

				
				String scname=request.getParameter("cadscname");//cname
				scname = new String(scname.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				
				String score=request.getParameter("cadscore");
				int  sscore=Integer.valueOf(score);
				String scno=sdao.queryCnoByCname(scname);//cno
				
				
				
				sdao.addStudent(sid, scno, "123123", sname, spro, sgender, score);
				sdao.addStuchoose(sid);
				response.sendRedirect("Maincadgraduin.jsp?k=1");
				break;
				
			case "updategrad":
				c=cdao.queryColAdminById(id);
				/*得学生信息*/
				sid=request.getParameter("cadsid");//id
				sname=request.getParameter("cadsname");//name
				sname = new String(sname.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				sgender=request.getParameter("cadsgender");//gender
				sgender = new String(sgender.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				spro=request.getParameter("cadspro");//profession
				spro = new String(spro.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法

				
				scname=request.getParameter("cadscname");//cname
				scname = new String(scname.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				
				String targetname=request.getParameter("cadsfintutor");//最终导师
				targetname = new String(targetname.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				//System.out.println(targetname);
				String targettid=tdao.queryTeacherByname(targetname);
				//System.out.println(targettid);
				sdao.updatefinalById(sid, targettid);//实行更新
				score=request.getParameter("cadscore");
				sscore=Integer.valueOf(score);
				scno=sdao.queryCnoByCname(scname);//cno
				
				//System.out.println("gender值"+tgender);
				
				
				sdao.exupStudent(sid,sname, spro, sgender, score);
				
				request.getSession().setAttribute("fintname", targetname);
				request.getSession().setAttribute("s", sdao.queryStudentById(sid));
				response.sendRedirect("Cadseegradinfo.jsp?k=1");
				
				break;
				
				
				
			case "selectshowgrad"://学生信息维护（搜索）

				request.getSession().setAttribute("slist", sdao.queryAllStudent());

				response.sendRedirect("Maincolsearchgrad.jsp");
				
				break;
				
			case "reupgrad"://学生信息维护（执行查找）
				
				//request.setCharacterEncoding("UTF-8");
				//response.setContentType("text/html;charset=UTF-8");
				seartext=request.getParameter("searchstutext");//取搜索框内容
				seartext = new String(seartext.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				//System.out.println("值"+seartext);
				/* 先取值
				 * 再以该值调用dao语句
				 * dao返回查询的导师列表
				 * 思考后因为与导师相关还是放在导师dao
				 * */
				List<Student> sli=null;
				sli=sdao.queryTargetgrad(seartext);
				if(sli==null) 
				{
					//System.out.println("没有值");
					response.sendRedirect("Maincolsearchgrad.jsp");
				}else {
					//System.out.println("有值");
					//System.out.println(a.getTname());
					request.getSession().setAttribute("slist", sli);
					response.sendRedirect("Maincolsearchgrad.jsp");
				}
				
				break;
				
			case "viewgrad"://查看学生详细信息页面
				sid=request.getParameter("sid");
				cname=sdao.queryCnameById(sid);
				//System.out.println("cname:"+cname);
				//System.out.println("导师编号"+tid);
				String tartid=sdao.queryfinalById(sid);
				String tutorname=null;
				if(tartid.equals(""))
				{
					tutorname="";
				}
				else 
				{
					tutorname=tdao.queryTeacherById(tartid).getTname();
				}
				request.getSession().setAttribute("fintname", tutorname);
				request.getSession().setAttribute("cname", cname);
				request.getSession().setAttribute("s", sdao.queryStudentById(sid));
				response.sendRedirect("Cadseegradinfo.jsp?");
				break;
				
			case "delegrad"://删除学生
				sid=request.getParameter("sid");
				int dstu=sdao.deleteStudentById(sid);
				//System.out.println("p的值:"+p);
				response.sendRedirect("Coladmin.action?id="+id+"&opttype=selectshowgrad");//删除应该不必要（传参说明成不成功）
				break;
				
	
				
			case "changepwd"://修改密码页面
				c=cdao.queryColAdminById(id);
				request.getSession().setAttribute("cadmin", c);
				response.sendRedirect("Maincadminpwd.jsp");
				break;
				
			case "dochangepwd"://真正修改密码
				int pd=0;//
				c=cdao.queryColAdminById(id);//得到管理员数据
				String pawd=request.getParameter("pastpwd");//输入的旧密码
				String npwd=request.getParameter("newpwd");//输入的新密码
				String cnpwd=request.getParameter("connewpwd");//输入的确认密码
				
		
				if(pawd.equals(c.getTpwd()))
				{
					if(npwd.equals(cnpwd))
					{
						pd=1;
						cdao.updateColadminpwd(id, npwd);
						c=cdao.queryColAdminById(id);//得到新的管理员数据
						request.getSession().setAttribute("cadmin", c);
						response.sendRedirect("Maincadminpwd.jsp?pd=1");
					}
					else //新密码不符
					{
						pd=-2;
						c=cdao.queryColAdminById(id);//得到新的管理员数据
						request.getSession().setAttribute("cadmin", c);
						response.sendRedirect("Maincadminpwd.jsp?pd=-2");
					}
				}
				else //旧密码不符
				{
					pd=-1;
					c=cdao.queryColAdminById(id);//得到新的管理员数据
					request.getSession().setAttribute("cadmin", c);
					response.sendRedirect("Maincadminpwd.jsp?pd=-1");
				}
				
				break;	

				
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
