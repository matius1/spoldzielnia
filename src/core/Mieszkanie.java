package core;

public class Mieszkanie {
	private int id;
	private int id_bloku;
	private int nr_mieszkania;
	private int pietro;
	private float powierzchnia;
	private int ilosc_pokoi;
	private float pow_piwnicy;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_bloku() {
		return id_bloku;
	}
	public void setId_bloku(int id_bloku) {
		this.id_bloku = id_bloku;
	}
	public int getNr_mieszkania() {
		return nr_mieszkania;
	}
	public void setNr_mieszkania(int nr_mieszkania) {
		this.nr_mieszkania = nr_mieszkania;
	}
	public int getPietro() {
		return pietro;
	}
	public void setPietro(int pietro) {
		this.pietro = pietro;
	}
	public float getPowierzchnia() {
		return powierzchnia;
	}
	public void setPowierzchnia(float powierzchnia) {
		this.powierzchnia = powierzchnia;
	}
	public int getIlosc_pokoi() {
		return ilosc_pokoi;
	}
	public void setIlosc_pokoi(int ilosc_pokoi) {
		this.ilosc_pokoi = ilosc_pokoi;
	}
	public float getPow_piwnicy() {
		return pow_piwnicy;
	}
	public void setPow_piwnicy(float pow_piwnicy) {
		this.pow_piwnicy = pow_piwnicy;
	}
	
	public Mieszkanie(int id, int id_bloku, int nr_mieszkania, int pietro, float powierzchnia, int ilosc_pokoi,
			float pow_piwnicy) {
		super();
		this.id = id;
		this.id_bloku = id_bloku;
		this.nr_mieszkania = nr_mieszkania;
		this.pietro = pietro;
		this.powierzchnia = powierzchnia;
		this.ilosc_pokoi = ilosc_pokoi;
		this.pow_piwnicy = pow_piwnicy;
	}
	
	public Mieszkanie(int id_bloku, int nr_mieszkania, int pietro, float powierzchnia, int ilosc_pokoi,
			float pow_piwnicy) {
		super();
		this.id_bloku = id_bloku;
		this.nr_mieszkania = nr_mieszkania;
		this.pietro = pietro;
		this.powierzchnia = powierzchnia;
		this.ilosc_pokoi = ilosc_pokoi;
		this.pow_piwnicy = pow_piwnicy;
	}
	
	@Override
	public String toString() {
		return "\nMieszkanie [id=" + id + ", id_bloku=" + id_bloku + ", nr_mieszkania=" + nr_mieszkania + ", pietro="
				+ pietro + ", powierzchnia=" + powierzchnia + ", ilosc_pokoi=" + ilosc_pokoi + ", pow_piwnicy="
				+ pow_piwnicy + "]";
	}
	
	
	

}
