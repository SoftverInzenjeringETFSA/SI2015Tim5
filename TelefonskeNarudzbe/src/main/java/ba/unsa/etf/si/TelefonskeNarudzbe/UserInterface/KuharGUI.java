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
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.List;

import java.awt.Font;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.*;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.*;
public class KuharGUI {
	private DefaultListModel model = new DefaultListModel();
	private JFrame frmSpremanjeNarudbi;
	final static Logger logger = Logger.getLogger(KuharGUI.class);
	private final Action action = new SwingAction();
private KuharController kuharKontroler=new KuharController();
private JPanel glavniPanel;	
private Narudzba izabranaNarudzba;
private JTextArea sastojcitextArea ;
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
					logger.info(e);
					//e.printStackTrace();
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

	
	private void PrikaziNarudzbe(){
	glavniPanel.setVisible(true);
	List<Narudzba> listaNarudzbi=kuharKontroler.dajSveNarudzbeIzBaze();
	int brojac=0;
	for(Iterator<Narudzba> i = listaNarudzbi.iterator(); i.hasNext(); ) {
        Narudzba item = i.next();
        model.addElement(item.getId());	 
        brojac++;
        }
	}
	
	//private boolean ProvjeriDaLiJePreuzeta(int id){
		//boolean preuzeta=kuharKontroler.provjeriDaLiJePreuzeta(id);
		
		//return preuzeta;
//	}
	
	private void OtvoriNarudzbu(int id){
		//izabranaNarudzba=kuharKontroler.dajNarudzbuIzBaze(id);
		sastojcitextArea.setText( Integer.toString(id));
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSpremanjeNarudbi = new JFrame();
		frmSpremanjeNarudbi.setTitle("Spremanje narud\u017Ebi:");
		frmSpremanjeNarudbi.setBounds(100, 100, 458, 406);
		frmSpremanjeNarudbi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSpremanjeNarudbi.getContentPane().setLayout(null);
		
		JLabel lblDobrodoaoIme = new JLabel("Dobrodo\u0161ao, Ime!");
		lblDobrodoaoIme.setBounds(161, 21, 133, 22);
		frmSpremanjeNarudbi.getContentPane().add(lblDobrodoaoIme);
		
		JButton btnPrikaiNarudbe = new JButton("Prika\u017Ei narud\u017Ebe");
		btnPrikaiNarudbe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			 	PrikaziNarudzbe();
			}
		});
		btnPrikaiNarudbe.setBounds(134, 40, 160, 22);
		frmSpremanjeNarudbi.getContentPane().add(btnPrikaiNarudbe);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 117, 21);
		frmSpremanjeNarudbi.getContentPane().add(menuBar);
		
		JMenu mnMeni = new JMenu("Meni");
		menuBar.add(mnMeni);
		
		JMenuItem mntmOdjava = new JMenuItem("Odjava");
		mnMeni.add(mntmOdjava);
		
		JList list = new JList(model);
		list.setBounds(20, 83, 153, 261);
		frmSpremanjeNarudbi.getContentPane().add(list);
		list.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent event) {
		        if (!event.getValueIsAdjusting()){
		            JList source = (JList)event.getSource();
		            String selected = source.getSelectedValue().toString();
		            OtvoriNarudzbu( Integer.parseInt(selected));
		        }
		    }
		});
		sastojcitextArea = new JTextArea();
		sastojcitextArea.setBounds(183, 84, 241, 149);
		frmSpremanjeNarudbi.getContentPane().add(sastojcitextArea);
		
		JTextArea dodatneInformacijeTextArea = new JTextArea();
		dodatneInformacijeTextArea.setBounds(183, 263, 241, 45);
		frmSpremanjeNarudbi.getContentPane().add(dodatneInformacijeTextArea);
		
		JLabel lblDodatneInformacije = new JLabel("Dodatne informacije:");
		lblDodatneInformacije.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDodatneInformacije.setBounds(183, 244, 133, 22);
		frmSpremanjeNarudbi.getContentPane().add(lblDodatneInformacije);
		
		glavniPanel = new JPanel();
		glavniPanel.setBounds(10, 69, 424, 287);
		frmSpremanjeNarudbi.getContentPane().add(glavniPanel);
		glavniPanel.setLayout(null);
		
		JButton btnPreuzmi = new JButton("Preuzmi");
		btnPreuzmi.setBounds(179, 253, 111, 23);
		glavniPanel.add(btnPreuzmi);
		
		JButton btnSpremi = new JButton("Spremi");
		btnSpremi.setBounds(303, 253, 111, 23);
		glavniPanel.add(btnSpremi);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
