package servelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ShopCartDao;

/**
 * Servlet implementation class ShopCart
 */
@WebServlet("/ShopCart")
public class ShopCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShopCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 设置字符集
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String opt = request.getParameter("type");
		String uname = (String) request.getSession().getAttribute("uname");
		// 购物车操作
		try {
			System.out.println("flag_try");
			if (opt.equals("add")) {
				// 商品添加
				String gname = request.getParameter("gname");
				Float gprice = Float.valueOf(request.getParameter("gprice"));
				ShopCartDao scd = new ShopCartDao();
				scd.insertCart(uname, gname, gprice);
			} else if (opt.equals("delete")) {
				// 商品删除
				String gname = request.getParameter("gname");
				ShopCartDao scd = new ShopCartDao();
				scd.deleteCart(gname);
			} else if (opt.equals("select")) {
				// 购物车查询
				System.out.println("flag_select");
				ShopCartDao scd = new ShopCartDao();
				ArrayList<bean.ShopCart> cartlist = scd.selectCart(uname);
				System.out.println(cartlist.size());
				request.getSession().setAttribute("ShopCartList", cartlist);
				// 编写出页面
				response.setContentType("text/html;  charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta charset='UTF-8'>");
				out.println("<title>商品</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<a href='GoodsList'>商品列表</a>");
				out.println("<table border='1'>");
				out.println("<tr>");
				out.println("<td>序号</td>");
				out.println("<td>商品名称</td>");
				out.println("<td>商品价格</td>");
				out.println("<td>购买数量</td>");
				out.println("<td>总价</td>");
				out.println("<td>操作</td>");
				out.println("</tr>");
				for (int i = 0; i < cartlist.size(); i++) {
					out.println("<tr>");
					out.println("<td>" + (i + 1) + "</td>");
					out.println("<td>" + cartlist.get(i).getGname() + "</td>");
					out.println("<td>" + cartlist.get(i).getGprice() + "</td>");
					out.println("<td>" + cartlist.get(i).getCnum() + "</td>");
					out.println("<td>" + cartlist.get(i).getCprice() + "</td>");
					out.println(
							"<td><a href='ShopCart?type=delete&gname=" + cartlist.get(i).getGname() + "'>删除</a></td>");
					out.println("</tr>");
					out.println("</tr>");
				}
				out.println("</body>");
				out.println("</html>");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("wrong_E");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
