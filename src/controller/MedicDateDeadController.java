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

public class MedicDateDeadController extends StandartFrameOperation {

	MedicTypeDAO DAO;

	public MedicDateDeadController(JFrame frame) {
		super(frame);
		try {
			DAO = new MedicTypeDAO();
		} catch (Exception e) {e.printStackTrace();}
	}



	public MedicTypeDAO getDAO() {
		return DAO;
	}

	public void back() {
		switchVisible();
		ApplicationController.mainAdminController.switchVisible();	
		
	}

}
