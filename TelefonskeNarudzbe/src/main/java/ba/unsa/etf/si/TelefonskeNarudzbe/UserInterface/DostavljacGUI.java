package ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface;

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
import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.RacunPDF;
import org.apache.log4j.Logger;

import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Narudzba;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

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
	private int selectedIndex = -1;
	private JList listaNarudzbi;
	private DostavljacController dostKontroler = new DostavljacController();
	private Narudzba izabranaNarudzba;
	private JPanel glavniPanel;
	private JPanel panel;
	private Zaposlenik zap = dostKontroler.dajZaposlenika();
	private RacunPDF racun = new RacunPDF();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DostavljacGUI window = new DostavljacGUI();
					window.frmDostavaNarudbi.setVisible(true);
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
	public DostavljacGUI() {
		initialize();

	}

	public DostavljacGUI(Zaposlenik zapp) {
		zap = zapp;
		initialize();
		frmDostavaNarudbi.setVisible(true);

	}

	private void ProvjeriDaLiJePreuzeta() throws Exception {
		try {
			if (selectedIndex != -1) {
				boolean preuzeta = dostKontroler.provjeriDaLiJePreuzeta(izabranaNarudzba.getId());
				if (preuzeta)
					JOptionPane.showMessageDialog(null, "Narudžba je već preuzeta!");
				else {
					dostKontroler.promijeniStatusUPreuzeta(izabranaNarudzba.getId(), zap.getId());
					JOptionPane.showMessageDialog(null, "Narudžba je uspješno preuzeta");
					model.set(selectedIndex, model.getElementAt(selectedIndex) + "p");
					racun.createPDF(izabranaNarudzba);
					btnNarudbaDostavljena.setEnabled(true);
					textNovacaDostavljeno.setEnabled(true);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Niste odabrali narudžbu");
			}

		} catch (Exception e) {
			logger.info(e);
			JOptionPane.showMessageDialog(null, "Desila se greška!");
		}
	}
	private void OdjaviSe() throws Exception {
		try {
			dostKontroler.Odjava();
			frmDostavaNarudbi.setVisible(false);
			LoginGUI log = new LoginGUI();

		} catch (Exception e) {
			logger.info(e);
			throw new Exception();
		}
	}
	private void PrikaziNarudzbe() {
		try {
			// model.removeAllElements();
			glavniPanel.setVisible(true);
			List<Narudzba> listaNarudzbi = dostKontroler.dajSveNarudzbeIzBaze(zap.getId());
			int brojac = 0;
			for (Iterator<Narudzba> i = listaNarudzbi.iterator(); i.hasNext();) {
				Narudzba item = i.next();
				if (item.getStatus() == 4)
					model.addElement(item.getId() + "p");

				else
					model.addElement(item.getId());
				brojac++;
			}
		} catch (Exception e) {
			logger.info(e);
			JOptionPane.showMessageDialog(null, "Desila se greška!");
		}
	}

	private void ObrisiPolja() {
		adresaText.setText("");
		brojTelefonaText.setText("");
		infoText.setText("");
		cijenaText.setText("");
		selectedIndex = -1;
	}

	private void PromijeniStatusUspremna() {
		try {
			if (selectedIndex != -1) {
				Double cijena = Double.parseDouble(textNovacaDostavljeno.getText());
				if (cijena < 0 || !cijena.equals(izabranaNarudzba.getCijena()))
					throw new Exception("Cijena nije ispravno unesena ili je razlicita od cijene narudžbe.");
				dostKontroler.promijeniStatusUSpremna(izabranaNarudzba.getId());
				JOptionPane.showMessageDialog(null, "Narudžba je dostavljena!");

				model.removeElementAt(selectedIndex);

				ObrisiPolja();
			

			} else {
				JOptionPane.showMessageDialog(null, "Niste odabrali narudžbu");
			}

		} catch (Exception e) {
			logger.info(e);
			if (e.getMessage().equals("Cijena nije ispravno unesena ili je razlicita od cijene narudžbe."))
				JOptionPane.showMessageDialog(null, e.getMessage());
			else JOptionPane.showMessageDialog(null, "Desila se greška!");
		}

	}

	private void OtvoriNarudzbu(int id) throws Exception {
		selectedIndex = listaNarudzbi.getSelectedIndex();
		izabranaNarudzba = dostKontroler.dajNarudzbuIzBaze(id);
		try {
			if (izabranaNarudzba.getStatus() == 4) {
				btnNarudbaDostavljena.setEnabled(true);

				textNovacaDostavljeno.setEnabled(true);
			} else
				btnNarudbaDostavljena.setEnabled(false);

			Kupac kupac = dostKontroler.dajKupca(izabranaNarudzba.getKupac().getId());
			adresaText.setText(kupac.getAdresa());
			String formatted = String.format("%09d", kupac.getBrojTelefona());
			brojTelefonaText.setText((formatted));
			infoText.setText(kupac.getInfo());
			cijenaText.setText(Double.toString(izabranaNarudzba.getCijena()));
		} catch (Exception e) {
			logger.info(e);
			JOptionPane.showMessageDialog(null, "Desila se greška!");
		}
	}

	private void initialize() {
		frmDostavaNarudbi = new JFrame();
		frmDostavaNarudbi.setTitle("Dostava narud\u017Ebi");
		frmDostavaNarudbi.setBounds(100, 100, 475, 396);
		frmDostavaNarudbi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDostavaNarudbi.getContentPane().setLayout(null);

		JLabel lblDobrodoaoIme = new JLabel("Dobrodo\u0161ao, " + zap.getImePrezime() + "!");
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
		mntmOdjava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					OdjaviSe();
				} catch (Exception e1) {
					logger.info(e);
					e1.printStackTrace();
				}

			}
		});
		glavniPanel = new JPanel();
		glavniPanel.setBounds(10, 69, 439, 279);
		frmDostavaNarudbi.getContentPane().add(glavniPanel);
		glavniPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 150, 257);
		glavniPanel.add(scrollPane);

		listaNarudzbi = new JList(model);
		scrollPane.setViewportView(listaNarudzbi);
		panel = new JPanel();
		panel.setBounds(173, 11, 256, 254);
		glavniPanel.add(panel);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
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

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(210, 68, -129, 36);
		panel_1.add(scrollPane_1);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(88, 67, 123, 36);
		panel_1.add(scrollPane_2);

		infoText = new JTextArea();
		scrollPane_2.setViewportView(infoText);

		JButton btnPreuzmiNarudbu = new JButton("Preuzmi narudžbu");
		btnPreuzmiNarudbu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ProvjeriDaLiJePreuzeta();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Desila se greška!");
					logger.info(e);
					e.printStackTrace();
				}
			}
		});
		btnPreuzmiNarudbu.setBounds(71, 161, 160, 22);
		panel.add(btnPreuzmiNarudbu);

		JLabel lblNovacaDostavljenokm = new JLabel("Novaca dostavljeno (KM):");
		lblNovacaDostavljenokm.setBounds(10, 197, 148, 14);
		panel.add(lblNovacaDostavljenokm);

		textNovacaDostavljeno = new JTextField();
		textNovacaDostavljeno.setColumns(10);
		textNovacaDostavljeno.setBounds(158, 194, 73, 20);
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
		listaNarudzbi.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
					JList source = (JList) event.getSource();
					if (source.getSelectedValue() != null) {
						String selected = source.getSelectedValue().toString();
						selectedIndex = source.getSelectedIndex();
						int n = selected.length();
						String y = selected.substring(n - 1, n);
						String x = selected.substring(0, n - 1);
						if (y.equals("p"))
							try {
								OtvoriNarudzbu(Integer.parseInt(x));
							} catch (NumberFormatException e) {
								logger.info(e);
								JOptionPane.showMessageDialog(null, "Desila se greška!");
								e.printStackTrace();
							} catch (Exception e) {
								logger.info(e);
								JOptionPane.showMessageDialog(null, "Desila se greška!");
								e.printStackTrace();
							}
						else
							try {
								OtvoriNarudzbu(Integer.parseInt(selected));
							} catch (NumberFormatException e) {
								logger.info(e);
								JOptionPane.showMessageDialog(null, "Desila se greška!");
								e.printStackTrace();
							} catch (Exception e) {
								logger.info(e);
								JOptionPane.showMessageDialog(null, "Desila se greška!");
								e.printStackTrace();
							}
					}
				}
			}
		});
		glavniPanel.setVisible(false);
	}
}
