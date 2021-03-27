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
		String id=request.getParameter("id");//��ʦ
		String opttype=request.getParameter("opttype");
		String sid="";//ѧ��
		switch(opttype) 
		{
			case "selectshowgrad"://�鿴ѡ��ѧ��
				t=tdao.queryTeacherById(id);
				request.getSession().setAttribute("slist", tdao.queryAllselestudent(id));
				response.sendRedirect("Mainteasee.jsp");
				break;
				
			case "viewstu"://�鿴ѧ����ϸ��Ϣҳ��
				sid=request.getParameter("sid");
				//System.out.println("��ʦ���"+tid);
				t=tdao.queryTeacherById(id);
				request.getSession().setAttribute("s", sdao.queryStudentById(sid));
				response.sendRedirect("tutordecide.jsp");
				break;
				
			case "updateteachoice"://���µ�ʦѡ���tid,sid
				sid=request.getParameter("sid");
				//System.out.println("��ʦ���"+id);
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
				
			case "listgrad"://�鿴ѧ��
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
				
			case "deletestu"://ɾ��ѧ��
				sid=request.getParameter("sid");
				int p=tdao.deleteStudentintechoById(id, sid);
				//System.out.println("p��ֵ:"+p);
				response.sendRedirect("Teacher.action?id="+id+"&opttype=listgrad");//ɾ��Ӧ�ò���Ҫ
				break;
				
				
			case "confirmresult"://ȷ�����
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
				
			case "changepwd"://�޸�����ҳ��
				t=tdao.queryTeacherById(id);
				request.getSession().setAttribute("tea", t);
				response.sendRedirect("Mainteapwd.jsp");
				break;
			
			case "dochangepwd"://�����޸�����
				int pd=0;//
				t=tdao.queryTeacherById(id);//�õ���ʦ����
				String pawd=request.getParameter("pastpwd");//����ľ�����
				String npwd=request.getParameter("newpwd");//�����������
				String cnpwd=request.getParameter("connewpwd");//�����ȷ������
				
				//System.out.println("idֵ��"+id);
				//System.out.println("pawdֵ��"+pawd);
				//System.out.println("npwdֵ��"+npwd);
				//System.out.println("cnpwdֵ��"+cnpwd);
				
				if(pawd.equals(t.getTpwd()))
				{
					if(npwd.equals(cnpwd))
					{
						pd=1;
						tdao.updateTeacherpwd(id, npwd);
						t=tdao.queryTeacherById(id);//�õ��µĵ�ʦ����
						request.getSession().setAttribute("tea", t);
						response.sendRedirect("Mainteapwd.jsp?pd=1");
					}
					else //�����벻��
					{
						pd=-2;
						t=tdao.queryTeacherById(id);//�õ�ԭ��ʦ����
						request.getSession().setAttribute("tea", t);
						response.sendRedirect("Mainteapwd.jsp?pd=-2");
					}
				}
				else //�����벻��
				{
					pd=-1;
					t=tdao.queryTeacherById(id);//�õ�ԭ��ʦ����
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
