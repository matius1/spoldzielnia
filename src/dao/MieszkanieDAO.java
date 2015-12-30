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

import core.Mieszkanie;


public class MieszkanieDAO {

	private Connection myConn;

	public MieszkanieDAO() {

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

		MieszkanieDAO dao = new MieszkanieDAO();
		System.out.println(dao.getAllMieszkanie());
	}//

	public List<Mieszkanie> getAllMieszkanie() throws Exception {
		List<Mieszkanie> list = new ArrayList<>();
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from mieszkanie");

			while (myRs.next()) {
				Mieszkanie tempMieszkanie = convertRowToMieszkanie(myRs);
				list.add(tempMieszkanie);
			}
			return list;

		} finally {
			close(myStmt);

		}
	}//

//	public List<Mieszkanie> szukajMieszkanie(String nazwisko) throws SQLException {
//
//		List<Mieszkanie> list = new ArrayList<>();
//
//		PreparedStatement myStmt = null;
//		ResultSet myRs = null;
//
//		try {
//			nazwisko += "%"; // aby szukalo po czesci nazwiska
//
//			myStmt = myConn.prepareStatement("select * from mieszkanie where nazwisko like ?");
//
//			myStmt.setString(1, nazwisko);
//
//			myRs = myStmt.executeQuery();
//
//			while (myRs.next()) {
//				Mieszkanie tempMieszkanie = convertRowToMieszkanie(myRs);
//				list.add(tempMieszkanie);
//			}
//
//			return list;
//
//		} finally {
//			close(myStmt);
//		}
//	}//

//	public List<Mieszkanie> szukajMieszkanie(String nazwisko, String imie, String ulica,
//			String miejscowosc, String pesel, String nrTel) throws SQLException {
//		
//		List<Mieszkanie> list = new ArrayList<>();
//
//		PreparedStatement myStmt = null;
//		ResultSet myRs = null;
//
//		try {
//			nazwisko += "%"; // aby szukalo po czesci nazwiska
//			imie += "%";
//			ulica += "%";
//			miejscowosc += "%";
//			pesel += "%";
//			nrTel += "%";
//
//			myStmt = myConn.prepareStatement(
//					"select * from mieszkanie where nazwisko like ? and imie like ? and ulica like ? and miejscowosc like ? and pesel like ? and nr_telefonu like ?");
//
//			myStmt.setString(1, nazwisko);
//			myStmt.setString(2, imie);
//			myStmt.setString(3, ulica);
//			myStmt.setString(4, miejscowosc);
//			myStmt.setString(5, pesel);
//			myStmt.setString(6, nrTel);
//
//			myRs = myStmt.executeQuery();
//
//			while (myRs.next()) {
//				Mieszkanie tempMieszkanie = convertRowToMieszkanie(myRs);
//				list.add(tempMieszkanie);
//			}
//
//			return list;
//
//		} finally {
//			close(myStmt);
//		}
//	}//
//	
//	
//	
	
//	public void edytujMieszkanie(Mieszkanie mieszkanie) throws SQLException{
//		PreparedStatement myStmt = null;
//		
//		try {
//			myStmt = myConn.prepareStatement("update mieszkanie "
//					+ "set imie=?, nazwisko=?, pesel=?, ulica=?, miejscowosc=?, nr_telefonu=? where id=?");
//					
//			myStmt.setString(1, mieszkanie.getImie());
//			myStmt.setString(2, mieszkanie.getNazwisko());
//			myStmt.setString(3, mieszkanie.getPesel());
//			myStmt.setString(4, mieszkanie.getUlica());
//			myStmt.setString(5, mieszkanie.getMiejscowosc());
//			myStmt.setString(6, mieszkanie.getNrTelefonu());
//			myStmt.setInt(7, mieszkanie.getId());
//			
//			myStmt.executeUpdate();
//			
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally{
//			close(myStmt);
//		}
//		
//	}
//	
//	
	
//	public void addMieszkanie(Mieszkanie mieszkanie) throws SQLException {
//			
//			PreparedStatement myStmt = null;
//			
//			//
//			try {
//				myStmt = myConn.prepareStatement("insert into mieszkanie"
//						+ " (id, imie, nazwisko, pesel, ulica, miejscowosc, nr_telefonu)"
//						+ " values (NULL, ?, ?, ?, ?, ?, ?)");
//	
//			
//				myStmt.setString(1, mieszkanie.getImie());
//				myStmt.setString(2, mieszkanie.getNazwisko());
//				myStmt.setString(3, mieszkanie.getPesel());
//				myStmt.setString(4, mieszkanie.getUlica());
//				myStmt.setString(5, mieszkanie.getMiejscowosc());
//				myStmt.setString(6, mieszkanie.getNrTelefonu());
//				
//				myStmt.executeUpdate();
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}finally{
//				close(myStmt);
//			
//			}
//	}
	
//	public void usunMieszkanie(int mieszkanieID) throws SQLException{
//		PreparedStatement myStmt = null;
//		
//		try {
//			myStmt = myConn.prepareStatement("delete from mieszkanie where id=?");
//			System.err.println("Usunieto mieszkaniea id: " + mieszkanieID);
//					
//			myStmt.setInt(1, mieszkanieID);
//			myStmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally{
//			close(myStmt);
//		}
//	}

	

	private Mieszkanie convertRowToMieszkanie(ResultSet myRs) throws SQLException {

		
		int id = myRs.getInt("id_mieszkania");
		int id_bloku = myRs.getInt("id_bloku");
		int nr_mieszkania = myRs.getInt("nr_mieszkania");
		int pietro = myRs.getInt("pietro");
		float powierzchnia = myRs.getFloat("powierzchnia");
		int ilosc_pokoi = myRs.getInt("ilosc_pokoi");
		float pow_piwnicy = myRs.getFloat("piwnica_powierzchnia");
		

		Mieszkanie tempMieszkanie = new Mieszkanie(id, id_bloku, nr_mieszkania, pietro, powierzchnia, ilosc_pokoi, pow_piwnicy);
		return tempMieszkanie;

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
