# JSP实训项目设计报告—MVC简易购物商城

[TOC]

## 设计目的

《JSP程序设计实战》是计算机专业教学计划中十分重要的实践性教学环节，本课程使学生通过《JSP程序设计实战》课程的学习，具备一定的基于Java语言开发Web应用程序的能力，开设的实践性必修课程。通过课程实训教学环节，完成具有一定难度、一定规模的任务，强化、贯通所学知识，进一步巩固和提高编写程序的能力，并体验开发应用程序的设计过程，从而提高学生动手能力，达到理论与实践互相渗透、有机结合的目的。

## 设计要求

掌握《JSP程序设计实战》课程中的编程方法、技巧和程序的调试步骤，，并对软件项目开发流程有初步了解。

## 设计思路

### 系统要求

系统由单点登录、商品展示和购物车共三部分功能模块组成。各模块功能描述如下：

#### 单点登录模块

1.  登录验证：系统验证输入用户名和密码，经验证符合才能继续服务。
2. 系统每个模块均有登录验证，如未登录直接访问其他模块，则跳转至登录模块。

#### 商品展示模块

1. 将商品列表展现给用户，包括商品名称、数量、单价等信息。
2. 支持从商品列表选择商品至购物车。
3. 支持从商品列表跳转至购物车。
4. 支持数据库功能。

#### 购物车展示模块

1. 从商品列表选择的商品应该出现在购物车列表里
2. 支持从购物车中删除商品
3. 支持从购物车跳转至商品列表

### 概要设计

系统按MVC概念进行设计，实现注意点分离的设计理念，也就是让专业的对象做专业的事情，View层负责视图相关的内容，Model层负责描述数据模型，Controller层负责总控，各自分工协作。

在商品购物平台中，应该会涉及到很多模型，有涉及商品的模型，有涉及用户的模型，也有涉及购物车的模型，也有涉及订单的模型，还有一大些关于仓储，服务等方面的模型。本项目是简易商品购物商城，从中抽象出来的对象模型主要是三种，分别是商品类（Goods类）、购物车类（ShopCart类）、用户类（User类）

#### Model层

模型层（Model）：指从现实世界中抽象出来的对象模型，是应用逻辑的反应；它封装了数据和对数据的操作，是实际进行数据处理的地方（模型层与数据库才有交互）

负责实现用户，商品等数据的结构设计并保存

#### View层

视图层（View）：是应用和用户之间的接口，它负责将应用显示给用户 和 显示模型的状态。

负责实现项目主页面、登录页面、商品列表和购物车的展现

#### Controller层

控制器（Controller）:控制器负责视图和模型之间的交互，控制对用户输入的响应、响应方式和流程；它主要负责两方面的动作，一是把用户的请求分发到相应的模型，二是吧模型的改变及时地反映到视图上。

负责信息流向控制，根据用户登录情况，负责控制主页面、登录页面、商品列表和购物车之间的切换

### 详细设计

#### Model层

进行Model层的编写的时候，一般将创建的所有JavaBean类放到一个专门的包里面，方便代码的分类和管理，为了方便查找，将包名命名为bean

创建Goods类，作为商品的基础类，有商品名称、价格和数量等属性，实现属性的设置、获取和初始化等功能

```java
package bean;

public class Goods {
	// 对应字段 gname
	private String gname;
	// 对应字段 gprice
	private int gprice;
	// 对应字段 gunmber
	private int gnumber;

	/**
	 * @return the gname
	 */
	public String getGname() {
		return gname;
	}

	/**
	 * @param gname the gname to set
	 */
	public void setGname(String gname) {
		this.gname = gname;
	}

	/**
	 * @return the gprice
	 */
	public int getGprice() {
		return gprice;
	}

	/**
	 * @param gprice the gprice to set
	 */
	public void setGprice(int gprice) {
		this.gprice = gprice;
	}

	/**
	 * @return the gnumber
	 */
	public int getGnumber() {
		return gnumber;
	}

	/**
	 * @param gnumber the gnumber to set
	 */
	public void setGnumber(int gnumber) {
		this.gnumber = gnumber;
	}

    //有参构造
	public Goods(String gname, int gprice, int gnumber) {
		super();
		this.gname = gname;
		this.gprice = gprice;
		this.gnumber = gnumber;
	}

    //无参构造
	public Goods() {
		super();
	}

}
```

