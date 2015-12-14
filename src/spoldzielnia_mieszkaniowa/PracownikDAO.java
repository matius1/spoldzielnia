package spoldzielnia_mieszkaniowa;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;


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
