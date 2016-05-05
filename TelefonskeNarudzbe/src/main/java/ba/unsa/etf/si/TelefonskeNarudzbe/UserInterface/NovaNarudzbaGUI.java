package ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.AbstractListModel;
import javax.swing.JMenuItem;


public class NovaNarudzbaGUI {

	private JFrame frmInformacijeONarudbi;
	private JTextField txtLoionika;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovaNarudzbaGUI window = new NovaNarudzbaGUI();
					window.frmInformacijeONarudbi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NovaNarudzbaGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInformacijeONarudbi = new JFrame();
		frmInformacijeONarudbi.setTitle("Informacije o narud\u017Ebi");
		frmInformacijeONarudbi.setBounds(100, 100, 458, 467);
		frmInformacijeONarudbi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInformacijeONarudbi.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 34, 408, 27);
		frmInformacijeONarudbi.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPretragaJela = new JLabel("Pretraga jela");
		lblPretragaJela.setBounds(10, 0, 89, 27);
		panel.add(lblPretragaJela);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setBounds(90, 3, 191, 20);
		panel.add(comboBox);
		
		JButton btnDodajJelo = new JButton("Dodaj jelo");
		btnDodajJelo.setBounds(309, 2, 89, 23);
		panel.add(btnDodajJelo);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Hamburger", "Chiken Burger"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setToolTipText("Hamburger\r\nChicken burger");
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(9, 97, 208, 89);
		frmInformacijeONarudbi.getContentPane().add(list);
		
		JLabel lblDodatniOpis = new JLabel("Dodatni opis narud\u017Ebe:");
		lblDodatniOpis.setBounds(228, 72, 159, 14);
		frmInformacijeONarudbi.getContentPane().add(lblDodatniOpis);
		
		JTextPane txtpnHamburgerBezKeapa = new JTextPane();
		txtpnHamburgerBezKeapa.setText("Hamburger bez ke\u010Dapa, Chiken Burger bez paradajza");
		txtpnHamburgerBezKeapa.setBounds(227, 97, 191, 89);
		frmInformacijeONarudbi.getContentPane().add(txtpnHamburgerBezKeapa);
		
		JLabel lblNaruenaJela = new JLabel("Naru\u010Dena jela:");
		lblNaruenaJela.setBounds(10, 72, 87, 14);
		frmInformacijeONarudbi.getContentPane().add(lblNaruenaJela);
		
		JPanel panel_1 = new JPanel();
		panel_1.setToolTipText("Podaci o naru\u010Diocu");
		panel_1.setBounds(10, 197, 408, 119);
		frmInformacijeONarudbi.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Adresa:");
		lblNewLabel.setBounds(59, 30, 46, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblPodaciOKorisniku = new JLabel("Podaci o naru\u010Diocu");
		lblPodaciOKorisniku.setBounds(149, 2, 114, 14);
		panel_1.add(lblPodaciOKorisniku);
		
		txtLoionika = new JTextField();
		txtLoionika.setText("Lo\u017Eioni\u010Dka 21");
		txtLoionika.setBounds(109, 27, 289, 20);
		panel_1.add(txtLoionika);
		txtLoionika.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Broj telefona:");
		lblNewLabel_1.setBounds(30, 55, 89, 14);
		panel_1.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setText("062-111-383");
		textField_1.setColumns(10);
		textField_1.setBounds(109, 52, 289, 20);
		panel_1.add(textField_1);
		
		JLabel lblNewLabel_2 = new JLabel("Informacije:");
		lblNewLabel_2.setBounds(38, 81, 114, 14);
		panel_1.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(109, 78, 289, 20);
		panel_1.add(textField_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(234, 327, 184, 90);
		frmInformacijeONarudbi.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Cijena:");
		lblNewLabel_3.setBounds(20, 11, 46, 14);
		panel_2.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setText("6");
		textField_3.setEditable(false);
		textField_3.setBounds(68, 8, 86, 20);
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblPopust = new JLabel("Ukupno:");
		lblPopust.setBounds(20, 61, 46, 14);
		panel_2.add(lblPopust);
		
		JLabel label = new JLabel("Popust:");
		label.setBounds(20, 36, 46, 14);
		panel_2.add(label);
		
		textField_4 = new JTextField();
		textField_4.setText("0");
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(68, 33, 86, 20);
		panel_2.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setText("6");
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(68, 58, 86, 20);
		panel_2.add(textField_5);
		
		JLabel lblNewLabel_4 = new JLabel("KM");
		lblNewLabel_4.setBounds(160, 61, 24, 14);
		panel_2.add(lblNewLabel_4);
		
		JLabel label_1 = new JLabel("KM");
		label_1.setBounds(160, 11, 24, 14);
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("%");
		label_2.setBounds(160, 36, 24, 14);
		panel_2.add(label_2);
		
		JButton btnObraunaj = new JButton("Obra\u010Dunaj ");
		btnObraunaj.setBounds(65, 338, 107, 23);
		frmInformacijeONarudbi.getContentPane().add(btnObraunaj);
		
		JButton btnNewButton = new JButton("Spremi ");
		btnNewButton.setEnabled(false);
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton.setBounds(65, 372, 107, 23);
		frmInformacijeONarudbi.getContentPane().add(btnNewButton);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 97, 21);
		frmInformacijeONarudbi.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Meni");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmOdjava = new JMenuItem("Odjava");
		mnNewMenu.add(mntmOdjava);
		btnObraunaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}
}
