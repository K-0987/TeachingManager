package service.studentservlet.course;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.StudentDao;
/**
 * Servlet implementation class SCServlet
 */
@WebServlet("/SCServlet")
public class SCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SCServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		StudentDao sD = new StudentDao();
		String type = request.getParameter("btn");
		String s_id = request.getParameter("s_id");
		System.out.println(request.getParameter("btn"));
		String ntype = type.substring(0, 1);
		if(ntype.equals("D")) {
			String c_id = type.substring(1);
			try {
				sD.exitCourse(s_id, c_id);
				request.setAttribute("s_id", s_id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				request.setAttribute("msg", -2);
				request.getRequestDispatcher("/student_course.jsp").forward(request, response);
				e.printStackTrace();
				return;
			}
		}
		request.setAttribute("msg", 0);
		request.getRequestDispatcher("/student_course.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
