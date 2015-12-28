package ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import core.Wlasciciel;

public class WlascicielModelTabeli extends AbstractTableModel {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int OBJECT_COL = -1;
	public static final int NAZWISKO_COL = 0;
	public static final int IMIE_COL = 1;
	public static final int PESEL_COL = 2;
	public static final int ULICA_COL = 3;
	public static final int MIEJSCOWOSC_COL = 4;
	public static final int NR_TEL_COL = 5;
	
	private String[] columnNames = { "Nazwisko" ,"Imie", "Pesel", "Ulica", "Miejscowosc", "Nr Telefonu" };

	private List<Wlasciciel> wlasciciele;
	
	
	
	public WlascicielModelTabeli(List<Wlasciciel> wlasciciele) {
		this.wlasciciele = wlasciciele;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		
		return wlasciciele.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Wlasciciel tempWlasciciel = wlasciciele.get(rowIndex);
		switch(columnIndex){
		
		case NAZWISKO_COL:
			return tempWlasciciel.getNazwisko();
		case IMIE_COL:
			return tempWlasciciel.getImie();
		case PESEL_COL:
			return tempWlasciciel.getPesel();
		case ULICA_COL:
			return tempWlasciciel.getUlica();
		case MIEJSCOWOSC_COL:
			return tempWlasciciel.getMiejscowosc();
		case NR_TEL_COL:
			return tempWlasciciel.getNrTelefonu();
		case OBJECT_COL:
			return tempWlasciciel;
		default:
			return tempWlasciciel.getNazwisko();
		
		}
	}
	
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	

}
