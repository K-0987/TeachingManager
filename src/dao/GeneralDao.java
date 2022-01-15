package dao;

import util.DButil;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import bean.*;

public class GeneralDao {

	DButil util = new DButil();
	
	public int changePassword(String u_id,String newPassword) throws SQLException{
		int affectNums = 0;
		String sql = "update ACCOUNT set PASSWORD='" + newPassword + "' where USER_ID='" + u_id + "'";
		Connection conn = util.openConnection();
		Statement stmt = conn.createStatement();
		affectNums = stmt.executeUpdate(sql);
		return affectNums;
	}
	
	public int changePasswordByUser(String s_id, String epassword, String password) throws SQLException{
		int affectNums = 0;
		String s = null;
		String sql1 = "select PASSWORD from ACCOUNT where USER_ID='" + s_id + "'";
		String sql2 = "update ACCOUNT set PASSWORD='" + password + "' where USER_ID='" + s_id + "'";
		Connection conn = util.openConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql1);
		rs.next();
		s = rs.getString(1);
		if(s.equals(epassword))
			affectNums = stmt.executeUpdate(sql2);
		return affectNums;
	}
	
	public List<CourseBean> selectAllCourse(String[] keys, String s_id){
		String sql = "select * from ALL_COURSE";
		List<CourseBean> cList = new ArrayList<CourseBean>();
		boolean flag = true;
		DButil util = new DButil();
		if(keys != null) {
			sql += " where title like '%" + keys[0] + "%' or name like '%" + keys[0] + "%'";
			for(int i = 1;i < keys.length;i++) 
				sql += " or title like '%" + keys[i] + "%' or name like '%" + keys[0] + "%'";
			if(s_id != null) {
				sql += " and ID not in (select c_id from ELECT where s_id='" + s_id + "')";
				flag = false;
			}
		}
		if(s_id != null && flag)
			sql += " where ID not in (select c_id from ELECT where s_id='" + s_id + "')";
		System.out.println(sql);
		try {
			Connection conn = util.openConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				CourseBean cB = new CourseBean();
				cB.setId(rs.getString(1));
				cB.setTitle(rs.getString(2));
				cB.setIns_name(rs.getString(3));
				cB.setDept_name(rs.getString(4));
				cB.setHours(rs.getInt(5));
				cB.setCredits(rs.getInt(6));
				cList.add(cB);
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}
		
		return cList;
	}
	
}
