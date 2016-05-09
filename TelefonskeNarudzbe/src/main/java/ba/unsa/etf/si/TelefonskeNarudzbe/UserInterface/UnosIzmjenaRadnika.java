package ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;
import org.hibernate.SharedSessionContract;
import org.hibernate.Transaction;
import com.mysql.cj.api.Session;

import Util.HibernateUtil;


import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Narudzba;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;


import java.util.Scanner;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Zaposlenik;
public class UnosIzmjenaRadnika extends JFrame {
	static Scanner sc = new Scanner(System.in);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private UnosIzmjenaRadnika forma = this;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	final static Logger logger = Logger.getLogger(UnosIzmjenaRadnika.class);
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnosIzmjenaRadnika frame = new UnosIzmjenaRadnika();
					frame.setVisible(true);
					Session session = (Session) HibernateUtil.getSessionFactory().openSession();
					//nadjiRadnika(session);
				} catch (Exception e) {
					logger.info(e);
					//e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UnosIzmjenaRadnika() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblImeIPrezime = new JLabel("Ime i prezime:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblDatumRoenja = new JLabel("Datum ro\u0111enja:");
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		
		JLabel lblRadnoMjesto = new JLabel("Radno mjesto:");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Radnik na telefonu", "Kuhar", "Dostavlja\u010D"}));
		
		JLabel lblNewLabel = new JLabel("Dodatne informacije:");
		
		JButton btnNewButton = new JButton("Zavr\u0161i ure\u0111ivanje");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				forma.dispose();
			}
		});
		
		JLabel lblKorisnikoIme = new JLabel("Korisni\u010Dko ime:");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblifra = new JLabel("Lozinka:");
		
		passwordField = new JPasswordField();
		
		JLabel lblPotvrdaLozinke = new JLabel("Potvrda lozinke:");
		
		passwordField_1 = new JPasswordField();
		
		JTextArea textArea = new JTextArea();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(150, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
					.addGap(125))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addComponent(lblPotvrdaLozinke)
						.addComponent(lblifra)
						.addComponent(lblKorisnikoIme)
						.addComponent(lblRadnoMjesto)
						.addComponent(lblDatumRoenja)
						.addComponent(lblImeIPrezime))
					.addGap(50)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
						.addComponent(formattedTextField, 242, 242, Short.MAX_VALUE)
						.addComponent(comboBox, 0, 242, Short.MAX_VALUE)
						.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
						.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
						.addComponent(passwordField_1, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblImeIPrezime)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDatumRoenja)
						.addComponent(formattedTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRadnoMjesto)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKorisnikoIme)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblifra)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPotvrdaLozinke)
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	private static void nadjiRadnika(Session session) {
		
	
	Transaction t = ((SharedSessionContract) session).beginTransaction();
	System.out.println("Unesite id narudzbe");
	long id = sc.nextLong();
	Zaposlenik s = (Zaposlenik) ((org.hibernate.Session) session).get(Zaposlenik.class, id);
	if (s==null) {
	System.out.println("Nema studenta sa tim IDom u bazi");
	} else {
	System.out.println("Student: "+s.getUsername()+" "+s.getPassword());
	}
	t.commit();
	}
}
