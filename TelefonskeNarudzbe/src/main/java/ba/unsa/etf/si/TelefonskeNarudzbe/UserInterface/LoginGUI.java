package ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;


public class LoginGUI
{

	private JFrame frmPrijavaNaSistem;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI window = new LoginGUI();
					window.frmPrijavaNaSistem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrijavaNaSistem = new JFrame();
		frmPrijavaNaSistem.setResizable(false);
		frmPrijavaNaSistem.setTitle("Prijava na sistem");
		frmPrijavaNaSistem.setBounds(100, 100, 332, 148);
		frmPrijavaNaSistem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrijavaNaSistem.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Korisni\u010Dko ime");
		lblNewLabel.setBounds(27, 11, 96, 39);
		frmPrijavaNaSistem.getContentPane().add(lblNewLabel);
		
		JLabel lblLozinka = new JLabel("Lozinka");
		lblLozinka.setBounds(27, 36, 86, 34);
		frmPrijavaNaSistem.getContentPane().add(lblLozinka);
		
		textField = new JTextField();
		textField.setBounds(119, 20, 160, 20);
		frmPrijavaNaSistem.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(119, 43, 160, 20);
		frmPrijavaNaSistem.getContentPane().add(textField_1);
		
		JButton btnPrijava = new JButton("Prijava");
		btnPrijava.setBounds(190, 74, 89, 23);
		frmPrijavaNaSistem.getContentPane().add(btnPrijava);
	}
}
