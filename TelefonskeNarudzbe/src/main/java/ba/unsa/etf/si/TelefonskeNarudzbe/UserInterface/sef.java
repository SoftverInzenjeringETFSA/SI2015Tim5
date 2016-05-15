package ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;


import org.apache.log4j.Logger;

import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Narudzba;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Zaposlenik;
import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.IzvjestajController;
public class sef {

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3 = new JTable();
	private JTable table_5;
	private JTable dostavljac_tbl = new JTable();
	private JScrollPane scrollPane_dostavljac = new JScrollPane();
	private JTable jelo_tbl = new JTable();
	private JScrollPane scrollPane_jelo = new JScrollPane();
	private JScrollPane scrollPane_3 = new JScrollPane();
	final static Logger logger = Logger.getLogger(sef.class);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sef window = new sef();
					window.frame.setVisible(true);
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
	public static Zaposlenik logovani;
	public sef() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 26, 994, 645);
		frame.getContentPane().add(tabbedPane);
		
		String[] values = new String[] {"Izvještaj o svim narudžbama u vremenskom periodu", 
				                       "Izvještaj o svim odrađenim dostavama po dostavljaču", 
				                       "Izvještaj o narudžbama po jelima koja ih čine", 
				                       "Statistički izvještaj o vremenu isporuke narudžbi", 
				                       "Statistički izvjestaj o broju naručenih jela"};

		final JPanel IzvjestajiTab = new JPanel();
		tabbedPane.addTab("Izvje\u0161taji", null, IzvjestajiTab, null);
		IzvjestajiTab.setLayout(null);
		final JList list = new JList(values);
		
		list.setBounds(28, 23, 460, 90);
		IzvjestajiTab.add(list);
		
		JButton btnPrikaiIzvjetaj = new JButton("Odaberi izvještaj");

		final JButton btnGenerisiIzvjetaj = new JButton("Generiši izvještaj");
		btnGenerisiIzvjetaj.setBounds(600, 157, 196, 23);
		IzvjestajiTab.add(btnGenerisiIzvjetaj);
		btnGenerisiIzvjetaj.setVisible(false);
		
		//btn za onos kriterija
		final JLabel lblKriterij = new JLabel();
		lblKriterij.setBounds(28, 161, 100, 14);
		IzvjestajiTab.add(lblKriterij);
		lblKriterij.setVisible(false);
		
		//unos kriterija
		final JTextField kriterij = new JTextField();
		kriterij.setBounds(159, 158, 166, 20);
		IzvjestajiTab.add(kriterij);
		kriterij.setColumns(10); 
		kriterij.setVisible(false);
		
		//format datuma
		MaskFormatter datum = new MaskFormatter();
		try {
			datum = new MaskFormatter("##.##.####.");
			datum.setPlaceholderCharacter(' ');
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//datum
		final JLabel lblDatum = new JLabel("Datum:");
		lblDatum.setBounds(28, 124, 46, 14);
		IzvjestajiTab.add(lblDatum);
		lblDatum.setVisible(false);
		//OD
		final JLabel lblOd = new JLabel("Od:");   
		lblOd.setBounds(28, 161, 25, 14);
		IzvjestajiTab.add(lblOd);
		lblOd.setVisible(false);
		final JFormattedTextField datumOd = new JFormattedTextField(datum);
		datumOd.setBounds(63, 158, 150, 20);
		IzvjestajiTab.add(datumOd);
		datumOd.setColumns(10);
		datumOd.setVisible(false);
		//DO
		final JLabel lblDo = new JLabel("Do:");
		lblDo.setBounds(303, 161, 25, 14);
		IzvjestajiTab.add(lblDo);
		lblDo.setVisible(false);
		final JFormattedTextField datumDo = new JFormattedTextField(datum);
		datumDo.setColumns(10);
		datumDo.setBounds(338, 158, 150, 20);
		IzvjestajiTab.add(datumDo);
		datumDo.setColumns(10);
		datumDo.setVisible(false);
		/*
		//tabela 5
		String[] kolone_izvjestaj_2 = {"Izabrano jelo", "Broj narudžbi izabranog jela"};
		Object[][] podaci_izvjestaj_2 = {{"",""}};
		final JTable table_4 = new JTable(podaci_izvjestaj_2, kolone_izvjestaj_2);
		table_4.setBounds(109, 340, 340, -61);
		IzvjestajiTab.add(table_4);
		final JScrollPane scrollPane_4 = new JScrollPane(table_4);
		scrollPane_4.setBounds(10, 218, 969, 388);
		IzvjestajiTab.add(scrollPane_4);
		scrollPane_4.setVisible(false);
		
		//tabela 4
		String[] kolone_izvjestaj_6 = {"Vremenski rok(minute)", "Broj narudžbi", "Procenat"};
		Object[][] podaci_izvjestaj_6 = {{"","",""}};
		final JTable table_6 = new JTable(podaci_izvjestaj_6, kolone_izvjestaj_6);
		table_6.setBounds(109, 340, 340, -61);
		IzvjestajiTab.add(table_6);
		final JScrollPane scrollPane_6 = new JScrollPane(table_6);
		scrollPane_6.setBounds(10, 218, 969, 388);
		IzvjestajiTab.add(scrollPane_6);
	
		//sve tabele neaktivne na pocetku
		scrollPane_3.setVisible(false);
		table_3.setVisible(false);
		scrollPane_4.setVisible(false);
		table_4.setVisible(false);
		scrollPane_6.setVisible(false);
		table_6.setVisible(false);
		*/
		btnPrikaiIzvjetaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGenerisiIzvjetaj.setVisible(false);
				String odabrano = list.getSelectedValue().toString();
				lblDatum.setVisible(false);  
				lblOd.setVisible(false); 
				datumOd.setVisible(false);
				lblDo.setVisible(false);
				datumDo.setVisible(false);
				if(odabrano.equals("Izvještaj o svim narudžbama u vremenskom periodu"))
				{
					btnGenerisiIzvjetaj.setVisible(true);
					lblDatum.setVisible(true);  
					lblOd.setVisible(true); 
					datumOd.setVisible(true);
					lblDo.setVisible(true);
					datumDo.setVisible(true);
					ocistiFormuOdTabela();
					kriterij.setVisible(false);
					lblKriterij.setVisible(false);
				}
				if(odabrano.equals("Izvještaj o svim odrađenim dostavama po dostavljaču"))
				{
					btnGenerisiIzvjetaj.setVisible(true);
					lblDatum.setVisible(false);  
					lblOd.setVisible(false); 
					datumOd.setVisible(false);
					lblDo.setVisible(false);
					datumDo.setVisible(false);
					ocistiFormuOdTabela();
					lblKriterij.setText("Ime dostavljača: ");
					lblKriterij.setVisible(true);
					kriterij.setText("");
					kriterij.setVisible(true);
					}
				if(odabrano.equals("Izvještaj o narudžbama po jelima koja ih čine"))
				{
					btnGenerisiIzvjetaj.setVisible(true);
					ocistiFormuOdTabela();
					lblKriterij.setText("Naziv jela:");
					lblKriterij.setVisible(true);
					kriterij.setText("");
					kriterij.setVisible(true);
				}
				if(odabrano.equals("Statistički izvještaj o vremenu isporuke narudžbi"))
				{
					ocistiFormuOdTabela();
					kriterij.setVisible(false);
					lblKriterij.setVisible(false);
				}
				if(odabrano.equals("Statistički izvjestaj o broju naručenih jela"))
				{
					ocistiFormuOdTabela();
					kriterij.setText("");
					kriterij.setVisible(true);
					lblKriterij.setText("Naziv jela:");
					lblKriterij.setVisible(true);
				}
			}
		});
		btnPrikaiIzvjetaj.setBounds(600, 46, 196, 23);
		IzvjestajiTab.add(btnPrikaiIzvjetaj);
		
		//btn za prikaz izvještaja
		btnGenerisiIzvjetaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String odabrano = list.getSelectedValue().toString();
				if(odabrano.equals("Izvještaj o svim narudžbama u vremenskom periodu"))
				{
					ocistiFormuOdTabela();
					//datum kupljenje sa forme
					String datumOdString = new String();
					datumOdString = datumOd.getText();
					if(IzvjestajController.validirajDatum(datumOdString) == false)
					{
						JOptionPane.showMessageDialog(frame, "Pogrešan datum od:");
					}
					String datumDoString = new String();
					datumDoString = datumDo.getText();
					if(IzvjestajController.validirajDatum(datumDoString) == false)
					{
						JOptionPane.showMessageDialog(frame, "Pogrešan datum do:");
					}
					if(IzvjestajController.validirajDatum(datumDoString) && IzvjestajController.validirajDatum(datumOdString))
					{
						try {
							String[] kolone_izvjestaj_1 = {"ID", "SPISAK JELA", "DATUM", "OPIS", "ODGOVORNA OSOBA", "ADRESA NARUČIOCA", "BROJ TELEFONA NARUČIOCA", "CIJENA"};
							Object[][] podaci_izvjestaj_1 = IzvjestajController.dajNaruzbePoDatumu(datumOdString, datumDoString);
							
							table_3 = new JTable(podaci_izvjestaj_1, kolone_izvjestaj_1);
							table_3.setBounds(109, 340, 340, -61);
							table_3.getColumn("ID").setMaxWidth(70);
							table_3.getColumn("CIJENA").setMaxWidth(70);
							IzvjestajiTab.add(table_3);
							
							scrollPane_3 = new JScrollPane(table_3);
							scrollPane_3.setBounds(10, 218, 969, 388);
							IzvjestajiTab.add(scrollPane_3);
							table_3.setVisible(true);
							scrollPane_3.setVisible(true);
						
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(frame, "Pogrešan datum od:");
							e1.printStackTrace();
						}
					}
				}
					
				//drugi izvještaj
					if(odabrano.equals("Izvještaj o svim odrađenim dostavama po dostavljaču"))
					{
						ocistiFormuOdTabela();
						String dostavljac = new String();
						dostavljac = kriterij.getText();
						try {
							Object[][] podaci_izvjestaj_1 = IzvjestajController.dajNaruzbePoDostavljacu(dostavljac);
							String[] kolone_izvjestaj_1 = {"ID", "SPISAK JELA", "DATUM", "OPIS", "ODGOVORNA OSOBA", "ADRESA NARUČIOCA", "BROJ TELEFONA NARUČIOCA", "CIJENA"};
							
							dostavljac_tbl = new JTable(podaci_izvjestaj_1, kolone_izvjestaj_1);
							dostavljac_tbl.setBounds(109, 340, 340, -61);
							dostavljac_tbl.getColumn("ID").setMaxWidth(70);
							dostavljac_tbl.getColumn("CIJENA").setMaxWidth(70);
							IzvjestajiTab.add(dostavljac_tbl);
							
							scrollPane_dostavljac = new JScrollPane(dostavljac_tbl);
							scrollPane_dostavljac.setBounds(10, 218, 969, 388);
							IzvjestajiTab.add(scrollPane_dostavljac);
							scrollPane_dostavljac.setVisible(false);
							dostavljac_tbl.setVisible(true);
							scrollPane_dostavljac.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							dostavljac_tbl.setVisible(false);
							scrollPane_dostavljac.setVisible(false);
							JOptionPane.showMessageDialog(frame, "U bazi ne postoji traženi dostavljač:");
							e1.printStackTrace();
						}		
					}
					//kraj drugog izvjetaja
					
					//treci izvještaj
					if(odabrano.equals("Izvještaj o narudžbama po jelima koja ih čine"))
					{
						ocistiFormuOdTabela();
						String jelo = new String();
						jelo = kriterij.getText();
						try {
							Object[][] podaci_izvjestaj_1 = IzvjestajController.dajNaruzbePoJelu(jelo);
							String[] kolone_izvjestaj_1 = {"ID", "SPISAK JELA", "DATUM", "OPIS", "ODGOVORNA OSOBA", "ADRESA NARUČIOCA", "BROJ TELEFONA NARUČIOCA", "CIJENA"};
							
							jelo_tbl = new JTable(podaci_izvjestaj_1, kolone_izvjestaj_1);
							jelo_tbl.setBounds(109, 340, 340, -61);
							jelo_tbl.getColumn("ID").setMaxWidth(70);
							jelo_tbl.getColumn("CIJENA").setMaxWidth(70);
							IzvjestajiTab.add(jelo_tbl);
							
							scrollPane_jelo = new JScrollPane(jelo_tbl);
							scrollPane_jelo.setBounds(10, 218, 969, 388);
							IzvjestajiTab.add(scrollPane_jelo);
							scrollPane_jelo.setVisible(false);
							jelo_tbl.setVisible(true);
							scrollPane_jelo.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							jelo_tbl.setVisible(false);
							scrollPane_jelo.setVisible(false);
							JOptionPane.showMessageDialog(frame, "U bazi ne postoji traženo jelo");
							e1.printStackTrace();
						}		
					}
					//krja treceg izvjetaja
			}
		});

		
		//EMINA:
		JPanel JelovnikTab = new JPanel();
		tabbedPane.addTab("Jelovnik", null, JelovnikTab, null);
		JelovnikTab.setLayout(null);
		
		String[] kolone =  {"Naziv jela", "Cijena(KM)", "Sastojci", "Opis"};
		Object[][] podaci = {{"Hamburger", "3", "Pljeskavica, salata, kecap, zemicka", ""}, 
							 {"Cevapi - velika porcija", "6", "10 cevapa, somun", ""},
							 {"Cevapi - mala porcija", "3", "5 cevapa, pola somuna", ""},
							 {"Pileci fileti", "4", "200gr. pilecih fileta, somun, salata", ""},
							 {"Pileci sendvic", "3", "2 pileca fileta, salata, zemicka", ""}
							};
		table = new JTable(podaci, kolone);
		table.setBounds(10, 11, 671, 275);
		table.getColumn("Cijena(KM)").setMaxWidth(70);
		
	
		JelovnikTab.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 969, 553);
		JelovnikTab.add(scrollPane);
			
		JButton btnDodajNovoJelo = new JButton("Dodaj novo jelo");
		btnDodajNovoJelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDodajNovoJelo.setBounds(97, 575, 161, 29);
		JelovnikTab.add(btnDodajNovoJelo);
		
		JButton btnIzmijeniPostojeeJelo = new JButton("Izmijeni postoje\u0107e jelo");
		btnIzmijeniPostojeeJelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnIzmijeniPostojeeJelo.setBounds(393, 575, 162, 29);
		JelovnikTab.add(btnIzmijeniPostojeeJelo);

		
		JButton btnIzbriiJelo = new JButton("Izbriši jelo");
		btnIzbriiJelo.setBounds(718, 575, 162, 29);
		JelovnikTab.add(btnIzbriiJelo);

		
		JPanel SastojciTab = new JPanel();
		tabbedPane.addTab("Sastojci", null, SastojciTab, null);
		SastojciTab.setLayout(null);

		
		String[] kolone_sastojci = {"Naziv", "Mjerna jedinica sastojka", "Opis"};
		Object[][] podaci_sastojci = {{"Pljeskavica", "komad", ""},
									  {"Somun", "komad", ""},
									  {"Pileći filet", "gram, komad", ""}
									 };
		table_2 = new JTable(podaci_sastojci, kolone_sastojci);
		table_2.setBounds(10, 11, 969, 530);
		SastojciTab.add(table_2);
		
		JScrollPane scrollPane_2 = new JScrollPane(table_2);
		scrollPane_2.setBounds(10, 11, 969, 552);
		SastojciTab.add(scrollPane_2);
		
		JButton btnDodajSastojak = new JButton("Dodaj sastojak");
		btnDodajSastojak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
			}
		});
		btnDodajSastojak.setBounds(106, 574, 162, 30);
		SastojciTab.add(btnDodajSastojak);
		
		JButton button_1 = new JButton("Izmijeni sastojak");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(387, 574, 141, 30);
		SastojciTab.add(button_1);
		
		JButton button_2 = new JButton("Izbriši sastojak");
		button_2.setBounds(710, 574, 141, 30);
		SastojciTab.add(button_2);
		

		String[] kolone_radnici =  {"Ime i prezime", "Datum rođenja", "Radno mjesto", "Korisničko ime", "Lozinka", "Dodatne informacije"};
		Object[][] podaci_radnici = {{"Merisa Golic", "26.04.1995.", "radnik na telefonu", "operater_merisa", "****", ""},
									 {"Ivona Ivkovic", "01.01.1994.", "radnik na telefonu", "operater_ivona", "****", ""},
								     {"Admira Husic", "01.01.1994.", "kuhar", "kuhar_admira", "****", ""},
								     {"Dzana Feratovic", "01.01.1994.", "kuhar", "kuhar_dzana", "****", ""},
								     {"Emina Huskic", "01.01.1994.", "dostavljac", "dostavljac_emina", "****", ""},
								     {"Arnela Duzan", "01.01.1994.", "dostavljac", "dostavljac_arnela", "****", ""}
									};
		
		JPanel KorisniciTab = new JPanel();
		tabbedPane.addTab("Korisni\u010Dki ra\u010Duni", null, KorisniciTab, null);
		KorisniciTab.setLayout(null);
		table_1 = new JTable(podaci_radnici, kolone_radnici);
		table_1.setBounds(1, 26, 450, 48);
		
		KorisniciTab.add(table_1);
		
		JScrollPane scrollPane_1 = new JScrollPane(table_1);
		scrollPane_1.setBounds(10, 11, 969, 552);
		KorisniciTab.add(scrollPane_1);
		
		JButton btnDodajNovogRadnika = new JButton("Dodaj novog radnika");
		btnDodajNovogRadnika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UnosIzmjenaRadnika forma = new UnosIzmjenaRadnika();
				forma.setVisible(true);
			}
		});
		btnDodajNovogRadnika.setBounds(66, 574, 165, 30);
		KorisniciTab.add(btnDodajNovogRadnika);
		
		JButton btnNewButton = new JButton("Izmijeni podatke");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UnosIzmjenaRadnika forma = new UnosIzmjenaRadnika();
				forma.setVisible(true);
			}
		});
		btnNewButton.setBounds(300, 574, 165, 30);
		KorisniciTab.add(btnNewButton);
		
		JButton btnIzmijeniSifru = new JButton("Izmijeni lozinku");
		btnIzmijeniSifru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnIzmijeniSifru.setBounds(519, 574, 166, 30);
		KorisniciTab.add(btnIzmijeniSifru);
		
		JButton button = new JButton("Izbriši radnika");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(759, 574, 166, 30);
		KorisniciTab.add(button);
		
		JPanel PopustiTab = new JPanel();
		tabbedPane.addTab("Popusti", null, PopustiTab, null);
		PopustiTab.setLayout(null);
		
		Object[][] podaci_popust = {{"0-10", "0"},
				  					{"10-20", "5"},
				  					{"30-40", "10"},
				  					{"50-60", "15"},
				  					{"70-80", "20"},
				  					{"90-100", "25"},
				  					{"100-", "30"}
				 };
		String[] kolone_popust = {"Raspon cijena(KM)", "Popust (%)"};
		
		table_5 = new JTable(podaci_popust, kolone_popust);
		
		JScrollPane scrollPane_5 = new JScrollPane(table_5);
		scrollPane_5.setBounds(10, 11, 969, 475);
		PopustiTab.add(scrollPane_5);
		
		JButton btnDodajPopust = new JButton("Dodaj popust");
		btnDodajPopust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDodajPopust.setBounds(161, 537, 139, 30);
		PopustiTab.add(btnDodajPopust);
		
		JButton btnNewButton_1 = new JButton("Izmijeni popust");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(399, 537, 139, 30);
		PopustiTab.add(btnNewButton_1);
		
		JButton btnIzbriiPopust = new JButton("Izbriši popust");
		btnIzbriiPopust.setBounds(642, 537, 139, 30);
		PopustiTab.add(btnIzbriiPopust);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 48, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu mnLogOut = new JMenu("Meni");
		menuBar.add(mnLogOut);
		
		JMenuItem mntmLogOut = new JMenuItem("Odjava");
		mnLogOut.add(mntmLogOut);
	}

	private void ocistiFormuOdTabela() {
		table_3.setVisible(false);
		dostavljac_tbl.setVisible(false);
		scrollPane_dostavljac.setVisible(false);
		jelo_tbl.setVisible(false);
		scrollPane_jelo.setVisible(false);
		scrollPane_3.setVisible(false);
	}
}
