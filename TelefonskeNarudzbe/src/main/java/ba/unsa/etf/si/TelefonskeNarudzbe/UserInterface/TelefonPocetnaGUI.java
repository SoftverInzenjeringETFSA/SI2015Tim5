package ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelefonPocetnaGUI {

	private JFrame frmSpremanjeNarudbi;

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
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelefonPocetnaGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
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
		
		JLabel lblDobrodoaoIme = new JLabel("Dobrodo\u0161ao, Ime!");
		lblDobrodoaoIme.setBounds(94, 12, 266, 14);
		frmSpremanjeNarudbi.getContentPane().add(lblDobrodoaoIme);
		
		JButton btnNewButton = new JButton("Nova narud\u017Eba");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton.setBounds(78, 37, 139, 23);
		frmSpremanjeNarudbi.getContentPane().add(btnNewButton);
	}
}
