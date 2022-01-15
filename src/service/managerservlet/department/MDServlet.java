package service.managerservlet.department;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DepartmentBean;
import dao.ManagerDao;

/**
 * Servlet implementation class MDServlet
 */
@WebServlet("/MDServlet")
public class MDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MDServlet() {
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
		String name = request.getParameter("e_dname");
		String building = request.getParameter("e_dbuilding");
		String descripe = request.getParameter("e_ddescripe");
		System.out.println(request.getParameter("btn"));
		System.out.println(name + building + descripe);
		if(type.equals("edit")) {
			try {
				if (building != null && building != "")
					mD.UpdateDepartment(name, "BUILDING", building);
				if (descripe != null && descripe != "")
					mD.UpdateDepartment(name, "DESCRIPE", descripe);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("msg", -1);
				request.getRequestDispatcher("/alldepartment.jsp").forward(request, response);
				return;
			}
		}
		else if(type.equals("new")) {
			DepartmentBean d =new DepartmentBean();
			d.setName(name);
			d.setBuilding(building);
			d.setDescripe(descripe);
			try {
				mD.AddDepartment(d);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("msg", -2);
				request.getRequestDispatcher("/alldepartment.jsp").forward(request, response);
				return;
			}	
		}
		request.setAttribute("msg", 0);
		request.getRequestDispatcher("/alldepartment.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
