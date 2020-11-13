package forms;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import controller.ApplicationController;
import entity.Medic;
import entity.MedicType;
import entity.Warehouse;

import com.toedter.calendar.JDateChooser;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserPersonalOrderFrame extends JFrame{
	private JTextField textField;
	private JTable table;

	public UserPersonalOrderFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				back();
			}
		});
		setTitle("Мои заказы");
		setBounds(100, 100, 742, 428);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel_4 = new JLabel("Введите название лекарства для поиска");
		panel.add(lblNewLabel_4);

		textField = new JTextField();
		textField.setColumns(10);
		panel.add(textField);

		JButton btnNewButton_1 = new JButton("Поиск");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionSearchButton();
			}
		});
		panel.add(btnNewButton_1);

		table = new JTable();
		
		getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
		 
	}

	

	private void actionSearchButton() {
		ApplicationController.UserPersonalOrderController.actionSearchUsersOrderButton(textField.getText().trim(),true, table);
	}

	public void refreshView() {
		
		ApplicationController.UserPersonalOrderController.actionSearchUsersOrderButton("".trim(),true, table);
	}
	
	public void back() {
		ApplicationController.UserPersonalOrderController.back();
	}
}
