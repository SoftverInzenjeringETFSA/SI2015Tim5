package ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.UnosIzmjenaRadnikaController;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Zaposlenik;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class IzmjenaLozinke extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private IzmjenaLozinke forma = this;
	final static Logger logger = Logger.getLogger(IzmjenaLozinke.class);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzmjenaLozinke frame = new IzmjenaLozinke(null);
					frame.setVisible(true);
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
	public IzmjenaLozinke(final Zaposlenik z) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNovaLozinka = new JLabel("Nova lozinka:");
		
		passwordField_1 = new JPasswordField();
		
		JLabel lblPotvrdaLozinke = new JLabel("Potvrda lozinke:");
		
		passwordField_2 = new JPasswordField();
		
		JButton btnPotvrdiIzmjene = new JButton("Potvrdi izmjene");
		btnPotvrdiIzmjene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Arrays.equals(passwordField_2.getPassword(), passwordField_1.getPassword())) {
					JOptionPane.showMessageDialog(null, "passwordi se ne slazu!");
					return;
				}
				if (passwordField_2.getPassword().length == 0 || passwordField_2.getPassword() == null) {
					JOptionPane.showMessageDialog(null, "Popunite oba polja za password!");
					return;
				}
				if (passwordField_1.getPassword().length == 0 || passwordField_1.getPassword() == null) {
					JOptionPane.showMessageDialog(null, "Popunite oba polja za password!");
					return;
				}
				char[] pass = passwordField_1.getPassword();
				UnosIzmjenaRadnikaController r = new UnosIzmjenaRadnikaController();
				String password = String.valueOf(pass);
				r.izmijeniRadnika(z.getImePrezime(), z.getDatumRodenja().toString(), z.getUsername(), password, z.getRadnomjesto().getId(),z.getDodatneInformacije());
				forma.dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(52)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPotvrdaLozinke)
						.addComponent(lblNovaLozinka))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnPotvrdiIzmjene, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField_1, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
						.addComponent(passwordField_2))
					.addContainerGap(57, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(62)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNovaLozinka)
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPotvrdaLozinke)
						.addComponent(passwordField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
					.addComponent(btnPotvrdiIzmjene)
					.addGap(41))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
