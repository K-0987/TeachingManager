package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.InstructorBean;
import bean.StudentBean;
import bean.CourseBean;
import java.sql.ResultSet;

import util.DButil;

public class InstructorDao {
	
	DButil util = new DButil();

	public int DeleteCourse(String c_id) throws SQLException {
		int affectNums = 0;
		String sql = "delete from COURSE where ID='" + c_id + "'";
		Connection conn = util.openConnection();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		return affectNums;		
	}
	
	public String selectNameById(String i_id) throws SQLException {
		String name = null;
		String sql = "select NAME from INSTRUCTOR where ID='" + i_id + "'";
		try {
			Connection conn = util.openConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			name = rs.getString(1);
		} catch(SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return name;
	}
	
	public InstructorBean selectAInstructor(String i_id) throws SQLException{
		InstructorBean i = new InstructorBean();
		String sql = "select * from Instructor where ID = '" + i_id + "'";
		Connection conn = util.openConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		i.setId(rs.getString(1));
		i.setName(rs.getString(2));
		i.setSex(rs.getString(3));
		i.setDept_name(rs.getString(4));
		i.setPosition(rs.getString(5));
		return i;
	}
	
	public List<CourseBean> selectCourseByInstructor(String i_id) throws SQLException{
		String sql = "select * from COURSE where INS_ID='" + i_id + "'";
		List<CourseBean> cList = new ArrayList<CourseBean>();
		try {
			Connection conn = util.openConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				CourseBean c = new CourseBean();
				c.setId(rs.getString(1));
				c.setTitle(rs.getString(2));
				c.setDept_name(rs.getString(4));
				c.setHours(rs.getInt(5));
				c.setCredits(rs.getInt(6));
				cList.add(c);
			}
		} catch(SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return cList;
	}
	
	public int CreateCourse(String title, String ins_id, String dept_name, String hours, String credits) throws SQLException{
		int affectNums = 0;
		String sql = "insert into COURSE(TITLE,INS_ID,DEPT_NAME,HOURS,CREDITS) values(?,?,?,?,?)";
		Connection conn = util.openConnection();
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, title);
		ptmt.setString(2, ins_id);
		ptmt.setString(3, dept_name);
		ptmt.setString(4, hours);
		ptmt.setString(5, credits);
		affectNums = ptmt.executeUpdate();
		return affectNums;
	}
	
	public List<StudentBean> selectAllStudentFromCourse(String c_id) throws SQLException{
		String sql = "select * from STUDENT_COURSE where C_ID='" + c_id + "'";
		List<StudentBean> sList = new ArrayList<StudentBean>();
		try {
			Connection conn = util.openConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				StudentBean s = new StudentBean();
				s.setCompleted(rs.getString(2));
				s.setId(rs.getString(3));
				s.setName(rs.getString(4));
				s.setSex(rs.getString(5));
				s.setDept_name(rs.getString(6));
				s.setTol_cred(rs.getInt(7));
				sList.add(s);
			}
		} catch(SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return sList;
	}
	
	public int checkCourse(String s_id, String c_id) throws SQLException{
		int affectNums = 0;
		String sql = "update ELECT set COMPLETED='Y' where S_ID='" + s_id + "' and C_ID='" + c_id + "'";
		String sql2 = "insert into LOG(CREATER,RELATED) values('"+ c_id + "','"+ s_id + "')";
		Connection conn = util.openConnection();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		System.out.println(sql2);
		stmt.executeUpdate(sql2);
		return affectNums;	
	}
	
}

