package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import dao.TeacherDao;
import entity.Choose;
import entity.Student;
import entity.Teacher;


@WebServlet("/Student.action")
public class StuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao sdao=new StudentDao();
	private TeacherDao tdao=new TeacherDao();
	private Student s=null;
	private Choose choice=null;
    public StuController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String opttype=request.getParameter("opttype");
		String tid="";
		switch(opttype) 
		{
			case "showtutors"://查看本学院老师页面
				s=sdao.queryStudentById(id);
				request.getSession().setAttribute("tlist", tdao.queryAllcolteacher(s.getCno()));
				response.sendRedirect("Mainstusee.jsp");
				break;
				
			case "viewtutor"://查看导师详细信息页面
				tid=request.getParameter("tid");
				//System.out.println("导师编号"+tid);
				s=sdao.queryStudentById(id);
				request.getSession().setAttribute("t", tdao.queryTeacherById(tid));
				response.sendRedirect("studecide.jsp");
				break;
				
			case "updatechoice"://更新志愿表tid,sid
				tid=request.getParameter("tid");
				//System.out.println("导师编号"+tid);
				int k=sdao.updateChoiceById(id,tid);
				if(k==-1) 
				{
					response.sendRedirect("studecide.jsp?k=-1");
				}
				else if(k==-2)
				{
					//System.out.println(k);
					response.sendRedirect("studecide.jsp?k=-2");
				}
				else {
					
					response.sendRedirect("studecide.jsp");
				}
				break;
				
				
			case "selecttutors"://选三志愿页面
				//System.out.println("selecttutors"+tid);
				//System.out.println("selecttutors"+id);
				choice=sdao.queryChoiceById(id);
				request.getSession().setAttribute("choice", choice);
				//System.out.println("choice的各属性:1"+choice.getFirst()+"2"+choice.getSecond()+"3"+choice.getThird());
				List<Teacher> tli=null;
				if(tdao.queryChooseteacher(choice)==null) 
				{
					response.sendRedirect("Mainstuchoice.jsp");
				}else {
					tli=tdao.queryChooseteacher(choice);
					request.getSession().setAttribute("ctlist", tli);
					response.sendRedirect("Mainstuchoice.jsp");
				}
				break;
				
			case "deletetutor"://删除导师
				tid=request.getParameter("tid");
				int p=sdao.deleteTutorById(id, tid);
				//System.out.println("p的值:"+p);
				response.sendRedirect("Student.action?id="+id+"&opttype=selecttutors");//删除应该不必要
				break;
				
				
			case "reupchoice"://调整志愿表次序tid,sid
				String tendest=request.getParameter("tendest");//1志愿
				//System.out.println("tendest值："+tendest);
				String tender=request.getParameter("tender");//2志愿
				//System.out.println("tender值："+tender);
				String tend=request.getParameter("tend");//3志愿
				//System.out.println("tend值："+tend);
				//System.out.println("导师编号"+tid);
				if(tendest.equals(tender)&&tendest.equals(tend)) 
				{
					tender="";
					tend="";
				}
				else if(tendest.equals(tend)||tender.equals(tend))
				{
					tend="";
				}
				else if(tendest.equals(tender))
				{
					tender=tend;
					tend="";
				}
				
				
				if(tend==null||tend.equals("")) 
				{
					tend="";
					sdao.rechangeChoiceById(id, tendest, tender, tend);
					System.out.println("if:"+id);
					response.sendRedirect("Student.action?id="+id+"&opttype=selecttutors");
				}
				else {
					System.out.println("else:"+id);
					sdao.rechangeChoiceById(id, tendest, tender, tend);
					response.sendRedirect("Student.action?id="+id+"&opttype=selecttutors");
				}
				break;
				
				
			case "showresult"://最终导师页面
				String tutorid="";
				tutorid=sdao.queryfinalById(id);
				if(tutorid.equals(""))
				{
					response.sendRedirect("Mainstufailfin.jsp");
				}
				else {
					request.getSession().setAttribute("tutor", tdao.queryTeacherById(tutorid));
					response.sendRedirect("Mainstufin.jsp");
				}
				break;
				
			case "changepwd"://修改密码页面
				s=sdao.queryStudentById(id);
				request.getSession().setAttribute("stu", s);
				response.sendRedirect("Mainstupwd.jsp");
				break;
			
			case "dochangepwd"://真正修改密码
				int pd=0;//
				s=sdao.queryStudentById(id);//得到学生数据
				String pawd=request.getParameter("pastpwd");//输入的旧密码
				String npwd=request.getParameter("newpwd");//输入的新密码
				String cnpwd=request.getParameter("connewpwd");//输入的确认密码
				
				//System.out.println("id值："+id);
				//System.out.println("pawd值："+pawd);
				//System.out.println("npwd值："+npwd);
				//System.out.println("cnpwd值："+cnpwd);
				
				if(pawd.equals(s.getSpwd()))
				{
					if(npwd.equals(cnpwd))
					{
						pd=1;
						sdao.updateStudentpwd(id, npwd);
						s=sdao.queryStudentById(id);//得到新的学生数据
						request.getSession().setAttribute("stu", s);
						response.sendRedirect("Mainstupwd.jsp?pd=1");
					}
					else //新密码不符
					{
						pd=-2;
						s=sdao.queryStudentById(id);//得到新的学生数据
						request.getSession().setAttribute("stu", s);
						response.sendRedirect("Mainstupwd.jsp?pd=-2");
					}
				}
				else //旧密码不符
				{
					pd=-1;
					s=sdao.queryStudentById(id);//得到新的学生数据
					request.getSession().setAttribute("stu", s);
					response.sendRedirect("Mainstupwd.jsp?pd=-1");
				}
				
				break;			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
