package Test_Aufgabe;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	
	public static void createTableSchuelerZuKlasse(Connection c)
	{
		Statement stmt;
		String sql;
		try 
		{
        	stmt = c.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS SchuelerZuKlasse " +
                  "(schuelerid INT NOT NULL," +
                  "klassenid INT NOT NULL," +
                  "datum DATE NOT NULL," +
                  "PRIMARY KEY(schuelerid, klassenid, datum)," +
                  "FOREIGN KEY(schuelerid) REFERENCES Schueler(id)," +
                  "FOREIGN KEY(klassenid) REFERENCES Klasse(id));";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) 
		{
            e.printStackTrace();
        }
    }
	
	public static void insertIntoSchulerZuKlasse(Connection c, String schuelername, String klassenname) 
	{
		String sql;
		try 
		{
        	int schuelerid = 0;
    		int klassenid = 0;
    		java.sql.Date zuteilungsdatum = java.sql.Date.valueOf(LocalDate.now()); 
    		
        	sql = "SELECT id FROM schueler where name = ?";
        	PreparedStatement preStmt = c.prepareStatement(sql);
			preStmt.setString(1, schuelername);
			
			ResultSet rs = preStmt.executeQuery();
			while(rs.next())
			{
				schuelerid = rs.getInt("id");
			}

			sql = "SELECT id FROM klasse where name = ?";
			preStmt = c.prepareStatement(sql);
			preStmt.setString(1, klassenname);
			
			rs = preStmt.executeQuery();
			while(rs.next()) 
			{
				klassenid = rs.getInt("id");
			}
			
			sql = "INSERT INTO SchuelerZuKlasse VALUES (?, ?, ?)";
	        preStmt = c.prepareStatement(sql);
	        preStmt.setInt(1, schuelerid);
	        preStmt.setInt(2, klassenid);
	        preStmt.setDate(3, zuteilungsdatum);
	        preStmt.executeUpdate();
		
			rs.close();
			preStmt.close();
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
}