package tableModal;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Users;
/**
 * модель таблицы клиентов
 * @author admin
 *
 */
public class UsersTableModal extends AbstractTableModel {

	private static final int ID_COL = 0;
	private static final int FIO_COL = 1;
	private static final int ADRES_COL = 2;
	private static final int PHONE_COL = 3;
	private static final int ROLE_COL = 4;

	private final String[] columnNames = {"ИД", "ФИО", "Адрес", "Телефон", "Роль" };
	private List<Users> list;

	public UsersTableModal(List<Users> theProviders) {
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

		Users temp = list.get(row);

		switch (col) {
		case ID_COL:
			return temp.getId();
		case FIO_COL:
			return temp.getFio();
		case ADRES_COL:
			return temp.getAdres();
		case PHONE_COL:
			return temp.getPhone();
		case ROLE_COL:
			return temp.getRole();
		default:
			return temp;
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
