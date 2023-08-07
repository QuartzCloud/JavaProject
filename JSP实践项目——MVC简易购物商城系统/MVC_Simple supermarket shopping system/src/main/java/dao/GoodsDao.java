package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Goods;
import util.DataBase;

public class GoodsDao extends DataBase {
	public void insertGoods(String gname, float gprice, int gnumber) throws SQLException {
		// TODO Auto-generated method stub
		// insert into `goods` values ('"+name+"', "+gprice+", "+gnumber+");
		String sql = "insert into `goods` values ('" + gname + "', " + gprice + ", " + gnumber + ");";
		this.executeDML(sql);
	}

	public ArrayList<Goods> selectGoods() throws SQLException {
		// TODO Auto-generated method stub
		// select * from `Goods`
		String sql = "select * from `Goods`";
		ArrayList<Goods> list = new ArrayList<>();
		ResultSet rs = this.getResultSet(sql);
		try {
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setGname(rs.getString("gname"));
				goods.setGnumber(rs.getInt("gnumber"));
				goods.setGprice(rs.getInt("gprice"));
				list.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}