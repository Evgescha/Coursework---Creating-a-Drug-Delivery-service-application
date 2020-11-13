package forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.ApplicationController;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainUsersFrame extends JFrame{

		/**
	 * Create the application.
	 */
	public MainUsersFrame() {
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					ApplicationController.mainUsersController.btnBackAction();
				}
			});
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
	setBounds(100, 100, 233, 147);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	getContentPane().setLayout(null);
	
	JButton btnNewButton = new JButton("Новый заказ");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		btnNewOrderAction();
		}

	});
	btnNewButton.setBounds(10, 11, 186, 23);
	getContentPane().add(btnNewButton);
	
	JButton btnNewButton_1 = new JButton("Мои заказы");
	btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			btnMyOrdersAction();
		}

	});
	btnNewButton_1.setBounds(10, 45, 186, 23);
	getContentPane().add(btnNewButton_1);
	
	JButton btnNewButton_1_1 = new JButton("Назад");
	btnNewButton_1_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			btnBackAction();
		}

	});
	btnNewButton_1_1.setBounds(10, 74, 186, 23);
	getContentPane().add(btnNewButton_1_1);
	}
	private void btnNewOrderAction() {
		ApplicationController.mainUsersController.btnNewOrderAction();
		
	}
	private void btnMyOrdersAction() {
		ApplicationController.mainUsersController.btnMyOrdersAction();
		
	}
	private void btnBackAction() {
		 ApplicationController.mainUsersController.btnBackAction();
		
	}

}
