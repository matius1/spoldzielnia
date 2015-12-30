package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import core.Blok;


public class BlokDAO {

	private Connection myConn;

	public BlokDAO() {

		// uzyskanie danych do bazy danych

		Properties props = new Properties();
		try {

			props.load(new FileInputStream("dane.properties"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String url = props.getProperty("dburl");

		// polaczenie z baza danych

		try {
			myConn = DriverManager.getConnection(url, user, password);
			System.out.println("Polaczono z baza danych: " + url);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}//

	public static void main(String[] args) throws Exception {

		BlokDAO dao = new BlokDAO();
		System.out.println(dao.getAllBlok());
//		System.out.println(dao.szukajAll());
//		Blok ttt = new Blok("Kaz", "2", "Krakow", "Stonka");
//		System.out.println("TEST: " +ttt.getClass());
//		System.out.println("ALL: " + dao.szukajBlok("", "", ""));
		
		
		
	}//

	public List<Blok> getAllBlok() throws Exception {
		List<Blok> list = new ArrayList<>();
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from blok");

			while (myRs.next()) {
				Blok tempBlok = convertRowToBlok(myRs);
				list.add(tempBlok);
			}
			return list;

		} finally {
			close(myStmt);

		}
	}//
	
	


	public List<Blok> szukajBlok(int id, String ulica, String ulica_nr, String miejscowosc) throws SQLException {
		
		List<Blok> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
//			id = 1;
			ulica += "%";
			ulica_nr += "%";
			miejscowosc += "%";
		

			myStmt = myConn.prepareStatement(
					"select * from blok where id_bloku like ? and ulica like ? and ulica_nr like ? and miejscowosc like ?");

			myStmt.setInt(1, id);
			myStmt.setString(2, ulica);
			myStmt.setString(3, ulica_nr);
			myStmt.setString(4, miejscowosc);
			
			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				Blok tempBlok = convertRowToBlok(myRs);
				list.add(tempBlok);
			}

			return list;

		} finally {
			close(myStmt);
		}
	}//
	
	
	public List<Blok> szukajBlok(String ulica, String ulica_nr, String miejscowosc) throws SQLException {
		
		List<Blok> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
	
			ulica += "%";
			ulica_nr += "%";
			miejscowosc += "%";
		

			myStmt = myConn.prepareStatement(
					"select * from blok where ulica like ? and ulica_nr like ? and miejscowosc like ?");

	
			myStmt.setString(1, ulica);
			myStmt.setString(2, ulica_nr);
			myStmt.setString(3, miejscowosc);
			
			myRs = myStmt.executeQuery();

			System.out.println("Wyszukalo");
			
			while (myRs.next()) {
				Blok tempBlok = convertRowToBlok(myRs);
				list.add(tempBlok);
			}

			return list;

		} finally {
			close(myStmt);
		}
	}//
	

	public List<Blok> szukajAll() throws SQLException {
	
	List<Blok> list = new ArrayList<>();

	Statement myStmt = null;
	ResultSet myRs = null;

	try {

		myStmt = myConn.createStatement();

	
		
		myRs = myStmt.executeQuery("SELECT * FROM blok");

		System.out.println("Wyszukalo");
		
		while (myRs.next()) {
			Blok tempBlok = convertRowToBlok(myRs);
			list.add(tempBlok);
		}

		return list;

	} finally {
		close(myStmt);
	}
}//
	
	
	
	public void edytujBlok(Blok blok) throws SQLException{
		PreparedStatement myStmt = null;
		
		try {
			myStmt = myConn.prepareStatement("update blok "
					+ "set ulica=?, ulica_nr=?, miejscowosc=?, where id=?");
					
		
			myStmt.setString(1, blok.getUlica());
			myStmt.setString(2, blok.getUlica());
			myStmt.setString(3, blok.getMiejscowosc());
			myStmt.setInt(4, blok.getId());
				
			myStmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(myStmt);
		}
		
	}
	
	
	
	public void addBlok(Blok blok) throws SQLException {
			
			PreparedStatement myStmt = null;
			
			//
			try {
				myStmt = myConn.prepareStatement("insert into blok"
						+ " (id_bloku, ulica, ulica_nr, miejscowosc, nazwa_spoldzielni)"
						+ " values (NULL, ?, ?, ?, 'Stonka')");
	
			
				myStmt.setString(1, blok.getUlica());
				myStmt.setString(2, blok.getUlica_nr());
				myStmt.setString(3, blok.getMiejscowosc());
				
				myStmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				close(myStmt);
			
			}
	}
	
	public void usunBlok(int blokID) throws SQLException{
		PreparedStatement myStmt = null;
		
		try {
			myStmt = myConn.prepareStatement("delete from blok where id=?");
			System.err.println("Usunieto blok id: " + blokID);
					
			myStmt.setInt(1, blokID);
			myStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(myStmt);
		}
	}

	

	private Blok convertRowToBlok(ResultSet myRs) throws SQLException {

		int id = myRs.getInt("id_bloku");		
		String ulica = myRs.getString("ulica");
		String ulica_nr = myRs.getString("ulica_nr");
		String miejscowosc = myRs.getString("miejscowosc");
		String spoldzielnia = myRs.getString("nazwa_spoldzielni");

		
		Blok tempBlok = new Blok(id, ulica, ulica_nr, miejscowosc, spoldzielnia);
		
		return tempBlok;

	}//

	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {

		}

		if (myConn != null) {
			myConn.close();
		}
	}//

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);
	}//

	private void close(Statement myStmt) throws SQLException {
		close(null, myStmt, null);
	}//

}
