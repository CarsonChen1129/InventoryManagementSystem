package ims;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import java.awt.Font;

/**
 * 
 * @author CarsonChen
 *
 */
public class OperationInterface {

	private JFrame frmInventorymanagementsystem;
	private final JLabel lblNewLabel = new JLabel("New label");
	private final JButton btnNewButton_1 = new JButton("Exit");
	private final JTextArea textArea = new JTextArea();
	private final JScrollPane scrollPane = new JScrollPane(textArea);
	public static String path = null;
	private final JButton btnRunTest = new JButton("Run Test");
	private final JButton btnRestock = new JButton("Restock a product");
	private final JButton btnPick = new JButton("Pick a product");
	private final JLabel lblJiajunChencarson = new JLabel("Jiajun Chen (Carson) @ 2015");
	private Warehouse warehouse	= new Warehouse();

	
	
	/* The following codes set where the text get redirected. */ 
	  private void updateTextArea(final String text) {
	    SwingUtilities.invokeLater(new Runnable() {
	      @Override
		public void run() {
	        textArea.append(text);
	      }
	    });
	  }
	 
	  /*Followings are The Methods that do the Redirect.  */
	  private void redirectSystemStreams() {
	    OutputStream out = new OutputStream() {
	      @Override
	      public void write(int b) throws IOException {
	        updateTextArea(String.valueOf((char) b));
	      }
	 
	      @Override
	      public void write(byte[] b, int off, int len) throws IOException {
	        updateTextArea(new String(b, off, len));
	      }
	 
	      @Override
	      public void write(byte[] b) throws IOException {
	        write(b, 0, b.length);
	      }
	    };
	 
	    System.setOut(new PrintStream(out, true));
	    System.setErr(new PrintStream(out, true));
	  }
	
	 /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					OperationInterface window = new OperationInterface();
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
	public OperationInterface() {
		
		redirectSystemStreams();
		
		Properties props=System.getProperties();   
		String osName = props.getProperty("os.name"); 
		System.out.println("===========================");
		System.out.println("Welcome to the Inventory Management System!");
		System.out.println("Your system is: "+osName);
		System.out.println("===========================");
		
		
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmInventorymanagementsystem = new JFrame();
		frmInventorymanagementsystem.setTitle("InventoryManagementSystem");
		frmInventorymanagementsystem.setBounds(100, 100, 774, 557);
		frmInventorymanagementsystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInventorymanagementsystem.getContentPane().setLayout(null);
		lblNewLabel.setBounds(52, 16, 160, 179);
		lblNewLabel.setIcon(new ImageIcon("imgs/worker.jpg"));
		
		frmInventorymanagementsystem.getContentPane().add(lblNewLabel);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
					JOptionPane.showMessageDialog(frmInventorymanagementsystem,"Thank you for using! :D");;
					System.exit(0);
			}
		});
		btnNewButton_1.setBounds(52, 473, 160, 40);
		
		frmInventorymanagementsystem.getContentPane().add(btnNewButton_1);
		textArea.setBounds(293, 16, 459, 497);
		
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(293, 16, 459, 497);
		
		frmInventorymanagementsystem.getContentPane().add(scrollPane);
		btnRunTest.setBounds(52, 237, 160, 50);
		
		btnRunTest.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				try{
					Operations operateOne = new Operations();
					operateOne.runTest(warehouse);
				}catch(Exception e1){
					e1.printStackTrace();
					JOptionPane.showMessageDialog(frmInventorymanagementsystem, "Fail to run the test x_x");
				}
					
			}
		});
		
		frmInventorymanagementsystem.getContentPane().add(btnRunTest);
		btnRestock.setBounds(52, 319, 160, 50);
		
		btnRestock.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				try{
					String productId = JOptionPane.showInputDialog("Please enter the product ID: ");
					int amountToRestock;
					try{
						amountToRestock = Integer.parseInt(JOptionPane.showInputDialog("Please enter the amount to restock: "));
					}catch(Exception e2){
						JOptionPane.showMessageDialog(frmInventorymanagementsystem, "Please enter valid number to restock");
						return;
					}
					System.out.println();
					System.out.println("====================Restock====================");
					System.out.println();
					Operations opt = new Operations();
					opt.restockProduct(productId, amountToRestock, warehouse);
				}catch(Exception e1){
					e1.printStackTrace();
					JOptionPane.showMessageDialog(frmInventorymanagementsystem, "Fail to run the restock functionality x_x");
				}
					
			}
		});
		
		frmInventorymanagementsystem.getContentPane().add(btnRestock);
		btnPick.setBounds(52, 398, 160, 50);
		
		btnPick.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				try{
					String productId = JOptionPane.showInputDialog("Please enter the product ID: ");
					int amountToPick;
					try{
						amountToPick = Integer.parseInt(JOptionPane.showInputDialog("Please enter the amount to pick: "));
					}catch(Exception e2){
						JOptionPane.showMessageDialog(frmInventorymanagementsystem, "Please enter valid number to pick");
						return;
					}
					System.out.println();
					System.out.println("====================Pick====================");
					System.out.println();
					Operations opt = new Operations();
					opt.pickProduct(productId, amountToPick, warehouse);
				}catch(Exception e1){
					e1.printStackTrace();
					JOptionPane.showMessageDialog(frmInventorymanagementsystem, "Fail to run the pick functionality x_x");
				}
					
			}
		});
		
		frmInventorymanagementsystem.getContentPane().add(btnPick);
		lblJiajunChencarson.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblJiajunChencarson.setBounds(62, 194, 143, 16);
		
		frmInventorymanagementsystem.getContentPane().add(lblJiajunChencarson);
         
	}

}
