package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import bean.User;
import util.DataBase;

public class UserDao extends DataBase {

	public void insertUser(String uname, String password) throws SQLException {
		// TODO Auto-generated method stub
		// insert into `user` values ('"+uname+"','"+password+"');
		String sql = "insert into `user` values ('" + uname + "','" + password + "');";
		this.executeDML(sql);
	}

	public User selectUser(String uname) throws SQLException {
		// TODO Auto-generated method stub
		// select password from `User` where uname='" + uname + "'
		String sql = "select  password  from `User` where uname='" + uname + "'";
		ResultSet rs = this.getResultSet(sql);
		User user = new User();
		try {
			while (rs.next()) {
				user.setPassword(rs.getString("password"));
				user.setUsername(uname);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}