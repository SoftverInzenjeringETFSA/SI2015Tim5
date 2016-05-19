package ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.jboss.logging.Logger;

import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.UnosIzmjenaJelaController;
import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.UnosIzmjenaPopustaController;
import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.UnosIzmjenaSastojkaController;
import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.ValidacijaController;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Jelo;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Sastojak;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.SastojciJeloVeza;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

public class UnosIzmjenaJela extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnZavriUreivanje;
	private UnosIzmjenaJela forma = this;
	final static Logger logger = Logger.getLogger(UnosIzmjenaJela.class);
	private JTable table;
	private JTextArea textArea;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	private static boolean isInteger(String s) {
	    try { 
	      Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	    	//System.out.println("INTEDZER");
	    	logger.info(e);
		      
	        return false; 
	    } catch(NullPointerException e) {
	    	logger.info(e);
	        return false;
	    }
	    catch(Exception e){
	    	logger.info(e);
	    	return false;
	    	
	    }
	    // only got here if we didn't return false
	    return true;
	}
	public void dajOkvir(){
		this.setVisible(false);
		this.dispose();
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnosIzmjenaJela frame = new UnosIzmjenaJela(null);
					frame.setVisible(true);
				} catch (Exception e) {
					logger.info(e);
					// e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UnosIzmjenaJela(final sef Sef) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 455, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		textField = new JTextField();
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JLabel lblNazivJela = new JLabel("Naziv jela:");

		JLabel lblCijenakm = new JLabel("Cijena (KM):");

		JLabel lblSastojci = new JLabel("Sastojci:");

		JLabel lblOpis = new JLabel("Opis:");

		btnZavriUreivanje = new JButton("Zavr\u0161i ure\u0111ivanje");
		btnZavriUreivanje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String naziv = textField.getText();
				try{
				Double cijena = Double.parseDouble(textField_1.getText());
				if (cijena<=0) {
					JOptionPane.showMessageDialog(null, "Cijena mora biti nenegativan broj!");
					return;
				}
				
				cijena = ValidacijaController.zaokruziNa2(cijena);
				if(!ValidacijaController.jeLiDuzeOd3Slova(naziv)){
					JOptionPane.showMessageDialog(null, "Naziv jela mora imati barem 3 slova!");
					return;
				}
				String opis = textArea.getText();
				if(!ValidacijaController.manjeOd500(opis)){
					JOptionPane.showMessageDialog(null, "Opis jela moze imati maksimalno 500 znakova!");
					return;
				}
				ArrayList<String> sastojakNaziv = new ArrayList<String>();
				ArrayList<Double> sastojakKolicina = new ArrayList<Double>();
				ArrayList<Sastojak> sastojci = new ArrayList<Sastojak>();
				for (int count = 0; count < table_1.getRowCount(); count++) {

					if (table_1.getValueAt(count, 1).toString()!="") {
						sastojakNaziv.add(table_1.getValueAt(count, 0).toString());
						UnosIzmjenaSastojkaController c = new UnosIzmjenaSastojkaController();
						sastojci.add(c.vratiSastojak(table_1.getValueAt(count, 0).toString()));
						sastojakKolicina.add(Double.parseDouble(table_1.getValueAt(count, 1).toString()));
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Kolicina sastojka mora biti nenegativan broj!");
						return;
					}
				}

				if (naziv == null || naziv.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Popunite polje naziv!");
					return;
				} else if (cijena == null || textField_1.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Popunite polje cijena!");
					return;
				} else if (opis == null || opis.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Popunite polje opis!");
					return;
				} else {
					UnosIzmjenaJelaController c = new UnosIzmjenaJelaController();
					c.izmjenaJela(naziv, opis, cijena, sastojci, sastojakKolicina, -1, Sef);
					JOptionPane.showMessageDialog(null, "Jelo je dodano/izmijenjeno");
					setVisible(false); dispose();
				}

			
				}
				catch(Exception eks)
				{
					logger.info(eks);
					JOptionPane.showMessageDialog(null, "Potrebno je ispravno popuniti sva polja!");
					return;
			
				}
				
		}});

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Naziv sastojka", "Kolicina" }) {
			Class[] columnTypes = new Class[] { String.class, Double.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return column ==1 ? false : true;
		    }
			
		});
		textArea = new JTextArea();

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(44, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(lblNazivJela)
								.addComponent(lblCijenakm).addComponent(lblSastojci)
								.addComponent(lblOpis))
						.addGap(42)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 211,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 208,
														GroupLayout.PREFERRED_SIZE)
												.addContainerGap())
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(btnZavriUreivanje).addContainerGap())
												.addGroup(gl_contentPane.createSequentialGroup()
														.addGroup(gl_contentPane
																.createParallelGroup(Alignment.LEADING, false)
																.addComponent(textField_1).addComponent(textField,
																		GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
														.addContainerGap(75, Short.MAX_VALUE)))))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(22)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNazivJela))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCijenakm, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addGap(20)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblSastojci).addGap(52))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 61,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblOpis).addGap(114))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 97,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)))
						.addComponent(btnZavriUreivanje).addGap(44)));

		UnosIzmjenaSastojkaController sc = new UnosIzmjenaSastojkaController();
		List<Sastojak> sastojci = sc.vratiSveSastojke();
		DefaultTableModel model = new DefaultTableModel();
		
				// Create a couple of columns
		model.addColumn("Naziv sastojka");
		model.addColumn("Kolicina");

		// Append a row
		for (int i = 0; i < sastojci.size(); i++) {
			if(sastojci.get(i).isIzbrisan()) continue;
			model.addRow(new Object[] { ((Sastojak) sastojci.get(i)).getNaziv(), 0 });
	}
		table_1 = new JTable(model);
		scrollPane.setViewportView(table_1);
		contentPane.setLayout(gl_contentPane);

	}
	
	public UnosIzmjenaJela(Jelo j, final int dodajNovi, final sef Sef) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 455, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setText(j.getNaziv());
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setText(ValidacijaController.vratiDecimalan(j.getCijena()));
		textField_1.addFocusListener(new FocusListener() {            
	        public void focusLost(FocusEvent arg0) {
	        	try{
	        		String t = textField_1.getText();
	        		Double broj = Double.parseDouble(t);
	        	//	Double d = ValidacijaController.zaokruziNa2(broj);
	        		textField_1.setText(ValidacijaController.vratiDecimalan(broj));
	        	  
	          }
	        	catch(Exception e){
	        		logger.info(e);
	        		textField_1.setBackground(Color.red);
	        	}
	        }

	        public void focusGained(FocusEvent arg0) {
	           textField_1.setBackground(Color.white);

	        };
	    });
		JLabel lblNazivJela = new JLabel("Naziv jela:");

		JLabel lblCijenakm = new JLabel("Cijena (KM):");

		JLabel lblSastojci = new JLabel("Sastojci:");

		JLabel lblOpis = new JLabel("Opis:");

		btnZavriUreivanje = new JButton("Zavr\u0161i ure\u0111ivanje");
		btnZavriUreivanje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String naziv = textField.getText();
				try{
				Double cijena = Double.parseDouble(textField_1.getText());
				if (cijena<=0) {
					JOptionPane.showMessageDialog(null, "Cijena mora biti nenegativan broj!");
					return;
				}
				cijena = ValidacijaController.zaokruziNa2(cijena);
				String opis = textArea.getText();
				if(!ValidacijaController.jeLiDuzeOd3Slova(naziv)){
					JOptionPane.showMessageDialog(null, "Naziv jela mora imati više od 3 slova!");
					return;
				}
				if (!ValidacijaController.manjeOd500(opis))
				{
					JOptionPane.showMessageDialog(null, "Opis može imati najviše 500 znakova!");
					return;
				}
				ArrayList<String> sastojakNaziv = new ArrayList<String>();
				ArrayList<Double> sastojakKolicina = new ArrayList<Double>();
				ArrayList<Sastojak> sastojci = new ArrayList<Sastojak>();
				for (int count = 0; count < table_1.getRowCount(); count++) {

					if (Double.parseDouble(table_1.getValueAt(count, 1).toString())>=0) {
						sastojakNaziv.add(table_1.getValueAt(count, 0).toString());
						UnosIzmjenaSastojkaController c = new UnosIzmjenaSastojkaController();
						sastojci.add(c.vratiSastojak(table_1.getValueAt(count, 0).toString()));
						sastojakKolicina.add(Double.parseDouble(table_1.getValueAt(count, 1).toString()));
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Kolicina sastojka mora biti nenegativan broj!");
						return;
					}
				}

				if (naziv == null || naziv.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Popunite polje naziv!");
					return;
				} else if (cijena == null || textField_1.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Popunite polje cijena!");
					return;
				} else if (opis == null || opis.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Popunite polje opis!");
					return;
				} else {
				
					UnosIzmjenaJelaController c = new UnosIzmjenaJelaController();
					c.izmjenaJela(naziv, opis, cijena, sastojci, sastojakKolicina, dodajNovi,Sef);
					Sef.refreshTabeleJelo();
					JOptionPane.showMessageDialog(null, "Jelo je dodano/izmijenjeno");
					setVisible(false); dispose();
				}
					
				}
				catch(Exception eks)
				{
					logger.info(eks);
					JOptionPane.showMessageDialog(null, "Potrebno je ispravno popuniti sva polja!");
					return;
			
				}
				
		}});
		

		textArea = new JTextArea();
		textArea.setText(j.getOpis());
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(44, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(lblNazivJela)
								.addComponent(lblCijenakm).addComponent(lblSastojci)
								.addComponent(lblOpis))
						.addGap(42)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 211,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 208,
														GroupLayout.PREFERRED_SIZE)
												.addContainerGap())
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(btnZavriUreivanje).addContainerGap())
												.addGroup(gl_contentPane.createSequentialGroup()
														.addGroup(gl_contentPane
																.createParallelGroup(Alignment.LEADING, false)
																.addComponent(textField_1).addComponent(textField,
																		GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
														.addContainerGap(75, Short.MAX_VALUE)))))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(22)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNazivJela))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCijenakm, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addGap(20)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblSastojci).addGap(52))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 61,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblOpis).addGap(114))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 97,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)))
						.addComponent(btnZavriUreivanje).addGap(44)));

	
		DefaultTableModel model = new DefaultTableModel(){
			  @Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       if(column==0) return false;
			       return true;
			    }
		};
		Jelo jelo=UnosIzmjenaJelaController.vratiJelo(textField.getText().toString());
		List<SastojciJeloVeza> sjv= UnosIzmjenaSastojkaController.vratiKolicineSastojakaJela(jelo);
		
				// Create a couple of columns
		model.addColumn("Naziv sastojka");
		model.addColumn("Kolicina");

		// Append a row
		/*for (int i = 0; i < sastojci.size(); i++) {
			if(sastojci.get(i).isIzbrisan()) continue;
			model.addRow(new Object[] { ((Sastojak) sastojci.get(i)).getNaziv(), 0 });
	}*/
		for (int i=0; i<sjv.size(); i++){
			if(sjv.get(i).getJelo().getNaziv().equals(j.getNaziv())){
				model.addRow(new Object[] {sjv.get(i).getSastojak().getNaziv(), sjv.get(i).getKolicina() });
				
			}
		}
		List<Sastojak> sastojci = UnosIzmjenaSastojkaController.vratiSveSastojke();
		for (Sastojak s: sastojci){
			if(s.isIzbrisan()) continue;
			boolean postoji=false;
			for (SastojciJeloVeza sas: sjv){
			if(s.getNaziv().equals(sas.getSastojak().getNaziv()) && sas.getJelo().getNaziv().equals(j.getNaziv())){
				postoji=true;
			}
			}
			
			if (postoji) continue;
			model.addRow(new Object[] {s.getNaziv(), 0.0 });
			
			
		}
		table_1 = new JTable(model);
		scrollPane.setViewportView(table_1);
		contentPane.setLayout(gl_contentPane);
	}
}
