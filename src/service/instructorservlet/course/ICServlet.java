package service.instructorservlet.course;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.InstructorDao;

/**
 * Servlet implementation class ICServlet
 */
@WebServlet("/ICServlet")
public class ICServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ICServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		InstructorDao iD = new InstructorDao();
		String type = request.getParameter("btn");
		System.out.println(request.getParameter("btn"));
		if(type.equals("edit")) {
			String id = request.getParameter("e_cid");
			String title = request.getParameter("e_ctitle");
			String ins_id = request.getParameter("e_cinsid");
			String dept_name = request.getParameter("e_cdept");
			String hours = request.getParameter("e_chour");
			String credits = request.getParameter("e_ccred");
			System.out.println(id + title + dept_name + hours + credits);
			try {
				iD.CreateCourse(title, ins_id, dept_name, hours, credits);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("msg", -1);
				request.getRequestDispatcher("/create_course.jsp").forward(request, response);
				return;
			}
		}else {
			String ntype = type.substring(0,1);
			if(ntype.equals("G")) {
				String s_id = type.substring(1);
				String c_id = request.getParameter("c_id");
				try {
					iD.checkCourse(s_id, c_id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("c_id", c_id);
				request.getRequestDispatcher("/completecourse.jsp").forward(request, response);
				return;
			}
			else {
			request.setAttribute("c_id", type);
			request.setAttribute("i_id", request.getParameter("i_id"));
			request.getRequestDispatcher("/completecourse.jsp").forward(request, response);
			return;
			}
		}
		request.setAttribute("msg", 0);
		request.getRequestDispatcher("/create_course.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
