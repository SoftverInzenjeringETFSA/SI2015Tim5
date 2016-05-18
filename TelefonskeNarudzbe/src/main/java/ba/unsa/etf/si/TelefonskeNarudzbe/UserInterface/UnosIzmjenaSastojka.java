package ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.UnosIzmjenaSastojkaController;
import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.ValidacijaController;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Sastojak;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UnosIzmjenaSastojka extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private UnosIzmjenaSastojka forma = this;
	final static Logger logger = Logger.getLogger(UnosIzmjenaSastojka.class);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnosIzmjenaSastojka frame = new UnosIzmjenaSastojka();
					frame.setVisible(true);
				} catch (Exception e) {
					logger.info(e);
					//e.printStackTrace();
				}
			}
		});
	}
	public void dajOkvir(){
		this.setVisible(false);
		this.dispose();
	}
	/**
	 * Create the frame.
	 */
	public UnosIzmjenaSastojka() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 402, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNaziv = new JLabel("Naziv:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblMjernaJedinica = new JLabel("Mjerna jedinica:");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblOpis = new JLabel("Opis:");
		
		final JTextArea textArea = new JTextArea();
		
		JButton btnNewButton = new JButton("Zavr\u0161i ure\u0111ivanje");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UnosIzmjenaSastojkaController controller = new UnosIzmjenaSastojkaController();
				String naziv = textField.getText();
				String mjernaJedinica = textField_1.getText();
				String opis=textArea.getText();
				if(naziv == null || naziv.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Popunite polje naziv!");
				}
				else if (mjernaJedinica == null || mjernaJedinica.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Popunite polje mjerna jedinica!");
				}
				else if (opis == null || opis.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Popunite polje opis!");
				}
				else if(!ValidacijaController.manjeOd500(opis)){
					JOptionPane.showMessageDialog(null, "Opis moze imati najvise 500 znakova!");
					
				}
				else
				{
				controller.izmjenaSastojka(naziv,opis,mjernaJedinica);
				JOptionPane.showMessageDialog(null, "Uspjesno dodan/izmijenjen sastojak!");
				textField.setText("");
				textField_1.setText("");
				textArea.setText("");
				setVisible(false); dispose();
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblOpis)
						.addComponent(lblNaziv)
						.addComponent(lblMjernaJedinica))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textArea)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(textField_1)
									.addComponent(textField, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)))
							.addContainerGap(82, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNaziv))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMjernaJedinica)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOpis)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	public UnosIzmjenaSastojka(Sastojak s) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 402, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNaziv = new JLabel("Naziv:");
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setText(s.getNaziv());
		JLabel lblMjernaJedinica = new JLabel("Mjerna jedinica:");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setText(s.getMjernaJedinica());
		JLabel lblOpis = new JLabel("Opis:");
		
		final JTextArea textArea = new JTextArea();
		textArea.setText(s.getOpis());
		JButton btnNewButton = new JButton("Zavr\u0161i ure\u0111ivanje");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UnosIzmjenaSastojkaController controller = new UnosIzmjenaSastojkaController();
				String naziv = textField.getText();
				String mjernaJedinica = textField_1.getText();
				String opis=textArea.getText();
				if(naziv == null || naziv.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Popunite polje naziv!");
				}
				else if (mjernaJedinica == null || mjernaJedinica.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Popunite polje mjerna jedinica!");
				}
				else if (opis == null || opis.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Popunite polje opis!");
				}
				else
				{
				controller.izmjenaSastojka(naziv, opis,mjernaJedinica);
				JOptionPane.showMessageDialog(null, "Uspjesno dodan/izmijenjen sastojak!");
				textField.setText("");
				textField_1.setText("");
				textArea.setText("");
				setVisible(false); dispose();
				
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblOpis)
						.addComponent(lblNaziv)
						.addComponent(lblMjernaJedinica))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textArea)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(textField_1)
									.addComponent(textField, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)))
							.addContainerGap(82, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNaziv))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMjernaJedinica)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOpis)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

}
