package servelet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.Goods;
import dao.GoodsDao;

/**
 * Servlet implementation class GoodsList
 */
@WebServlet("/GoodsList")
public class GoodsList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GoodsList() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		GoodsDao goodsDao = new GoodsDao();
		try {
			ArrayList<Goods> list = goodsDao.selectGoods();
			request.getSession().setAttribute("GoodsList", list);
			response.setContentType("text/html;  charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset='UTF-8'>");
			out.println("<title>商品</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<a href='ShopCart?type=select'>购物车</a>");
			out.println("<table border='1'>");
			out.println("<tr>");
			out.println("<td>序号</td>");
			out.println("<td>商品名称</td>");
			out.println("<td>商品价格</td>");
			out.println("<td>库存数量</td>");
			out.println("<td>操作</td>");
			out.println("</tr>");
			for (int i = 0; i < list.size(); i++) {
				out.println("<tr>");
				out.println("<td>" + (i+1) + "</td>");
				out.println("<td>" + list.get(i).getGname() + "</td>");
				out.println("<td>" + list.get(i).getGprice() + "</td>");
				out.println("<td>" + list.get(i).getGnumber() + "</td>");
				out.println("<td><a href='ShopCart?type=add&gname=" + list.get(i).getGname() + "&gprice=" + list.get(i).getGprice() + "'>添加</a></td>");
				out.println("</tr>");
				out.println("</tr>");
			}
			out.println("</body>");
			out.println("</html>");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
