package service.managerservlet.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ManagerDao;
import dao.GeneralDao;
import bean.StudentBean;

/**
 * Servlet implementation class MSServlet
 */
@WebServlet("/MSServlet")
public class MSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MSServlet() {
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
		request.setCharacterEncoding("UTF-8");
		ManagerDao mD = new ManagerDao();
		String type = request.getParameter("btn");
		System.out.println(request.getParameter("btn"));
		if (type.equals("edit")) {
			String epassword = request.getParameter("e_sspword");
			String password = request.getParameter("e_spword");
			String id = request.getParameter("e_sid");
			if (epassword != null) {
				GeneralDao gD = new GeneralDao();
				try {
					if (gD.changePasswordByUser(id, epassword, password) == 0)
						request.setAttribute("msg", -1);
					else
						request.setAttribute("msg", 0);
					request.getRequestDispatcher("/self.jsp").forward(request, response);
					return;
				} catch (SQLException e) {
					e.printStackTrace();
					request.setAttribute("msg", -1);
					request.getRequestDispatcher("/self.jsp").forward(request, response);
					return;
				}
			} else {
				String tableName = "STUDENT";
				String name = request.getParameter("e_sname");
				String sex = request.getParameter("e_ssex");
				String dept_name = request.getParameter("e_sdept");
				String tol_cred = request.getParameter("e_scred");
				System.out.println(id + name + sex + dept_name + password + tol_cred);
				try {
					if (name != null && name != "")
						mD.UpdateAny(tableName, id, "NAME", name);
					if (sex != null && sex != "")
						mD.UpdateAny(tableName, id, "SEX", sex);
					if (dept_name != null && dept_name != "")
						mD.UpdateAny(tableName, id, "DEPT_NAME", dept_name);
					if (tol_cred != null && tol_cred != "")
						mD.UpdateAny(tableName, id, "TOL_CRED", tol_cred);
					if (password != null && password != "") {
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
		} else if (type.equals("add")) {
			StudentBean s = new StudentBean();
			s.setName(request.getParameter("s_name"));
			s.setSex(request.getParameter("s_sex"));
			s.setDept_name(request.getParameter("s_dept_name"));
			try {
				mD.AddStudent(s);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				request.setAttribute("msg", -1);
				request.getRequestDispatcher("/alluser.jsp").forward(request, response);
				return;
			}
		} else {
			String ntype = type.substring(0, 1);
			if (ntype.equals("D")) {
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
			} else if (ntype.equals("C")) {
				String u_id = type.substring(1);
				request.setAttribute("s_id", u_id);
				request.getRequestDispatcher("/student_course.jsp").forward(request, response);
				return;
			}
		}
		request.setAttribute("msg", 0);
		request.getRequestDispatcher("/alluser.jsp").forward(request, response);

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
