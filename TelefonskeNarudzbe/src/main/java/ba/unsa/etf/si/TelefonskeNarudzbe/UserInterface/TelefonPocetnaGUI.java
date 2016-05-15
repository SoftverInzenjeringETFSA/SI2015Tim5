package ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Zaposlenik;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.NovaNarudzbaController; // i ovo brisati

public class TelefonPocetnaGUI {

	private JFrame frmSpremanjeNarudbi;
	private static NovaNarudzbaController kontroler = new NovaNarudzbaController(); // i
																					// ovo..
	private NovaNarudzbaGUI nova;

	final static Logger logger = Logger.getLogger(TelefonPocetnaGUI.class);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelefonPocetnaGUI window = new TelefonPocetnaGUI();
					window.frmSpremanjeNarudbi.setVisible(true);
				} catch (Exception e) {
					logger.info(e);
					// e.printStackTrace();
				}
			}
		});
	}

private static Zaposlenik ja;

	public TelefonPocetnaGUI() {

		initialize();

	}

	

	public TelefonPocetnaGUI(Zaposlenik zaposlenik) {
		ja = zaposlenik;
		initialize();
		frmSpremanjeNarudbi.setVisible(true);

	}

	private void OdjaviSe() throws Exception {
		try {
			kontroler.Odjava();
			frmSpremanjeNarudbi.setVisible(false);
			LoginGUI log = new LoginGUI();

		} catch (Exception e) {
			logger.info(e);
			//throw new Exception();
		}
	}

	/**
	 * Create the application
	 * 
	 * /** Initialize the contents of the frame.
	 */
	private void initialize() {

		frmSpremanjeNarudbi = new JFrame();
		frmSpremanjeNarudbi.setBounds(100, 100, 308, 145);
		frmSpremanjeNarudbi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmSpremanjeNarudbi.setJMenuBar(menuBar);

		JMenu mnMeni = new JMenu("Meni");
		menuBar.add(mnMeni);

		JMenuItem mntmOdjava = new JMenuItem("Odjava");
		mnMeni.add(mntmOdjava);
		frmSpremanjeNarudbi.getContentPane().setLayout(null);
		mntmOdjava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					OdjaviSe();
				} catch (Exception e1) {
					logger.info(e1);
					//e1.printStackTrace();
				}

			}
		});
		JLabel lblDobrodoaoIme = new JLabel("Dobrodo\u0161ao, " + ja.getImePrezime() + "!");
		lblDobrodoaoIme.setBounds(94, 12, 266, 14);
		frmSpremanjeNarudbi.getContentPane().add(lblDobrodoaoIme);

		JButton btnNewButton = new JButton("Nova narud\u017Eba");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				nova = new NovaNarudzbaGUI(ja);
			
			}
			
		});
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton.setBounds(78, 37, 139, 23);
		frmSpremanjeNarudbi.getContentPane().add(btnNewButton);
	}
}
