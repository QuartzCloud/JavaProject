package servelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// 获取login.jsp页面的内容
		String uname = request.getParameter("uname");
		String password = request.getParameter("password");
		System.out.println(uname);
		System.out.println(password);
		UserDao udi = new UserDao();
		try {
			// 获取对应的
			String sql = "select  password  from `User` where uname='" + uname + "'";
			ResultSet rs = null;
			rs = udi.getResultSet(sql);
			while (rs.next()) {
				String upwd = rs.getNString("password");
				System.out.println(upwd);
				System.out.println("OK");
				if (upwd.equals(password)) {
					request.getSession().setAttribute("uname", uname);
					response.sendRedirect("index.jsp");
				} else {
					PrintWriter out = response.getWriter();
					out.println("<script>alert('wrong2')</script>");
					response.sendRedirect("login.jsp");
				}
			}

		} catch (SQLException e) {/* 优化catch后面的操作 */
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.println("<script>alert('wrong2')</script>");
			response.sendRedirect("login.jsp");
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
