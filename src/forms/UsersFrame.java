package forms;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
import entity.Users;

import javax.swing.BoxLayout;
/**
 * форма отображения 
 * @author admin
 *
 */
public class UsersFrame extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JTextField textField_3;

	public UsersFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				back();
			}
		});
		setTitle("Пользователи");
		setBounds(100, 100, 776, 303);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel_4 = new JLabel("Введите название для поиска");
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

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(3, 3, 3, 3));
		getContentPane().add(panel_1, BorderLayout.WEST);

		JLabel lblNewLabel = new JLabel("ФИО");

		JLabel lblNewLabel_1 = new JLabel("Адрес");
	

		JButton btnNewButton_2 = new JButton("Обновить");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionUpdateButton();
			}
		});

		JButton btnNewButton = new JButton("Добавить");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCreateButton();
			}
		});

		JButton btnNewButton_3 = new JButton("Удалить");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionDeleteButton();
			}
		});
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		panel_1.add(lblNewLabel);
		
				textField_1 = new JTextField();
				textField_1.setColumns(10);
				panel_1.add(textField_1);
		panel_1.add(lblNewLabel_1);
		
				textField_2 = new JTextField();
				textField_2.setColumns(10);
				panel_1.add(textField_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("Телефон");
		panel_1.add(lblNewLabel_1_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		panel_1.add(textField_3);
		panel_1.add(btnNewButton);
		panel_1.add(btnNewButton_2);
		panel_1.add(btnNewButton_3);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				actionTableMouseClicked();
			}
		});
		getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
		 
	}

	private void actionDeleteButton() {
		if (table.getRowCount() > 0 && table.getSelectedRowCount() > 0) {
			int row = table.getSelectedRow();
			String id = table.getModel().getValueAt(row, 0).toString();
			ApplicationController.UsersController.actionDeleteButton(Long.parseLong(id));
		}
	}

	private void actionTableMouseClicked() {
		if (table.getRowCount() > 0)
			try {
				int row = table.getSelectedRow();
				Users user= (Users) table.getModel().getValueAt(row, 99);
				textField_1.setText(user.getFio());
				textField_2.setText(user.getAdres());
				textField_3.setText(user.getPhone());
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
	}

	private void actionUpdateButton() {
		int column = 0;
		int row = table.getSelectedRow();
		Long id = Long.parseLong(table.getModel().getValueAt(row, column).toString());
		ApplicationController.UsersController.actionUpdateButton(textField_1.getText(), textField_2.getText(), textField_3.getText(),id);
	}

	private void actionCreateButton() {
		ApplicationController.UsersController.actionCreateButton(textField_1.getText(), textField_2.getText(), textField_3.getText());
	}

	private void actionSearchButton() {
		ApplicationController.UsersController.actionSearchButton(textField.getText().trim(), table);
		
	}

	public void refreshView() {
		
		ApplicationController.UsersController.actionSearchButton("", table);
	}
	
	public void back() {
		ApplicationController.UsersController.back();
	}
}
