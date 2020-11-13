package controller;

import javax.swing.JFrame;

import defaultOperation.StandartFrameOperation;
import forms.MedicDeadDate;

public class MainAdminController extends StandartFrameOperation {

	public MainAdminController(JFrame frame) {
		super(frame);
	}

	public void btnMedicTypeAction() {
		switchVisible();
		ApplicationController.MedicTypeController.switchVisible();		
	}
	
	public void btnMedicAction() {
		switchVisible();
		ApplicationController.MedicController.switchVisible();		
		
	}
	public void btnWarehouseAction() {
		switchVisible();
		ApplicationController.warehouseController.switchVisible();		
		
	}
	
	public void btnUsersAction() {
		switchVisible();
		ApplicationController.UsersController.switchVisible();		
		
	}
	public void btnOrderAction() {
		switchVisible();
		ApplicationController.orderController.switchVisible();		
		
	}
	public void btnDateDeadAction() {
		switchVisible();
		ApplicationController.MedicDateDeadController.switchVisible();		
		
	}
	public void back() {
		switchVisible();
		ApplicationController.LoginController.switchVisible();		
		
	}
}
