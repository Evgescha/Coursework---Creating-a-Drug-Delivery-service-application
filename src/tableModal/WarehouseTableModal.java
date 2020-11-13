package tableModal;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Warehouse;

/**
 * 
 */
public class WarehouseTableModal extends AbstractTableModel {

	public  static final int ID_COL = 0;
	public static final int medic = 1;
	public  static final int count = 2;
	public  static final int dateGetting = 3;
	public  static final int dateDead = 4;
	public  static final int priceGetting = 5;
	public  static final int pricaeSelling = 6;

	private final String[] columnNames = {"Id","Лекарство", "Количество", "Дата привоза","Годен до","Привоз по","Продажа по" };
	private List<Warehouse> list;

	public WarehouseTableModal(List<Warehouse> theProviders) {
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

		Warehouse temp = list.get(row);

		switch (col) {
		case ID_COL:
			return temp.getId();
		case medic:
			return temp.getMedic();
		case count:
			return temp.getCount();
		case dateGetting:
			return temp.getDateGetting();
		case dateDead:
			return temp.getDateDead();
		case priceGetting:
			return temp.getPriceGetting();
		case pricaeSelling:
			return temp.getPricaeSelling();
		
		default:
			return temp;
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
