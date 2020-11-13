package forms;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.ApplicationController;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * форма главного меню
 * @author admin
 *
 */
public class MainAdminFrame extends JFrame{

	public MainAdminFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				back();
			}

		});
		initialize();	
	}

	private void initialize() {
		
		setTitle("Главная");
		setBounds(100, 100, 233, 257);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Типы лекарств");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMedicTypeAction();
			}

		
		});
		btnNewButton.setBounds(10, 11, 197, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Лекарства");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMedicAction();
			}

		});
		btnNewButton_1.setBounds(10, 45, 197, 23);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Склад");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnWarehouseAction();
				}

		});
		btnNewButton_2.setBounds(10, 79, 197, 23);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Покупатели");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUsersAction();
				}
		});
		btnNewButton_3.setBounds(10, 113, 197, 23);
		getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Заказы");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnOrderAction();
				}

		});
		btnNewButton_4.setBounds(10, 147, 197, 23);
		getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Истекает срок");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDateDeadAction();
				}

		});
		btnNewButton_5.setBounds(10, 181, 197, 23);
		getContentPane().add(btnNewButton_5);
	}
	
	
	private void btnMedicTypeAction() {
		ApplicationController.mainAdminController.btnMedicTypeAction();
		
	}
	
	private void btnMedicAction() {
		ApplicationController.mainAdminController.btnMedicAction();
		
	}
	private void btnWarehouseAction() {
		ApplicationController.mainAdminController.btnWarehouseAction();
		
	}
	
	private void btnUsersAction() {
		ApplicationController.mainAdminController.btnUsersAction();
		
	}
	private void btnOrderAction() {
		ApplicationController.mainAdminController.btnOrderAction();
		
	}
	private void btnDateDeadAction() {
		ApplicationController.mainAdminController.btnDateDeadAction();
		
	}
	public void  back() {
		ApplicationController.mainAdminController.back();
		
	}
}
