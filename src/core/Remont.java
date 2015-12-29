package core;

public class Remont {

	private int id;
	private int id_bloku;
	private String opis;
	private float kwota;
	
	
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
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public float getKwota() {
		return kwota;
	}
	public void setKwota(float kwota) {
		this.kwota = kwota;
	}
	
	public Remont(int id, int id_bloku, String opis, float kwota) {
		super();
		this.id = id;
		this.id_bloku = id_bloku;
		this.opis = opis;
		this.kwota = kwota;
	}
	
	public Remont(int id_bloku, String opis, float kwota) {
		super();
		this.id_bloku = id_bloku;
		this.opis = opis;
		this.kwota = kwota;
	}
	
	@Override
	public String toString() {
		return "Remont [id=" + id + ", id_bloku=" + id_bloku + ", opis=" + opis + ", kwota=" + kwota + "]";
	}
	
	
	
	
	
}
