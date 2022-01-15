package service.managerservlet.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.InstructorBean;
import dao.GeneralDao;
import dao.ManagerDao;
import dao.InstructorDao;

/**
 * Servlet implementation class MIServlet
 */
@WebServlet("/MIServlet")
public class MIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MIServlet() {
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
			String epassword = request.getParameter("e_iipword");
			String password = request.getParameter("e_ipword");
			String id = request.getParameter("e_iid");
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
				String tableName = "INSTRUCTOR";
				String name = request.getParameter("e_iname");
				String sex = request.getParameter("e_isex");
				String dept_name = request.getParameter("e_idept");
				String position = request.getParameter("e_iposition");
				System.out.println(id + name + sex + dept_name + password + position);
				try {
					if (name != null && name != "")
						mD.UpdateAny(tableName, id, "NAME", name);
					if (sex != null && sex != "")
						mD.UpdateAny(tableName, id, "SEX", sex);
					if (dept_name != null && dept_name != "")
						mD.UpdateAny(tableName, id, "DEPT_NAME", dept_name);
					if (position != null && position != "")
						mD.UpdateAny(tableName, id, "POSITION", position);
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
			InstructorBean i = new InstructorBean();
			i.setName(request.getParameter("i_name"));
			i.setSex(request.getParameter("i_sex"));
			i.setDept_name(request.getParameter("i_dept_name"));
			i.setPosition(request.getParameter("i_position"));
			try {
				mD.AddInstructor(i);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				request.setAttribute("msg", -1);
				request.getRequestDispatcher("/alluser.jsp").forward(request, response);
				e.printStackTrace();
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
				String name = null;
				InstructorDao iD = new InstructorDao();
				try {
					name = iD.selectNameById(u_id);
				} catch (SQLException e) {
					request.setAttribute("msg", -2);
					request.getRequestDispatcher("/alluser.jsp").forward(request, response);
					e.printStackTrace();
					return;
				}
				String names[] = new String[] { name };
				request.setAttribute("keys", names);
				request.getRequestDispatcher("/allcourse.jsp").forward(request, response);
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
