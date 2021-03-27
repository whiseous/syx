package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TeacherDao;
import dao.UniadminDao;
import dao.ColadminDao;
import dao.LoginDao;
import dao.StudentDao;
import entity.ColAdmin;
import entity.Student;
import entity.Teacher;
import entity.Uniadmin;


@WebServlet("/Login.action")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao dao1=new StudentDao();
	private TeacherDao dao2=new TeacherDao();
	private ColadminDao dao3=new ColadminDao();
	private UniadminDao dao4=new UniadminDao();
	private LoginDao ldao=new LoginDao();
	
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String iden=request.getParameter("identity");
		/*获取时间*/
		Calendar calendar= Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
		String logtime=sdf.format(calendar.getTime());
		ldao.addlogs(id, iden, "用户登陆", logtime);
		int flag;
		flag=ldao.judgelogtime(logtime);
		
			switch(iden) 
			{
				case "student":
					if(flag==-1)
					{
						response.sendRedirect("LoginFrm.html?f=-1");
					}else if(flag==-2)
					{
						response.sendRedirect("LoginFrm.html?f=-2");
					}else if(flag==1)
					{
						Student s=dao1.queryStudentById(id);
						//System.out.println("密码："+s.getSpwd());
						if(s==null||s.getSpwd().equals(pwd)==false)
						{
							response.sendRedirect("LoginFrm.html");
							break;
						}
						request.getSession().setAttribute("stu", s);
						request.getSession().setAttribute("cname", dao1.queryCnameById(id));
						response.sendRedirect("Mainstu.jsp");
					}
					
					break;
				case "teacher":
					if(flag==-1)
					{
						response.sendRedirect("LoginFrm.html?f=-1");
					}else if(flag==-2)
					{
						response.sendRedirect("LoginFrm.html?f=-2");
					}else if(flag==1)
					{
					Teacher t=dao2.queryTeacherById(id);
					if(t==null||t.getTpwd().equals(pwd)==false)
					{
						response.sendRedirect("LoginFrm.html");
					}
					else {
						request.getSession().setAttribute("tea", t);
						request.getSession().setAttribute("cname", dao2.queryCnameById(id));
						response.sendRedirect("Maintea.jsp");
					}
					}
					break;
				case "coladmin":
					if(flag==-1)
					{
						response.sendRedirect("LoginFrm.html?f=-1");
					}else if(flag==-2)
					{
						response.sendRedirect("LoginFrm.html?f=-2");
					}else if(flag==1)
					{
					ColAdmin c=dao3.queryColAdminById(id);
					if(c==null||c.getTpwd().equals(pwd)==false)
					{
						response.sendRedirect("LoginFrm.html");
					}else {
					request.getSession().setAttribute("cadmin", c);
					response.sendRedirect("Maincadmin.jsp");
					}
					}
					break;
				case "uniadmin":
					Uniadmin u=dao4.queryUniadminById(id);
					if(u==null||u.getUpwd().equals(pwd)==false)
					{
						response.sendRedirect("LoginFrm.html");
					}else {
					request.getSession().setAttribute("uadmin", u);
					response.sendRedirect("Mainuadmin.jsp");
					}
					break;
			}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
