package core;

public class Blok {
	
	private int id;
	private String ulica;
	private String ulica_nr;
	private String miejscowosc;
	private String nazwa_spoldzielni = "Stonka";
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getNazwa_spoldzielni() {
		return nazwa_spoldzielni;
	}
	public void setNazwa_spoldzielni(String nazwa_spoldzielni) {
		this.nazwa_spoldzielni = nazwa_spoldzielni;
	}
	public String getUlica_nr() {
		return ulica_nr;
	}
	public void setUlica_nr(String ulica_nr) {
		this.ulica_nr = ulica_nr;
	}
	
	
	public Blok(int id, String ulica, String ulica_nr, String miejscowosc, String nazwa_spoldzielni) {
		super();
		this.id = id;
		this.ulica = ulica;
		this.ulica_nr = ulica_nr;
		this.miejscowosc = miejscowosc;
		this.nazwa_spoldzielni = nazwa_spoldzielni;
	}
	
	
	public Blok(String ulica, String ulica_nr, String miejscowosc, String nazwa_spoldzielni) {
		super();
		this.ulica = ulica;
		this.ulica_nr = ulica_nr;
		this.miejscowosc = miejscowosc;
		this.nazwa_spoldzielni = nazwa_spoldzielni;
	}
	@Override
	public String toString() {
		return "\nBlok [id=" + id + ", ulica=" + ulica + ", ulica_nr=" + ulica_nr + ", miejscowosc=" + miejscowosc
				+ ", nazwa_spoldzielni=" + nazwa_spoldzielni + "] ";
	}
	

	
	
	

}
