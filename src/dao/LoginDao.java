package dao;

import util.DButil;
import bean.AccountBean;
import java.sql.*;

public class LoginDao {

	AccountBean account = null;

	public AccountBean selectAccount(String user_id, String password) {
		String sql = "select * from ACCOUNT where USER_ID ='" + user_id + "' and PASSWORD='" + password + "'";
		DButil util = new DButil();
		try {
			Connection conn = util.openConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				account = new AccountBean();
				do{
					account.setUser_id(user_id);
					account.setPassword(password);
					account.setIdentity(rs.getString(3));
				}while(rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}

		return account;
	}
}
