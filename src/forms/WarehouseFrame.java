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

public class WarehouseFrame extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	JComboBox<Medic> comboBox;
	private JTextField textField_3;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;

	public WarehouseFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				back();
			}
		});
		setTitle("Склад");
		setBounds(100, 100, 877, 428);
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
		
		dateChooser = new JDateChooser();
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
				
				JLabel lblNewLabel_1_1 = new JLabel("Лекарство");
				panel_1.add(lblNewLabel_1_1);
				
				comboBox = new JComboBox();
				panel_1.add(comboBox);
				
						JLabel lblNewLabel = new JLabel("Количество");
						panel_1.add(lblNewLabel);
		
				textField_1 = new JTextField();
				textField_1.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent keyEvent) {
						char c = keyEvent.getKeyChar();
						if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
							keyEvent.consume();
						}
					}
				});
				textField_1.setColumns(10);
				panel_1.add(textField_1);
		
		JLabel lblNewLabel_2 = new JLabel("Дата поставки");
		panel_1.add(lblNewLabel_2);
		panel_1.add(dateChooser);
		
				JButton btnNewButton_3 = new JButton("Удалить");
				btnNewButton_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						actionDeleteButton();
					}
				});
				

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
							
							JLabel lblNewLabel_2_1 = new JLabel("Истечение срока годности");
							panel_1.add(lblNewLabel_2_1);
							
							dateChooser_1 = new JDateChooser();
							panel_1.add(dateChooser_1);
							
							JLabel lblNewLabel_3 = new JLabel("Цена поставки");
							panel_1.add(lblNewLabel_3);
							
									textField_2 = new JTextField();
									textField_2.addKeyListener(new KeyAdapter() {
										@Override
										public void keyTyped(KeyEvent keyEvent) {
											char c = keyEvent.getKeyChar();
											if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
												keyEvent.consume();
											}
										}
									});
									textField_2.setColumns(10);
									panel_1.add(textField_2);
							
							JLabel lblNewLabel_5 = new JLabel("Цена продажи");
							panel_1.add(lblNewLabel_5);
							
							textField_3 = new JTextField();
							textField_3.addKeyListener(new KeyAdapter() {
								@Override
								public void keyTyped(KeyEvent keyEvent) {
									char c = keyEvent.getKeyChar();
									if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
										keyEvent.consume();
									}
								}
							});
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
			Warehouse wh = (Warehouse) table.getModel().getValueAt(row, 99);
			ApplicationController.warehouseController.actionDeleteButton(wh.getId());
		}
	}

	private void actionTableMouseClicked() {
		if (table.getRowCount() > 0)
			try {
				int row = table.getSelectedRow();
				Warehouse wh = (Warehouse) table.getModel().getValueAt(row, 99);
				
				comboBox.getModel().setSelectedItem(wh.getMedic());
				textField_1.setText(wh.getCount()+"");
				
				Date date = Date.valueOf(wh.getDateGetting().toString());
				dateChooser.setDate(date);
				 date = Date.valueOf(wh.getDateDead().toString());
				dateChooser_1.setDate(date);
				
				textField_2.setText(wh.getPriceGetting()+"");
				textField_3.setText(wh.getPricaeSelling()+"");
			} catch (Exception e) {
			}
	}

	private void actionUpdateButton() {
		int column = 0;
		int row = table.getSelectedRow();
		Long id = Long.parseLong(table.getModel().getValueAt(row, column).toString());
		ApplicationController.warehouseController.actionUpdateButton(
				 ((Medic)comboBox.getSelectedItem()).getId(),
				Integer.parseInt(textField_1.getText()),
				new Date(dateChooser.getDate().getTime()),
				new Date(dateChooser_1.getDate().getTime()),
				Float.parseFloat(textField_2.getText()),
				Float.parseFloat(textField_3.getText()),
				id);
				
	}

	private void actionCreateButton() {
		ApplicationController.warehouseController.actionCreateButton(((Medic)comboBox.getSelectedItem()).getId(), 
				Integer.parseInt(textField_1.getText()),
				new Date(dateChooser.getDate().getTime()),
				new Date(dateChooser_1.getDate().getTime()),				
				Float.parseFloat(textField_2.getText()), 
				Float.parseFloat(textField_3.getText()));
	}

	private void actionSearchButton() {
		ApplicationController.warehouseController.actionSearchButton(textField.getText().trim(), table);
		refreshComboBoxes();
	}

	public void refreshView() {
		
		ApplicationController.warehouseController.actionSearchButton("", table);
		refreshComboBoxes();
	}
	public void refreshComboBoxes() {
		try {
			comboBox.setModel(
					new DefaultComboBoxModel(ApplicationController.MedicController.getDAO().readAll().toArray()));
			System.out.println("Перезагрузка списка");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void back() {
		ApplicationController.warehouseController.back();
	}
}
