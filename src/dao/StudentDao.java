package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bean.CourseBean;
import bean.StudentBean;
import bean.LogBean;
import util.DButil;

public class StudentDao {
	
	DButil util = new DButil();

	public List<CourseBean> selectCoursesByStudent(String s_id) throws SQLException{
		String sql = "select * from COURSE_STUDENT where S_ID='" + s_id + "'";
		List<CourseBean> cList = new ArrayList<CourseBean>();
		try {
			Connection conn = util.openConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				CourseBean c = new CourseBean();
				c.setId(rs.getString(2));
				c.setTitle(rs.getString(3));
				c.setIns_name(rs.getString(4));
				c.setDept_name(rs.getString(5));
				c.setHours(rs.getInt(6));
				c.setCredits(rs.getInt(7));
				c.setCompleted(rs.getString(8));
				cList.add(c);
			}
		} catch(SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return cList;
	}
	
	public int exitCourse(String s_id, String c_id) throws SQLException{
		int affectNums = 0;
		String sql = "delete from ELECT where S_ID='" + s_id + "' and C_ID='" + c_id + "'";
		Connection conn = util.openConnection();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		return affectNums;		
	}
	
	public String selectNameById(String s_id) throws SQLException {
		String name = null;
		String sql = "select NAME from STUDENT where ID='" + s_id + "'";
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
	
	public int selectCourse(String s_id, String c_id) throws SQLException{
		int affectNums = 0;
		String sql = "insert into ELECT values(?,?,'N')";
		Connection conn = util.openConnection();
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, s_id);
		ptmt.setString(2, c_id);
		affectNums = ptmt.executeUpdate();
		return affectNums;
	}
	
	public StudentBean selectAStudent(String s_id) throws SQLException{
		StudentBean s = new StudentBean();
		String sql = "select * from STUDENT where ID = '" + s_id + "'";
		Connection conn = util.openConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		s.setId(rs.getString(1));
		s.setName(rs.getString(2));
		s.setSex(rs.getString(3));
		s.setDept_name(rs.getString(4));
		s.setTol_cred(rs.getInt(5));
		return s;
	}
	
	public List<LogBean> readLog(String s_id) throws SQLException{
		List<LogBean> log = new ArrayList<LogBean>();
		String sql = "select TITLE,TIME from LOG,COURSE where LOG.CREATER=COURSE.ID AND RELATED='" + s_id + "' order by TIME desc";
		Connection conn = util.openConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			LogBean l = new LogBean();
			l.setTitle(rs.getString(1));
			l.setTime(rs.getTimestamp(2));
			log.add(l);
		}
		return log;
	}
}
