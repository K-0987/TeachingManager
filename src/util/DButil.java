package util;
import java.sql.*;

public class DButil {
	protected Connection conn = null;
	
	public Connection openConnection() {
		try {
			if(conn == null||conn.isClosed()) {
				String url = "jdbc:oracle:thin:@//localhost:1521/orcl";
				String user = "SCOTT";
				String password = "tiger";
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url,user,password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return conn;
	}
	
	public void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}
	}
}
