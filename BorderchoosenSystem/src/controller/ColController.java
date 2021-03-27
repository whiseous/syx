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
		String id=request.getParameter("id");//Ժ����
		String opttype=request.getParameter("opttype");
		String sid="";//ѧ��
		String seartext="";
		String cname="";
		switch(opttype) 
		{
			/*��ʦ��Ϣά��*/
			case "tutorin"://��ʦ��Ϣ¼�����
				c=cdao.queryColAdminById(id);
				response.sendRedirect("Maincadtutorin.jsp");
				break;
				
			case "inserttutor"://¼�뵼ʦ��Ϣ
				c=cdao.queryColAdminById(id);
				/*�õ�ʦ��Ϣ*/
				String tid=request.getParameter("cadtid");
				String tname=request.getParameter("cadtname");
				tname = new String(tname.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				String tgender=request.getParameter("cadtgender");
				tgender = new String(tgender.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				String tbirth=request.getParameter("cadtbirth");
				String tedu=request.getParameter("cadtedu");
				tedu = new String(tedu.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				String tjob=request.getParameter("cadtjob");
				tjob = new String(tjob.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				String tsub=request.getParameter("cadtsub");
				tsub = new String(tsub.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				String ttype=request.getParameter("cadttype");
				ttype = new String(ttype.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				String tcname=request.getParameter("cadtcname");
				tcname = new String(tcname.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				String ttele=request.getParameter("cadttele");
				String tmail=request.getParameter("cadtmail");
				String tfield=request.getParameter("cadtfield");
				tfield = new String(tfield.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				String limit=request.getParameter("cadtlimit");
				int  tlimit=Integer.valueOf(limit);
				String tcno=sdao.queryCnoByCname(tcname);
				
				//System.out.println("genderֵ"+tgender);
				
				
				tdao.addTeacher(tid, tcno,"111111", tname, tgender, tbirth, tjob, tedu, tsub, ttype, ttele, tmail, tfield, tlimit);
				response.sendRedirect("Maincadtutorin.jsp?k=1");
				break;
				
			case "updatetutor":
				c=cdao.queryColAdminById(id);
				/*�õ�ʦ��Ϣ*/
				tid=request.getParameter("cadtid");
				tname=request.getParameter("cadtname");
				tname = new String(tname.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				tgender=request.getParameter("cadtgender");
				tgender = new String(tgender.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				tbirth=request.getParameter("cadtbirth");
				tedu=request.getParameter("cadtedu");
				tedu = new String(tedu.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				tjob=request.getParameter("cadtjob");
				tjob = new String(tjob.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				tsub=request.getParameter("cadtsub");
				tsub = new String(tsub.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				ttype=request.getParameter("cadttype");
				ttype = new String(ttype.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				tcname=request.getParameter("cadtcname");
				tcname = new String(tcname.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				ttele=request.getParameter("cadttele");
				tmail=request.getParameter("cadtmail");
				tfield=request.getParameter("cadtfield");
				tfield = new String(tfield.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				limit=request.getParameter("cadtlimit");
				tlimit=Integer.valueOf(limit);
				tcno=sdao.queryCnoByCname(tcname);
				
				//System.out.println("genderֵ"+tgender);
				
				
				tdao.exupTeacher(tid, tname, tgender, tbirth, tjob, tedu, tsub, ttype, ttele, tmail, tfield, tlimit);
				request.getSession().setAttribute("t", tdao.queryTeacherById(tid));
				response.sendRedirect("Cadseetutorinfo.jsp?k=1");
				
				break;
				
				
			case "selectshowtutor"://��ʦ��Ϣά����������

				request.getSession().setAttribute("tlist", tdao.queryAllteacher());

				response.sendRedirect("Maincolsearchtutor.jsp");
				
				break;
				
			case "reuptutor"://��ʦ��Ϣά����ִ�в��ң�
				
				//request.setCharacterEncoding("UTF-8");
				//response.setContentType("text/html;charset=UTF-8");
				seartext=request.getParameter("searchtext");//ȡ����������
				seartext = new String(seartext.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				//System.out.println("ֵ"+seartext);
				/* ��ȡֵ
				 * ���Ը�ֵ����dao���
				 * dao���ز�ѯ�ĵ�ʦ�б�
				 * ˼������Ϊ�뵼ʦ��ػ��Ƿ��ڵ�ʦdao
				 * */
				List<Teacher> tli=null;
				tli=tdao.queryTargettutor(seartext);
				if(tli==null) 
				{
					//System.out.println("û��ֵ");
					response.sendRedirect("Maincolsearchtutor.jsp");
				}else {
					//System.out.println("��ֵ");
					tli=tdao.queryTargettutor(seartext);
					//Teacher a=tli.get(0);
					//System.out.println(a.getTname());
					request.getSession().setAttribute("tlist", tli);
					response.sendRedirect("Maincolsearchtutor.jsp");
				}
				
				break;
				
			case "viewtutor"://�鿴��ʦ��ϸ��Ϣҳ��
				tid=request.getParameter("tid");
				cname=tdao.queryCnameById(tid);
				//System.out.println("cname:"+cname);
				//System.out.println("��ʦ���"+tid);
				request.getSession().setAttribute("cname", cname);
				request.getSession().setAttribute("t", tdao.queryTeacherById(tid));
				response.sendRedirect("Cadseetutorinfo.jsp?");
				break;
				
			case "deletutor"://ɾ����ʦ
				tid=request.getParameter("tid");
				int p=tdao.deleteTeacherById(tid);
				//System.out.println("p��ֵ:"+p);
				response.sendRedirect("Coladmin.action?id="+id+"&opttype=selectshowtutor");//ɾ��Ӧ�ò���Ҫ������˵���ɲ��ɹ���
				break;
				
				
				/*ѧ����Ϣά��*/

			case "graduin"://ѧ����Ϣ¼�����
				c=cdao.queryColAdminById(id);
				response.sendRedirect("Maincadgraduin.jsp");
				break;
				
			case "insertgrad"://¼��ѧ����Ϣ
				c=cdao.queryColAdminById(id);
				/*��ѧ����Ϣ*/
				sid=request.getParameter("cadsid");//id
				String sname=request.getParameter("cadsname");//name
				sname = new String(sname.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				String sgender=request.getParameter("cadsgender");//gender
				sgender = new String(sgender.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				String spro=request.getParameter("cadspro");//profession
				spro = new String(spro.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���

				
				String scname=request.getParameter("cadscname");//cname
				scname = new String(scname.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				
				String score=request.getParameter("cadscore");
				int  sscore=Integer.valueOf(score);
				String scno=sdao.queryCnoByCname(scname);//cno
				
				
				
				sdao.addStudent(sid, scno, "123123", sname, spro, sgender, score);
				sdao.addStuchoose(sid);
				response.sendRedirect("Maincadgraduin.jsp?k=1");
				break;
				
			case "updategrad":
				c=cdao.queryColAdminById(id);
				/*��ѧ����Ϣ*/
				sid=request.getParameter("cadsid");//id
				sname=request.getParameter("cadsname");//name
				sname = new String(sname.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				sgender=request.getParameter("cadsgender");//gender
				sgender = new String(sgender.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				spro=request.getParameter("cadspro");//profession
				spro = new String(spro.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���

				
				scname=request.getParameter("cadscname");//cname
				scname = new String(scname.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				
				String targetname=request.getParameter("cadsfintutor");//���յ�ʦ
				targetname = new String(targetname.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				//System.out.println(targetname);
				String targettid=tdao.queryTeacherByname(targetname);
				//System.out.println(targettid);
				sdao.updatefinalById(sid, targettid);//ʵ�и���
				score=request.getParameter("cadscore");
				sscore=Integer.valueOf(score);
				scno=sdao.queryCnoByCname(scname);//cno
				
				//System.out.println("genderֵ"+tgender);
				
				
				sdao.exupStudent(sid,sname, spro, sgender, score);
				
				request.getSession().setAttribute("fintname", targetname);
				request.getSession().setAttribute("s", sdao.queryStudentById(sid));
				response.sendRedirect("Cadseegradinfo.jsp?k=1");
				
				break;
				
				
				
			case "selectshowgrad"://ѧ����Ϣά����������

				request.getSession().setAttribute("slist", sdao.queryAllStudent());

				response.sendRedirect("Maincolsearchgrad.jsp");
				
				break;
				
			case "reupgrad"://ѧ����Ϣά����ִ�в��ң�
				
				//request.setCharacterEncoding("UTF-8");
				//response.setContentType("text/html;charset=UTF-8");
				seartext=request.getParameter("searchstutext");//ȡ����������
				seartext = new String(seartext.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				//System.out.println("ֵ"+seartext);
				/* ��ȡֵ
				 * ���Ը�ֵ����dao���
				 * dao���ز�ѯ�ĵ�ʦ�б�
				 * ˼������Ϊ�뵼ʦ��ػ��Ƿ��ڵ�ʦdao
				 * */
				List<Student> sli=null;
				sli=sdao.queryTargetgrad(seartext);
				if(sli==null) 
				{
					//System.out.println("û��ֵ");
					response.sendRedirect("Maincolsearchgrad.jsp");
				}else {
					//System.out.println("��ֵ");
					//System.out.println(a.getTname());
					request.getSession().setAttribute("slist", sli);
					response.sendRedirect("Maincolsearchgrad.jsp");
				}
				
				break;
				
			case "viewgrad"://�鿴ѧ����ϸ��Ϣҳ��
				sid=request.getParameter("sid");
				cname=sdao.queryCnameById(sid);
				//System.out.println("cname:"+cname);
				//System.out.println("��ʦ���"+tid);
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
				
			case "delegrad"://ɾ��ѧ��
				sid=request.getParameter("sid");
				int dstu=sdao.deleteStudentById(sid);
				//System.out.println("p��ֵ:"+p);
				response.sendRedirect("Coladmin.action?id="+id+"&opttype=selectshowgrad");//ɾ��Ӧ�ò���Ҫ������˵���ɲ��ɹ���
				break;
				
	
				
			case "changepwd"://�޸�����ҳ��
				c=cdao.queryColAdminById(id);
				request.getSession().setAttribute("cadmin", c);
				response.sendRedirect("Maincadminpwd.jsp");
				break;
				
			case "dochangepwd"://�����޸�����
				int pd=0;//
				c=cdao.queryColAdminById(id);//�õ�����Ա����
				String pawd=request.getParameter("pastpwd");//����ľ�����
				String npwd=request.getParameter("newpwd");//�����������
				String cnpwd=request.getParameter("connewpwd");//�����ȷ������
				
		
				if(pawd.equals(c.getTpwd()))
				{
					if(npwd.equals(cnpwd))
					{
						pd=1;
						cdao.updateColadminpwd(id, npwd);
						c=cdao.queryColAdminById(id);//�õ��µĹ���Ա����
						request.getSession().setAttribute("cadmin", c);
						response.sendRedirect("Maincadminpwd.jsp?pd=1");
					}
					else //�����벻��
					{
						pd=-2;
						c=cdao.queryColAdminById(id);//�õ��µĹ���Ա����
						request.getSession().setAttribute("cadmin", c);
						response.sendRedirect("Maincadminpwd.jsp?pd=-2");
					}
				}
				else //�����벻��
				{
					pd=-1;
					c=cdao.queryColAdminById(id);//�õ��µĹ���Ա����
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
