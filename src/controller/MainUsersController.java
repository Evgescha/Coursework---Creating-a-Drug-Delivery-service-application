package controller;

import javax.swing.JFrame;

import defaultOperation.StandartFrameOperation;

public class MainUsersController extends StandartFrameOperation {

	public MainUsersController(JFrame frame) {
		super(frame);
	}
	public void btnNewOrderAction() {
		switchVisible();
		ApplicationController.UserOrderController.switchVisible();
		
	}
	public void btnMyOrdersAction() {
		ApplicationController.UserPersonalOrderController.switchVisible();
		switchVisible();
		
	}
	public void btnBackAction() {
		switchVisible();
		ApplicationController.LoginController.switchVisible();
		
	}
}
