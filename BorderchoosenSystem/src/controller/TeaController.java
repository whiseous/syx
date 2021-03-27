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

/**
 * Servlet implementation class TeaController
 */
@WebServlet("/Teacher.action")
public class TeaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao sdao=new StudentDao();
	private TeacherDao tdao=new TeacherDao();
	private Teacher t=null;
	private Choose choice=null;
    public TeaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");//导师
		String opttype=request.getParameter("opttype");
		String sid="";//学生
		switch(opttype) 
		{
			case "selectshowgrad"://查看选择学生
				t=tdao.queryTeacherById(id);
				request.getSession().setAttribute("slist", tdao.queryAllselestudent(id));
				response.sendRedirect("Mainteasee.jsp");
				break;
				
			case "viewstu"://查看学生详细信息页面
				sid=request.getParameter("sid");
				//System.out.println("导师编号"+tid);
				t=tdao.queryTeacherById(id);
				request.getSession().setAttribute("s", sdao.queryStudentById(sid));
				response.sendRedirect("tutordecide.jsp");
				break;
				
			case "updateteachoice"://更新导师选择表tid,sid
				sid=request.getParameter("sid");
				//System.out.println("导师编号"+id);
				int k=tdao.updateteaChoiceById(id,sid);
				if(k==1) 
				{
					response.sendRedirect("tutordecide.jsp?k=1");
				}
				else if(k==-2)
				{
					//System.out.println(k);
					response.sendRedirect("tutordecide.jsp?k=-2");
				}
				else if(k==0){
					
					response.sendRedirect("tutordecide.jsp?k=0");
				}
				else if(k==5) 
				{
					response.sendRedirect("tutordecide.jsp?k=5");
				}
				break;
				
			case "listgrad"://查看学生
				t=tdao.queryTeacherById(id);
				List<Student> sli=null;
				if(sdao.queryteaChoosestudent(id)==null) 
				{
					response.sendRedirect("Mainteachoice.jsp");
				}else {
					sli=sdao.queryteaChoosestudent(id);
					request.getSession().setAttribute("slist", sli);
					response.sendRedirect("Mainteachoice.jsp");
				}
				break;
				
			case "deletestu"://删除学生
				sid=request.getParameter("sid");
				int p=tdao.deleteStudentintechoById(id, sid);
				//System.out.println("p的值:"+p);
				response.sendRedirect("Teacher.action?id="+id+"&opttype=listgrad");//删除应该不必要
				break;
				
				
			case "confirmresult"://确定结果
				t=tdao.queryTeacherById(id);
				List<Student> fistuli=null;
				if(sdao.queryteaChoosestudent(id)==null) 
				{
					response.sendRedirect("Mainteafailfin.jsp");
				}else {
					fistuli=sdao.queryteaChoosestudent(id);
					request.getSession().setAttribute("slist", fistuli);
					response.sendRedirect("Mainteafin.jsp");
				}
				break;
				
			case "changepwd"://修改密码页面
				t=tdao.queryTeacherById(id);
				request.getSession().setAttribute("tea", t);
				response.sendRedirect("Mainteapwd.jsp");
				break;
			
			case "dochangepwd"://真正修改密码
				int pd=0;//
				t=tdao.queryTeacherById(id);//得到导师数据
				String pawd=request.getParameter("pastpwd");//输入的旧密码
				String npwd=request.getParameter("newpwd");//输入的新密码
				String cnpwd=request.getParameter("connewpwd");//输入的确认密码
				
				//System.out.println("id值："+id);
				//System.out.println("pawd值："+pawd);
				//System.out.println("npwd值："+npwd);
				//System.out.println("cnpwd值："+cnpwd);
				
				if(pawd.equals(t.getTpwd()))
				{
					if(npwd.equals(cnpwd))
					{
						pd=1;
						tdao.updateTeacherpwd(id, npwd);
						t=tdao.queryTeacherById(id);//得到新的导师数据
						request.getSession().setAttribute("tea", t);
						response.sendRedirect("Mainteapwd.jsp?pd=1");
					}
					else //新密码不符
					{
						pd=-2;
						t=tdao.queryTeacherById(id);//得到原导师数据
						request.getSession().setAttribute("tea", t);
						response.sendRedirect("Mainteapwd.jsp?pd=-2");
					}
				}
				else //旧密码不符
				{
					pd=-1;
					t=tdao.queryTeacherById(id);//得到原导师数据
					request.getSession().setAttribute("tea", t);
					response.sendRedirect("Mainteapwd.jsp?pd=-1");
				}
				
				break;			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
