package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import bean.ShopCart;
import util.DataBase;

public class ShopCartDao extends DataBase {

	public void insertCart1(String uname, String gname) throws SQLException {
		// TODO Auto-generated method stub
		// insert into `ShopCart` values ('"+uname+"','"+gname+"',"+gprice+",1);
		String sql = "insert into `ShopCart` values ('" + uname + "','" + gname + "',1);";
		this.executeDML(sql);
	}

	public void insertCart(String uname, String gname, float gprice) throws SQLException {
		// TODO Auto-generated method stub
		// insert into `ShopCart` values ('"+uname+"','"+gname+"',"+gprice+",1);
		String sql = "insert into `ShopCart` values ('"+uname+"','"+gname+"',"+gprice+",1);";
		this.executeDML(sql);
	}

	public void deleteCart(String gname) throws SQLException {
		// TODO Auto-generated method stub
		// delete from shopcart where gname='ÈýÐÇ 500GB SSD¹ÌÌ¬Ó²ÅÌ SATA3.0½Ó¿Ú';
		String sql = "delete from shopcart where gname='" + gname + "';";
		this.executeDML(sql);
	}

	public ArrayList<ShopCart> selectCart(String uname) throws SQLException {
		// TODO Auto-generated method stub
		// select gname, gprice, cnum, gprice * ShopCart.cnum 'cprice' from ShopCart
		// where uname='" + uname + "';
		String sql = "select gname, gprice, cnum, gprice * ShopCart.cnum 'cprice' from ShopCart where uname='" + uname
				+ "';";
		ArrayList<ShopCart> list = new ArrayList<>();
		ResultSet rs = this.getResultSet(sql);
		try {
			while (rs.next()) {
				ShopCart cart = new ShopCart();
				cart.setGname(rs.getString("gname"));
				cart.setUname(uname);
				cart.setGprice(rs.getInt("gprice"));
				cart.setCnum(rs.getInt("cnum"));
				cart.setCprice(rs.getFloat("cprice"));
				list.add(cart);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}