package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BanktellerDao;
import dao.LoginDao;
import entity.Bankteller;

@WebServlet("/Loginbstore.action")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String account=request.getParameter("account");
		String pwd=request.getParameter("pwd");
		String iden=request.getParameter("identity");
		
		Bankteller bteller=new Bankteller();
		BanktellerDao bdao=new BanktellerDao();
		LoginDao ldao=new LoginDao();
		
		/*时间*/
		Calendar calendar= Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
		String logtime=sdf.format(calendar.getTime());
		switch(iden) 
		{
			case "bankteller":
				bteller=bdao.queryBanktellerByAccount(account);
				if(bteller==null)
				{
					response.sendRedirect("LoginFrm.html?pwd=-2");
				}
				else if(bteller.getPassword().equals(pwd)==false)
				{
					response.sendRedirect("LoginFrm.html?pwd=-1");
				}else {
				
				//System.out.print(admin.getAid());
				ldao.addlogs(bteller.getEid(), "业务员", logtime,"登录");
				request.getSession().setAttribute("bteller", bteller);
				response.sendRedirect("Main.jsp");
				}
			break;
			
			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
