package ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface;
/*
import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.*;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.*;
import java.awt.EventQueue;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger;

import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.KuharController;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Narudzba;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DostavljacGUI {
	final static Logger logger = Logger.getLogger(DostavljacGUI.class);
	private DefaultListModel model = new DefaultListModel();
	private JFrame frmDostavaNarudbi;
	private JTextField adresaText;
	private JTextField brojTelefonaText;
	private JTextField cijenaText;
	private JTextField textNovacaDostavljeno;
	private JTextArea infoText;
	private JButton btnNarudbaDostavljena;
		private DostavljacController dostKontroler=new DostavljacController();
	private Narudzba izabranaNarudzba;
private JPanel glavniPanel;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
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
	/*public DostavljacGUI() {
		initialize();
		
	}*/
/*@SuppressWarnings("deprecation")
private void ProvjeriDaLiJePreuzeta(){
	boolean preuzeta=dostKontroler.provjeriDaLiJePreuzeta(izabranaNarudzba.getId());
	if (preuzeta) JOptionPane.showMessageDialog(null,"narudzba je preuzeta");
	else {
		dostKontroler.promijeniStatusUPreuzeta(izabranaNarudzba.getId(), 3);
		JOptionPane.showMessageDialog(null,"narudzba je preuzeta");
		textNovacaDostavljeno.setEnabled(true);
	btnNarudbaDostavljena.setEnabled(true);}
}
	private void PrikaziNarudzbe(){
		glavniPanel.setVisible(true);
		List<Narudzba> listaNarudzbi=dostKontroler.dajSveNarudzbeIzBaze();
		int brojac=0;
		for(Iterator<Narudzba> i = listaNarudzbi.iterator(); i.hasNext(); ) {
	        Narudzba item = i.next();
	        model.addElement(item.getId());	 
	        brojac++;
	        }
		}
	private void PromijeniStatusUspremna()
	{try{
		double cijena=Double.parseDouble(textNovacaDostavljeno.getText());
		if(cijena<0) throw new Exception();
			dostKontroler.promijeniStatusUSpremna(izabranaNarudzba.getId(), 3);
			JOptionPane.showMessageDialog(null,"Narudžba je dostavljena!");
	}
	catch(Exception e){
		JOptionPane.showMessageDialog(null,"Unesite broj");
	}
	
			
	}
		
		
		private void OtvoriNarudzbu(long id){
			izabranaNarudzba=dostKontroler.dajNarudzbuIzBaze((long) id);
		//	Kupac kupac=dostKontroler.dajKupca(izabranaNarudzba.getNarucioc());
			adresaText.setText(kupac.getAdresa());
			brojTelefonaText.setText(kupac.getBrojTelefona());
			
			infoText.setText(izabranaNarudzba.getOpis());
			cijenaText.setText(Double.toString(izabranaNarudzba.getCijena()));
		}
	private void initialize() {
		frmDostavaNarudbi = new JFrame();
		frmDostavaNarudbi.setTitle("Dostava narud\u017Ebi");
		frmDostavaNarudbi.setBounds(100, 100, 450, 396);
		frmDostavaNarudbi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDostavaNarudbi.getContentPane().setLayout(null);
		
		JLabel lblDobrodoaoIme = new JLabel("Dobrodo\u0161ao, Ime!");
		lblDobrodoaoIme.setBounds(161, 21, 133, 22);
		frmDostavaNarudbi.getContentPane().add(lblDobrodoaoIme);
		
		JButton btnPrikaiNarudbe = new JButton("Prika\u017Ei narud\u017Ebe");
		btnPrikaiNarudbe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			 	PrikaziNarudzbe();
			}
		});
		btnPrikaiNarudbe.setBounds(134, 40, 160, 22);
		frmDostavaNarudbi.getContentPane().add(btnPrikaiNarudbe);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 117, 21);
		frmDostavaNarudbi.getContentPane().add(menuBar);
		
		JMenu mnMeni = new JMenu("Meni");
		menuBar.add(mnMeni);
		
		JMenuItem mntmOdjava = new JMenuItem("Odjava");
		mnMeni.add(mntmOdjava);
		
		JList listaNarudzbi = new JList(model);
		listaNarudzbi.setBounds(20, 83, 153, 254);
		frmDostavaNarudbi.getContentPane().add(listaNarudzbi);
		listaNarudzbi.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent event) {
		        if (!event.getValueIsAdjusting()){
		            JList source = (JList)event.getSource();
		            String selected = source.getSelectedValue().toString();
		            OtvoriNarudzbu( Integer.parseInt(selected));
		        }
		    }
		});
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
		
		infoText = new JTextArea();
		infoText.setBounds(88, 70, 123, 34);
		panel_1.add(infoText);
		
		adresaText = new JTextField();
		adresaText.setBounds(88, 8, 124, 20);
		panel_1.add(adresaText);
		adresaText.setColumns(10);
		
		brojTelefonaText = new JTextField();
		brojTelefonaText.setColumns(10);
		brojTelefonaText.setBounds(87, 36, 124, 20);
		panel_1.add(brojTelefonaText);
		
		cijenaText = new JTextField();
		cijenaText.setColumns(10);
		cijenaText.setBounds(87, 116, 124, 20);
		panel_1.add(cijenaText);
		
		JButton btnPreuzmiNarudbu = new JButton("Preuzmi narudžbu");
		btnPreuzmiNarudbu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			ProvjeriDaLiJePreuzeta();
			}
		});
		btnPreuzmiNarudbu.setBounds(71, 161, 160, 22);
		panel.add(btnPreuzmiNarudbu);
		
		JLabel lblNovacaDostavljenokm = new JLabel("Novaca dostavljeno (KM):");
		lblNovacaDostavljenokm.setBounds(20, 195, 132, 14);
		panel.add(lblNovacaDostavljenokm);
		
		textNovacaDostavljeno = new JTextField();
		textNovacaDostavljeno.setColumns(10);
		textNovacaDostavljeno.setBounds(147, 194, 73, 20);
		panel.add(textNovacaDostavljeno);
		textNovacaDostavljeno.setEnabled(false);
		btnNarudbaDostavljena = new JButton("Narud\u017Eba dostavljena");
		btnNarudbaDostavljena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				PromijeniStatusUspremna();
			}
		});
		btnNarudbaDostavljena.setBounds(71, 219, 160, 22);
		panel.add(btnNarudbaDostavljena);
		btnNarudbaDostavljena.setEnabled(false);
		glavniPanel = new JPanel();
		glavniPanel.setBounds(10, 69, 424, 279);
		frmDostavaNarudbi.getContentPane().add(glavniPanel);
		glavniPanel.setVisible(false);
	}
}*/
