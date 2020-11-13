package controller;

import java.sql.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import dao.WarehouseDAO;
import defaultOperation.StandartFrameOperation;
import entity.Medic;
import entity.Warehouse;
import forms.WarehouseFrame;
import tableModal.MedicTableModal;
import tableModal.UsersTableModal;
import tableModal.WarehouseTableModal;

public class WarehouseController extends StandartFrameOperation {

	WarehouseDAO DAO;

	public WarehouseController(JFrame frame) {
		super(frame);
		try {
			DAO = new WarehouseDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionSearchButton(String medicname, JTable table) {
		try {
			List<Warehouse> list = null;

			if (medicname.trim().length() > 0)
				list = DAO.search(medicname);
			else
				list = DAO.readAll();

			WarehouseTableModal model = new WarehouseTableModal(list);
			table.setModel(model);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void searchByDeadDateDay(int day, JTable table) {
		try {
			List<Warehouse> list = null;

			list = DAO.searchByDeadDateDay(day);

			WarehouseTableModal model = new WarehouseTableModal(list);
			table.setModel(model);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public void actionCreateButton(Long medic, int count, Date dateGetting, Date dateDead, float priceGetting,
			float pricaeSelling) {
		try {
			if (medic != null && count >= 0 && priceGetting > 0 && pricaeSelling > 0) {
				Medic md = ApplicationController.MedicController.DAO.read(medic).get(0);
				Warehouse entity = new Warehouse(md, count, dateGetting, dateDead, priceGetting, pricaeSelling);
				DAO.create(entity);
				refrechView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно добавлено", "Успех",
						JOptionPane.INFORMATION_MESSAGE);

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void actionUpdateButton(Long medic, int count, Date dateGetting, Date dateDead, float priceGetting,
			float pricaeSelling, Long id) {
		try {
			Medic md = ApplicationController.MedicController.DAO.read(medic).get(0);
			Warehouse entity = new Warehouse(md, count, dateGetting, dateDead, priceGetting, pricaeSelling);
			entity.setId(id);
			DAO.update(entity);
			refrechView();
			JOptionPane.showMessageDialog(getFrame(), "Успешно обновлено", "Успех", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionDeleteButton(long id) {
		if (id > 0)
			try {
				DAO.Delete(id);
				refrechView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно удалено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка. Возможно элемент используется в другой таблице",
						"Ошибка", JOptionPane.ERROR_MESSAGE);
				System.out.println(e);
			}

	}

	
	@Override
	public void switchVisible() {
		super.switchVisible();
		refrechView();
	}
	
	public void refrechView() {
		((WarehouseFrame) getFrame()).refreshView();
	}

	public WarehouseDAO getDAO() {
		return DAO;
	}

	public void back() {
		switchVisible();
		ApplicationController.mainAdminController.switchVisible();	
	}

	
}
