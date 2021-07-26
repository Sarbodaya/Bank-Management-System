package aSimulatorSystem;
import java.sql.*;
public class Conn {
	Connection c;
	Statement s;
	
	Conn()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3308/project2","root","");
			s = c.createStatement();
		}
		
		catch(Exception e)
		{
			System.out.print(e);
		}
	}
	
}
