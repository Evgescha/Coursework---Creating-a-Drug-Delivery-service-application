package forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.ApplicationController;

public class LoginFrame extends JFrame{
	private JTextField textField;
	private JTextField textField_1;

	
	/**
	 * Create the application.
	 */
	public LoginFrame() {
		setTitle("Вход");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setBounds(100, 100, 433, 215);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Логин (ФИО)");
		lblNewLabel.setBounds(10, 11, 157, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Пароль (Телефон)");
		lblNewLabel_1.setBounds(10, 50, 157, 14);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(177, 8, 231, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(177, 47, 231, 20);
		getContentPane().add(textField_1);
		
		JButton btnNewButton = new JButton("Войти");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLoginAction();
			}


		});
		btnNewButton.setBounds(10, 78, 398, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Регистрация");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRegisterAction();
			}


		});
		btnNewButton_1.setBounds(10, 112, 398, 23);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Выход");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExitAction();
			}

		});
		btnNewButton_2.setBounds(10, 148, 398, 23);
		getContentPane().add(btnNewButton_2);
	}

	private void btnLoginAction() {
		if(textField.getText().length()>0 &&textField_1.getText().length()>0 )
		ApplicationController.LoginController.btnLoginAction(textField.getText(),textField_1.getText());
		
	}
	private void btnRegisterAction() {
		ApplicationController.LoginController.btnRegisterAction();
		
	}
	private void btnExitAction() {
		System.exit(0);
		
	}
}
