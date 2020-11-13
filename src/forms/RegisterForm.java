package forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.ApplicationController;

public class RegisterForm extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;


	/**
	 * Create the application.
	 */
	public RegisterForm() {
		setTitle("Регистрация");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setBounds(100, 100, 298, 240);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ФИО");
		lblNewLabel.setBounds(10, 11, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Адрес");
		lblNewLabel_1.setBounds(10, 49, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Телефон");
		lblNewLabel_2.setBounds(10, 93, 60, 14);
		getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(88, 8, 173, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(88, 46, 173, 20);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(88, 90, 173, 20);
		getContentPane().add(textField_2);
		
		JButton btnNewButton = new JButton("Регистрация");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			btnRegisterAction();
			}

			
		});
		btnNewButton.setBounds(10, 127, 251, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Назад");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			btnBackAction();
			}

			
		});
		btnNewButton_1.setBounds(10, 161, 251, 23);
		getContentPane().add(btnNewButton_1);
	}
	
	private void btnRegisterAction() {
		if(textField.getText().length()>0 &&
				textField_1.getText().length()>0 &&
				textField_2.getText().length()>0 )
			ApplicationController.RegistrationController.btnRegisterAction(textField.getText(),textField_1.getText(),textField_2.getText());
		
	}
	private void btnBackAction() {
		ApplicationController.RegistrationController.btnBackAction();
		
	}
}