创建ShopCart类，作为购物车模型的抽象，用于购物车的基础类，有购物车中商品的名称，单价，用户名，总价，购物车编号等属性，实现这些属性的设置，获取和初始化

```java
package bean;

public class ShopCart {

	// 对应字段 gname
	String gname;
	// 对应字段 gprice
	float gprice;
	// 对应字段 cnum
	int cnum;
	// 对应字段 cprice
	float cprice;
	// 对应字段 uname
	String uname;

	public ShopCart( String uname, String gname, float gprice,int cnum, float cprice) {
		super();
		this.gname = gname;
		this.gprice = gprice;
		this.cnum = cnum;
		this.cprice = cprice;
		this.uname = uname;
	}

	public ShopCart() {
		super();
	}

	/**
	 * @return the uname
	 */
	public String getUname() {
		return uname;
	}

	/**
	 * @param uname the uname to set
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}

	/**
	 * @return the gname
	 */
	public String getGname() {
		return gname;
	}

	/**
	 * @return the gprice
	 */
	public float getGprice() {
		return gprice;
	}

	/**
	 * @return the cnum
	 */
	public int getCnum() {
		return cnum;
	}

	/**
	 * @return the cprice
	 */
	public float getCprice() {
		return cprice;
	}

	/**
	 * @param gname the gname to set
	 */
	public void setGname(String gname) {
		this.gname = gname;
	}

	/**
	 * @param gprice the gprice to set
	 */
	public void setGprice(float gprice) {
		this.gprice = gprice;
	}

	/**
	 * @param cnum the cnum to set
	 */
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	/**
	 * @param cprice the cprice to set
	 */
	public void setCprice(float cprice) {
		this.cprice = cprice;
	}

}
```

创建User类，作为用户模型的抽象，用于用户的基础类，有用户中用户名，等属性，实现这些属性的设置，获取和初始化

```java
package bean;

public class User {
	// 对应字段 uname
	String uname;
	// 对应字段 password
	String password;

	public User(String uname, String password) {
		super();
		this.uname = uname;
		this.password = password;
	}

	public User() {
		super();
	}

	/**
	 * @return the uname
	 */
	public String getUsername() {
		return uname;
	}

	/**
	 * @param username the uname to set
	 */
	public void setUsername(String username) {
		this.uname = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
```

> 建立的三个类可以用快捷键进行创建，创建的时候不仅要创建set和get方法，还要创建有参构造和无参构造

然后根据创建的JavaBean类编写相应的数据库代码，为后面的数据库功能做基础

```sql
-- 创建数据库
create database jsp;
```

在数据库中根据Javabean创建数据表

```sql
-- 用户表
create table User
(
    `uname`    varchar(30) not null primary key comment '用户名',
    `password` varchar(30) not null comment '密码'
);
```

> 用户名和密码都不能为空，同时用户名字段要作为你主键

```sql
-- 购物车表
create table ShopCart
(
    `uname`  varchar(30) comment '用户名',
    `gname`  varchar(30) comment '商品名称',
    `gprice` float comment '商品单价',
    `cnum`   int comment '购买数量' not null,
    foreign key (uname) references User (uname)
);
```

> 购物车中的用户名字段应该跟用户表中的用户名字段对应，购物车中的商品名称字段和商品单价字段应该分别和商品列表中的商品名称字段和商品单价字段对应。这里简化了流程，没有进行对应，可以根据需要进行添加修改

```sql
-- 商品表
create table `Goods`
(
    `gname`   varchar(30) primary key comment '商品名',
    `gprice`  float comment '商品单价',
    `gnumber` int comment '商品库存数' not null
);
```

> 商品库存数可以根据后面的购物车的一系列操作进行增删改查的，因为1没有设计购物车之后的操作也就没有涉及多少相关的内容

在创建的数据库中添加一下初始记录

```sql
insert into `User` values ('zql', '1223456');
```

> 在User表中插入数据，分别1对应着用户名和密码

