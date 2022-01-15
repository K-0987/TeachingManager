package service.managerservlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GeneralDao;
import dao.ManagerDao;

/**
 * Servlet implementation class MMServlet
 */
@WebServlet("/MMServlet")
public class MMServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MMServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		ManagerDao mD = new ManagerDao();
		String type = request.getParameter("btn");
		System.out.println(request.getParameter("btn"));
		if(type.equals("edit")) {
			String id = request.getParameter("e_mid");
			String password = request.getParameter("e_mpword");
			System.out.println(password);
			try {
				if(password != null && password != "") {
					GeneralDao gD = new GeneralDao();
					gD.changePassword(id, password);
				}	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("msg", -1);
				request.getRequestDispatcher("/alluser.jsp").forward(request, response);
				return;
			}
		}
		if(type.equals("add")) {
			String user_id = request.getParameter("m_id");
			String pWord = request.getParameter("m_password");
			try {
				mD.AddManager(user_id, pWord);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				request.setAttribute("msg", -1);
				request.getRequestDispatcher("/alluser.jsp").forward(request, response);
				return;
			}
		}else {
			String ntype = type.substring(0, 1);
			if(ntype.equals("D")) {
				String u_id = type.substring(1);
				try {
					mD.DeleteUser(u_id);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("msg", -2);
					request.getRequestDispatcher("/alluser.jsp").forward(request, response);
					e.printStackTrace();
					return;
				}
			}				
		}
		request.setAttribute("msg", 0);
		request.getRequestDispatcher("/alluser.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
