package dao;

import util.DButil;
import java.util.ArrayList;
import java.util.List;
import java.io.StringReader;
import java.sql.*;
import bean.*;

public class ManagerDao {
	
	DButil util = new DButil();

	public List<StudentBean> selectAllStudent() {
		String sql = "select * from STUDENT";
		List<StudentBean> aList = new ArrayList<StudentBean>();
		try {
			Connection conn = util.openConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				StudentBean aB = new StudentBean();
				aB.setId(rs.getString(1));
				aB.setName(rs.getString(2));
				aB.setSex(rs.getString(3));
				aB.setDept_name(rs.getString(4));
				aB.setTol_cred(rs.getInt(5));
				aList.add(aB);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
		}
		return aList;
	}

	public List<InstructorBean> selectAllInstructor() {
		String sql = "select * from INSTRUCTOR";
		List<InstructorBean> aList = new ArrayList<InstructorBean>();
		try {
			Connection conn = util.openConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				InstructorBean aB = new InstructorBean();
				aB.setId(rs.getString(1));
				aB.setName(rs.getString(2));
				aB.setSex(rs.getString(3));
				aB.setDept_name(rs.getString(4));
				aB.setPosition(rs.getString(5));
				aList.add(aB);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
		}
		return aList;
	}
	
	public List<AccountBean> selectAllManager() {
		String sql = "select * from ACCOUNT where IDENTITY='M'";
		List<AccountBean> aList = new ArrayList<AccountBean>();
		try {
			Connection conn = util.openConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				AccountBean aB = new AccountBean();
				aB.setUser_id(rs.getString(1));
				aB.setPassword(rs.getString(2));
				aList.add(aB);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
		}
		return aList;
	}
	
	public List<DepartmentBean> selectAllDepartment() {
		String sql = "select * from DEPARTMENT";
		List<DepartmentBean> dList = new ArrayList<DepartmentBean>();
		try {
			Connection conn = util.openConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				DepartmentBean dB = new DepartmentBean();
				dB.setName(rs.getString(1));
				dB.setBuilding(rs.getString(2));
				Clob c = rs.getClob(3);
				if(c != null)
					dB.setDescripe(c.getSubString(1, (int)c.length()));
				else
					dB.setDescripe("");
				dList.add(dB);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
		}
		return dList;
	}
	
	public int AddManager(String user_id, String pWord) throws SQLException {
		int affectNums = 0;
		String sql = "insert into ACCOUNT values(?,?,'M')";
		Connection conn = util.openConnection();
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, user_id);
		ptmt.setString(2, pWord);
		affectNums = ptmt.executeUpdate();
		return affectNums;
	}
	
	public int AddStudent(StudentBean student) throws SQLException {
		int affectNums = 0;
		String sql = "insert into STUDENT(NAME,SEX,DEPT_NAME) values(?,?,?)";
		Connection conn = util.openConnection();
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, student.getName());
		ptmt.setString(2, student.getSex());
		ptmt.setString(3, student.getDept_name());
		affectNums = ptmt.executeUpdate();
		return affectNums;
	}
	
	public int AddInstructor(InstructorBean instructor) throws SQLException {
		int affectNums = 0;
		String sql = "insert into INSTRUCTOR(NAME,SEX,DEPT_NAME,POSITION) values(?,?,?,?)";
		Connection conn = util.openConnection();
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, instructor.getName());
		ptmt.setString(2, instructor.getSex());
		ptmt.setString(3, instructor.getDept_name());
		ptmt.setString(4, instructor.getPosition());
		affectNums = ptmt.executeUpdate();
		return affectNums;
	}
	
	public int AddDepartment(DepartmentBean department) throws SQLException {
		int affectNums = 0;
		String sql = "insert into DEPARTMENT(NAME,BUILDING,DESCRIPE) values(?,?,?)";
		Connection conn = util.openConnection();
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, department.getName());
		ptmt.setString(2, department.getBuilding());
		String s = department.getDescripe();
		StringReader reader = new StringReader(s);
		ptmt.setCharacterStream(3, reader, s.length());
		affectNums = ptmt.executeUpdate();
		return affectNums;
	}
	
	public int DeleteUser(String u_id) throws SQLException {
		int affectNums = 0;
		String sql = "delete from ACCOUNT where USER_ID='" + u_id + "'";
		Connection conn = util.openConnection();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		return affectNums;		
	}
	
	public int UpdateAny(String tableName, String s_id, String col_name, String v) throws SQLException{
		int affectNums = 0;
		String sql = "update " + tableName + " set " + col_name + "='" + v + "' where ID='" + s_id + "'";
		Connection conn = util.openConnection();
		Statement stmt = conn.createStatement();
		affectNums = stmt.executeUpdate(sql);
		return affectNums;	
	}
	
	public int UpdateDepartment(String name, String col_name, String v) throws SQLException{
		int affectNums = 0;
		String sql = "update DEPARTMENT set " + col_name + "='" + v + "' where NAME='" + name + "'";
		Connection conn = util.openConnection();
		Statement stmt = conn.createStatement();
		affectNums = stmt.executeUpdate(sql);
		return affectNums;	
	}
	
	
}
