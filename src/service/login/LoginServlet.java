package service.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.LoginDao;
import bean.AccountBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		String user_id = (String) request.getParameter("id");
		String password = (String) request.getParameter("pWord");
		String identity = (String) request.getParameter("t1");
		System.out.println(user_id + password + identity);
		LoginDao dao = new LoginDao();
		AccountBean account = dao.selectAccount(user_id, password);
		if (password != null && password != "" && user_id != null && user_id != "") {
			if (account != null) {
				if (account.getIdentity().equals(identity)) {
					if (identity.equals("M")) 
						response.sendRedirect("Mindex.html");
					else {
						request.setAttribute("id", user_id);
						if (identity.equals("S"))
							request.getRequestDispatcher("/Sindex.jsp").forward(request, response);
						else if (identity.equals("I")) 
							request.getRequestDispatcher("/Iindex.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("msg", "权限不足");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("msg", "用户名或密码错误");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("msg", "用户名或密码不能为空");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
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
