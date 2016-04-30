package ba.unsa.etf.si.TelefonskeNarudzbe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JList;
import javax.swing.JTextField;

public class sef {

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table_3;
	private JTable table_5;

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
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public sef() {
		initialize();
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

		JPanel IzvjestajiTab = new JPanel();
		tabbedPane.addTab("Izvje\u0161taji", null, IzvjestajiTab, null);
		IzvjestajiTab.setLayout(null);
		JList list = new JList(values);
		
		list.setBounds(28, 23, 460, 90);
		IzvjestajiTab.add(list);
		
		JButton btnPrikaiIzvjetaj = new JButton("Odaberi izvještaj");
		btnPrikaiIzvjetaj.setBounds(600, 46, 196, 23);
		IzvjestajiTab.add(btnPrikaiIzvjetaj);
		
		/*
		// Izvještaj 1
		 JLabel lblDatum = new JLabel("Datum:");
		lblDatum.setBounds(28, 124, 46, 14);
		IzvjestajiTab.add(lblDatum);
		
		textField_1 = new JTextField();
		textField_1.setBounds(63, 158, 150, 20);
		IzvjestajiTab.add(textField_1);
		textField_1.setColumns(10); 
		 
		JLabel lblOd = new JLabel("Od:");
		lblOd.setBounds(28, 161, 25, 14);
		IzvjestajiTab.add(lblOd);
		
		JLabel label = new JLabel("Do:");
		label.setBounds(303, 161, 25, 14);
		IzvjestajiTab.add(label);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(338, 158, 150, 20);
		IzvjestajiTab.add(textField_2);
		
		JButton btnGeneriiIzvjetaj = new JButton("Generiši izvještaj");
		btnGeneriiIzvjetaj.setBounds(600, 157, 196, 23);
		IzvjestajiTab.add(btnGeneriiIzvjetaj);
		
		
		String[] kolone_izvjestaj_1 = {"ID", "SPISAK JELA", "DATUM", "OPIS", "ODGOVORNA OSOBA", "ADRESA NARUČIOCA", "BROJ TELEFONA NARUČIOCA", "CIJENA"};
		Object[][] podaci_izvjestaj_1 = {{"","","","", "", "", "", ""}};
		
		
		table_3 = new JTable(podaci_izvjestaj_1, kolone_izvjestaj_1);
		table_3.setBounds(109, 340, 340, -61);
		table_3.getColumn("ID").setMaxWidth(70);
		table_3.getColumn("CIJENA").setMaxWidth(70);
		IzvjestajiTab.add(table_3);
		
		JScrollPane scrollPane_3 = new JScrollPane(table_3);
		scrollPane_3.setBounds(10, 218, 969, 388);
		IzvjestajiTab.add(scrollPane_3);
	   //
	     
	  */
		/*
		// Izvještaj 2
				
		textField_1 = new JTextField();
		textField_1.setBounds(159, 158, 166, 20);
		IzvjestajiTab.add(textField_1);
		textField_1.setColumns(10); 
		 
		JLabel lblOd = new JLabel("Ime dostavljača:");
		lblOd.setBounds(28, 161, 100, 14);
		IzvjestajiTab.add(lblOd);
		
		JButton btnGeneriiIzvjetaj = new JButton("Generiši izvještaj");
		btnGeneriiIzvjetaj.setBounds(600, 157, 196, 23);
		IzvjestajiTab.add(btnGeneriiIzvjetaj);
		
		String[] kolone_izvjestaj_1 = {"ID", "SPISAK JELA", "DATUM", "OPIS", "ODGOVORNA OSOBA", "ADRESA NARUČIOCA", "BROJ TELEFONA NARUČIOCA", "CIJENA"};
		Object[][] podaci_izvjestaj_1 = {{"","","","", "", "", "", ""}};
		
		
		table_3 = new JTable(podaci_izvjestaj_1, kolone_izvjestaj_1);
		table_3.setBounds(109, 340, 340, -61);
		table_3.getColumn("ID").setMaxWidth(70);
		table_3.getColumn("CIJENA").setMaxWidth(70);
		IzvjestajiTab.add(table_3);
		
		JScrollPane scrollPane_3 = new JScrollPane(table_3);
		scrollPane_3.setBounds(10, 218, 969, 388);
		IzvjestajiTab.add(scrollPane_3);
	   //
	    */
		/*
		// Izvještaj 3
		
		textField_1 = new JTextField();
		textField_1.setBounds(159, 158, 166, 20);
		IzvjestajiTab.add(textField_1);
		textField_1.setColumns(10); 
			 
		JLabel lblOd = new JLabel("Naziv jela:");
		lblOd.setBounds(28, 161, 100, 14);
		IzvjestajiTab.add(lblOd);
				
		JButton btnGeneriiIzvjetaj = new JButton("Generiši izvještaj");
		btnGeneriiIzvjetaj.setBounds(600, 157, 196, 23);
		IzvjestajiTab.add(btnGeneriiIzvjetaj);
				
				
		String[] kolone_izvjestaj_1 = {"ID", "SPISAK JELA", "DATUM", "OPIS", "ODGOVORNA OSOBA", "ADRESA NARUČIOCA", "BROJ TELEFONA NARUČIOCA", "CIJENA"};
		Object[][] podaci_izvjestaj_1 = {{"","","","", "", "", "", ""}};
				
				
		table_3 = new JTable(podaci_izvjestaj_1, kolone_izvjestaj_1);
		table_3.setBounds(109, 340, 340, -61);
		table_3.getColumn("ID").setMaxWidth(70);
		table_3.getColumn("CIJENA").setMaxWidth(70);
		IzvjestajiTab.add(table_3);
				
		JScrollPane scrollPane_3 = new JScrollPane(table_3);
		scrollPane_3.setBounds(10, 218, 969, 388);
		IzvjestajiTab.add(scrollPane_3);
			   //
		*/
		/*
		// Izvještaj 4
				
		String[] kolone_izvjestaj_1 = {"Vremenski rok(minute)", "Broj narudžbi", "Procenat"};
		Object[][] podaci_izvjestaj_1 = {{"","",""}};
		
		
		table_3 = new JTable(podaci_izvjestaj_1, kolone_izvjestaj_1);
		table_3.setBounds(109, 340, 340, -61);
		IzvjestajiTab.add(table_3);
		
		JScrollPane scrollPane_3 = new JScrollPane(table_3);
		scrollPane_3.setBounds(10, 146, 969, 460);
		IzvjestajiTab.add(scrollPane_3);
	   //
		*/
		/*
		//Izvještaj 5
		textField_1 = new JTextField();
		textField_1.setBounds(159, 158, 166, 20);
		IzvjestajiTab.add(textField_1);
		textField_1.setColumns(10); 
			 
		JLabel lblOd = new JLabel("Naziv jela:");
		lblOd.setBounds(28, 161, 100, 14);
		IzvjestajiTab.add(lblOd);
				
		JButton btnGeneriiIzvjetaj = new JButton("Generiši izvještaj");
		btnGeneriiIzvjetaj.setBounds(600, 157, 196, 23);
		IzvjestajiTab.add(btnGeneriiIzvjetaj);
		
		String[] kolone_izvjestaj_1 = {"Izabrano jelo", "Broj narudžbi izabranog jela"};
		Object[][] podaci_izvjestaj_1 = {{"",""}};
				
				
		table_3 = new JTable(podaci_izvjestaj_1, kolone_izvjestaj_1);
		table_3.setBounds(109, 340, 340, -61);
		IzvjestajiTab.add(table_3);
				
		JScrollPane scrollPane_3 = new JScrollPane(table_3);
		scrollPane_3.setBounds(10, 218, 969, 388);
		IzvjestajiTab.add(scrollPane_3);
		//
		*/
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
				UnosIzmjenaJela forma = new UnosIzmjenaJela();
				forma.setVisible(true);
			}
		});
		btnDodajNovoJelo.setBounds(97, 575, 161, 29);
		JelovnikTab.add(btnDodajNovoJelo);
		
		JButton btnIzmijeniPostojeeJelo = new JButton("Izmijeni postoje\u0107e jelo");
		btnIzmijeniPostojeeJelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UnosIzmjenaJela forma = new UnosIzmjenaJela();
				forma.setVisible(true);
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
				UnosIzmjenaSastojka forma = new UnosIzmjenaSastojka();
				forma.setVisible(true);
			}
		});
		btnDodajSastojak.setBounds(106, 574, 162, 30);
		SastojciTab.add(btnDodajSastojak);
		
		JButton button_1 = new JButton("Izmijeni sastojak");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UnosIzmjenaSastojka forma = new UnosIzmjenaSastojka();
				forma.setVisible(true);
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
				IzmjenaLozinke forma = new IzmjenaLozinke();
				forma.setVisible(true);
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
				UnosIzmjenaPopusta forma = new UnosIzmjenaPopusta();
				forma.setVisible(true);
			}
		});
		btnDodajPopust.setBounds(161, 537, 139, 30);
		PopustiTab.add(btnDodajPopust);
		
		JButton btnNewButton_1 = new JButton("Izmijeni popust");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UnosIzmjenaPopusta forma = new UnosIzmjenaPopusta();
				forma.setVisible(true);
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
}
