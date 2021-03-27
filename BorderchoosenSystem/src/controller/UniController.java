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
		String id=request.getParameter("id");//У����
		String opttype=request.getParameter("opttype");
		String sid="";//ѧ��
		String seartext="";
		String cname="";
		switch(opttype) 
		{
			/*��ʦ��Ϣά��*/
			case "tutorin"://��ʦ��Ϣ¼�����
				u=udao.queryUniadminById(id);
				response.sendRedirect("Mainuadtutorin.jsp");
				break;
				
			case "inserttutor"://¼�뵼ʦ��Ϣ
				u=udao.queryUniadminById(id);
				/*�õ�ʦ��Ϣ*/
				String tid=request.getParameter("uadtid");
				String tname=request.getParameter("uadtname");
				tname = new String(tname.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				String tgender=request.getParameter("uadtgender");
				tgender = new String(tgender.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				String tbirth=request.getParameter("uadtbirth");
				String tedu=request.getParameter("uadtedu");
				tedu = new String(tedu.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				String tjob=request.getParameter("uadtjob");
				tjob = new String(tjob.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				String tsub=request.getParameter("uadtsub");
				tsub = new String(tsub.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				String ttype=request.getParameter("uadttype");
				ttype = new String(ttype.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				String tcname=request.getParameter("uadtcname");
				tcname = new String(tcname.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				String ttele=request.getParameter("uadttele");
				String tmail=request.getParameter("uadtmail");
				String tfield=request.getParameter("uadtfield");
				tfield = new String(tfield.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				String limit=request.getParameter("uadtlimit");
				int  tlimit=Integer.valueOf(limit);
				String tcno=sdao.queryCnoByCname(tcname);
				
				//System.out.println("genderֵ"+tgender);
				
				
				tdao.addTeacher(tid, tcno,"111111", tname, tgender, tbirth, tjob, tedu, tsub, ttype, ttele, tmail, tfield, tlimit);
				response.sendRedirect("Mainuadtutorin.jsp?k=1");
				break;
				
			case "updatetutor":
				u=udao.queryUniadminById(id);
				/*�õ�ʦ��Ϣ*/
				tid=request.getParameter("uadtid");
				tname=request.getParameter("uadtname");
				tname = new String(tname.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				tgender=request.getParameter("uadtgender");
				tgender = new String(tgender.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				tbirth=request.getParameter("uadtbirth");
				tedu=request.getParameter("uadtedu");
				tedu = new String(tedu.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				tjob=request.getParameter("uadtjob");
				tjob = new String(tjob.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				tsub=request.getParameter("uadtsub");
				tsub = new String(tsub.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				ttype=request.getParameter("uadttype");
				ttype = new String(ttype.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				tcname=request.getParameter("uadtcname");
				tcname = new String(tcname.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				ttele=request.getParameter("uadttele");
				tmail=request.getParameter("uadtmail");
				tfield=request.getParameter("uadtfield");
				tfield = new String(tfield.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				limit=request.getParameter("uadtlimit");
				tlimit=Integer.valueOf(limit);
				tcno=sdao.queryCnoByCname(tcname);
				
				//System.out.println("genderֵ"+tgender);
				
				
				tdao.exupTeacher(tid, tname, tgender, tbirth, tjob, tedu, tsub, ttype, ttele, tmail, tfield, tlimit);
				request.getSession().setAttribute("t", tdao.queryTeacherById(tid));
				response.sendRedirect("Uadseetutorinfo.jsp?k=1");
				
				break;
				
				
			case "selectshowtutor"://��ʦ��Ϣά����������

				request.getSession().setAttribute("tlist", tdao.queryAllteacher());

				response.sendRedirect("Mainsearchtutor.jsp");
				
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
					response.sendRedirect("Mainsearchtutor.jsp");
				}else {
					//System.out.println("��ֵ");
					tli=tdao.queryTargettutor(seartext);
					Teacher a=tli.get(0);
					//System.out.println(a.getTname());
					request.getSession().setAttribute("tlist", tli);
					response.sendRedirect("Mainsearchtutor.jsp");
				}
				
				break;
				
			case "viewtutor"://�鿴��ʦ��ϸ��Ϣҳ��
				tid=request.getParameter("tid");
				cname=tdao.queryCnameById(tid);
				//System.out.println("cname:"+cname);
				//System.out.println("��ʦ���"+tid);
				request.getSession().setAttribute("cname", cname);
				request.getSession().setAttribute("t", tdao.queryTeacherById(tid));
				response.sendRedirect("Uadseetutorinfo.jsp?");
				break;
				
			case "deletutor"://ɾ����ʦ
				tid=request.getParameter("tid");
				int p=tdao.deleteTeacherById(tid);
				//System.out.println("p��ֵ:"+p);
				response.sendRedirect("Uniadmin.action?id="+id+"&opttype=selectshowtutor");//ɾ��Ӧ�ò���Ҫ������˵���ɲ��ɹ���
				break;
				
				
				/*ѧ����Ϣά��*/

			case "graduin"://ѧ����Ϣ¼�����
				u=udao.queryUniadminById(id);
				response.sendRedirect("Mainuadgraduin.jsp");
				break;
				
			case "insertgrad"://¼��ѧ����Ϣ
				u=udao.queryUniadminById(id);
				/*��ѧ����Ϣ*/
				sid=request.getParameter("uadsid");//id
				String sname=request.getParameter("uadsname");//name
				sname = new String(sname.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				String sgender=request.getParameter("uadsgender");//gender
				sgender = new String(sgender.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				String spro=request.getParameter("uadspro");//profession
				spro = new String(spro.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���

				
				String scname=request.getParameter("uadscname");//cname
				scname = new String(scname.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				
				String score=request.getParameter("uadscore");
				int  sscore=Integer.valueOf(score);
				String scno=sdao.queryCnoByCname(scname);//cno
				
				
				
				sdao.addStudent(sid, scno, "123123", sname, spro, sgender, score);
				sdao.addStuchoose(sid);
				response.sendRedirect("Mainuadgraduin.jsp?k=1");
				break;
				
			case "updategrad":
				u=udao.queryUniadminById(id);
				/*��ѧ����Ϣ*/
				sid=request.getParameter("uadsid");//id
				sname=request.getParameter("uadsname");//name
				sname = new String(sname.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				sgender=request.getParameter("uadsgender");//gender
				sgender = new String(sgender.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				spro=request.getParameter("uadspro");//profession
				spro = new String(spro.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���

				
				scname=request.getParameter("uadscname");//cname
				scname = new String(scname.getBytes("ISO-8859-1"),"UTF-8"); //ȡ���ķ���
				
				score=request.getParameter("uadscore");
				sscore=Integer.valueOf(score);
				scno=sdao.queryCnoByCname(scname);//cno
				
				//System.out.println("genderֵ"+tgender);
				
				
				sdao.exupStudent(sid,sname, spro, sgender, score);
				request.getSession().setAttribute("s", sdao.queryStudentById(sid));
				response.sendRedirect("Uadseegradinfo.jsp?k=1");
				
				break;
				
				
				
			case "selectshowgrad"://ѧ����Ϣά����������

				request.getSession().setAttribute("slist", sdao.queryAllStudent());

				response.sendRedirect("Mainsearchgrad.jsp");
				
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
					response.sendRedirect("Mainsearchgrad.jsp");
				}else {
					//System.out.println("��ֵ");
					//System.out.println(a.getTname());
					request.getSession().setAttribute("slist", sli);
					response.sendRedirect("Mainsearchgrad.jsp");
				}
				
				break;
				
			case "viewgrad"://�鿴ѧ����ϸ��Ϣҳ��
				sid=request.getParameter("sid");
				cname=sdao.queryCnameById(sid);
				//System.out.println("cname:"+cname);
				//System.out.println("��ʦ���"+tid);
				request.getSession().setAttribute("cname", cname);
				request.getSession().setAttribute("s", sdao.queryStudentById(sid));
				response.sendRedirect("Uadseegradinfo.jsp?");
				break;
				
			case "delegrad"://ɾ��ѧ��
				sid=request.getParameter("sid");
				int dstu=sdao.deleteStudentById(sid);
				//System.out.println("p��ֵ:"+p);
				response.sendRedirect("Uniadmin.action?id="+id+"&opttype=selectshowgrad");//ɾ��Ӧ�ò���Ҫ������˵���ɲ��ɹ���
				break;
				
			case "setsystime"://����ϵͳ����ʱ��
				u=udao.queryUniadminById(id);
				response.sendRedirect("Uadsettime.jsp");
				break;
				
			case "updatetime"://����ϵͳ����ʱ��
				String stime=request.getParameter("starttime");
				
				String etime=request.getParameter("endtime");
				//System.out.println(stime);
				int res=stime.compareTo(etime);
				System.out.println(res);
		        if(res>0||res==0)
		        	{response.sendRedirect("Uadsettime.jsp?id="+id+"&k=0");}
		        else {
				/* ����ʱ���ԭ������
				 * daoԭʱ����0
				 * dao��ʱ����1
				 * */
		        	udao.closetime();
					udao.updatetime(stime, etime);
					response.sendRedirect("Uadsettime.jsp?id="+id+"&k=1");
		        }
				
				break;
				
			case "selectcoadmin"://����Ժ����
				/*Ժ�������+��ӵ�ʦ*/
				u=udao.queryUniadminById(id);
				request.getSession().setAttribute("clist", cdao.queryAllcoadmin());
				response.sendRedirect("Mainuadsetcol.jsp");
				break;
				
				
			case "uadreuptutor"://���Ժ����-��ѯ��ʦ����
				u=udao.queryUniadminById(id);
				request.getSession().setAttribute("tlist", tdao.queryAllteacher());
				
				response.sendRedirect("Mainuadsearchcoltutor.jsp");
				
				break;
				
			case "addcol"://���Ժ����(ִ��)
				u=udao.queryUniadminById(id);
				tid=request.getParameter("tid");
				
				cdao.addColAdminByTeacher(tdao.queryTeacherById(tid));
				
				response.sendRedirect("Uniadmin.action?id="+id+"&opttype=selectcoadmin");
				
				break;	
				
			case "delecol"://ɾ��Ժ����(ִ��)
				u=udao.queryUniadminById(id);
				tid=request.getParameter("cid");
				
				cdao.deleteColAdminById(tid);
				
				response.sendRedirect("Uniadmin.action?id="+id+"&opttype=selectcoadmin");
				
				break;
				
			case "viewcol"://�鿴Ժ����(ִ��)
				u=udao.queryUniadminById(id);
				tid=request.getParameter("cid");
				cname=tdao.queryCnameById(tid);
				//System.out.println("cname:"+cname);
				//System.out.println("��ʦ���"+tid);
				request.getSession().setAttribute("cname", cname);
				request.getSession().setAttribute("t", tdao.queryTeacherById(tid));
				response.sendRedirect("Uadseecoltutorinfo.jsp?");
				break;
				
				
			case "changepwd"://�޸�����ҳ��
				u=udao.queryUniadminById(id);
				request.getSession().setAttribute("uadmin", u);
				response.sendRedirect("Mainuadminpwd.jsp");
				break;
				
			case "dochangepwd"://�����޸�����
				int pd=0;//
				u=udao.queryUniadminById(id);//�õ�����Ա����
				String pawd=request.getParameter("pastpwd");//����ľ�����
				String npwd=request.getParameter("newpwd");//�����������
				String cnpwd=request.getParameter("connewpwd");//�����ȷ������
				
				//System.out.println("idֵ��"+id);
				//System.out.println("pawdֵ��"+pawd);
				//System.out.println("npwdֵ��"+npwd);
				//System.out.println("cnpwdֵ��"+cnpwd);
				
				if(pawd.equals(u.getUpwd()))
				{
					if(npwd.equals(cnpwd))
					{
						pd=1;
						udao.updateUadminpwd(id, npwd);
						u=udao.queryUniadminById(id);//�õ��µĹ���Ա����
						request.getSession().setAttribute("uadmin", u);
						response.sendRedirect("Mainuadminpwd.jsp?pd=1");
					}
					else //�����벻��
					{
						pd=-2;
						u=udao.queryUniadminById(id);//�õ��µĹ���Ա����
						request.getSession().setAttribute("uadmin", u);
						response.sendRedirect("Mainuadminpwd.jsp?pd=-2");
					}
				}
				else //�����벻��
				{
					pd=-1;
					u=udao.queryUniadminById(id);//�õ��µĹ���Ա����
					request.getSession().setAttribute("uadmin", u);
					response.sendRedirect("Mainuadminpwd.jsp?pd=-1");
				}
				
				break;	
				
			case "viewlogs"://�鿴��־
				u=udao.queryUniadminById(id);//�õ�����Ա����
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
