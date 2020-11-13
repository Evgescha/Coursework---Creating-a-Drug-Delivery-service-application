package controller;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import dao.MedicTypeDAO;
import defaultOperation.StandartFrameOperation;
import entity.MedicType;
import forms.MedicTypeFrame;
import tableModal.MedicTypeTableModal;

public class MedicTypeController extends StandartFrameOperation {

	MedicTypeDAO DAO;

	public MedicTypeController(JFrame frame) {
		super(frame);
		try {
			DAO = new MedicTypeDAO();
		} catch (Exception e) {e.printStackTrace();}
	}

	public void actionSearchButton(String name, JTable table) {
		try {
			List<MedicType> list = null;

			if (name != null && name.trim().length() > 0)
				list = DAO.search(name);
			else
				list = DAO.readAll();

			MedicTypeTableModal model = new MedicTypeTableModal(list);
			table.setModel(model);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionCreateButton(String type) {
		if (type.length() > 0 ) {
			MedicType mt = new MedicType(type);
			try {
				DAO.create(mt);
				((MedicTypeFrame) getFrame()).refreshView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно добавлено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void actionUpdateButton(String typeNew, String typeOld) {
		if (typeNew.length()>0 ) {
			try {
				MedicType mt = new MedicType(typeNew);
				DAO.update(typeNew,typeOld);
				((MedicTypeFrame) getFrame()).refreshView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно обновлено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void actionDeleteButton(String type) {
		
			try {
				DAO.Delete(type);
				((MedicTypeFrame) getFrame()).refreshView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно удалено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка. Возможно элемент используется в другой таблице" , "Ошибка", JOptionPane.ERROR_MESSAGE);
				System.out.println(e);
			}
		
	}

	public MedicTypeDAO getDAO() {
		return DAO;
	}

	public void back() {
		switchVisible();
		ApplicationController.mainAdminController.switchVisible();	
		
	}

}
