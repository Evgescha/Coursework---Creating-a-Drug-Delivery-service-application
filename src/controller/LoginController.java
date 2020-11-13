package controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import defaultOperation.StandartFrameOperation;
import entity.Users;

public class LoginController extends StandartFrameOperation {

	public LoginController(JFrame frame) {
		super(frame);
	}

	public void btnLoginAction(String fio, String phone) {
		Users login = ApplicationController.UsersController.login(fio, phone);
		if (login != null) {
			if (login.getRole().equals("user")) {
				switchVisible();
				ApplicationController.UserOrderController.user=login;
				ApplicationController.mainUsersController.switchVisible();
			} else {
				switchVisible();
				ApplicationController.mainAdminController.switchVisible();

			}
		} else
			JOptionPane.showMessageDialog(getFrame(), "Нет таких пользователей", "Не найдено",
					JOptionPane.ERROR_MESSAGE);
	}

	public void btnRegisterAction() {
		ApplicationController.RegistrationController.switchVisible();
		switchVisible();

	}

}
