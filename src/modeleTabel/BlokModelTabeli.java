package modeleTabel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import core.Blok;


public class BlokModelTabeli extends AbstractTableModel {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int OBJECT_COL = -1;
	public static final int ID_COL = 0;
	public static final int ULICA_COL = 1;
	public static final int ULICA_NR_COL = 2;
	public static final int MIEJSCOWOSC_COL = 3;
	public static final int SPOLDZIELNIA_COL = 4;
	
	private String[] columnNames = { "ID", "Ulica", "Numer", "Miejscowosc", "Spoldzielnia" };

	private List<Blok> bloki;
	
	
	
	public BlokModelTabeli(List<Blok> bloki) {
		this.bloki = bloki;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		
		return bloki.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Blok tempBlok = bloki.get(rowIndex);
		switch(columnIndex){
		case ID_COL:
			return tempBlok.getId();
		case ULICA_COL:
			return tempBlok.getUlica();
		case ULICA_NR_COL:
			return tempBlok.getUlica_nr();
		case MIEJSCOWOSC_COL:
			return tempBlok.getMiejscowosc();
		case SPOLDZIELNIA_COL:
			return tempBlok.getNazwa_spoldzielni();
			
		case OBJECT_COL:
			return tempBlok;
		default:
			return tempBlok.getId();
		
		}
	}
	
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	

}
