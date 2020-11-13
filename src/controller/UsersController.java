package controller;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import dao.UserDAO;
import defaultOperation.StandartFrameOperation;
import entity.Users;
import forms.MedicFrame;
import forms.UsersFrame;
import tableModal.UsersTableModal;

public class UsersController extends StandartFrameOperation{

	UserDAO DAO;
	
	public UsersController(JFrame frame) {
		super(frame);
		try {
			DAO = new UserDAO();
		} catch (Exception e) {e.printStackTrace();}
	}

	public void actionSearchButton(String fio, JTable table) {
		try {
			List<Users> list = null;

			if (fio!= null && fio.trim().length() > 0)
				list = DAO.searchByFIO(fio);
			else
				list = DAO.readAll();

			UsersTableModal model = new UsersTableModal(list);
			table.setModel(model);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}
	public Users login(String fio,String phone) {
		Users user = null;
		try {
			if (fio!= null && fio.trim().length() > 0)
				user = DAO.searchByFIOAndPhone(fio,phone);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Не найдено", JOptionPane.ERROR_MESSAGE);
		}
		return user;
	}

	public void actionCreateButton(String fio, String adres, String phone) {
		if (fio.length() > 0 && phone.length() > 0 && adres.length() > 0) {
			Users entity = new Users(fio, adres, phone,"user");
			try {
				DAO.create(entity);
				refrechView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно добавлено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void actionUpdateButton(String fio, String adres, String phone, Long id) {
		if (fio.length() > 0 && phone.length() > 0 && adres.length() > 0 && id>0) {
			try {
				Users entity= new Users(fio, adres, phone,"user");
				entity.setId(id);
				DAO.update(entity);
				refrechView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно обновлено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void actionDeleteButton(long id) {
		if(id>0)
			try {
				DAO.Delete(id);
				refrechView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно удалено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка. Возможно элемент используется в другой таблице" , "Ошибка", JOptionPane.ERROR_MESSAGE);
				System.out.println(e);
			}
		
	}	
	@Override
	public void switchVisible() {
		super.switchVisible();
		refrechView();
	}

	public void refrechView() {
		((UsersFrame) getFrame()).refreshView();
	}
	public UserDAO getDAO() {return DAO;}

	public void back() {
		switchVisible();
		ApplicationController.mainAdminController.switchVisible();	
		
	}
}
