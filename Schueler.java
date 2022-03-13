package Test_Aufgabe;

import java.sql.Connection;
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
		try {
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
		Statement stmt;
		String sql;
        try 
        {
        	stmt = c.createStatement();
        	java.sql.Date localdate = java.sql.Date.valueOf(ld); //Von Livio :D
        	
            sql = String.format("INSERT INTO Schueler (name, geburtsdatum) VALUES(\"%s\", \"%s\");", name, localdate);
            stmt.executeUpdate(sql);
            
            stmt.close();
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
}