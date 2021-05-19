import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class loginDAO {
	
	private String dburl = "jdbc:mysql://localhost:3306/userdb";
	private String dbusername = "root";
	private String dbpassword = "Ranju2701@";
	private String dbDriver = "com.mysql.cj.jdbc.Driver";
	
	public void loaddriver(String dbDriver) {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		public Connection getConnection() {
			Connection con = null;
			try {
			con = DriverManager.getConnection(dburl,dbusername,dbpassword);
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return con;
	}
    public boolean validate(memberLogin login) {
    	loaddriver(dbDriver);
    	Connection con = getConnection();
    	boolean status = false;
    	String sql = "select * from newmember where uname = ? and password=?";
    	
    	PreparedStatement ps;
    	try {
			ps = con.prepareStatement(sql);
			ps.setString(1, login.getUname());
			ps.setString(2, login.getPassword());
			
			ResultSet rs = ps.executeQuery();
			status = rs.next();
		
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return status;
    }
}
