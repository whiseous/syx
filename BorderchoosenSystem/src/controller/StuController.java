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
			case "showtutors"://�鿴��ѧԺ��ʦҳ��
				s=sdao.queryStudentById(id);
				request.getSession().setAttribute("tlist", tdao.queryAllcolteacher(s.getCno()));
				response.sendRedirect("Mainstusee.jsp");
				break;
				
			case "viewtutor"://�鿴��ʦ��ϸ��Ϣҳ��
				tid=request.getParameter("tid");
				//System.out.println("��ʦ���"+tid);
				s=sdao.queryStudentById(id);
				request.getSession().setAttribute("t", tdao.queryTeacherById(tid));
				response.sendRedirect("studecide.jsp");
				break;
				
			case "updatechoice"://����־Ը��tid,sid
				tid=request.getParameter("tid");
				//System.out.println("��ʦ���"+tid);
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
				
				
			case "selecttutors"://ѡ��־Ըҳ��
				//System.out.println("selecttutors"+tid);
				//System.out.println("selecttutors"+id);
				choice=sdao.queryChoiceById(id);
				request.getSession().setAttribute("choice", choice);
				//System.out.println("choice�ĸ�����:1"+choice.getFirst()+"2"+choice.getSecond()+"3"+choice.getThird());
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
				
			case "deletetutor"://ɾ����ʦ
				tid=request.getParameter("tid");
				int p=sdao.deleteTutorById(id, tid);
				//System.out.println("p��ֵ:"+p);
				response.sendRedirect("Student.action?id="+id+"&opttype=selecttutors");//ɾ��Ӧ�ò���Ҫ
				break;
				
				
			case "reupchoice"://����־Ը�����tid,sid
				String tendest=request.getParameter("tendest");//1־Ը
				//System.out.println("tendestֵ��"+tendest);
				String tender=request.getParameter("tender");//2־Ը
				//System.out.println("tenderֵ��"+tender);
				String tend=request.getParameter("tend");//3־Ը
				//System.out.println("tendֵ��"+tend);
				//System.out.println("��ʦ���"+tid);
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
				
				
			case "showresult"://���յ�ʦҳ��
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
				
			case "changepwd"://�޸�����ҳ��
				s=sdao.queryStudentById(id);
				request.getSession().setAttribute("stu", s);
				response.sendRedirect("Mainstupwd.jsp");
				break;
			
			case "dochangepwd"://�����޸�����
				int pd=0;//
				s=sdao.queryStudentById(id);//�õ�ѧ������
				String pawd=request.getParameter("pastpwd");//����ľ�����
				String npwd=request.getParameter("newpwd");//�����������
				String cnpwd=request.getParameter("connewpwd");//�����ȷ������
				
				//System.out.println("idֵ��"+id);
				//System.out.println("pawdֵ��"+pawd);
				//System.out.println("npwdֵ��"+npwd);
				//System.out.println("cnpwdֵ��"+cnpwd);
				
				if(pawd.equals(s.getSpwd()))
				{
					if(npwd.equals(cnpwd))
					{
						pd=1;
						sdao.updateStudentpwd(id, npwd);
						s=sdao.queryStudentById(id);//�õ��µ�ѧ������
						request.getSession().setAttribute("stu", s);
						response.sendRedirect("Mainstupwd.jsp?pd=1");
					}
					else //�����벻��
					{
						pd=-2;
						s=sdao.queryStudentById(id);//�õ��µ�ѧ������
						request.getSession().setAttribute("stu", s);
						response.sendRedirect("Mainstupwd.jsp?pd=-2");
					}
				}
				else //�����벻��
				{
					pd=-1;
					s=sdao.queryStudentById(id);//�õ��µ�ѧ������
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
