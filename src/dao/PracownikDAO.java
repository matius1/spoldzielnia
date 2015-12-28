package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import core.Pracownik;


public class PracownikDAO {
	
	private Connection myConn;
	
	public PracownikDAO(){
		//uzyskanie danych do bazy danych
		
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
		
		//polaczenie z baza danych
		
		try {
			myConn = DriverManager.getConnection(url, user, password);
			System.out.println("Polaczono z baza danych: " + url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}//
	
	public static void main(String[] args) throws Exception {
		
		PracownikDAO dao = new PracownikDAO();
		System.out.println(dao.getAllPracownik());
	}//
	
	public List<Pracownik> getAllPracownik() throws Exception{
		 List<Pracownik> list = new ArrayList<>();
		 Statement myStmt = null;
		 ResultSet myRs = null;
		 
		 try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from pracownik");
			 
			 while(myRs.next()){
				 Pracownik tempPracownik = convertRowToPracownik(myRs);
				 list.add(tempPracownik);
			 }
			 
			 return list;
		} finally {
			close(myStmt);
			
		}
	}//
	
	public List<Pracownik> szukajPracownik(String nazwisko) throws SQLException{
		List<Pracownik> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try{
			nazwisko += "%";  //aby szukalo po czesci nazwiska
			
			myStmt = myConn.prepareStatement("select * from pracownik where nazwisko like ?");
			
			myStmt.setString(1, nazwisko);
			
			myRs = myStmt.executeQuery();
			
			
			while(myRs.next()){
				Pracownik tempPracownik = convertRowToPracownik(myRs);
				list.add(tempPracownik);
			}
			
			return list;
			
		}finally{
			close(myStmt);
		}
	}
	
	
	public List<Pracownik> szukajPracownik(String nazwisko, String stanowisko) throws SQLException{
		List<Pracownik> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try{
			nazwisko += "%";  //aby szukalo po czesci nazwiska
			stanowisko += "%";
			
			myStmt = myConn.prepareStatement("select * from pracownik where nazwisko like ? and stanowisko like ?");
			
			myStmt.setString(1, nazwisko);
			myStmt.setString(2, stanowisko);
			
			myRs = myStmt.executeQuery();
			
			
			while(myRs.next()){
				Pracownik tempPracownik = convertRowToPracownik(myRs);
				list.add(tempPracownik);
			}
			
			return list;
			
		}finally{
			close(myStmt);
		}
	}
	
	
	public List<Pracownik> szukajPracownik(String nazwisko, String stanowisko, String imie, String ulica, String miejscowosc, String pesel, String nrKonta, String nrTel) throws SQLException{
		List<Pracownik> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		
		try{
			nazwisko += "%";  //aby szukalo po czesci nazwiska
			stanowisko += "%";
			imie += "%";
			ulica += "%";
			miejscowosc += "%";
			pesel += "%";
			nrKonta += "%";
			nrTel += "%";
			
			myStmt = myConn.prepareStatement("select * from pracownik where nazwisko like ? and stanowisko like ? and imie like ? and ulica like ? and miejscowosc like ? and pesel like ? and nr_konta like ?  and nr_telefonu like ?");
			
			myStmt.setString(1, nazwisko);
			myStmt.setString(2, stanowisko);
			myStmt.setString(3, imie);
			myStmt.setString(4, ulica);
			myStmt.setString(5, miejscowosc);
			myStmt.setString(6, pesel);
			myStmt.setString(7, nrKonta);
			myStmt.setString(8, nrTel);
			
			myRs = myStmt.executeQuery();
			
			
			while(myRs.next()){
				Pracownik tempPracownik = convertRowToPracownik(myRs);
				list.add(tempPracownik);
			}
			
			
			return list;
			
		}finally{
			close(myStmt);
		}
	}
	
	
	

	private Pracownik convertRowToPracownik(ResultSet myRs) throws SQLException{
		
		int id = myRs.getInt("id");
		String imie = myRs.getString("imie");
		String nazwisko = myRs.getString("nazwisko");
		String pesel = myRs.getString("pesel");
		String ulica = myRs.getString("ulica");
		String miejscowosc = myRs.getString("miejscowosc");
		String stanowisko = myRs.getString("stanowisko");
		String nrKonta = myRs.getString("nr_konta");
		String nrTelefonu = myRs.getString("nr_telefonu");
		
		Pracownik tempPracownik = new Pracownik(id, imie, nazwisko, pesel, ulica, miejscowosc, stanowisko, nrKonta, nrTelefonu);
		
		return tempPracownik;
		
	}//
	
	
	public void addPracownik(Pracownik pracownik) throws SQLException {
		
		PreparedStatement myStmt = null;
		
		//
		try {
			myStmt = myConn.prepareStatement("insert into pracownik"
					+ " (id, imie, nazwisko, pesel, ulica, miejscowosc, stanowisko, nr_konta, nr_telefonu, nazwa_spoldzielni_fk)"
					+ " values (NULL, ?, ?, ?, ?, ?, ?, ?, ?, 'Stonka' )");

			myStmt.setString(1, pracownik.getImie());
			myStmt.setString(2, pracownik.getNazwisko());
			myStmt.setString(3, pracownik.getPesel());
			myStmt.setString(4, pracownik.getUlica());
			myStmt.setString(5, pracownik.getMiejscowosc());
			myStmt.setString(6, pracownik.getStanowisko());
			myStmt.setString(7, pracownik.getNrKonta());
			myStmt.setString(8, pracownik.getNrTelefonu());
			
			myStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(myStmt);
		}
	}
	
	
	public void edytujPracownik(Pracownik pracownik) throws SQLException{
		PreparedStatement myStmt = null;
		
		try {
			myStmt = myConn.prepareStatement("update pracownik "
					+ "set imie=?, nazwisko=?, pesel=?, ulica=?, miejscowosc=?, stanowisko=?, nr_konta=?, nr_telefonu=? where id=?");
					
			myStmt.setString(1, pracownik.getImie());
			myStmt.setString(2, pracownik.getNazwisko());
			myStmt.setString(3, pracownik.getPesel());
			myStmt.setString(4, pracownik.getUlica());
			myStmt.setString(5, pracownik.getMiejscowosc());
			myStmt.setString(6, pracownik.getStanowisko());
			myStmt.setString(7, pracownik.getNrKonta());
			myStmt.setString(8, pracownik.getNrTelefonu());
			myStmt.setInt(9, pracownik.getId());
			
			myStmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(myStmt);
		}
		
	}
	
	
	public void usunPracowni(int pracownikID) throws SQLException{
		PreparedStatement myStmt = null;
		
		try {
			myStmt = myConn.prepareStatement("delete from pracownik where id=?");
					
			myStmt.setInt(1, pracownikID);
			myStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(myStmt);
		}
	}
	
	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

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
