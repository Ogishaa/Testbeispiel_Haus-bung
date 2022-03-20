package Test_Aufgabe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Schueler 
{
	public static void dropTableSchueler(Connection c) 
	{
		Statement stmt;
		String sql;
		try 
		{
        	stmt = c.createStatement();
            sql = "DROP TABLE IF EXISTS Schueler;";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) 
		{
            e.printStackTrace();
        }
	}
	
	public static void createTableSchueler(Connection c) 
	{
		Statement stmt;
		String sql;
		try 
		{
        	stmt = c.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS Schueler(" +
                  "id INT PRIMARY KEY AUTO_INCREMENT," +
                  "name VARCHAR(25)," +
                  "geburtsdatum DATE);";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) 
		{
            e.printStackTrace();
        }
    }
	
	public static void insertIntoSchueler(Connection c, String name, LocalDate ld) 
	{
		String sql;
        try 
        {
        	java.sql.Date localdate = java.sql.Date.valueOf(ld); 
        	
            sql = "INSERT INTO Schueler (name, geburtsdatum) VALUES (?,?)";
            PreparedStatement preStmt = c.prepareStatement(sql);
            
            preStmt.setString(1, name);
            preStmt.setDate(2, localdate);
            
            preStmt.executeUpdate();
            
            preStmt.close();
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
}