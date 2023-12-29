import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.User;

public class UserDb {
	
	public Connection init ()
	{
		Connection cnx=null;
		String url = "jdbc:mysql://localhost:3306/user_jee_db";
		String user="root";
		String password="";
		try {
			cnx= DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println(e.getErrorCode()+ ": "+e.getMessage());
		}
		return cnx;
	}
	
	public ArrayList<User> getUsers() throws SQLException
	{
		ArrayList<User> users = new ArrayList<User>();
		
		Connection cnx = this.init();
		Statement stm = cnx.createStatement();
		String sql = "select * from user";
		ResultSet rs= stm.executeQuery(sql);
		while (rs.next())
			try {
				users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return users ;
	}
	

}
