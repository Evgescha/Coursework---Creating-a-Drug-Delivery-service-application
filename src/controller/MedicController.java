package controller;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import dao.MedicDAO;
import defaultOperation.StandartFrameOperation;
import entity.Medic;
import entity.MedicType;
import forms.MedicFrame;
import tableModal.MedicTableModal;

public class MedicController extends StandartFrameOperation {

	public MedicDAO DAO;

	public MedicController(JFrame frame) {
		super(frame);
		try {
			DAO = new MedicDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionSearchButton(String name, JTable table) {
		try {
			List<Medic> list = null;

			if (name != null && name.trim().length() > 0)
				list = DAO.search(name);
			else
				list = DAO.readAll();

			MedicTableModal model = new MedicTableModal(list);
			table.setModel(model);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}

	

	public void actionCreateButton(String name, String sizetype, String medictype) {
		if (name != null && name.length() > 0) {
			Medic entity = new Medic(name, new MedicType(medictype), sizetype);
			try {
				DAO.create(entity);
				refrechView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно добавлено", "Успех",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void actionUpdateButton(Long id, String name, String sizetype, String medictype) {
		if (name != null) {
			try {
				Medic entity = new Medic(name, new MedicType(medictype), sizetype);
				entity.setId(id);
				DAO.update(entity);
				refrechView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно обновлено", "Успех",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
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
		((MedicFrame) getFrame()).refreshView();
	}

	public MedicDAO getDAO() {
		return DAO;
	}

	public void back() {
		switchVisible();
		ApplicationController.mainAdminController.switchVisible();

	}
}
