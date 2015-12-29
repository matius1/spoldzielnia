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

import core.Pracownik;
import core.Wlasciciel;

public class WlascicielDAO {

	private Connection myConn;

	public WlascicielDAO() {

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

		WlascicielDAO dao = new WlascicielDAO();
		System.out.println(dao.getAllWlasciciel());
	}//

	public List<Wlasciciel> getAllWlasciciel() throws Exception {
		List<Wlasciciel> list = new ArrayList<>();
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from wlasciciel");

			while (myRs.next()) {
				Wlasciciel tempWlasciciel = convertRowToWlasciciel(myRs);
				list.add(tempWlasciciel);
			}
			return list;

		} finally {
			close(myStmt);

		}
	}//

	public List<Wlasciciel> szukajWlasciciel(String nazwisko) throws SQLException {

		List<Wlasciciel> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			nazwisko += "%"; // aby szukalo po czesci nazwiska

			myStmt = myConn.prepareStatement("select * from wlasciciel where nazwisko like ?");

			myStmt.setString(1, nazwisko);

			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				Wlasciciel tempWlasciciel = convertRowToWlasciciel(myRs);
				list.add(tempWlasciciel);
			}

			return list;

		} finally {
			close(myStmt);
		}
	}//

	public List<Wlasciciel> szukajWlasciciel(String nazwisko, String imie, String ulica,
			String miejscowosc, String pesel, String nrTel) throws SQLException {
		
		List<Wlasciciel> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			nazwisko += "%"; // aby szukalo po czesci nazwiska
			imie += "%";
			ulica += "%";
			miejscowosc += "%";
			pesel += "%";
			nrTel += "%";

			myStmt = myConn.prepareStatement(
					"select * from wlasciciel where nazwisko like ? and imie like ? and ulica like ? and miejscowosc like ? and pesel like ? and nr_telefonu like ?");

			myStmt.setString(1, nazwisko);
			myStmt.setString(2, imie);
			myStmt.setString(3, ulica);
			myStmt.setString(4, miejscowosc);
			myStmt.setString(5, pesel);
			myStmt.setString(6, nrTel);

			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				Wlasciciel tempWlasciciel = convertRowToWlasciciel(myRs);
				list.add(tempWlasciciel);
			}

			return list;

		} finally {
			close(myStmt);
		}
	}//
	
	
	
	
	public void edytujWlasciciel(Wlasciciel wlasciciel) throws SQLException{
		PreparedStatement myStmt = null;
		
		try {
			myStmt = myConn.prepareStatement("update wlasciciel "
					+ "set imie=?, nazwisko=?, pesel=?, ulica=?, miejscowosc=?, nr_telefonu=? where id=?");
					
			myStmt.setString(1, wlasciciel.getImie());
			myStmt.setString(2, wlasciciel.getNazwisko());
			myStmt.setString(3, wlasciciel.getPesel());
			myStmt.setString(4, wlasciciel.getUlica());
			myStmt.setString(5, wlasciciel.getMiejscowosc());
			myStmt.setString(6, wlasciciel.getNrTelefonu());
			myStmt.setInt(7, wlasciciel.getId());
			
			myStmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(myStmt);
		}
		
	}
	
	
	
	public void addWlasciciel(Wlasciciel wlasciciel) throws SQLException {
			
			PreparedStatement myStmt = null;
			
			//
			try {
				myStmt = myConn.prepareStatement("insert into wlasciciel"
						+ " (id, imie, nazwisko, pesel, ulica, miejscowosc, nr_telefonu)"
						+ " values (NULL, ?, ?, ?, ?, ?, ?)");
	
			
				myStmt.setString(1, wlasciciel.getImie());
				myStmt.setString(2, wlasciciel.getNazwisko());
				myStmt.setString(3, wlasciciel.getPesel());
				myStmt.setString(4, wlasciciel.getUlica());
				myStmt.setString(5, wlasciciel.getMiejscowosc());
				myStmt.setString(6, wlasciciel.getNrTelefonu());
				
				myStmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				close(myStmt);
			
			}
	}
	
	public void usunWlasciciel(int wlascicielID) throws SQLException{
		PreparedStatement myStmt = null;
		
		try {
			myStmt = myConn.prepareStatement("delete from wlasciciel where id=?");
			System.err.println("Usunieto wlasciciela id: " + wlascicielID);
					
			myStmt.setInt(1, wlascicielID);
			myStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(myStmt);
		}
	}

	

	private Wlasciciel convertRowToWlasciciel(ResultSet myRs) throws SQLException {

		int id = myRs.getInt("id");
		String imie = myRs.getString("imie");
		String nazwisko = myRs.getString("nazwisko");
		String pesel = myRs.getString("pesel");
		String ulica = myRs.getString("ulica");
		String miejscowosc = myRs.getString("miejscowosc");
		String nrTelefonu = myRs.getString("nr_telefonu");
		

		Wlasciciel tempWlasciciel = new Wlasciciel(id, imie, nazwisko, pesel, ulica, miejscowosc, nrTelefonu);
		return tempWlasciciel;

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
