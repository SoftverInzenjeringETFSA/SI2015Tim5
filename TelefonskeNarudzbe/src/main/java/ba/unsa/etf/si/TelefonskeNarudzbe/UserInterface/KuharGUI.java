package ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
import javax.swing.JScrollPane;

public class KuharGUI {
	private DefaultListModel model = new DefaultListModel();
	private JFrame frmSpremanjeNarudbi;
	final static Logger logger = Logger.getLogger(KuharGUI.class);
	private final Action action = new SwingAction();
	private KuharController kuharKontroler = new KuharController();
	private JPanel glavniPanel;
	private Narudzba izabranaNarudzba;
	private JTextArea sastojcitextArea;
	private JButton btnSpremi;
	private int selectedIndex = -1;
	private JButton btnPreuzmi;
	private JList list;
	private JTextArea dodatneInformacijeTextArea;
	private Zaposlenik zap = kuharKontroler.dajZaposlenika();

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

	private void ProvjeriDaLiJePreuzeta() throws Exception {
		try {
			if (selectedIndex != -1) {
				boolean preuzeta = kuharKontroler.provjeriDaLiJePreuzeta(izabranaNarudzba.getId());
				if (preuzeta)
					JOptionPane.showMessageDialog(null, "Narudžba je već preuzeta!");
				else {
					kuharKontroler.promijeniStatusUPreuzeta(izabranaNarudzba.getId(), zap.getId());
					JOptionPane.showMessageDialog(null, "Narudžba je uspješno preuzeta");
					model.set(selectedIndex, model.getElementAt(selectedIndex) + "p");
					btnSpremi.setEnabled(true);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Niste odabrali narudžbu");
			}

		} catch (Exception e) {
			logger.info(e);
			JOptionPane.showMessageDialog(null, "Desila se greška!");
		}
	}

	private void PrikaziNarudzbe() {
		try {
			model.removeAllElements();
			glavniPanel.setVisible(true);
			List<Narudzba> listaNarudzbi = kuharKontroler.dajSveNarudzbeIzBaze(zap.getId());
			int brojac = 0;
			for (Iterator<Narudzba> i = listaNarudzbi.iterator(); i.hasNext();) {
				Narudzba item = i.next();
				if (item.getStatus() == 2)
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
		sastojcitextArea.setText("");
		dodatneInformacijeTextArea.setText("");
		selectedIndex = -1;
	}

	private void PromijeniStatusUspremna() {
		try {
			if (selectedIndex != -1) {
				kuharKontroler.promijeniStatusUSpremna(izabranaNarudzba.getId(), zap.getId());
				JOptionPane.showMessageDialog(null, "Narudžba je spremna!");

				model.removeElementAt(selectedIndex);
				ObrisiPolja();
			} else {
				JOptionPane.showMessageDialog(null, "Niste odabrali narudžbu");
			}

		} catch (Exception e) {
			logger.info(e);
			JOptionPane.showMessageDialog(null, "Desila se greška!");
		}

	}

	private void OtvoriNarudzbu(int id) throws Exception {
		izabranaNarudzba = kuharKontroler.dajNarudzbuIzBaze(id);
		try {
			if (izabranaNarudzba.getStatus() == 2)
				btnSpremi.setEnabled(true);
			else
				btnSpremi.setEnabled(false);

			String narudzbe = kuharKontroler.dajNarudzbe(izabranaNarudzba.getId());
			sastojcitextArea.setText(narudzbe);
			dodatneInformacijeTextArea.setText(izabranaNarudzba.getOpis());
		} catch (Exception e) {
			logger.info(e);
			JOptionPane.showMessageDialog(null, "Desila se greška!");
		}
	}

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

		glavniPanel = new JPanel();
		glavniPanel.setBounds(10, 69, 424, 287);
		frmSpremanjeNarudbi.getContentPane().add(glavniPanel);

		btnPreuzmi = new JButton("Preuzmi");
		btnPreuzmi.setBounds(179, 253, 111, 23);
		btnPreuzmi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ProvjeriDaLiJePreuzeta();
				} catch (Exception e) {
					logger.info(e);
					JOptionPane.showMessageDialog(null, "Desila se greška!");
					e.printStackTrace();
				}
			}
		});
		glavniPanel.setLayout(null);
		glavniPanel.add(btnPreuzmi);
		glavniPanel.setVisible(false);

		btnSpremi = new JButton("Spremi");
		btnSpremi.setBounds(303, 253, 111, 23);
		btnSpremi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PromijeniStatusUspremna();
			}
		});
		glavniPanel.add(btnSpremi);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 146, 265);
		glavniPanel.add(scrollPane);

		list = new JList(model);
		scrollPane.setViewportView(list);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(179, 11, 235, 162);
		glavniPanel.add(scrollPane_1);
		sastojcitextArea = new JTextArea();
		scrollPane_1.setViewportView(sastojcitextArea);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(179, 197, 235, 45);
		glavniPanel.add(scrollPane_2);

		dodatneInformacijeTextArea = new JTextArea();
		scrollPane_2.setViewportView(dodatneInformacijeTextArea);

		JLabel lblDodatneInformacije = new JLabel("Dodatne informacije:");
		lblDodatneInformacije.setBounds(179, 172, 133, 22);
		glavniPanel.add(lblDodatneInformacije);
		lblDodatneInformacije.setFont(new Font("Tahoma", Font.PLAIN, 11));
		list.addListSelectionListener(new ListSelectionListener() {
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
