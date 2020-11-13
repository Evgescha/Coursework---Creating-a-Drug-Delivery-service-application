package controller;

import forms.LoginFrame;
import forms.MainAdminFrame;
import forms.MainUsersFrame;
import forms.MedicDeadDate;
import forms.MedicFrame;
import forms.MedicTypeFrame;
import forms.OrderFrame;
import forms.RegisterForm;
import forms.UserOrderFrame;
import forms.UserPersonalOrderFrame;
import forms.UsersFrame;
import forms.WarehouseFrame;

public class ApplicationController {
	// страница входа
	public static LoginController LoginController = new LoginController(new LoginFrame());
	// страница регистрации
	public static RegistrationController RegistrationController = new RegistrationController(new RegisterForm());
	// страница админа
	public static MainAdminController mainAdminController = new MainAdminController(new MainAdminFrame());
	// страница пользователя
	public static MainUsersController mainUsersController = new MainUsersController(new MainUsersFrame());
	// страница лекарств
	public static MedicController MedicController = new MedicController(new MedicFrame());
	// страница типов лекарст
	public static MedicTypeController MedicTypeController = new MedicTypeController(new MedicTypeFrame());
	// страница заказов
		public static OrderController orderController = new OrderController(new OrderFrame());
	// страница заказов клиента
	public static UserOrderController UserOrderController = new UserOrderController(new UserOrderFrame());
	// страница таблицы пользователя
	public static UsersController UsersController = new UsersController(new UsersFrame());
	// страница склада
	public static WarehouseController warehouseController = new WarehouseController(new WarehouseFrame());
	//срок годности
	public static MedicDateDeadController MedicDateDeadController = new MedicDateDeadController(new MedicDeadDate());
	//личные заказы
	public static UserPersonalOrderController UserPersonalOrderController = new UserPersonalOrderController (new UserPersonalOrderFrame() );
	
	public static void main(String[] args) {
		LoginController.showFrame();

	}

}
