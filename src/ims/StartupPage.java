package ims;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartupPage {

	private JFrame frmInventorymanagementsystem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartupPage window = new StartupPage();
					window.frmInventorymanagementsystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartupPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmInventorymanagementsystem = new JFrame();
		frmInventorymanagementsystem.setTitle("InventoryManagementSystem");
		frmInventorymanagementsystem.setBounds(100, 100, 421, 430);
		frmInventorymanagementsystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInventorymanagementsystem.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("imgs/picture1.jpg"));
		lblNewLabel.setBounds(34, 19, 358, 262);
		frmInventorymanagementsystem.getContentPane().add(lblNewLabel);
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(70, 334, 292, 40);
		frmInventorymanagementsystem.getContentPane().add(btnStart);
		btnStart.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				try{
					new OperationInterface();
					OperationInterface.main(null);
					frmInventorymanagementsystem.dispose();
				}catch(Exception e1){
					e1.printStackTrace();
					JOptionPane.showMessageDialog(frmInventorymanagementsystem, "Fail to open the interface x_x");
				}			
			}
		});
		
		
		JLabel lblJiajunChen = new JLabel("Jiajun Chen (Carson) @ 2015");
		lblJiajunChen.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblJiajunChen.setBounds(145, 386, 144, 16);
		frmInventorymanagementsystem.getContentPane().add(lblJiajunChen);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome  to the Inventory Management System");
		lblWelcomeToThe.setBounds(69, 293, 303, 16);
		frmInventorymanagementsystem.getContentPane().add(lblWelcomeToThe);
	}
}
