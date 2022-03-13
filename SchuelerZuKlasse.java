package Test_Aufgabe;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.time.LocalDate;

public class SchuelerZuKlasse 
{
	public static void dropTableSchuelerZuKlasse(Connection c) 
	{
		Statement stmt;
		String sql;
        try 
        {
        	stmt = c.createStatement();
            sql = "DROP TABLE IF EXISTS SchuelerZuKlasse;";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
	}
	
	
//AUFGABE b)
	public static void createTableSchuelerZuKlasse(Connection c)
	{
		Statement stmt;
		String sql;
		try 
		{
        	stmt = c.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS SchuelerZuKlasse" +
                  "(schuelerid INTEGER NOT NULL," +
                  "klassenid INTEGER NOT NULL," +
                  "datum DATE NOT NULL," +
                  "PRIMARY KEY(schuelerid, klassenid, datum)," +
                  "FOREIGN KEY(schuelerid) REFERENCES Schueler(id)" +
                  "FOREIGN KEY(klassenid) REFERENCES Klasse(id);";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) 
		{
            e.printStackTrace();
        }
    }
	
	
	public static void insertIntoSchulerZuKlasse(Connection c, String schuelername, String klassenname) 
	{
		Statement stmt;
		String sql;
		try 
		{
        	int schuelerid = 0;
    		int klassenid = 0;
        	stmt = c.createStatement();
    		java.sql.Date localdate = java.sql.Date.valueOf(LocalDate.now()); //Von Livio :D
    		
        	sql = String.format("SELECT id FROM schueler where name = \"%s\"", schuelername);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				schuelerid = rs.getInt("id");
			}
			sql = String.format("SELECT id FROM klasse where name = \"%s\"", klassenname);
			rs = stmt.executeQuery(sql);
			while(rs.next()) 
			{
				klassenid = rs.getInt("id");
			}
			sql = String.format("INSERT INTO SchuelerZuKlasse VALUES(%d, %d, \"" +  localdate + "\");", schuelerid, klassenid);
			stmt.executeUpdate(sql);
		
			rs.close();
			stmt.close();
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
}