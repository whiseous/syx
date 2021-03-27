package dbutil;
import java.sql.*;

public class SQLHelper {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url= "jdbc:mysql://localhost:3306/bankstore?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8&allowPublicKeyRetrieval=true";
	private static String dbuser = "siliexin", dbpwd = "wkw19990316";
	private static Connection conn=null;
	static {
		try {
			Class.forName(driver);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public static int executeUpdate(String sql,Object... params) {
		int r=0;
		try {
			Connection conn=DriverManager.getConnection(url,dbuser,dbpwd);
			PreparedStatement pcmd=conn.prepareStatement(sql);
			int count=1;
			for(Object param:params) {
				if(param instanceof String)
					pcmd.setString(count++, param.toString());
				else if(param instanceof Integer)
					pcmd.setInt(count++, new Integer(param.toString()));
				else if(param instanceof Double)
					pcmd.setDouble(count++, new Double(param.toString()));
				else
					pcmd.setObject(count++, param);
			}
			r=pcmd.executeUpdate();
			conn.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return r;
	}
	public static int executeUpdate(String sql) {
		int r=0;
		try {
			Connection conn=DriverManager.getConnection(url,dbuser,dbpwd);
			Statement cmd=conn.createStatement();
			r=cmd.executeUpdate(sql);
			conn.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return r;
	}
	/*ִ��Select���*/
	public static ResultSet executeQuery(String sql,Object...params) {
		ResultSet rs=null;
		try {
		    conn=DriverManager.getConnection(url,dbuser,dbpwd);
		    PreparedStatement pcmd=conn.prepareStatement(sql);
			int count=1;
			for(Object param:params) {
				if(param instanceof String)
					pcmd.setString(count++, param.toString());
				else if(param instanceof Integer)
					pcmd.setInt(count++, new Integer(param.toString()));
				else if(param instanceof Double)
					pcmd.setDouble(count++, new Double(param.toString()));
				else
					pcmd.setObject(count++, param);
			}
			rs=pcmd.executeQuery();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}
	public static Object executeSingleValue(String sql) {
		Object obj=null;
		ResultSet rs=null;
		try {
		    conn=DriverManager.getConnection(url,dbuser,dbpwd);
			Statement cmd=conn.createStatement();
			rs=cmd.executeQuery(sql);
			if(rs!=null && rs.next()) {
				obj=rs.getObject(1);
			}
			closeConnection();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return obj;
	}
	public static ResultSet executeQuery(String sql) {
		ResultSet rs=null;
		try {
		    conn=DriverManager.getConnection(url,dbuser,dbpwd);
			Statement cmd=conn.createStatement();
			rs=cmd.executeQuery(sql);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}
	public static void closeConnection() {
		try {
			if(conn!=null && !conn.isClosed())
				conn.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
