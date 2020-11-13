package controller;

import javax.swing.JFrame;

import defaultOperation.StandartFrameOperation;

public class RegistrationController extends StandartFrameOperation {

	public RegistrationController(JFrame frame) {
		super(frame);
	}

	public void btnRegisterAction(String fio, String adres,String phone) {
		ApplicationController.UsersController.actionCreateButton(fio, adres, phone);
		
	}
	public void btnBackAction() {
		switchVisible();
		ApplicationController.LoginController.switchVisible();
	}
}
