package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import dao.OrderDAO;
import defaultOperation.StandartFrameOperation;
import entity.Order;
import forms.UserPersonalOrderFrame;
import tableModal.OrderTableModal;

public class UserPersonalOrderController  extends StandartFrameOperation {

	OrderDAO DAO;

	public UserPersonalOrderController(JFrame frame) {
		super(frame);
		try {
			DAO = new OrderDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void switchVisible() {
		super.switchVisible();
		refrechView();
	}
	
	public void refrechView() {
		((UserPersonalOrderFrame) getFrame()).refreshView();
	}

	public OrderDAO getDAO() {
		return DAO;
	}

	public void back() {
		switchVisible();
		ApplicationController.mainUsersController.switchVisible();	
		
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
