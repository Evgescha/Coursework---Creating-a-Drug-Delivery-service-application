package controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import dao.OrderDAO;
import defaultOperation.StandartFrameOperation;
import entity.Medic;
import entity.Order;
import entity.Users;
import forms.OrderFrame;
import tableModal.OrderTableModal;

public class OrderController extends StandartFrameOperation {

	OrderDAO DAO;

	public OrderController(JFrame frame) {
		super(frame);
		try {
			DAO = new OrderDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionSearchButton(String userFIO, JTable table) {
		try {
			List<Order> list = null;

			if (userFIO != null && userFIO.trim().length() > 0)
				list = DAO.search(userFIO);
			else
				list = DAO.readAll();

			OrderTableModal model = new OrderTableModal(list);
			table.setModel(model);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionCreateButton(Users users, Medic medic, int count, Date date, boolean isGot) {
		if (users!=null &&medic!=null && count > 0) {
			Order entity = new Order ( users,  medic,  count,  date, isGot);
			try {
				DAO.create(entity);
				refrechView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно добавлено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void actionUpdateButton(Long id,Users users, Medic medic, int count, Date date, boolean isGot) {
		if (users!=null &&medic!=null && count > 0) {
			try {
				Order entity = new Order ( users,  medic,  count,  date, isGot);
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
		((OrderFrame) getFrame()).refreshView();
	}

	public OrderDAO getDAO() {
		return DAO;
	}

	public void back() {
		switchVisible();
		ApplicationController.mainAdminController.switchVisible();	
		
	}

	public void actionSearchUsersOrderButton(String medic, boolean isGot, JTable table) {
		try {
			List<Order> list = null;

			
			list = DAO.searchMyUserAndMedic(medic,isGot);
			if(list==null)list=new ArrayList<Order>();

			OrderTableModal model = new OrderTableModal(list);
			table.setModel(model);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
