package tableModal;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.MedicType;

/**
 * 
 */
public class MedicTypeTableModal extends AbstractTableModel {

	public static final int TYPE_COL = 1;

	private final String[] columnNames = {"Тип"};
	private List<MedicType> list;

	public MedicTypeTableModal(List<MedicType> theProviders) {
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

		MedicType temp = list.get(row);

		switch (col) {
		
		case TYPE_COL:			
			return temp.getType();
		
		
		default:
			return temp;
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
