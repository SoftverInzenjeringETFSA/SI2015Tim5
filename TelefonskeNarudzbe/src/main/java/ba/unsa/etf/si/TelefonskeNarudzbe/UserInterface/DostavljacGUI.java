package ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DostavljacGUI {
	final static Logger logger = Logger.getLogger(DostavljacGUI.class);
	private JFrame frmDostavaNarudbi;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DostavljacGUI window = new DostavljacGUI();
					window.frmDostavaNarudbi.setVisible(true);
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
	public DostavljacGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDostavaNarudbi = new JFrame();
		frmDostavaNarudbi.setTitle("Dostava narud\u017Ebi");
		frmDostavaNarudbi.setBounds(100, 100, 450, 387);
		frmDostavaNarudbi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDostavaNarudbi.getContentPane().setLayout(null);
		
		JLabel lblDobrodoaoIme = new JLabel("Dobrodo\u0161ao, Ime!");
		lblDobrodoaoIme.setBounds(161, 21, 133, 22);
		frmDostavaNarudbi.getContentPane().add(lblDobrodoaoIme);
		
		JButton btnPrikaiNarudbe = new JButton("Prika\u017Ei narud\u017Ebe");
		btnPrikaiNarudbe.setBounds(134, 40, 160, 22);
		frmDostavaNarudbi.getContentPane().add(btnPrikaiNarudbe);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 117, 21);
		frmDostavaNarudbi.getContentPane().add(menuBar);
		
		JMenu mnMeni = new JMenu("Meni");
		menuBar.add(mnMeni);
		
		JMenuItem mntmOdjava = new JMenuItem("Odjava");
		mnMeni.add(mntmOdjava);
		
		JList list = new JList();
		list.setBounds(20, 83, 153, 254);
		frmDostavaNarudbi.getContentPane().add(list);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(183, 83, 241, 254);
		frmDostavaNarudbi.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 6, 221, 144);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblAdresa = new JLabel("Adresa:");
		lblAdresa.setBounds(35, 11, 46, 14);
		panel_1.add(lblAdresa);
		
		JLabel lblBrojTelefona = new JLabel("Broj telefona:");
		lblBrojTelefona.setBounds(10, 36, 71, 14);
		panel_1.add(lblBrojTelefona);
		
		JLabel lblInformacije = new JLabel("Informacije:");
		lblInformacije.setBounds(20, 90, 58, 14);
		panel_1.add(lblInformacije);
		
		JLabel lblCijenakm = new JLabel("Cijena(KM):");
		lblCijenakm.setBounds(23, 119, 58, 14);
		panel_1.add(lblCijenakm);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(88, 70, 123, 34);
		panel_1.add(textArea);
		
		textField = new JTextField();
		textField.setBounds(88, 8, 124, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(87, 36, 124, 20);
		panel_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(87, 116, 124, 20);
		panel_1.add(textField_2);
		
		JButton button = new JButton("Prika\u017Ei narud\u017Ebe");
		button.setBounds(71, 161, 160, 22);
		panel.add(button);
		
		JLabel lblNovacaDostavljenokm = new JLabel("Novaca dostavljeno (KM):");
		lblNovacaDostavljenokm.setBounds(20, 195, 132, 14);
		panel.add(lblNovacaDostavljenokm);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(147, 194, 73, 20);
		panel.add(textField_3);
		
		JButton btnNarudbaDostavljena = new JButton("Narud\u017Eba dostavljena");
		btnNarudbaDostavljena.setBounds(71, 219, 160, 22);
		panel.add(btnNarudbaDostavljena);
	}
}
