package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
	static String driver = "com.mysql.jdbc.Driver";
	static String ur1 = "jdbc:mysql://localhost:3306/jsp";
	static String user = "root";
	static String pwd = "123456";
	public static Connection conn = null;
	public static Statement sm = null;
	ResultSet rs = null;
	static {
		try {
			Class.forName(driver);
			System.out.println("OK");
			conn = DriverManager.getConnection(ur1, user, pwd);
			System.out.println("OK");
			sm = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("false");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("false");
		}
	}

	public ResultSet getResultSet(String sql) {
		try {
			rs = sm.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void executeDML(String sql) throws SQLException {
		sm.executeUpdate(sql);
	}
	
	public void closeDb() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (sm != null) {
				sm.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}