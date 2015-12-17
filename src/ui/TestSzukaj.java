package ui;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import core.Pracownik;
import dao.PracownikDAO;

public class TestSzukaj {

	private String imie;
	private String nazwisko;
	private String pesel;
	private String ulica;
	private String miejscowosc;
	private String stanowisko;
	private String nrKonta;
	private String nrTelefonu;
	private int id;

	@Test
	public void testSzukaj() throws SQLException  {
		List<Pracownik> list = new ArrayList<>();
		PracownikDAO test = new PracownikDAO();
		
		int id = 8;
		String imie = "Maciek";
		String nazwisko = "Ol";
		String pesel = "93021223133232";
		String ulica = "Chmieleniec";
		String miejscowosc = "Krakow";
		String stanowisko = "Majster";
		String nrKonta = "201221";
		String nrTelefonu = "99332223";
		
		this.id=id;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.pesel = pesel;
		this.ulica = ulica;
		this.miejscowosc = miejscowosc;
		this.stanowisko = stanowisko;
		this.nrKonta = nrKonta;
		this.nrTelefonu = nrTelefonu;
		
		Pracownik tempPracownik = new Pracownik(id, imie, nazwisko, pesel, ulica, miejscowosc, stanowisko, nrKonta, nrTelefonu);
		list.add(tempPracownik);
		
		
		List<Pracownik> result = test.szukajPracownik("Ol");
		assertEquals(list,result);
		
	
		
	}

}
