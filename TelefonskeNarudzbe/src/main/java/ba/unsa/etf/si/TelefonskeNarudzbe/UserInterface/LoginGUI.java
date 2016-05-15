package ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.LoginController;

//import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.LoginController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LoginGUI
{

	private JFrame frmPrijavaNaSistem;
	private JTextField textField;
	private JTextField textField_1;
	final static Logger logger = Logger.getLogger(LoginGUI.class);LoginController s = null;
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
					logger.info(e);
					//e.printStackTrace();
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
		btnPrijava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					s = LoginController.getInstance(textField.getText(), textField_1.getText());

					if (s.getZaposlenik().getRadnomjesto().getId() == 1) {
						frmPrijavaNaSistem.setVisible(false);
						dispose();
						sef Sef = new sef();
						Sef.logovani = s.getZaposlenik();
					}else if (s.getZaposlenik().getRadnomjesto().getId() == 2) {
						frmPrijavaNaSistem.setVisible(false);
						dispose();
						TelefonPocetnaGUI k = new TelefonPocetnaGUI( s.getZaposlenik());
						
					} else if (s.getZaposlenik().getRadnomjesto().getId() == 3) {
						frmPrijavaNaSistem.setVisible(false);
						dispose();
						KuharGUI k = new KuharGUI(s.getZaposlenik());
						
					} else if (s.getZaposlenik().getRadnomjesto().getId() == 4) {
						frmPrijavaNaSistem.setVisible(false);
						dispose();
						DostavljacGUI d = new DostavljacGUI(s.getZaposlenik());
					

					}
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
					e1.printStackTrace();
				}
				
			}

			private void dispose() {
				// TODO Auto-generated method stub
				
			}
		});
		btnPrijava.setBounds(190, 74, 89, 23);
		frmPrijavaNaSistem.getContentPane().add(btnPrijava);
		
		frmPrijavaNaSistem.setVisible(true);
	}
}
