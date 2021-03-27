package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BanktellerDao;
import entity.Bankteller;
import entity.Benefitrecord;
import entity.Depositor;
import entity.Depositrecord;
import entity.NametoBenefit;
import entity.Record;



@WebServlet("/bteller.action")
public class BanktellerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Bankteller bteller=new Bankteller();
       BanktellerDao bdao=new BanktellerDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BanktellerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String eid=request.getParameter("id");//获取id
		String opttype=request.getParameter("opttype");
		String dtype=request.getParameter("deposittype");
		String seartext="";
		Bankteller bteller= new Bankteller();
		BanktellerDao bdao=new BanktellerDao();
		Depositor user=null;
		String uid;
		String cname;
		String amount;
		String rate;
		String address;
		 
		switch(opttype) 
		{
			case "SERVICE_DEPOSIT_LIVE"://活期存款
				bteller=bdao.queryBanktellerById(eid);
				response.sendRedirect("LiveIn.jsp");
				break;
				
			case "SERVICE_DEPOSIT_SOLID"://定期存款
				bteller=bdao.queryBanktellerById(eid);
				response.sendRedirect("SolidIn.jsp");
				break;
				
			case "insertdeposit":
				bteller=bdao.queryBanktellerById(eid);
				//System.out.println("dtype="+dtype);
				if("live".equals(dtype)) {//活期
					/*得存款信息*/
					cname=request.getParameter("custname");
					cname = new String(cname.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
					amount=request.getParameter("amount");
					amount = new String(amount.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
					rate=request.getParameter("rate");
					/*查询储户得uid*/
					user=bdao.queryDepositorByName(cname);
					
					if(user==null)
					{
						response.sendRedirect("bteller.action?id="+eid+"&opttype=SERVICE_CREATE_CUSTOMER");
					}else {               
						/*获取时间*/
						Calendar calendar= Calendar.getInstance();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
						String date=sdf.format(calendar.getTime());
						uid=user.getUid();
						bdao.adddepositrecord(eid, uid, "活期", date, rate, amount, "0000-00-00");
						request.getSession().setAttribute("u", bdao.queryDepositorById(uid));
						request.getSession().setAttribute("d", bdao.queryDerecord(uid, eid, date));
						response.sendRedirect("DepositTextinfo.jsp?k=1");
					}
				}
				else if("solid".equals(dtype))//定期
				{
					/*得存款信息*/
					cname=request.getParameter("custname");
					cname = new String(cname.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
					amount=request.getParameter("amount");
					amount = new String(amount.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
					rate=request.getParameter("rate");
					String etime=request.getParameter("endtime");
					/*查询储户得uid*/
					user=bdao.queryDepositorByName(cname);
					
					if(user==null)
					{
						response.sendRedirect("bteller.action?id="+eid+"&opttype=SERVICE_CREATE_CUSTOMER");
					}else {   
						/*获取时间*/
						Calendar calendar= Calendar.getInstance();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
						String date=sdf.format(calendar.getTime());
						uid=user.getUid();
						bdao.adddepositrecord(eid, uid, "定期", date, rate, amount, etime);
						request.getSession().setAttribute("u", bdao.queryDepositorById(uid));
						request.getSession().setAttribute("d", bdao.queryDerecord(uid, eid, date));
						response.sendRedirect("DepositTextinfo.jsp?k=1");
					}
				}
				break;
		
			case "printdeposit":
				uid=request.getParameter("uid");
				String date=request.getParameter("ddate");
				
				request.getSession().setAttribute("u", bdao.queryDepositorById(uid));
				
				request.getSession().setAttribute("d", bdao.queryDerecord(uid, eid, date));
				response.sendRedirect("DepositTextinfo.jsp?");
				break;
				
			case "back_deposit":
				String detype=request.getParameter("detype");
				if("活期".equals(detype))
					response.sendRedirect("LiveIn.jsp");
				else if("定期".equals(detype))
					response.sendRedirect("SolidIn.jsp");
				break;
				
			case "SERVICE_CREATE_CUSTOMER"://创建新储户
				bteller=bdao.queryBanktellerById(eid);
				request.getSession().setAttribute("bteller", bteller);
				response.sendRedirect("CreateCustomer.jsp?id="+eid);
				break;
				
			case "insertcustomer":
				bteller=bdao.queryBanktellerById(eid);
				
				/*得存款信息*/
				cname=request.getParameter("custname");
				cname = new String(cname.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				address=request.getParameter("address");
				address = new String(address.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法

				bdao.adddepositor(cname, address);
				request.getSession().setAttribute("user", bdao.queryDepositorByName(cname));
				response.sendRedirect("CreateCustomer.jsp?id="+eid+"&k=5");

				break;
				
			
			case "SERVICE_WITHDRAW"://取钱(存款记录)
				request.getSession().setAttribute("slist", bdao.joinUsertoDerecord(bdao.queryAllDerecord()));
				//System.out.println("tlist"+bdao.queryAllDerecord().get(4).getProdate());
				response.sendRedirect("SearchMainDeposit.jsp");
				
				
				break;
			case "reupdeposit"://查存款（取钱前提）
				seartext=request.getParameter("searchtext");//取搜索框内容
				seartext = new String(seartext.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				
				List<Depositrecord> tli=null;
				tli=bdao.queryTargetDerecord(seartext);
				if(tli==null) 
				{
					//System.out.println("没有值");
					response.sendRedirect("SearchMainDeposit.jsp");
				}else {
					//System.out.println("有值");
					request.getSession().setAttribute("slist", bdao.joinUsertoDerecord(tli));
					response.sendRedirect("SearchMainDeposit.jsp");
				}
				break;
				
			case "viewdeposit"://(查看存款详细信息+取钱)
				uid=request.getParameter("uid");
				String ddate=request.getParameter("utime");
				
				request.getSession().setAttribute("u", bdao.queryDepositorById(uid));
				request.getSession().setAttribute("d", bdao.queryDerecord(uid, eid, ddate));
				response.sendRedirect("DepositRecordinfo.jsp?");
				break;
				
			case "withdraw"://(具体执行)
				uid=request.getParameter("uid");
				amount=request.getParameter("amount");
				String dedate=request.getParameter("ddate");
				String withdraw=request.getParameter("withdraw");
				
				Calendar calendar= Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
				date=sdf.format(calendar.getTime());
				
//				System.out.println("uid"+uid);
//				System.out.println("amount"+amount);
//				System.out.println("ddate"+dedate);
//				System.out.println("withdraw"+withdraw);
//				
				
				int flag=0;
				if(bdao.isNumeric(withdraw)==0)
					flag=-10;
				else {
					try {
						flag=bdao.updatedepositrecord(eid, uid, dedate, date, Double.valueOf(withdraw));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(flag==-10)
				{
					uid=request.getParameter("uid");
					ddate=request.getParameter("ddate");
					
					request.getSession().setAttribute("u", bdao.queryDepositorById(uid));
					request.getSession().setAttribute("d", bdao.queryDerecord(uid, eid, ddate));
					response.sendRedirect("DepositRecordinfo.jsp?k=-2");
				}else
				{
					request.getSession().setAttribute("u", bdao.queryDepositorById(uid));
					request.getSession().setAttribute("d", bdao.queryDerecord(uid, eid, dedate));
					request.getSession().setAttribute("b", bdao.queryberecordByUid(uid, date));
					response.sendRedirect("BenefitRecordinfo.jsp?");//利息单
				}
				break;
				
			case "printbenefit"://打印利息单
				uid=request.getParameter("uid");
				dedate=request.getParameter("ddate");
				date=request.getParameter("date");
				request.getSession().setAttribute("u", bdao.queryDepositorById(uid));
				request.getSession().setAttribute("d", bdao.queryDerecord(uid, eid, dedate));
				request.getSession().setAttribute("b", bdao.queryberecordByUid(uid, date));
				response.sendRedirect("BenefitRecordinfo.jsp?");//利息单
				break;
				
				
			case "viewbenefit":
				uid=request.getParameter("uid");
				date=request.getParameter("utime");
				
				request.getSession().setAttribute("u", bdao.queryDepositorById(uid));
				
				request.getSession().setAttribute("b", bdao.queryberecordByUid(uid, date));
				response.sendRedirect("WithdrawRecordinfo.jsp?");//取款单
				break;
			case "SERVICE_PRINT_WITHDRAW"://查询取钱记录
				request.getSession().setAttribute("slist", bdao.joinUsertoBerecord(bdao.queryAllBerecord()));
				response.sendRedirect("SearchMainBenefit.jsp");
				break;
				
			case "reupprintwithdraw":

				seartext=request.getParameter("searchtext");//取搜索框内容
				seartext = new String(seartext.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				List<Benefitrecord> bli=null;
				bli=bdao.queryTargetBerecord(seartext);
				if(bli==null) 
				{
					//System.out.println("没有值");
					response.sendRedirect("SearchMainBenefit.jsp");
				}else {
					//System.out.println("有值");
					request.getSession().setAttribute("slist", bdao.joinUsertoBerecord(bli));
					response.sendRedirect("SearchMainBenefit.jsp");
				}
				break;
				
			
//				
			case "SERVICE_PRINT_HISTORY"://打印储户操作历史
				request.getSession().setAttribute("slist", bdao.queryAllRecord());
				response.sendRedirect("SearchMainOpty.jsp");
				break;
				
			case "reupprintopty":

				seartext=request.getParameter("searchtext");//取搜索框内容
				seartext = new String(seartext.getBytes("ISO-8859-1"),"UTF-8"); //取中文方法
				List<Record> rli=null;
				rli=bdao.queryTargetRecord(seartext);
				if(rli==null) 
				{
					//System.out.println("没有值");
					response.sendRedirect("SearchMainOpty.jsp");
				}else {
					//System.out.println("有值");
					request.getSession().setAttribute("slist", rli);
					response.sendRedirect("SearchMainOpty.jsp");
				}
				break;
				
//				
			case "changepwd"://更改密码
				bteller=bdao.queryBanktellerById(eid);
				request.getSession().setAttribute("bteller", bteller);
				response.sendRedirect("Changepwd.jsp");
				break;
				
			case "dochangepwd"://执行更改密码
				int pd=0;//
				bteller=bdao.queryBanktellerById(eid);//
				String pawd=request.getParameter("pastpwd");//
				String npwd=request.getParameter("newpwd");//
				String cnpwd=request.getParameter("connewpwd");//
				

				
				if(pawd.equals(bteller.getPassword()))
				{
					if(npwd.equals(cnpwd))//全部正确
					{
						pd=1;
						bdao.updateBankteller(eid, npwd);
						bteller=bdao.queryBanktellerById(eid);
					}
					else //确认密码不相同
					{
						pd=-2;
						bteller=bdao.queryBanktellerById(eid);
						
					}
					
				}
				else //旧密码错误
				{
					pd=-1;
					bteller=bdao.queryBanktellerById(eid);
				}
				request.getSession().setAttribute("bteller", bteller);
				response.sendRedirect("Changepwd.jsp?pd="+pd);
				break;	
				
			case "viewlogs"://查日志
				bteller=bdao.queryBanktellerById(eid);
				List<entity.log> ulogs=null;
				ulogs=bdao.queryAllusers();
				request.getSession().setAttribute("ulogs", ulogs);
				response.sendRedirect("Viewlogs.jsp");
				break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
