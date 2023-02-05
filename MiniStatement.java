package com_hibernate.com_hibernate_ATM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MiniStatement {

	private static JPanel panel;
	private static JTable table;
	private static JTextField enterAmount;
	private static JScrollPane sp;
	private static JPanel panel_message;
	private static JPanel panel_pin;
	static Object[][] rows = new Object[5][6];
	static String[] columns = {"SL.", "Trans.Id", "Date&Time", "type", "status", "bal"};


	/**
	 * @wbp.parser.entryPoint
	 */
	public static JPanel miniStatement() {
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 486, 288);
		panel.setLayout(null);
		
		panel_pin = new JPanel();
		panel_pin.setLayout(null);
		panel_pin.setBackground(Color.BLACK);
		panel_pin.setBounds(34, 90, 418, 159);
		panel.add(panel_pin);
		
		JLabel lblNewLabel_1_1 = new JLabel("Enter your pin:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(121, 11, 184, 38);
		panel_pin.add(lblNewLabel_1_1);
		
		enterAmount = new JTextField();
		enterAmount.setColumns(10);
		enterAmount.setBounds(121, 60, 190, 30);
		panel_pin.add(enterAmount);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {

					int pin = Integer.parseInt(enterAmount.getText());
					Session session = Connection.getSessionFactoryObject().openSession();
					Account account = (Account) session.load(Account.class, HomePage.currentUser);
					
					Query query = session.createQuery("from Transaction where account_id = :id");
					query.setParameter("id", HomePage.currentUser);
					query.setMaxResults(5);
					ArrayList list = (ArrayList) query.list();
					

					for(int i = 0; i < list.size(); i++) {
						Transaction action = (Transaction) list.get(list.size() - 1 - i);
							rows[i][0] = i + 1;
							rows[i][1] = action.getTransaction_id();
							rows[i][2] = action.getTransaction_date() + "\n" + action.getTransaction_time();
							rows[i][3] = action.getTransaction_type();
							rows[i][4] = action.getTransaction_status();
							rows[i][5] = action.getBalance();

					}

					sp.setVisible(true);
					panel_message.setVisible(false);
					panel_pin.setVisible(false);
					
					
				}
				catch(NumberFormatException exp) {
					System.out.print(exp);
				}
				catch(ObjectNotFoundException exp) {
					System.out.print(exp);
				}
				catch(Exception exp) {
					
				}
				
			}
		});
		btnCheck.setBounds(162, 107, 115, 30);
		panel_pin.add(btnCheck);
		
		JLabel lblNewLabel = new JLabel("Mini Statement");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(126, 11, 236, 55);
		panel.add(lblNewLabel);
		
		table = new JTable(rows, columns);
		table.setForeground(new Color(255, 255, 255));
		table.setBackground(new Color(0, 0, 0));
		
		sp = new JScrollPane(table);
		sp.setLocation(0, 65);
	    sp.setSize(486, 223);
	    table.setFillsViewportHeight(true);
	    sp.setVisible(false);
   
		panel.add(sp);
		
		panel_message = new JPanel();
		panel_message.setBackground(new Color(0, 0, 0));
		panel_message.setBounds(0, 110, 486, 54);
		panel.add(panel_message);
		panel_message.setLayout(null);
		
		JLabel printMessage = new JLabel("");
		printMessage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		printMessage.setHorizontalAlignment(SwingConstants.CENTER);
		printMessage.setBounds(0, 300, 486, 64);
		panel_message.add(printMessage);
		
		return panel;
	}
}