```sql
insert into `ShopCart` values ('zql', '三星  500GB SSD固态硬盘 SATA3.0接口', 440, 1);
```

> 在shopCart表中1插入数据

```sql
insert into goods
values ('林清玄启悟人生系列：愿你，归来仍是少年', 31.90, 996);
insert into goods
values ('平凡的世界：全三册（激励青年的不朽经典）', 103.40, 947);
insert into goods
values ('曾国藩全集（全六卷 绸面精装插盒珍藏版）', 280.50, 998);
insert into goods
values ('中外文化文学经典系列 红岩 导读与赏析', 31.90, 995);
insert into goods
values ('古琴 老杉木乐器伏羲式_七弦琴 ', 3628.90, 19);
insert into goods
values ('专业演奏级乐器洞箫_8孔正手G调', 603.90, 97);
insert into goods
values ('三星  500GB SSD固态硬盘 SATA3.0接口 ', 440.00, 498);
```

> 在goods表中插入数据

到现在为止，我们已经完成了模型的构建，还要完成业务的处理。在实际业务处理过程中，往往需要进行多次数据库的访问。这些访问性质往往是相同的，采用Dao层可以将对数据库访问进行封装，避免经行重复性数据库访问开发操作，同时降低维护的成本。 

在此之前我们可以创建数据库连接池，减少每次数据库创建连接时造成的资源的较大消耗。创建数据库链接的时候可以单独放到一个文件夹里，方便分类管理

```java
package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
	static String driver = "com.mysql.jdbc.Driver";
	static String ur1 = "jdbc:mysql://localhost:3306/jsp";
	static String user = "root";//用户名
	static String pwd = "123456";//密码
	public static Connection conn = null;
	public static Statement sm = null;
	ResultSet rs = null;
	static {//静态代码块，只执行一次
		try {
			Class.forName(driver);
			System.out.println("OK");
            //连接数据库
			conn = DriverManager.getConnection(ur1, user, pwd);
			System.out.println("OK");
			sm = conn.createStatement();
		} catch (ClassNotFoundException e) {//捕捉ClassNotFoundException异常
			e.printStackTrace();
			System.out.println("false");
		} catch (SQLException e) {//捕捉SQLException异常
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
```

> 在DataBase类中，静态代码块主要是进行数据库的链接，只执行一次。getResultSet方法主要是是获取SQL语言执行后的结果，CloseDb方法主要用于关闭数据库。executeDML方法主要用于执行SQL语言，不要要返回结果

接着编写Dao层，Dao层一般分为Dao接口和Dao实现，因为本项目比较简单，直接编写Dao实现

根据简易购物商场的需求进行分析，GoodsDao中要用到插入和查找的功能，插入的功能是将形参作为条件，插入到数据库中，因此结果不需要返回，直接运行就可以了。查找的功能中需要将结果返回，并且因为返回的结果是一条条记录，所以可以将结果存放到一个ArrayList中，最后直接返回一个ArrayList作为结果

```java
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
			while (rs.next()) {//判断数据表中是否还有记录
				Goods goods = new Goods();//根据bean中的Goods类创建对象
				goods.setGname(rs.getString("gname"));//将gname对应的内容存入到goods对象中的Gname中
				goods.setGnumber(rs.getInt("gnumber"));
				goods.setGprice(rs.getInt("gprice"));
				list.add(goods);//将创建的对象存到一个集合之中，后面的操作只需要堆读取list集合就可以了，不用再次调用数据库
			}
		} catch (SQLException e) {//捕获SQLException异常
			e.printStackTrace();
		}
		return list;
	}
}
```

根据简易购物商场的需求进行分析，ShopCartDao中要用到插入，删除和查找的功能，插入的功能是将形参作为条件，插入到数据库中，因此结果不需要返回，直接运行就可以了。删除的功能也是因为直接根据参数进行查找，查找到符合的记录后进行删除，不需要再返回任何结果。查找的功能中需要将结果返回，并且因为返回的结果是一条条记录，所以可以将结果存放到一个ArrayList中，最后直接返回一个ArrayList作为结果

