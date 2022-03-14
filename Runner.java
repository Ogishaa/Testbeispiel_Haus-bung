package Test_Aufgabe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.time.LocalDate;

public class Runner 
{
	public static Connection getConnection(String url, String user, String passwort)  
	{
		try 
		{
			return DriverManager.getConnection(url, user, passwort);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) 
	{
		String url = "jdbc:mysql://localhost:3306/testbeispiel";
		String user = "ogisha";
		String passwort = "ogisha123";
		
		try 
		{
			Connection c = getConnection(url, user, passwort);
			c.setAutoCommit(true);
			
			System.out.println("DIE TABELLEN WERDEN GELOESCHT");
			SchuelerZuKlasse.dropTableSchuelerZuKlasse(c);
			Schueler.dropTableSchueler(c);
			Klasse.dropTableKlasse(c);
			
			System.out.println("DIE TABELLEN WERDEN ERSTELLT");
			Schueler.createTableSchueler(c);
			Klasse.createTableKlasse(c);
			SchuelerZuKlasse.createTableSchuelerZuKlasse(c);
			
			System.out.println("INSERT FUER DIE SCHUELER");
			Schueler.insertIntoSchueler(c, "Leo Juric", LocalDate.of(2004, 12, 14));
			Schueler.insertIntoSchueler(c, "Berkan Aslan", LocalDate.of(2003, 11, 21));
			Schueler.insertIntoSchueler(c, "Ogisha Manojlovic", LocalDate.of(2003, 12, 16));
			
			System.out.println("INSERT FUER DIE KLASSEN");
			Klasse.insertIntoKlasse(c, "3AHWII", "Sascha Jambor");
			Klasse.insertIntoKlasse(c, "2AHWII", "Philipp Wischounig");
			
			System.out.println("SCHUELER ZU KLASSE");
			SchuelerZuKlasse.insertIntoSchulerZuKlasse(c, "Leo Juric", "2AHWII");
			SchuelerZuKlasse.insertIntoSchulerZuKlasse(c, "Berkan Aslan", "2AHWII");
			SchuelerZuKlasse.insertIntoSchulerZuKlasse(c, "Ogisha Manojlovic", "3AHWII");
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}