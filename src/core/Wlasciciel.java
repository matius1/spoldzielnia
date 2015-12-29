package core;

public class Wlasciciel {

	private int id;
	private String imie;
	private String nazwisko;
	private String pesel;
	private String ulica;
	private String miejscowosc;
	private String nrTelefonu;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	public String getPesel() {
		return pesel;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	public String getUlica() {
		return ulica;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public String getMiejscowosc() {
		return miejscowosc;
	}
	public void setMiejscowosc(String miejscowosc) {
		this.miejscowosc = miejscowosc;
	}
	public String getNrTelefonu() {
		return nrTelefonu;
	}
	public void setNrTelefonu(String nrTelefonu) {
		this.nrTelefonu = nrTelefonu;
	}
	
	
	public Wlasciciel(int id, String imie, String nazwisko, String pesel, String ulica, String miejscowosc,
			String nrTelefonu) {
		super();
		this.id = id;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.pesel = pesel;
		this.ulica = ulica;
		this.miejscowosc = miejscowosc;
		this.nrTelefonu = nrTelefonu;
	}
	
	@Override
	public String toString() {
		return "Wlasciciel [id=" + id + ", imie=" + imie + ", nazwisko=" + nazwisko + ", pesel=" + pesel + ", ulica="
				+ ulica + ", miejscowosc=" + miejscowosc + ", nrTelefonu=" + nrTelefonu + "]";
	}
	
	
	
	
	
	
	

}
