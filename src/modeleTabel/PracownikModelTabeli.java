package modeleTabel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import core.Pracownik;

public class PracownikModelTabeli extends AbstractTableModel {
	
	
	public static final int OBJECT_COL = -1;
	public static final int NAZWISKO_COL = 0;
	public static final int IMIE_COL = 1;
	public static final int PESEL_COL = 5;
	public static final int ULICA_COL = 3;
	public static final int MIEJSCOWOSC_COL = 4;
	public static final int STANOWISKO_COL = 2;
	public static final int NR_KONTA_COL = 6;
	public static final int NR_TEL_COL = 7;
	
	private String[] columnNames = { "Nazwisko" ,"Imie", "Stanowisko", "Ulica", "Miejscowosc", "Pesel", "Nr Konta", "Nr Telefonu" };

	private List<Pracownik> pracownicy;
	
	
	
	public PracownikModelTabeli(List<Pracownik> pracownicy) {
		this.pracownicy = pracownicy;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		
		return pracownicy.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Pracownik tempPracownik = pracownicy.get(rowIndex);
		switch(columnIndex){
		
		case NAZWISKO_COL:
			return tempPracownik.getNazwisko();
		case IMIE_COL:
			return tempPracownik.getImie();
		case PESEL_COL:
			return tempPracownik.getPesel();
		case ULICA_COL:
			return tempPracownik.getUlica();
		case MIEJSCOWOSC_COL:
			return tempPracownik.getMiejscowosc();
		case STANOWISKO_COL:
			return tempPracownik.getStanowisko();
		case NR_KONTA_COL:
			return tempPracownik.getNrKonta();
		case NR_TEL_COL:
			return tempPracownik.getNrTelefonu();
		case OBJECT_COL:
			return tempPracownik;
		default:
			return tempPracownik.getNazwisko();
		
		}
	}
	
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
