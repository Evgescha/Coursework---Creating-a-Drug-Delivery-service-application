package forms;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import controller.ApplicationController;
import controller.UserOrderController;
import entity.Medic;
import entity.Order;
import entity.Users;
/**
 * @author admin
 *
 */
public class UserOrderFrame extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JComboBox<Medic> comboBox_1;
	private JDateChooser dateChooser;

	public UserOrderFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				back();
			}
		});
		setTitle("Заказы");
		setBounds(100, 100, 823, 428);
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

		textField_1 = new JTextField();
		textField_1.setColumns(10);
	

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
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_1_1 = new JLabel("Лекарство");
		panel_1.add(lblNewLabel_1_1);
		
		comboBox_1 = new JComboBox();
		panel_1.add(comboBox_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Количество");
		panel_1.add(lblNewLabel_1_2);
		panel_1.add(textField_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Дата доставки");
		panel_1.add(lblNewLabel_1_2_1);
		
		 dateChooser = new JDateChooser();
		panel_1.add(dateChooser);
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
			ApplicationController.orderController.actionDeleteButton(Long.parseLong(id));
			refreshView();
		}
	}

	private void actionTableMouseClicked() {
		if (table.getRowCount() > 0)
			try {
				int row = table.getSelectedRow();
				Order entity = (Order) table.getModel().getValueAt(row, 99);
				
				Medic medic= ApplicationController.MedicController.getDAO().read(entity.getMedic().getId()).get(0);
				
				comboBox_1.getModel().setSelectedItem(medic);
				textField_1.setText(entity.getCount()+"");
				dateChooser.setDate(entity.getDate());				
			} catch (Exception e) {
			}
	}

	private void actionUpdateButton() {
		int column = 0;
		int row = table.getSelectedRow();
		Long id = Long.parseLong(table.getModel().getValueAt(row, column).toString());
		ApplicationController.UserOrderController.actionUpdateButton(id,UserOrderController.user,(Medic)comboBox_1.getSelectedItem(),Integer.parseInt(textField_1.getText()),
				new Date(dateChooser.getDate().getTime()),false);
		refreshView();
	}

	private void actionCreateButton() {
		ApplicationController.orderController.actionCreateButton(UserOrderController.user,(Medic)comboBox_1.getSelectedItem(),Integer.parseInt(textField_1.getText()),
				new Date(dateChooser.getDate().getTime()),false );
		refreshView();
	}

	private void actionSearchButton() {
		ApplicationController.orderController.actionSearchUsersOrderButton(textField.getText().trim(),false, table);
		refreshComboBoxes();
	}

	public void refreshView() {
		
		ApplicationController.orderController.actionSearchUsersOrderButton("",false, table);
		refreshComboBoxes();
	}
	public void refreshComboBoxes() {
		try {
			
			comboBox_1.setModel(
					new DefaultComboBoxModel(ApplicationController.MedicController.getDAO().readAll().toArray()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void back() {
		
		ApplicationController.UserOrderController.back();
	}
}
