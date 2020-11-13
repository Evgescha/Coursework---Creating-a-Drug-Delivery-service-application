package tableModal;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Medic;
import entity.Order;

/**
 * 
 */
public class MedicTableModal extends AbstractTableModel {

	public  static final int ID_COL = 0;
	public static final int NAME_COL = 1;
	public  static final int TYPE_COL = 2;
	public  static final int SIZE_TYPE_COL = 3;

	private final String[] columnNames = {"Id","Название", "Тип", "Объем" };
	private List<Medic> list;

	public MedicTableModal(List<Medic> theProviders) {
		list = theProviders;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Medic temp = list.get(row);

		switch (col) {
		case ID_COL:
			return temp.getId();
		case NAME_COL:
			return temp.getName();
		case TYPE_COL:
			return temp.getType();
		case SIZE_TYPE_COL:
			return temp.getsizeType();
		
		default:
			return temp;
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
