package service.managerservlet.course;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.InstructorDao;
import dao.ManagerDao;
import dao.StudentDao;

/**
 * Servlet implementation class MCServlet
 */
@WebServlet("/MCServlet")
public class MCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MCServlet() {
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
			String tableName = "COURSE";
			String id = request.getParameter("e_cid");
			String title = request.getParameter("e_ctitle");
			String ins_id = request.getParameter("e_cinsid");
			String dept_name = request.getParameter("e_cdept");
			String hours = request.getParameter("e_chour");
			String credits = request.getParameter("e_ccred");
			System.out.println(id + title + dept_name + hours + credits);
			try {
				if(title != null && title != "")
					mD.UpdateAny(tableName, id, "TITLE", title);
				if(ins_id != null && ins_id != "")
					mD.UpdateAny(tableName, id, "INS_ID", ins_id);
				if(dept_name != null && dept_name != "")
					mD.UpdateAny(tableName, id, "DEPT_NAME", dept_name);
				if(hours != null && hours != "")
					mD.UpdateAny(tableName, id, "HOURS", hours);
				if(credits != null && credits != "") 
					mD.UpdateAny(tableName, id, "CREDITS", credits);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("msg", -1);
				request.getRequestDispatcher("/allcourse.jsp").forward(request, response);
				return;
			}
		}
		else if(type.equals("search")) {
			String words = request.getParameter("keys");
			if(words != null && words != "") {
			String[] keys = words.split(" ");			
			request.setAttribute("keys", keys);
			}
			request.getRequestDispatcher("/allcourse.jsp").forward(request, response);
			return;
		}
		else{
			String ntype = type.substring(0, 1);
			String c_id = type.substring(1);
			if(ntype.equals("D")) {
				InstructorDao iD = new InstructorDao();
				try {
					iD.DeleteCourse(c_id);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("msg", -2);
					request.getRequestDispatcher("/allcourse.jsp").forward(request, response);
					e.printStackTrace();
					return;
				}
			} else if(ntype.equals("S")) {				
				StudentDao sD = new StudentDao();
				String s_id = (String)request.getParameter("s_id");
				System.out.println(s_id);
				try {
					sD.selectCourse(s_id, c_id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					request.setAttribute("msg", -3);
					request.getRequestDispatcher("/allcourse.jsp").forward(request, response);
					e.printStackTrace();
					return;
				}
			}
		}
		request.setAttribute("msg", 0);
		request.getRequestDispatcher("/allcourse.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
