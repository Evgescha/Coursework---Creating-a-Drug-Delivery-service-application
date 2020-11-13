package tableModal;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Order;

/**
 * 
 * @author admin
 * модель таблицы заказа
 */
public class OrderTableModal extends AbstractTableModel {

	public  static final int ID_COL = 0;
	public static final int USERS_COL = 1;
	public  static final int MEDIC_COL = 2;
	public  static final int COUNT_COL = 3;
	public  static final int DATES_COL = 4;
	public static final int IS_GOT_COL = 5;

	private final String[] columnNames = {"Id","Пользователь", "Лекарство", "Количество", "Дата доставки","Доставлено" };
	private List<Order> list;

	public OrderTableModal(List<Order> theProviders) {
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

		Order temp = list.get(row);

		switch (col) {
		case ID_COL:
			return temp.getId();
		case USERS_COL:
			return temp.getUsers().getFio();
		case MEDIC_COL:
			return temp.getMedic().getName();
		case COUNT_COL:
			return temp.getCount();
		case DATES_COL:
			return temp.getDate();
		case IS_GOT_COL:
			return temp.isGot();
		
		default:
			return temp;
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