```java
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
		// delete from shopcart where gname='三星 500GB SSD固态硬盘 SATA3.0接口';
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
```

根据简易购物商场的需求进行分析，UserDao中要用到插入和查找的功能，插入的功能是将形参作为条件，插入到数据库中，因此结果不需要返回，直接运行就可以了。查找的功能中需要将结果返回，并且因为返回的结果是一条条记录，所以可以将结果存放到一个ArrayList中，最后直接返回一个ArrayList作为结果

```java
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
```

#### View层

view层主要是通过JSP、HTML等进行页面的展示的

##### 登录界面

在login.java界面中，把从login.jsp页面的内容传输Controller层中，由后台进行操作。

```jsp
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>login</title>
</head>
<body>
	<form action="Login" method="post">
		用户名:<input type="text" name="uname"> </br> 密码:<input type="text"
			name="password"></br>
		<input type="submit" value="提交">
	</form>
</body>
</html>
```

##### 系统主界面

主页面采用带导航的Frame框架的JSP页面实现，主框架页面包含三个分页面

![image-20230802153851495](E:\zhm\JSP项目总结\图片\主框架页面.jpg)

**功能描述：**

展现信息：主页面负责系统的主要展现功能，top窗口负责显示欢迎信息；left窗口负责导航，用户点击“商品列表”或“购物车”会在center窗口显示相应的信息；Center窗口负责显示商品列表和购物车的详细信息。

```jsp
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Tindex</title>
</head>
<script>
	alert('right')
</script>
<frameset rows="10%,90%">
	<frame src="top.jsp" name="frame_top">
	<frameset cols="25%,75%">
		<frame src="left.jsp" name="frame_left">
		<frame src="center.jsp" name="frame_center">
	</frameset>
</frameset>
</html>
```

```jsp
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
center
</body>
</html>
```

> center从页面主要是用来占位的，放着框架崩掉

```jsp
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>top</title>
</head>
<body>
	<%
	String uname = (String) session.getAttribute("uname");
	%>
	MVC商城欢迎你<%=uname%>
</body>
</html>
```

```jsp
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>left</title>
</head>
<body>
	<a href="GoodsList" target="frame_center">商品列表</a>
	<a href="ShopCart?type=select>" target="frame_center">购物车</a>
</body>
</html>
```

#### Controller层

本项目的Controller层的主要功能是对用户的登录进行进行操作，对购物车商品的操作和对商品列表的进行操作，还要负责页面之间的切换

对用户登录进行操作

```java
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
```

> 本段的主要逻辑是把用户在网页中输入的用户名和密码传到这里后获取用户名在数据库中对应的记录，然后把数据库中存在的密码和传来的密码进行比较，如果密码错误或者在查找用户对应的记录的产生数据库异常，直接弹出窗口提醒。

对购物车进行操作，并做到敬center页面跳转到购物车页面，显示相应的信息

```java
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
```

对商品列表进行操作，并做到敬center页面跳转到购物车页面，显示相应的信息

```java
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
				out.println("<td>" + (i + 1) + "</td>");
				out.println("<td>" + list.get(i).getGname() + "</td>");
				out.println("<td>" + list.get(i).getGprice() + "</td>");
				out.println("<td>" + list.get(i).getGnumber() + "</td>");
				out.println("<td><a href='ShopCart?type=add&gname=" + list.get(i).getGname() + "&gprice="
						+ list.get(i).getGprice() + "'>添加</a></td>");
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
```

### 系统运行效果

login.jsp展示

![image-20230802164430326](E:\zhm\JSP项目总结\图片\登录界面.jpg)

index.jsp/top.jsp/left.jsp展示

![image-20230802164705653](E:\zhm\JSP项目总结\图片\index.jpg)

商品列表展示

![image-20230802164813384](E:\zhm\JSP项目总结\图片\shoplist.jpg)

购物车列表展示

![image-20230802165743243](E:\zhm\JSP项目总结\图片\shopcart.jpg)

### 项目缺点

本项目继承了MVC结构的缺点，同时因为项目过去简单，有些功能做的还不是很完善，有些功能上的漏洞还是需要进行修补，但是这东西可以跑！！！可以跑！！！
