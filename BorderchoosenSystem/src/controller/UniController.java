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
import entity.Student;
import entity.Teacher;
import entity.Uniadmin;
import entity.users;


@WebServlet("/Uniadmin.action")
public class UniController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao sdao=new StudentDao();
	private TeacherDao tdao=new TeacherDao();
	private UniadminDao udao=new UniadminDao();
	private ColadminDao cdao=new ColadminDao();
	private Uniadmin u=null;
	private Choose choice=null;
    public UniController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");//校管理
		String opttype=request.getParameter("opttype");
		String sid="";//学生
		String seartext="";
		String cname="";
		switch(opttype) 
		{
			/*导师信息维护*/
			case "tutorin"://导师信息录入界面
				u=udao.queryUniadminById(id);
				response.sendRedirect("Mainuadtutorin.jsp");
				break;
				
			case "inserttutor"://录入导师信息
				u=udao.queryUniadminById(id);
				/*得导师信息*/
				String tid=request.getParameter("uadtid");
				String tname=request.getParameter("uadtname");
				tname = new String(tname.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				String tgender=request.getParameter("uadtgender");
				tgender = new String(tgender.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				String tbirth=request.getParameter("uadtbirth");
				String tedu=request.getParameter("uadtedu");
				tedu = new String(tedu.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				String tjob=request.getParameter("uadtjob");
				tjob = new String(tjob.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				String tsub=request.getParameter("uadtsub");
				tsub = new String(tsub.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				String ttype=request.getParameter("uadttype");
				ttype = new String(ttype.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				String tcname=request.getParameter("uadtcname");
				tcname = new String(tcname.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				String ttele=request.getParameter("uadttele");
				String tmail=request.getParameter("uadtmail");
				String tfield=request.getParameter("uadtfield");
				tfield = new String(tfield.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				String limit=request.getParameter("uadtlimit");
				int  tlimit=Integer.valueOf(limit);
				String tcno=sdao.queryCnoByCname(tcname);
				
				//System.out.println("gender值"+tgender);
				
				
				tdao.addTeacher(tid, tcno,"111111", tname, tgender, tbirth, tjob, tedu, tsub, ttype, ttele, tmail, tfield, tlimit);
				response.sendRedirect("Mainuadtutorin.jsp?k=1");
				break;
				
			case "updatetutor":
				u=udao.queryUniadminById(id);
				/*得导师信息*/
				tid=request.getParameter("uadtid");
				tname=request.getParameter("uadtname");
				tname = new String(tname.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				tgender=request.getParameter("uadtgender");
				tgender = new String(tgender.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				tbirth=request.getParameter("uadtbirth");
				tedu=request.getParameter("uadtedu");
				tedu = new String(tedu.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				tjob=request.getParameter("uadtjob");
				tjob = new String(tjob.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				tsub=request.getParameter("uadtsub");
				tsub = new String(tsub.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				ttype=request.getParameter("uadttype");
				ttype = new String(ttype.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				tcname=request.getParameter("uadtcname");
				tcname = new String(tcname.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				ttele=request.getParameter("uadttele");
				tmail=request.getParameter("uadtmail");
				tfield=request.getParameter("uadtfield");
				tfield = new String(tfield.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				limit=request.getParameter("uadtlimit");
				tlimit=Integer.valueOf(limit);
				tcno=sdao.queryCnoByCname(tcname);
				
				//System.out.println("gender值"+tgender);
				
				
				tdao.exupTeacher(tid, tname, tgender, tbirth, tjob, tedu, tsub, ttype, ttele, tmail, tfield, tlimit);
				request.getSession().setAttribute("t", tdao.queryTeacherById(tid));
				response.sendRedirect("Uadseetutorinfo.jsp?k=1");
				
				break;
				
				
			case "selectshowtutor"://导师信息维护（搜索）

				request.getSession().setAttribute("tlist", tdao.queryAllteacher());

				response.sendRedirect("Mainsearchtutor.jsp");
				
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
					response.sendRedirect("Mainsearchtutor.jsp");
				}else {
					//System.out.println("有值");
					tli=tdao.queryTargettutor(seartext);
					Teacher a=tli.get(0);
					//System.out.println(a.getTname());
					request.getSession().setAttribute("tlist", tli);
					response.sendRedirect("Mainsearchtutor.jsp");
				}
				
				break;
				
			case "viewtutor"://查看导师详细信息页面
				tid=request.getParameter("tid");
				cname=tdao.queryCnameById(tid);
				//System.out.println("cname:"+cname);
				//System.out.println("导师编号"+tid);
				request.getSession().setAttribute("cname", cname);
				request.getSession().setAttribute("t", tdao.queryTeacherById(tid));
				response.sendRedirect("Uadseetutorinfo.jsp?");
				break;
				
			case "deletutor"://删除导师
				tid=request.getParameter("tid");
				int p=tdao.deleteTeacherById(tid);
				//System.out.println("p的值:"+p);
				response.sendRedirect("Uniadmin.action?id="+id+"&opttype=selectshowtutor");//删除应该不必要（传参说明成不成功）
				break;
				
				
				/*学生信息维护*/

			case "graduin"://学生信息录入界面
				u=udao.queryUniadminById(id);
				response.sendRedirect("Mainuadgraduin.jsp");
				break;
				
			case "insertgrad"://录入学生信息
				u=udao.queryUniadminById(id);
				/*得学生信息*/
				sid=request.getParameter("uadsid");//id
				String sname=request.getParameter("uadsname");//name
				sname = new String(sname.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				String sgender=request.getParameter("uadsgender");//gender
				sgender = new String(sgender.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				String spro=request.getParameter("uadspro");//profession
				spro = new String(spro.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法

				
				String scname=request.getParameter("uadscname");//cname
				scname = new String(scname.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				
				String score=request.getParameter("uadscore");
				int  sscore=Integer.valueOf(score);
				String scno=sdao.queryCnoByCname(scname);//cno
				
				
				
				sdao.addStudent(sid, scno, "123123", sname, spro, sgender, score);
				sdao.addStuchoose(sid);
				response.sendRedirect("Mainuadgraduin.jsp?k=1");
				break;
				
			case "updategrad":
				u=udao.queryUniadminById(id);
				/*得学生信息*/
				sid=request.getParameter("uadsid");//id
				sname=request.getParameter("uadsname");//name
				sname = new String(sname.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				sgender=request.getParameter("uadsgender");//gender
				sgender = new String(sgender.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				spro=request.getParameter("uadspro");//profession
				spro = new String(spro.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法

				
				scname=request.getParameter("uadscname");//cname
				scname = new String(scname.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				
				score=request.getParameter("uadscore");
				sscore=Integer.valueOf(score);
				scno=sdao.queryCnoByCname(scname);//cno
				
				//System.out.println("gender值"+tgender);
				
				
				sdao.exupStudent(sid,sname, spro, sgender, score);
				request.getSession().setAttribute("s", sdao.queryStudentById(sid));
				response.sendRedirect("Uadseegradinfo.jsp?k=1");
				
				break;
				
				
				
			case "selectshowgrad"://学生信息维护（搜索）

				request.getSession().setAttribute("slist", sdao.queryAllStudent());

				response.sendRedirect("Mainsearchgrad.jsp");
				
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
					response.sendRedirect("Mainsearchgrad.jsp");
				}else {
					//System.out.println("有值");
					//System.out.println(a.getTname());
					request.getSession().setAttribute("slist", sli);
					response.sendRedirect("Mainsearchgrad.jsp");
				}
				
				break;
				
			case "viewgrad"://查看学生详细信息页面
				sid=request.getParameter("sid");
				cname=sdao.queryCnameById(sid);
				//System.out.println("cname:"+cname);
				//System.out.println("导师编号"+tid);
				request.getSession().setAttribute("cname", cname);
				request.getSession().setAttribute("s", sdao.queryStudentById(sid));
				response.sendRedirect("Uadseegradinfo.jsp?");
				break;
				
			case "delegrad"://删除学生
				sid=request.getParameter("sid");
				int dstu=sdao.deleteStudentById(sid);
				//System.out.println("p的值:"+p);
				response.sendRedirect("Uniadmin.action?id="+id+"&opttype=selectshowgrad");//删除应该不必要（传参说明成不成功）
				break;
				
			case "setsystime"://设置系统开放时间
				u=udao.queryUniadminById(id);
				response.sendRedirect("Uadsettime.jsp");
				break;
				
			case "updatetime"://更新系统开放时间
				String stime=request.getParameter("starttime");
				
				String etime=request.getParameter("endtime");
				//System.out.println(stime);
				int res=stime.compareTo(etime);
				System.out.println(res);
		        if(res>0||res==0)
		        	{response.sendRedirect("Uadsettime.jsp?id="+id+"&k=0");}
		        else {
				/* 更改时间表原有设置
				 * dao原时间置0
				 * dao新时间置1
				 * */
		        	udao.closetime();
					udao.updatetime(stime, etime);
					response.sendRedirect("Uadsettime.jsp?id="+id+"&k=1");
		        }
				
				break;
				
			case "selectcoadmin"://设置院管理
				/*院管理界面+添加导师*/
				u=udao.queryUniadminById(id);
				request.getSession().setAttribute("clist", cdao.queryAllcoadmin());
				response.sendRedirect("Mainuadsetcol.jsp");
				break;
				
				
			case "uadreuptutor"://添加院管理-查询导师界面
				u=udao.queryUniadminById(id);
				request.getSession().setAttribute("tlist", tdao.queryAllteacher());
				
				response.sendRedirect("Mainuadsearchcoltutor.jsp");
				
				break;
				
			case "addcol"://添加院管理(执行)
				u=udao.queryUniadminById(id);
				tid=request.getParameter("tid");
				
				cdao.addColAdminByTeacher(tdao.queryTeacherById(tid));
				
				response.sendRedirect("Uniadmin.action?id="+id+"&opttype=selectcoadmin");
				
				break;	
				
			case "delecol"://删除院管理(执行)
				u=udao.queryUniadminById(id);
				tid=request.getParameter("cid");
				
				cdao.deleteColAdminById(tid);
				
				response.sendRedirect("Uniadmin.action?id="+id+"&opttype=selectcoadmin");
				
				break;
				
			case "viewcol"://查看院管理(执行)
				u=udao.queryUniadminById(id);
				tid=request.getParameter("cid");
				cname=tdao.queryCnameById(tid);
				//System.out.println("cname:"+cname);
				//System.out.println("导师编号"+tid);
				request.getSession().setAttribute("cname", cname);
				request.getSession().setAttribute("t", tdao.queryTeacherById(tid));
				response.sendRedirect("Uadseecoltutorinfo.jsp?");
				break;
				
				
			case "changepwd"://修改密码页面
				u=udao.queryUniadminById(id);
				request.getSession().setAttribute("uadmin", u);
				response.sendRedirect("Mainuadminpwd.jsp");
				break;
				
			case "dochangepwd"://真正修改密码
				int pd=0;//
				u=udao.queryUniadminById(id);//得到管理员数据
				String pawd=request.getParameter("pastpwd");//输入的旧密码
				String npwd=request.getParameter("newpwd");//输入的新密码
				String cnpwd=request.getParameter("connewpwd");//输入的确认密码
				
				//System.out.println("id值："+id);
				//System.out.println("pawd值："+pawd);
				//System.out.println("npwd值："+npwd);
				//System.out.println("cnpwd值："+cnpwd);
				
				if(pawd.equals(u.getUpwd()))
				{
					if(npwd.equals(cnpwd))
					{
						pd=1;
						udao.updateUadminpwd(id, npwd);
						u=udao.queryUniadminById(id);//得到新的管理员数据
						request.getSession().setAttribute("uadmin", u);
						response.sendRedirect("Mainuadminpwd.jsp?pd=1");
					}
					else //新密码不符
					{
						pd=-2;
						u=udao.queryUniadminById(id);//得到新的管理员数据
						request.getSession().setAttribute("uadmin", u);
						response.sendRedirect("Mainuadminpwd.jsp?pd=-2");
					}
				}
				else //旧密码不符
				{
					pd=-1;
					u=udao.queryUniadminById(id);//得到新的管理员数据
					request.getSession().setAttribute("uadmin", u);
					response.sendRedirect("Mainuadminpwd.jsp?pd=-1");
				}
				
				break;	
				
			case "viewlogs"://查看日志
				u=udao.queryUniadminById(id);//得到管理员数据
				List<users> ulogs=null;
				ulogs=udao.queryAllusers();
				request.getSession().setAttribute("ulogs", ulogs);
				response.sendRedirect("Mainuadminlogs.jsp");
				break;
				
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
