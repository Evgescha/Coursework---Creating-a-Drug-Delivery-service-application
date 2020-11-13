package forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import controller.ApplicationController;
import javax.swing.JSpinner;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * форма отображения склада
 * @author admin
 *
 */
public class MedicDeadDate extends JFrame {
	private JTable table;
	private JSpinner spinner;
	private SpinnerNumberModel model;

	public MedicDeadDate() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				back();
			}
		});
		setTitle("Лекарства с истекажщим сроком годности");
		setBounds(100, 100, 835, 244);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel_4 = new JLabel("Показать товары, срок годности которых истекает ранее или через количество дней");
		panel.add(lblNewLabel_4);
		
		JButton btnNewButton_1 = new JButton("Поиск");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionSearchButton();
			}
		});
		
		  model = new SpinnerNumberModel(30,1,720,1);
		   spinner = new JSpinner(model);
		panel.add(spinner);
		panel.add(btnNewButton_1);
		
		
		table = new JTable();
				getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
			}

	
	private void actionSearchButton() {
		ApplicationController.warehouseController.searchByDeadDateDay(model.getNumber().intValue(), table);
	}

	public void refreshView() {
		ApplicationController.warehouseController.searchByDeadDateDay(30, table);
	}
	
	public void back() {
		ApplicationController.MedicDateDeadController.back();
	}
	
}
