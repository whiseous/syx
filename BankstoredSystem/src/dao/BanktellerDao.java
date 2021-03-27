package dao;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dbutil.SQLHelper;
import entity.Bankteller;
import entity.Benefitrecord;
import entity.Depositor;
import entity.Depositrecord;
import entity.NametoBenefit;
import entity.NametoDeposit;
import entity.Record;
import entity.log;

public class BanktellerDao {

	/*取业务员账号*/
	public Bankteller queryBanktellerById(String eid) {
		Bankteller bteller = null;
		try {
			String sql = "select * from bankteller where eid=?";
			ResultSet rs = SQLHelper.executeQuery(sql,eid);
			if(rs!=null && rs.next()) {
				bteller=new Bankteller();
				bteller.setEid(rs.getString(1));
				bteller.setEmployeename(rs.getString(2));
				bteller.setAccount(rs.getString(3));
				bteller.setPassword(rs.getString(4));
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return bteller;
	}
	
	public Bankteller queryBanktellerByAccount(String account) {
		Bankteller bteller = null;
		try {
			String sql = "select * from bankteller where account=?";
			ResultSet rs = SQLHelper.executeQuery(sql,account);
			if(rs!=null && rs.next()) {
				bteller=new Bankteller();
				bteller.setEid(rs.getString(1));
				bteller.setEmployeename(rs.getString(2));
				bteller.setAccount(rs.getString(3));
				bteller.setPassword(rs.getString(4));
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return bteller;
	}
	/*看日志*/
	public List<log> queryAllusers(){
		List<log> ulist = new ArrayList<log>();
		String sql = "select * from syslogs order by logtime desc";
		ResultSet rs = SQLHelper.executeQuery(sql);
		try {
			while (rs.next()) {
				log user = new log();
				user.setId(rs.getString(1));
				user.setIden(rs.getString(2));
				user.setTime(rs.getString(3));
				user.setOpty(rs.getString(4));
				ulist.add(user);
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ulist;
	}
	
	/*新建储户账户*/
	public int adddepositor(String username, String address) {
		String sql = "insert into depositor(username,address) values(?,?)";
		return SQLHelper.executeUpdate(sql,username,address);
	}
	/*存款*/
	public int adddepositrecord(String eid, String uid,String type,String amount ,String rate,String pdate) {
		int active=1;
		double benefit=0;
		/*获取时间*/
		Calendar calendar= Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
		String date=sdf.format(calendar.getTime());
		addOptyRecord(eid,uid,type,amount,rate);
		String sql = "insert into depositrecord values(?,?,?,?,?,?,?,?,?)";
		return SQLHelper.executeUpdate(sql,eid,uid,type,date,rate,amount,active,benefit,pdate);
	}
	public int adddepositrecord(String eid, String uid,String amount , String rate) {//活期
		
		return adddepositrecord(eid, uid,"活期",amount ,rate,"0000-00-00");
	}
	public int adddepositrecord(String eid, String uid,String amount , String rate,String pdate) {//定期
		
		return adddepositrecord(eid, uid,"定期",amount ,rate,pdate);
	}
	public int adddepositrecord(String eid, String uid,String type,String date,String rate,String amount ,String pdate) {
		int active=1;
		double benefit=0;

		addOptyRecord(eid,uid,type,amount,rate);
		String sql = "insert into depositrecord values(?,?,?,?,?,?,?,?,?)";
		return SQLHelper.executeUpdate(sql,eid,uid,type,date,rate,amount,active,benefit,pdate);
	}
	/*查询储户*/
	public Depositor queryDepositorByName(String name) {
		Depositor user = null;
		try {
			String sql = "select * from depositor where username=?";
			ResultSet rs = SQLHelper.executeQuery(sql,name);
			if(rs!=null && rs.next()) {
				user=new Depositor();
				user.setUsername(rs.getString(1));
				user.setAddress(rs.getString(2));
				user.setUid(rs.getString(3));
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return user;
	}
	
	public Depositor queryDepositorById(String id) {
		Depositor user = null;
		try {
			String sql = "select * from depositor where uid=?";
			ResultSet rs = SQLHelper.executeQuery(sql,id);
			if(rs!=null && rs.next()) {
				user=new Depositor();
				user.setUsername(rs.getString(1));
				user.setAddress(rs.getString(2));
				user.setUid(rs.getString(3));
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return user;
	}
	
	
	public double getrate(String type) //暂不启用
	{
		if("活期".equals(type))
			return 0.003;
		else if("定期".equals(type))
			return 0.008;
		else return 0;
	}
	/*查存款详细（单个）*/
	public Depositrecord queryDerecord(String uid,String eid,String utime) {
		Depositrecord de = new Depositrecord();
		String sql = "select * from  depositrecord where uid=? and eid=? and depositdate=?";
		ResultSet rs = SQLHelper.executeQuery(sql,uid,eid,utime);
		try {
			while (rs.next()) {
				
				de.setEid(rs.getString(1));
				de.setUid(rs.getString(2));
				de.setDepositype(rs.getString(3));
				de.setDepositdate(rs.getString(4));
				de.setRate(rs.getDouble(5));
				de.setAmount(rs.getDouble(6));
				de.setActive(rs.getInt(7));
				de.setBenefit(rs.getDouble(8));
				de.setProdate(rs.getString(9));
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return de;
	}
	
	/*整合人与存款记录*/
	public List<NametoDeposit> joinUsertoDerecord(List<Depositrecord> dlist) {
		List<NametoDeposit> nlist = new ArrayList<NametoDeposit>();
		
		for(Depositrecord de:dlist)
		{
			NametoDeposit ntd=new NametoDeposit();
			ntd.setUser(queryDepositorById(de.getUid()));
			ntd.setDrecord(de);
			nlist.add(ntd);
		}
		

		return nlist;
	}
	/*整合人与取款记录*/
	public List<NametoBenefit> joinUsertoBerecord(List<Benefitrecord> blist) {
		List<NametoBenefit> nblist = new ArrayList<NametoBenefit>();
		
		for(Benefitrecord be:blist)
		{
			NametoBenefit ntb=new NametoBenefit();
			ntb.setUser(queryDepositorById(be.getUid()));
			ntb.setBrecord(be);
			nblist.add(ntb);
		}
		

		return nblist;
	}
	
	
	/*查询全部存款*/
	public List<Depositrecord> queryAllDerecord() {
		List<Depositrecord> dlist = new ArrayList<Depositrecord>();
		String sql = "select * from  depositrecord where active=?";
		ResultSet rs = SQLHelper.executeQuery(sql,1);
		try {
			while (rs.next()) {
				Depositrecord de = new Depositrecord();
				de.setEid(rs.getString(1));
				de.setUid(rs.getString(2));
				de.setDepositype(rs.getString(3));
				de.setDepositdate(rs.getString(4));
				de.setRate(rs.getDouble(5));
				de.setAmount(rs.getDouble(6));
				de.setActive(rs.getInt(7));
				de.setBenefit(rs.getDouble(8));
				de.setProdate(rs.getString(9));
				dlist.add(de);
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return dlist;
	}
	/*查询存款记录(取款用)*/
	public List<Depositrecord> queryTargetDerecord(String text) {
		List<Depositrecord> dlist = new ArrayList<Depositrecord>();
		String target="%"+text+"%";
		//System.out.println("target"+target);
		/*学院名需要另外取值
		 * */
		String sql = "select * from  depositrecord INNER JOIN depositor ON depositrecord.uid=depositor.uid where eid like ? or username like ? or deposittype like ? or depositdate like ? or rate like ? or amount like ? and active=1 or benefit like ? or prodate like ?";
		ResultSet rs = SQLHelper.executeQuery(sql,target,target,target,target,target,target,target,target);
		try {
			while (rs.next()) {
				Depositrecord de = new Depositrecord();
				de.setEid(rs.getString(1));
				de.setUid(rs.getString(2));
				de.setDepositype(rs.getString(3));
				de.setDepositdate(rs.getString(4));
				de.setRate(rs.getDouble(5));
				de.setAmount(rs.getDouble(6));
				de.setActive(rs.getInt(7));
				de.setBenefit(rs.getDouble(8));
				de.setProdate(rs.getString(9));
				dlist.add(de);
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return dlist;
	}
	
	/*调取储户活期存款记录*/
	public Depositrecord queryderecordliveByUid(String id) {
		Depositrecord derecord = null;
		try {
			String sql = "select * from depositrecord where uid=? and where deposittype=?";
			ResultSet rs = SQLHelper.executeQuery(sql,id,"活期");
			if(rs!=null && rs.next()) {
				derecord=new Depositrecord();
				derecord.setEid(rs.getString(1));
				derecord.setUid(rs.getString(2));
				derecord.setDepositype(rs.getString(3));
				derecord.setDepositdate(rs.getString(4));
				derecord.setRate(rs.getDouble(5));
				derecord.setAmount(rs.getInt(6));
				derecord.setActive(rs.getInt(7));
				derecord.setBenefit(rs.getDouble(8));
				derecord.setProdate(rs.getString(9));
			}
			else 
			{
				SQLHelper.closeConnection();
				return derecord;
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return derecord;
	}
	
	/*调取储户定期存款记录*/
	public Depositrecord queryderecordsolidByUid(String id) {
		Depositrecord derecord = null;
		try {
			String sql = "select * from depositrecord where uid=? and where deposittype=?";
			ResultSet rs = SQLHelper.executeQuery(sql,id,"定期");
			if(rs!=null && rs.next()) {
				derecord=new Depositrecord();
				derecord.setEid(rs.getString(1));
				derecord.setUid(rs.getString(2));
				derecord.setDepositype(rs.getString(3));
				derecord.setDepositdate(rs.getString(4));
				derecord.setRate(rs.getDouble(5));
				derecord.setAmount(rs.getInt(6));
				derecord.setActive(rs.getInt(7));
				derecord.setBenefit(rs.getDouble(8));
				derecord.setProdate(rs.getString(9));
			}
			else 
			{
				SQLHelper.closeConnection();
				return derecord;
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return derecord;
	}
	/*取款操作（对存款进行加减）*/
	public int updatedepositrecord(String eid,String uid, String dedate,double withdraw) throws ParseException {
		int derecord_amount=0;
		double derecord_rate=0;
		double benefit=0;
		String pdate="";
		String type="";
//		System.out.println("dedate="+dedate);
		try {//获取当前存款信息
			String sql = "select * from depositrecord where uid=? and depositdate=? and active=1";
			ResultSet rs = SQLHelper.executeQuery(sql,uid,dedate);
			if(rs!=null && rs.next()) {//取必要的金额数与利率
				derecord_amount=rs.getInt(6);
				derecord_rate=rs.getDouble(5);
				pdate=rs.getString(9);
				type=rs.getString(3);
			}
			else 
			{
				SQLHelper.closeConnection();
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
//		System.out.println("derecord_amount="+derecord_amount);
//		System.out.println("type="+type);
		
		if(withdraw>derecord_amount)
			return -10;
		else {//取钱后数量更改
			/*
			 * 1.活期
			 * 2.定期完成前取
			 * 3.定期完成后取
			 * */

			/*获取时间*/
			Calendar calendar= Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
			String date=sdf.format(calendar.getTime());
			if("活期".equals(type))
			{
				benefit=getBenefitBylong(longOfTwoDate(dedate,date),derecord_rate,withdraw);
			}
			else if("定期".equals(type))
			{
				System.out.println("天数间隔："+longOfTwoDate(pdate,date));
				System.out.println("利息："+getBenefitBylong(longOfTwoDate(dedate,date),derecord_rate,withdraw));
				
				if(longOfTwoDate(pdate,date)<0)
				{
					benefit=getBenefitBylong(longOfTwoDate(dedate,date),derecord_rate,withdraw);
				}
				else 
				{
					benefit=getBenefitBylong(longOfTwoDate(dedate,pdate),derecord_rate,withdraw);
					benefit=benefit+getBenefitBylong(longOfTwoDate(pdate,date),derecord_rate/30*1.0000,withdraw);
				}
			}
			
			
			//System.out.println("benefit1="+benefit);
			
			
			if(withdraw==derecord_amount)
			{
				/* 1.置0
				 * 2.记录利息单
				 * 		3.记录操作记录
				 * */
				addbenefitrecord(eid,uid,withdraw+"",benefit+"",derecord_rate+"",date);
				String sql = "update depositrecord set active=? where uid=? and depositdate=?";
				return SQLHelper.executeUpdate(sql,0,uid,dedate);
			}
			else {
				//benefit=getBenefitBylong(longOfTwoDate(dedate,pdate),derecord_rate);

			addbenefitrecord(eid,uid,withdraw+"",benefit+"",derecord_rate+"",date);
			String sql = "update depositrecord set amount=? where uid=? and depositdate=?";
			
			return SQLHelper.executeUpdate(sql,derecord_amount-withdraw,uid,dedate);
			}
		}
	}
	
	
	public int updatedepositrecord(String eid,String uid, String dedate,String withdate,double withdraw) throws ParseException {
		int derecord_amount=0;
		double derecord_rate=0;
		double benefit=0;
		String pdate="";
		String type="";
		
		
	//	System.out.println("benefit0="+benefit);
		
		
		try {//获取当前存款信息
			String sql = "select * from depositrecord where uid=? and depositdate=? and active=1";
			ResultSet rs = SQLHelper.executeQuery(sql,uid,dedate);
			if(rs!=null && rs.next()) {//取必要的金额数与利率
				derecord_amount=rs.getInt(6);
				derecord_rate=rs.getDouble(5);
				pdate=rs.getString(9);
				type=rs.getString(3);
			}
			else 
			{
				SQLHelper.closeConnection();
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
//		System.out.println("derecord_amount="+derecord_amount);
//		System.out.println("type="+type);
		if(withdraw>derecord_amount)
			return -10;
		else {//取钱后数量更改
			/*
			 * 1.活期
			 * 2.定期完成前取
			 * 3.定期完成后取
			 * */

			if("活期".equals(type))
			{
				benefit=getBenefitBylong(longOfTwoDate(dedate,withdate),derecord_rate,withdraw);
//				System.out.println("天数"+longOfTwoDate(dedate,withdate));
//				System.out.println("利率"+derecord_rate);
//				System.out.println("here活期");
			}
			else if("定期".equals(type))
			{
				
				if(longOfTwoDate(pdate,withdate)<0)
				{
					benefit=getBenefitBylong(longOfTwoDate(dedate,withdate),derecord_rate/30,withdraw);
					
					
//					System.out.println("here定期期限内");
				}
				else 
				{
					benefit=getBenefitBylong(longOfTwoDate(dedate,pdate),derecord_rate,withdraw);
					benefit=benefit+getBenefitBylong(longOfTwoDate(pdate,withdate),derecord_rate/30,withdraw);
					
					
//					System.out.println("here定期期限后");
				}
			}
			
			
//			System.out.println("benefit1="+benefit);
			
			
			
			if(withdraw==derecord_amount)
			{
				/* 1.置0
				 * 2.记录利息单
				 * 		3.记录操作记录
				 * */
				addbenefitrecord(eid,uid,withdraw+"",benefit+"",derecord_rate+"",withdate);
				String sql = "update depositrecord set active=? where uid=? and depositdate=?";
				return SQLHelper.executeUpdate(sql,0,uid,dedate);
			}
			else {
				//benefit=getBenefitBylong(longOfTwoDate(dedate,pdate),derecord_rate);

			addbenefitrecord(eid,uid,withdraw+"",benefit+"",derecord_rate+"",withdate);
			String sql = "update depositrecord set amount=? where uid=? and depositdate=?";
			
			return SQLHelper.executeUpdate(sql,derecord_amount-withdraw,uid,dedate);
			}
		}
		
		
	}
	
	
	
	
	/*记录利息单*/
	public int addbenefitrecord(String eid, String uid,String withdraw,String benefit,String rate,String withdate) {

		addOptyRecord(eid,uid,"取款",withdraw,rate);//添加操作记录
		String sql = "insert into benefitrecord values(?,?,?,?,?)";
		return SQLHelper.executeUpdate(sql,eid,uid,withdraw,withdate,benefit);
	}
	
	
	
	/*查看操作记录*/
	public Record queryrecordByUid(String id) {
		Record record = null;
		try {
			String sql = "select * from record where uid=?";
			ResultSet rs = SQLHelper.executeQuery(sql,id);
			if(rs!=null && rs.next()) {
				record=new Record();
				record.setEid(rs.getString(1));
				record.setUid(rs.getString(2));
				record.setOptiontype(rs.getString(3));
				record.setNum(rs.getDouble(4));
				record.setDate(rs.getString(5));
				record.setRate(rs.getDouble(6));
			}
			else 
			{
				SQLHelper.closeConnection();
				return record;
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return record;
	}
	/*添加操作记录*/
	public int addOptyRecord(String eid, String uid,String type,String amount ,String rate) {
		/*获取时间*/
		Calendar calendar= Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
		String date=sdf.format(calendar.getTime());
		String sql = "insert into record values(?,?,?,?,?,?)";
		return SQLHelper.executeUpdate(sql,eid,uid,type,amount,date,rate);
	}
	
	
	/*查询储户取款记录（仅查看）*/
	public Benefitrecord queryberecordByUid(String id,String date) {
		Benefitrecord berecord = null;
		try {
			String sql = "select * from benefitrecord where uid=? and withdrawdate=?";
			ResultSet rs = SQLHelper.executeQuery(sql,id,date);
			if(rs!=null && rs.next()) {
				berecord=new Benefitrecord();
				berecord.setEid(rs.getString(1));
				berecord.setUid(rs.getString(2));
				berecord.setWithdraw(rs.getDouble(3));
				berecord.setWithdrawdate(rs.getString(4));
				berecord.setWdbenefit(rs.getDouble(5));
			}
			else 
			{
				SQLHelper.closeConnection();
				return berecord;
			}
			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return berecord;
	}
	
	
	public List<Benefitrecord> queryTargetBerecord(String target) {
		List<Benefitrecord> blist = new ArrayList<Benefitrecord>();
		String tar="%"+target+"%";
		try {
			String sql = "select * from  benefitrecord INNER JOIN depositor ON benefitrecord.uid=depositor.uid where eid like ? or username like ? or withdrawdate like ? or withdraw like ? or wdbenefit like ?";
			ResultSet rs = SQLHelper.executeQuery(sql,tar,tar,tar,tar,tar);
			while(rs.next()) {
				Benefitrecord berecord=new Benefitrecord();
				berecord.setEid(rs.getString(1));
				berecord.setUid(rs.getString(2));
				berecord.setWithdraw(rs.getDouble(3));
				berecord.setWithdrawdate(rs.getString(4));
				berecord.setWdbenefit(rs.getDouble(5));
				blist.add(berecord);
			}

			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return blist;
	}
	
	public List<Benefitrecord> queryAllBerecord() {
		List<Benefitrecord> blist = new ArrayList<Benefitrecord>();
		String sql = "select * from  benefitrecord";
		ResultSet rs = SQLHelper.executeQuery(sql);
		try {
			
			while(rs.next()) {
				Benefitrecord berecord=new Benefitrecord();
				berecord.setEid(rs.getString(1));
				berecord.setUid(rs.getString(2));
				berecord.setWithdraw(rs.getDouble(3));
				berecord.setWithdrawdate(rs.getString(4));
				berecord.setWdbenefit(rs.getDouble(5));
				blist.add(berecord);
			}

			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return blist;
	}
	
	/*查询操作记录*/
	public List<Record> queryAllRecord() {
		List<Record> rlist = new ArrayList<Record>();
		String sql = "select * from record";
		ResultSet rs = SQLHelper.executeQuery(sql);
		try {
			
			while(rs.next()) {
				Record rd=new Record();
				rd.setEid(rs.getString(1));
				rd.setUid(rs.getString(2));
				rd.setOptiontype(rs.getString(3));
				rd.setNum(rs.getDouble(4));
				rd.setDate(rs.getString(5));
				rd.setRate(rs.getDouble(6));
				rlist.add(rd);
			}

			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rlist;
	}
	public List<Record> queryTargetRecord(String target) {
		List<Record> rlist = new ArrayList<Record>();
		String tar="%"+target+"%";
		try {
			String sql = "select * from  record INNER JOIN depositor ON record.uid=depositor.uid where eid like ? or username like ? or optiontype like ? or num like ? or date like ? or rate like ?";
			ResultSet rs = SQLHelper.executeQuery(sql,tar,tar,tar,tar,tar,tar);
			while(rs.next()) {
				Record rd=new Record();
				rd.setEid(rs.getString(1));
				rd.setUid(rs.getString(2));
				rd.setOptiontype(rs.getString(3));
				rd.setNum(rs.getDouble(4));
				rd.setDate(rs.getString(5));
				rd.setRate(rs.getDouble(6));
				rlist.add(rd);
			}

			SQLHelper.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rlist;
	}
	
	/*判断是数字*/
	public int isNumeric(String text)
	{
		for(int i=0;i<text.length();i++)
		{
			if(!Character.isDigit(text.charAt(i)))
			{
				return 0;
			}
		}
		return 1;
	}
	
	/*更新密码*/
	public int updateBankteller(String eid, String pwd) {
		String sql = "update bankteller set password=? where eid=?";
		return SQLHelper.executeUpdate(sql,pwd,eid);
	}
	
	public static int  longOfTwoDate(String first, String second) throws ParseException {
        //格式化时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date date2 = null;
        //使用SimpleDateFormat的parse()方法生成Date
        date1 = format.parse(first);
        date2 = format.parse(second);

        //两日期相减，所得毫秒数 除以1000得秒 除以3600得小时 除24得天数得到两个日期的间隔
        int day = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        //返回天数
        return day;
    }
	public double getBenefitBylong(int days,double rate,double withdraw)
	{
		double benefit;
		benefit=withdraw*days*rate*1.0000/1000;
		return benefit;
	}
	
}
