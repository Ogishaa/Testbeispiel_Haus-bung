package Test_Aufgabe;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Klasse 
{
	public static void dropTableKlasse(Connection c) 
	{
		Statement stmt;
		String sql;
		try 
		{
        	stmt = c.createStatement();
            sql = "DROP TABLE IF EXISTS Klasse;";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) 
		{
            e.printStackTrace();
        }
	}

	public static void createTableKlasse(Connection c) 
	{
		Statement stmt;
		String sql;
		try 
		{
        	stmt = c.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS Klasse(" +
                  "id INT PRIMARY KEY AUTO_INCREMENT," +
                  "name CHAR(6)," +
                  "klassenvorstand VARCHAR(20));";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) 
		{
            e.printStackTrace();
        }
    }
	
	public static void insertIntoKlasse(Connection c, String name, String klassenvorstand) 
	{
		Statement stmt;
		String sql;
		try 
		{
        	stmt = c.createStatement();
            sql = String.format("INSERT INTO Klasse (name, klassenvorstand) VALUES(\"%s\", \"%s\");", name, klassenvorstand);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) 
		{
            e.printStackTrace();
        }
    }
}