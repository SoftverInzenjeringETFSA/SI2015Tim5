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
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import org.apache.log4j.Logger;
import org.jfree.ui.RefineryUtilities;

import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Jelo;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Narudzba;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Popust;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.RadnoMjesto;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Sastojak;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.SastojciJeloVeza;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Zaposlenik;
import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.BarChart_AWT;
import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.BrisanjeJela;
import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.BrisanjePopusta;
import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.BrisanjeRadnika;
import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.BrisanjeSastojka;
import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.IzvjestajController;
import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.UnosIzmjenaJelaController;
import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.UnosIzmjenaPopustaController;
import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.UnosIzmjenaRadnikaController;
import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.UnosIzmjenaSastojkaController;
import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.ValidacijaController;
import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.KuharController;

public class sef {
	public  int dodajNovi = -1;
	private  JFrame frame;
	private  JTable table;
	private  JTable table_1;
	private  JTable table_2;
	private  JTable table_3 = new JTable();
	private  JTable table_5;
	private JTable dostavljac_tbl = new JTable();
	private JScrollPane scrollPane_dostavljac = new JScrollPane();
	private JTable jelo_tbl = new JTable();
	private JScrollPane scrollPane_jelo = new JScrollPane();
	private JTable izvjestaj4_tbl = new JTable();
	private JScrollPane scrollPane_izvjestaj4 = new JScrollPane();
	private JTable izvjestaj5_tbl = new JTable();
	private JScrollPane scrollPane_izvjestaj5 = new JScrollPane();
	private JScrollPane scrollPane_3 = new JScrollPane();
	final static Logger logger = Logger.getLogger(sef.class);
	private String odabirIzvjestaja = new String();
	private static sef window;
	private JButton dugmeGraficki;
	private UnosIzmjenaJela forma;
	private UnosIzmjenaJela forma3;
	private UnosIzmjenaRadnika forma1;
	private UnosIzmjenaRadnika forma2;
	private UnosIzmjenaRadnika forma22;
	private IzmjenaLozinke formaLozinka;
	private UnosIzmjenaPopusta forma4;
	private UnosIzmjenaPopusta forma5;
	private UnosIzmjenaSastojka forma6;

