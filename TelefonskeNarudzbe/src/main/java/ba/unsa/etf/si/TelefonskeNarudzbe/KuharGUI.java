package ba.unsa.etf.si.TelefonskeNarudzbe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JTextArea;
import java.awt.Font;

public class KuharGUI {

	private JFrame frmSpremanjeNarudbi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KuharGUI window = new KuharGUI();
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
	public KuharGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSpremanjeNarudbi = new JFrame();
		frmSpremanjeNarudbi.setTitle("Spremanje narud\u017Ebi:");
		frmSpremanjeNarudbi.setBounds(100, 100, 458, 368);
		frmSpremanjeNarudbi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSpremanjeNarudbi.getContentPane().setLayout(null);
		
		JLabel lblDobrodoaoIme = new JLabel("Dobrodo\u0161ao, Ime!");
		lblDobrodoaoIme.setBounds(161, 21, 133, 22);
		frmSpremanjeNarudbi.getContentPane().add(lblDobrodoaoIme);
		
		JButton btnPrikaiNarudbe = new JButton("Prika\u017Ei narud\u017Ebe");
		btnPrikaiNarudbe.setBounds(134, 40, 160, 22);
		frmSpremanjeNarudbi.getContentPane().add(btnPrikaiNarudbe);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 117, 21);
		frmSpremanjeNarudbi.getContentPane().add(menuBar);
		
		JMenu mnMeni = new JMenu("Meni");
		menuBar.add(mnMeni);
		
		JMenuItem mntmOdjava = new JMenuItem("Odjava");
		mnMeni.add(mntmOdjava);
		
		JList list = new JList();
		list.setBounds(20, 83, 153, 227);
		frmSpremanjeNarudbi.getContentPane().add(list);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(183, 84, 241, 149);
		frmSpremanjeNarudbi.getContentPane().add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(183, 263, 241, 45);
		frmSpremanjeNarudbi.getContentPane().add(textArea_1);
		
		JLabel lblDodatneInformacije = new JLabel("Dodatne informacije:");
		lblDodatneInformacije.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDodatneInformacije.setBounds(183, 244, 133, 22);
		frmSpremanjeNarudbi.getContentPane().add(lblDodatneInformacije);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 69, 424, 250);
		frmSpremanjeNarudbi.getContentPane().add(panel);
	}
}
