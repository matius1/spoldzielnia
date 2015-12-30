package modeleTabel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import core.Mieszkanie;

public class MieszkanieModelTabeli extends AbstractTableModel {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int OBJECT_COL = -1;
	public static final int ID_COL = 0;
	public static final int ID_BLOKU_COL = 1;
	public static final int NR_MIESZKANIA_COL = 2;
	public static final int PIETRO_COL = 3;
	public static final int POWIERZCHNIA_COL = 4;
	public static final int IL_POKOI_COL = 5;
	public static final int POW_PIWNICY_COL = 6;
	
	private String[] columnNames = { "ID", "ID BLOKU" ,"NR MIESZKANIA", "PIETRO", "POWIERZCHNIA", "ILOSC POKOI", "POW PIWNICY" };

	private List<Mieszkanie> mieszkanie;
	
	
	
	public MieszkanieModelTabeli(List<Mieszkanie> mieszkanie) {
		this.mieszkanie = mieszkanie;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		
		return mieszkanie.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Mieszkanie tempMieszkanie = mieszkanie.get(rowIndex);
		switch(columnIndex){
		case ID_COL:
			return tempMieszkanie.getId();
		case ID_BLOKU_COL:
			return tempMieszkanie.getId_bloku();
		case NR_MIESZKANIA_COL:
			return tempMieszkanie.getNr_mieszkania();
		case PIETRO_COL:
			return tempMieszkanie.getPietro();
		case POWIERZCHNIA_COL:
			return tempMieszkanie.getPowierzchnia();
		case IL_POKOI_COL:
			return tempMieszkanie.getIlosc_pokoi();
		case POW_PIWNICY_COL:
			return tempMieszkanie.getPow_piwnicy();
		case OBJECT_COL:
			return tempMieszkanie;
		default:
			return tempMieszkanie.getId();
		
		}
	}
	
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	

}