	private UnosIzmjenaSastojka forma7;
	private IzvjestajController IzvjestajController = new IzvjestajController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new sef();
					window.frame.setVisible(true);
				} catch (Exception e) {
					logger.info(e);
					// e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private Zaposlenik logovani;

	public  Zaposlenik vratiLogovanog() {
		return logovani;
	}

	public sef() {
		initialize();
		frame.setVisible(true);
	}

	public sef(Zaposlenik z) {
		logovani = z;
		initialize();
		frame.setVisible(true);
	}

	private  void Graficki() {
		
		Object[][] podaci = IzvjestajController.dajVremenaIsporuke();
		final BarChart_AWT chart = new BarChart_AWT("Statistički izvještaj o vremenima isporuke", "", podaci);
		chart.pack();
		RefineryUtilities.centerFrameOnScreen(chart);
		chart.setVisible(true);

	}

	private void OdjaviSe() throws Exception {
		try {
			UnosIzmjenaPopustaController up = new UnosIzmjenaPopustaController();
			if (forma != null) {
				forma.dajOkvir();
			}
			if (forma1 != null) {
				forma1.dajOkvir();
			}
			if (forma2 != null) {
				forma2.dajOkvir();
			}
			if (forma3 != null) {
				forma3.dajOkvir();
			}
			if (forma4 != null) {
				forma4.dajOkvir();
			}
			if (forma5 != null) {
				forma5.dajOkvir();
			}
			if (forma6 != null) {
				forma6.dajOkvir();
			}
			if (forma7 != null) {
				forma7.dajOkvir();
			}
			if (forma22 != null) {
				forma22.dajOkvir();
			}
			up.Odjava();
			frame.setVisible(false);
			LoginGUI log = new LoginGUI();

		} catch (Exception e) {
			logger.info(e);
			// throw new Exception();
		}
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

		String[] values = new String[] { "Izvještaj o svim narudžbama u vremenskom periodu",
				"Izvještaj o svim odrađenim dostavama po dostavljaču", "Izvještaj o narudžbama po jelima koja ih čine",
				"Statistički izvještaj o vremenu isporuke narudžbi", "Statistički izvjestaj o broju naručenih jela" };

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

		// btn za onos kriterija
		final JLabel lblKriterij = new JLabel();
		lblKriterij.setBounds(28, 161, 100, 14);
		IzvjestajiTab.add(lblKriterij);
		lblKriterij.setVisible(false);

		// unos kriterija
		final JTextField kriterij = new JTextField();
		kriterij.setBounds(159, 158, 166, 20);
		IzvjestajiTab.add(kriterij);
		kriterij.setColumns(10);
		kriterij.setVisible(false);

		// format datuma
		MaskFormatter datum = new MaskFormatter();
		try {
			datum = new MaskFormatter("##.##.####.");
			datum.setPlaceholderCharacter(' ');
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.info(e);// e.printStackTrace();
		}
		// datum
		final JLabel lblDatum = new JLabel("Datum:");
		lblDatum.setBounds(28, 124, 46, 14);
		IzvjestajiTab.add(lblDatum);
		lblDatum.setVisible(false);
		// OD
		final JLabel lblOd = new JLabel("Od:");
		lblOd.setBounds(28, 161, 25, 14);
		IzvjestajiTab.add(lblOd);
		lblOd.setVisible(false);
		final JFormattedTextField datumOd = new JFormattedTextField(datum);
		datumOd.setBounds(63, 158, 150, 20);
		IzvjestajiTab.add(datumOd);
		datumOd.setColumns(10);
		datumOd.setVisible(false);
		// DO
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
		 * //tabela 5 String[] kolone_izvjestaj_2 = {"Izabrano jelo",
		 * "Broj narudžbi izabranog jela"}; Object[][] podaci_izvjestaj_2 =
		 * {{"",""}}; final JTable table_4 = new JTable(podaci_izvjestaj_2,
		 * kolone_izvjestaj_2); table_4.setBounds(109, 340, 340, -61);
		 * IzvjestajiTab.add(table_4); final JScrollPane scrollPane_4 = new
		 * JScrollPane(table_4); scrollPane_4.setBounds(10, 218, 969, 388);
		 * IzvjestajiTab.add(scrollPane_4); scrollPane_4.setVisible(false);
		 * 
		 * 
		 */

		btnPrikaiIzvjetaj.addActionListener(new ActionListener() {
			public final void actionPerformed(ActionEvent e) {
				dugmeGraficki.setVisible(false);
				btnGenerisiIzvjetaj.setVisible(false);
				odabirIzvjestaja = new String();
				if (list.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(frame, "Morate odabrati tip izvjestaja!");
					return;
				}
				odabirIzvjestaja = list.getSelectedValue().toString();
				odabirIzvjestaja = list.getSelectedValue().toString();
				lblDatum.setVisible(false);
				lblOd.setVisible(false);
				datumOd.setVisible(false);
				lblDo.setVisible(false);
				datumDo.setVisible(false);
				if (odabirIzvjestaja.equals("Izvještaj o svim narudžbama u vremenskom periodu")) {
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
				if (odabirIzvjestaja.equals("Izvještaj o svim odrađenim dostavama po dostavljaču")) {
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
				if (odabirIzvjestaja.equals("Izvještaj o narudžbama po jelima koja ih čine")) {
					btnGenerisiIzvjetaj.setVisible(true);
					ocistiFormuOdTabela();
					lblKriterij.setText("Naziv jela:");
					lblKriterij.setVisible(true);
					kriterij.setText("");
					kriterij.setVisible(true);
				}
				if (odabirIzvjestaja.equals("Statistički izvještaj o vremenu isporuke narudžbi")) {
					btnGenerisiIzvjetaj.setVisible(true);
					ocistiFormuOdTabela();
					kriterij.setVisible(false);
					lblKriterij.setVisible(false);
					dugmeGraficki.setVisible(true);
				}
				if (odabirIzvjestaja.equals("Statistički izvjestaj o broju naručenih jela")) {
					btnGenerisiIzvjetaj.setVisible(true);
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

		dugmeGraficki = new JButton("Prikaži izvještaj");
		dugmeGraficki.setBounds(599, 191, 197, 23);
		IzvjestajiTab.add(dugmeGraficki);
		dugmeGraficki.setVisible(false);
		dugmeGraficki.addActionListener(new ActionListener() {
			public final void actionPerformed(ActionEvent arg0) {
				Graficki();
			}
		});
		// btn za prikaz izvještaja
		btnGenerisiIzvjetaj.addActionListener(new ActionListener() {
			public final void actionPerformed(ActionEvent e) {
				String odabrano = list.getSelectedValue().toString();
				if (!odabrano.equals(odabirIzvjestaja)) {
					JOptionPane.showMessageDialog(frame, "Kliknite prvo na odaberi izvještaj!");
					return;
				}
				if (odabrano.equals("Izvještaj o svim narudžbama u vremenskom periodu")) {
					ocistiFormuOdTabela();
					// datum kupljenje sa forme
					String datumOdString = new String();
					datumOdString = datumOd.getText();
					ValidacijaController vc = new ValidacijaController();
					if (vc.validirajDatum(datumOdString) == false) {
						JOptionPane.showMessageDialog(frame, "Pogrešan datum od:");
					}
					String datumDoString = new String();
					datumDoString = datumDo.getText();
					if (vc.validirajDatum(datumDoString) == false) {
						JOptionPane.showMessageDialog(frame, "Pogrešan datum do:");
					}
					if (vc.validirajDatum(datumDoString)
							&& vc.validirajDatum(datumOdString)) {
						try {
							String[] kolone_izvjestaj_1 = { "ID", "SPISAK JELA", "DATUM", "OPIS", "ODGOVORNA OSOBA",
									"ADRESA NARUČIOCA", "BROJ TELEFONA NARUČIOCA", "CIJENA" };
							Object[][] podaci_izvjestaj_1 = IzvjestajController.dajNaruzbePoDatumu(datumOdString,
									datumDoString);

							table_3 = new JTable(podaci_izvjestaj_1, kolone_izvjestaj_1);
							table_3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
							logger.info(e1);// e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(frame, "Ne postoje narudzbe u tom periodu!");

							e1.printStackTrace();
						}
					}
				}

				// drugi izvještaj
				if (odabrano.equals("Izvještaj o svim odrađenim dostavama po dostavljaču")) {
					ocistiFormuOdTabela();
					String dostavljac = new String();
					dostavljac = kriterij.getText();
					if (!ValidacijaController.jeLiDuzeOd3Slova(dostavljac)) {
						JOptionPane.showMessageDialog(null, "Ime dostavljaca mora biti duze od 3 slova");
						return;
					}
					try {
						Object[][] podaci_izvjestaj_1 = IzvjestajController.dajNaruzbePoDostavljacu(dostavljac);
						String[] kolone_izvjestaj_1 = { "ID", "SPISAK JELA", "DATUM", "OPIS", "ODGOVORNA OSOBA",
								"ADRESA NARUČIOCA", "BROJ TELEFONA NARUČIOCA", "CIJENA" };

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
						logger.info(e1);// e1.printStackTrace();
					}
				}
				// kraj drugog izvjetaja

				// treci izvještaj
				if (odabrano.equals("Izvještaj o narudžbama po jelima koja ih čine")) {
					ocistiFormuOdTabela();
					String jelo = new String();
					jelo = kriterij.getText();
					try {
						Object[][] podaci_izvjestaj_1 = IzvjestajController.dajNaruzbePoJelu(jelo);
						String[] kolone_izvjestaj_1 = { "ID", "SPISAK JELA", "DATUM", "OPIS", "ODGOVORNA OSOBA",
								"ADRESA NARUČIOCA", "BROJ TELEFONA NARUČIOCA", "CIJENA" };

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
						logger.info(e1);// e1.printStackTrace();
					}
				}
				// krja treceg izvjetaja

				// cetvrti izvjestaj
				if (odabrano.equals("Statistički izvještaj o vremenu isporuke narudžbi")) {
					ocistiFormuOdTabela();
					String[] kolone_izvjestaj_6 = { "Vremenski rok(minute)", "Broj narudžbi", "Procenat" };
					Object[][] podaci_izvjestaj_6 = IzvjestajController.dajVremenaIsporuke();
					izvjestaj4_tbl = new JTable(podaci_izvjestaj_6, kolone_izvjestaj_6);
					izvjestaj4_tbl.setBounds(109, 340, 340, -61);
					IzvjestajiTab.add(izvjestaj4_tbl);
					scrollPane_izvjestaj4 = new JScrollPane(izvjestaj4_tbl);
					scrollPane_izvjestaj4.setBounds(10, 218, 969, 388);
					IzvjestajiTab.add(scrollPane_izvjestaj4);
					izvjestaj4_tbl.setVisible(true);
					scrollPane_izvjestaj4.setVisible(true);

				}
				// kraj cetvrtog

				// pocetak petog
				if (odabrano.equals("Statistički izvjestaj o broju naručenih jela")) {

					Object[][] podaci_izvjestaj_2;
					try {
						ocistiFormuOdTabela();
						String jelo = kriterij.getText();
						String[] kolone_izvjestaj_2 = { "Izabrano jelo", "Broj narudžbi izabranog jela" };
						podaci_izvjestaj_2 = IzvjestajController.dajBrojNarudzbiPoJelu(jelo);
						izvjestaj5_tbl = new JTable(podaci_izvjestaj_2, kolone_izvjestaj_2);
						izvjestaj5_tbl.setBounds(109, 340, 340, -61);
						IzvjestajiTab.add(izvjestaj5_tbl);
						scrollPane_izvjestaj5 = new JScrollPane(izvjestaj5_tbl);
						scrollPane_izvjestaj5.setBounds(10, 218, 969, 388);
						IzvjestajiTab.add(scrollPane_izvjestaj5);
						scrollPane_izvjestaj5.setVisible(true);
						izvjestaj5_tbl.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						logger.info(e1);// e1.printStackTrace();
						ocistiFormuOdTabela();
						JOptionPane.showMessageDialog(frame, "U bazi ne postoji traženo jelo");

					}

				}
				// kraj petog

			}
		});

		// EMINA:
		JPanel JelovnikTab = new JPanel();
		tabbedPane.addTab("Jelovnik", null, JelovnikTab, null);
		JelovnikTab.setLayout(null);
		UnosIzmjenaJelaController jc = new UnosIzmjenaJelaController();
		List<Jelo> listaJela = jc.vratiSvaJela();
		String[] kolone = { "Naziv jela", "Cijena(KM)", "Sastojci" };
		DefaultTableModel tableModel2 = new DefaultTableModel(kolone, 0){
			  @Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		for (Jelo j : listaJela) {
			if (j.getIzbrisano() != null && j.getIzbrisano() == true)
				continue;
			Object[] o = new Object[3];
			o[0] = j.getNaziv();
			o[1] = ValidacijaController.vratiDecimalan(j.getCijena());
			o[2] = UnosIzmjenaSastojkaController.vratiSastojkeJela(j);
			tableModel2.addRow(o);
		}
		table = new JTable(tableModel2);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.setBounds(10, 11, 671, 275);
		table.getColumn("Cijena(KM)").setMaxWidth(70);

		JelovnikTab.add(table);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 969, 553);
		JelovnikTab.add(scrollPane);

		JButton btnDodajNovoJelo = new JButton("Dodaj novo jelo");
		btnDodajNovoJelo.addActionListener(new ActionListener() {

			public final void actionPerformed(ActionEvent arg0) {
				dodajNovi = -1;
				forma3 = new UnosIzmjenaJela(sef.this);
				forma3.setVisible(true);
			}
		});
		btnDodajNovoJelo.setBounds(97, 575, 161, 29);
		JelovnikTab.add(btnDodajNovoJelo);

		JButton btnIzmijeniPostojeeJelo = new JButton("Izmijeni postoje\u0107e jelo");
		btnIzmijeniPostojeeJelo.addActionListener(new ActionListener() {

			public final void actionPerformed(ActionEvent arg0) {
				try {
					int selected = table.getSelectedRow();
					String naziv = (String) table.getValueAt(selected, 0);
					Jelo j = UnosIzmjenaJelaController.vratiJelo(naziv);
					dodajNovi=j.getId();
					forma = new UnosIzmjenaJela(j, dodajNovi, sef.this);
					forma.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Morate odabrati jelo!");

				}
			}
		});
		btnIzmijeniPostojeeJelo.setBounds(393, 575, 162, 29);
		JelovnikTab.add(btnIzmijeniPostojeeJelo);

		JButton btnIzbriiJelo = new JButton("Izbriši jelo");
		btnIzbriiJelo.addActionListener(new ActionListener() {
			public final void actionPerformed(ActionEvent arg0) {
				try {
					int selected = table.getSelectedRow();
					String naziv = (String) table.getValueAt(selected, 0);
					BrisanjeJela.BrisiJelo(naziv);
					((DefaultTableModel) table.getModel()).fireTableDataChanged();
					((DefaultTableModel) table.getModel()).removeRow(selected);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Morate odabrati jelo!");

				}
			}
		});
		btnIzbriiJelo.setBounds(718, 575, 162, 29);
		JelovnikTab.add(btnIzbriiJelo);

		JPanel SastojciTab = new JPanel();
		tabbedPane.addTab("Sastojci", null, SastojciTab, null);
		SastojciTab.setLayout(null);

		List<Sastojak> listaSastojaka = UnosIzmjenaSastojkaController.vratiSveSastojke();
		String[] kolone_sastojci = { "Naziv", "Mjerna jedinica sastojka", "Opis" };
		final DefaultTableModel tableModel3 = new DefaultTableModel(kolone_sastojci, 0){
			  @Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		for (Sastojak s : listaSastojaka) {
			if (s.isIzbrisan())
				continue;
			Object[] o = new Object[3];
			o[0] = s.getNaziv();
			o[1] = s.getMjernaJedinica();
			o[2] = s.getOpis();
			tableModel3.addRow(o);
		}
		table_2 = new JTable(tableModel3);
		table_2.setBounds(10, 11, 969, 530);
		SastojciTab.add(table_2);

		JScrollPane scrollPane_2 = new JScrollPane(table_2);
		table_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane_2.setBounds(10, 11, 969, 552);
		SastojciTab.add(scrollPane_2);

		JButton btnDodajSastojak = new JButton("Dodaj sastojak");
		btnDodajSastojak.addActionListener(new ActionListener() {
			public final void actionPerformed(ActionEvent e) {
				dodajNovi = -1;
				forma6 = new UnosIzmjenaSastojka(sef.this);
				forma6.setVisible(true);
			}
		});
		btnDodajSastojak.setBounds(106, 574, 162, 30);
		SastojciTab.add(btnDodajSastojak);

		JButton button_1 = new JButton("Izmijeni sastojak");
		button_1.addActionListener(new ActionListener() {
			public final void actionPerformed(ActionEvent e) {
				try {
					int selected = table_2.getSelectedRow();
					String naziv = (String) table_2.getValueAt(selected, 0);
					Sastojak s = UnosIzmjenaSastojkaController.vratiSastojak(naziv);
					dodajNovi=s.getId();
					forma7 = new UnosIzmjenaSastojka(s,dodajNovi, sef.this);
					forma7.setVisible(true);
				} catch (Exception ee) {
					JOptionPane.showMessageDialog(null, "Morate odabrati sastojak!");

				}
			}
		});
		button_1.setBounds(387, 574, 141, 30);
		SastojciTab.add(button_1);

		JButton button_2 = new JButton("Izbriši sastojak");
		button_2.addActionListener(new ActionListener() {
			public final void actionPerformed(ActionEvent arg0) {
				try {
					int selected = table_2.getSelectedRow();
					String naziv = (String) table_2.getValueAt(selected, 0);
					BrisanjeSastojka br = new BrisanjeSastojka();
					br.BrisiSastojak(naziv);
					((DefaultTableModel) table_2.getModel()).removeRow(selected);
					refreshTabeleJelo();
				} catch (Exception ee) {
					JOptionPane.showMessageDialog(null, "Morate odabrati sastojak!");

				}
			}
		});
		button_2.setBounds(710, 574, 141, 30);
		SastojciTab.add(button_2);
		UnosIzmjenaRadnikaController c = new UnosIzmjenaRadnikaController();
		List<Zaposlenik> listaZaposlenika = c.vratiSveRadnike();
		String[] kolone_radnici = { "Ime i prezime", "Datum rođenja", "Radno mjesto", "Korisničko ime", "Lozinka",
				"Dodatne informacije" };
		JPanel KorisniciTab = new JPanel();
		DefaultTableModel tableModel = new DefaultTableModel(kolone_radnici, 0){
			  @Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		for (Zaposlenik z : listaZaposlenika) {
			if (z.getRadnomjesto().getId() > 4)
				continue;
			Object[] o = new Object[6];
			o[0] = z.getImePrezime();
			o[1] = z.getDatumRodenja();
			RadnoMjesto rm = c.vratiRadnoMjesto(z.getRadnomjesto().getId());
			o[2] = rm.getNaziv();
			o[3] = z.getUsername();
			o[4] = z.getPassword();
			o[5] = z.getDodatneInformacije();
			tableModel.addRow(o);
		}

		table_1 = new JTable(tableModel);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tabbedPane.addTab("Korisni\u010Dki ra\u010Duni", null, KorisniciTab, null);
		KorisniciTab.setLayout(null);
		table_1.setBounds(1, 26, 450, 48);

		KorisniciTab.add(table_1);

		JScrollPane scrollPane_1 = new JScrollPane(table_1);
		scrollPane_1.setBounds(10, 11, 969, 552);
		KorisniciTab.add(scrollPane_1);

		JButton btnDodajNovogRadnika = new JButton("Dodaj novog radnika");
		btnDodajNovogRadnika.addActionListener(new ActionListener() {
			public final void actionPerformed(ActionEvent arg0) {
				dodajNovi = -1;
				forma1 = new UnosIzmjenaRadnika(sef.this);
				forma1.setVisible(true);
			}
		});
		btnDodajNovogRadnika.setBounds(66, 574, 165, 30);
		KorisniciTab.add(btnDodajNovogRadnika);

		JButton btnNewButton = new JButton("Izmijeni podatke");
		btnNewButton.addActionListener(new ActionListener() {
			public final void actionPerformed(ActionEvent e) {
				try {
					int selected = table_1.getSelectedRow();
					String username = (String) table_1.getValueAt(selected, 3);
					Zaposlenik z = UnosIzmjenaRadnikaController.vratiRadnika(username);
					dodajNovi=z.getId();
					forma2 = new UnosIzmjenaRadnika(z, dodajNovi, sef.this);
					forma2.setVisible(true);
				} catch (Exception ee) {
					JOptionPane.showMessageDialog(null, "Morate odabrati radnika!");

				}
			}
		});
		btnNewButton.setBounds(300, 574, 165, 30);
		KorisniciTab.add(btnNewButton);

		JButton btnIzmijeniSifru = new JButton("Izmijeni lozinku");
		btnIzmijeniSifru.addActionListener(new ActionListener() {
			public final void actionPerformed(ActionEvent e) {
				try {
					/*
					 * dodajNovi = false; int selected =
					 * table_1.getSelectedRow(); String username = (String)
					 * table_1.getValueAt(selected, 3); Zaposlenik z =
					 * UnosIzmjenaRadnikaController.vratiRadnika(username);
					 * forma22 = new UnosIzmjenaRadnika(z);
					 * forma22.setVisible(true);
					 */
					int selected = table_1.getSelectedRow();
					String username = (String) table_1.getValueAt(selected, 3);
					Zaposlenik z = UnosIzmjenaRadnikaController.vratiRadnika(username);
					dodajNovi=z.getId();
					formaLozinka = new IzmjenaLozinke(z, dodajNovi, sef.this);
					formaLozinka.setVisible(true);
				} catch (Exception ee) {
					JOptionPane.showMessageDialog(null, "Morate odabrati radnika!");

				}
			}
		});
		btnIzmijeniSifru.setBounds(519, 574, 166, 30);
		KorisniciTab.add(btnIzmijeniSifru);

		JButton button = new JButton("Izbriši radnika");
		button.addActionListener(new ActionListener() {
			public final void actionPerformed(ActionEvent e) {
				try {
					int selected = table_1.getSelectedRow();
					String imePrezime = (String) table_1.getValueAt(selected, 0);
					if (BrisanjeRadnika.BrisiRadnika(imePrezime, vratiLogovanog())) {
						((DefaultTableModel) table_1.getModel()).fireTableDataChanged();
						table_1.repaint();
						((DefaultTableModel) table_1.getModel()).removeRow(selected);
					}
				} catch (Exception ee) {
					JOptionPane.showMessageDialog(null, "Morate odabrati radnika!");

				}
			}

		});
		button.setBounds(759, 574, 166, 30);
		KorisniciTab.add(button);

		JPanel PopustiTab = new JPanel();
		tabbedPane.addTab("Popusti", null, PopustiTab, null);
		PopustiTab.setLayout(null);

		UnosIzmjenaPopustaController pc = new UnosIzmjenaPopustaController();
		List<Popust> listaPopusta = pc.vratiSvePopuste();
		String[] kolone_popust = { "Cijena od (KM)", "Cijena do (KM)", "Popust (%)" };
		final DefaultTableModel tableModel4 = new DefaultTableModel(kolone_popust, 0){
			  @Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		for (Popust p : listaPopusta) {
			if (p.getIznos() == 0)
				continue;
			Object[] o = new Object[3];
			o[0] = p.getOd();
			o[1] = p.getDoo();
			o[2] = p.getIznos();
			tableModel4.addRow(o);
		}

		table_5 = new JTable(tableModel4);
		table_5.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane_5 = new JScrollPane(table_5);
		scrollPane_5.setBounds(10, 11, 969, 475);
		PopustiTab.add(scrollPane_5);

		JButton btnDodajPopust = new JButton("Dodaj popust");
		btnDodajPopust.addActionListener(new ActionListener() {

			public final void actionPerformed(ActionEvent arg0) {
				dodajNovi = -1;
				forma4 = new UnosIzmjenaPopusta(sef.this);
				forma4.setVisible(true);
			}
		});
		btnDodajPopust.setBounds(161, 537, 139, 30);
		PopustiTab.add(btnDodajPopust);

		JButton btnNewButton_1 = new JButton("Izmijeni popust");
		btnNewButton_1.addActionListener(new ActionListener() {
			public final void actionPerformed(ActionEvent e) {
				try {
					int selected = table_5.getSelectedRow();
					String cijenaOd = String.valueOf(table_5.getValueAt(selected, 0));
					String cijenaDo = String.valueOf(table_5.getValueAt(selected, 1));
					Popust p = UnosIzmjenaPopustaController.vratiPopust(cijenaOd, cijenaDo);
					dodajNovi = p.getId();
					forma5 = new UnosIzmjenaPopusta(p, sef.this);
					((DefaultTableModel) table_5.getModel()).fireTableChanged(null);
					table_5.repaint();
					forma5.setVisible(true);
				} catch (Exception ee) {
					JOptionPane.showMessageDialog(null, "Morate odabrati popust!");

				}

			}
		});
		btnNewButton_1.setBounds(399, 537, 139, 30);
		PopustiTab.add(btnNewButton_1);

		JButton btnIzbriiPopust = new JButton("Izbriši popust");
		btnIzbriiPopust.addActionListener(new ActionListener() {
			public final void actionPerformed(ActionEvent e) {
				try {
					int selected = table_5.getSelectedRow();
					Double cijenaOd = Double.parseDouble(table_5.getValueAt(selected, 0).toString());
					Double cijenaDo = Double.parseDouble(table_5.getValueAt(selected, 1).toString());
					BrisanjePopusta bp = new BrisanjePopusta();
					bp.BrisiPopust(cijenaOd, cijenaDo);
					tableModel4.fireTableDataChanged();
					((DefaultTableModel) table_5.getModel()).removeRow(selected);

					table_5.repaint();
				} catch (Exception ee) {
					JOptionPane.showMessageDialog(null, "Morate odabrati popust!");

				}
			}
		});
		btnIzbriiPopust.setBounds(642, 537, 139, 30);
		PopustiTab.add(btnIzbriiPopust);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 48, 21);
		frame.getContentPane().add(menuBar);

		JMenu mnLogOut = new JMenu("Meni");
		menuBar.add(mnLogOut);

		JMenuItem mntmLogOut = new JMenuItem("Odjava");
		mnLogOut.add(mntmLogOut);
		mntmLogOut.addActionListener(new ActionListener() {
			public final void actionPerformed(ActionEvent e) {
				try {
					OdjaviSe();
				} catch (Exception e1) {
					logger.info(e1);
					// e1.printStackTrace();
				}

			}
		});
	}

	public void refreshTabelePopust() {
		List<Popust> listaPopusta = UnosIzmjenaPopustaController.vratiSvePopuste();
		String[] kolone_popust = { "Cijena od (KM)", "Cijena do (KM)", "Popust (%)" };
		final DefaultTableModel tableModel4 = new DefaultTableModel(kolone_popust, 0){
			  @Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		for (Popust k : listaPopusta) {
			if (k.getIznos() == 0)
				continue;
			Object[] o = new Object[3];
			o[0] = k.getOd();
			o[1] = k.getDoo();
			o[2] = k.getIznos();
			tableModel4.addRow(o);
		}
		table_5.setModel(tableModel4);

	}

	public void refreshTabeleZaposlenici() {
		UnosIzmjenaRadnikaController c = new UnosIzmjenaRadnikaController();
		List<Zaposlenik> listaZaposlenika = c.vratiSveRadnike();
		String[] kolone_radnici = { "Ime i prezime", "Datum rođenja", "Radno mjesto", "Korisničko ime", "Lozinka",
				"Dodatne informacije" };
		JPanel KorisniciTab = new JPanel();
		DefaultTableModel tableModel = new DefaultTableModel(kolone_radnici, 0){
			  @Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		for (Zaposlenik z : listaZaposlenika) {
			if (z.getRadnomjesto().getId() > 4)
				continue;
			Object[] o = new Object[6];
			o[0] = z.getImePrezime();
			o[1] = z.getDatumRodenja();
			RadnoMjesto rm = c.vratiRadnoMjesto(z.getRadnomjesto().getId());
			o[2] = rm.getNaziv();
			o[3] = z.getUsername();
			o[4] = z.getPassword();
			o[5] = z.getDodatneInformacije();
			tableModel.addRow(o);
		}
		table_1.setModel(tableModel);
	}

	public void refreshTabeleJelo() {

		UnosIzmjenaJelaController jc = new UnosIzmjenaJelaController();
		List<Jelo> listaJela = jc.vratiSvaJela();
		String[] kolone = { "Naziv jela", "Cijena(KM)", "Sastojci" };
		DefaultTableModel tableModel2 = new DefaultTableModel(kolone, 0){
			  @Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		for (int i = 0; i < listaJela.size(); i++) {
			Jelo j = listaJela.get(i);
			if (j.getIzbrisano() == true)
				continue;
			Object[] o = new Object[3];
			o[0] = j.getNaziv();
			o[1] = ValidacijaController.vratiDecimalan(j.getCijena());
			o[2] = UnosIzmjenaSastojkaController.vratiSastojkeJela(j);
			tableModel2.addRow(o);
		}
		table.setModel(tableModel2);
	}

	public void refreshTabeleSastojci() {

		List<Sastojak> listaSastojaka = UnosIzmjenaSastojkaController.vratiSveSastojke();
		String[] kolone_sastojci = { "Naziv", "Mjerna jedinica sastojka", "Opis" };
		final DefaultTableModel tableModel3 = new DefaultTableModel(kolone_sastojci, 0){
			  @Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		for (Sastojak s : listaSastojaka) {
			Object[] o = new Object[3];
			o[0] = s.getNaziv();
			o[1] = s.getMjernaJedinica();
			o[2] = s.getOpis();
			tableModel3.addRow(o);
		}
		
		table_2.setModel(tableModel3);

	}

	private void ocistiFormuOdTabela() {
		table_3.setVisible(false);
		dostavljac_tbl.setVisible(false);
		scrollPane_dostavljac.setVisible(false);
		jelo_tbl.setVisible(false);
		scrollPane_jelo.setVisible(false);
		scrollPane_3.setVisible(false);
		izvjestaj4_tbl.setVisible(false);
		scrollPane_izvjestaj4.setVisible(false);
		izvjestaj5_tbl.setVisible(false);
		scrollPane_izvjestaj5.setVisible(false);
	}

	public int vratiIzabraniSastojak() {
		int selected = table_2.getSelectedRow();
		String naziv = (String) table_2.getValueAt(selected, 0);
		Sastojak s = UnosIzmjenaSastojkaController.vratiSastojak(naziv);
		return s.getId();

	}

	public int vratiIzabraniPopust() {
		int selected = table_5.getSelectedRow();
		String cijenaOd = (String) table_5.getValueAt(selected, 0);
		String cijenaDo = (String) table_5.getValueAt(selected, 1);
		Popust p = UnosIzmjenaPopustaController.vratiPopust(cijenaOd, cijenaDo);
		return p.getId();

	}

	public int vratiIzabranoJelo() {
		int selected = table.getSelectedRow();
		String naziv = (String) table.getValueAt(selected, 0);
		Jelo p = UnosIzmjenaJelaController.vratiJelo(naziv);
		return p.getId();

	}

	public int vratiIzabranogRadnika() {
		int selected = table_1.getSelectedRow();
		String username = (String) table_1.getValueAt(selected, 3);
		Zaposlenik s = UnosIzmjenaRadnikaController.vratiRadnika(username);

		return s.getId();

	}

}
