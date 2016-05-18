package ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.UnosIzmjenaJelaController;
import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.UnosIzmjenaPopustaController;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Narudzba;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Popust;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.hibernate.Transaction;
import org.hibernate.SharedSessionContract;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class UnosIzmjenaPopusta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private UnosIzmjenaPopusta forma = this;
	final static Logger logger = Logger.getLogger(UnosIzmjenaPopusta.class);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnosIzmjenaPopusta frame = new UnosIzmjenaPopusta();
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
	
	public UnosIzmjenaPopusta() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 340, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCijenaOd = new JLabel("Raspon cijena:");
		
		JLabel lblPopust = new JLabel("Popust:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Zavr\u0161i ure\u0111ivanje");
		
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"0-9KM", "10-19KM", "20-29KM", "30-39KM", "40-49KM", ">=50KM"}));
		comboBox.setEditable(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(39)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblCijenaOd)
								.addComponent(lblPopust))
							.addGap(29)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textField)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCijenaOd)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPopust))
					.addGap(40)
					.addComponent(btnNewButton)
					.addContainerGap(100, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ind = comboBox.getSelectedIndex();
				if (textField.getText().isEmpty() || textField.getText()==null){
					JOptionPane.showMessageDialog(null, "Popunite polje za popust!");
					return;	
				}
				try{
					int popust = Integer.parseInt(textField.getText());
					if(popust<0){
						JOptionPane.showMessageDialog(null, "Popust mora biti nenegativan cijeli broj!");
						return;
					}
					else if (popust>=100){
						JOptionPane.showMessageDialog(null, "Popust mora biti manji od 100(u procentima)"
								+ "");
						return;
					}
					double cijenaOd=0;
					double cijenaDo=0;
					
					if (ind ==0){
							
							cijenaOd=0;
							cijenaDo=9;}
							else if( ind== 1){
							cijenaOd=10;
							cijenaDo=19;}
							else if (ind==2){
							cijenaOd=20;
							cijenaDo=29;}
							else if (ind==3){
							cijenaOd=30;
							cijenaDo=39;
							}
							else if (ind==4){
							cijenaOd=40;
							cijenaDo=49;
							}
							else if (ind==5){
							JOptionPane.showMessageDialog(null, "Indeks je:"+ ind);
							
							cijenaOd=50;
							cijenaDo=10000;
							}
				
					
					UnosIzmjenaPopustaController c=new UnosIzmjenaPopustaController();
					c.izmjenaPopusta(cijenaOd, cijenaDo, popust);
					JOptionPane.showMessageDialog(null, "Popust uspjesno dodan/izmijenjen!");
					setVisible(false); //you can't see me!
					dispose(); //Destroy the JFrame object
			
				}
				catch(Exception ek)
				{
					logger.info(ek);
					JOptionPane.showMessageDialog(null, "Popust mora biti nenegativan cijeli broj!");
					return;
				}
				
			}
		});
	}
	public UnosIzmjenaPopusta(Popust p) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 340, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCijenaOd = new JLabel("Raspon cijena:");
		
		JLabel lblPopust = new JLabel("Popust:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField.setText(String.valueOf(p.getIznos()));
		JButton btnNewButton = new JButton("Zavr\u0161i ure\u0111ivanje");
		
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"0-9KM", "10-19KM", "20-29KM", "30-39KM", "40-49KM", ">=50KM"}));
		comboBox.setEditable(false);
		comboBox.setSelectedIndex((int) (p.getOd()/10));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(39)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblCijenaOd)
								.addComponent(lblPopust))
							.addGap(29)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textField)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCijenaOd)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPopust))
					.addGap(40)
					.addComponent(btnNewButton)
					.addContainerGap(100, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ind = comboBox.getSelectedIndex();
				if (textField.getText().isEmpty() || textField.getText()==null){
					JOptionPane.showMessageDialog(null, "Popunite polje za popust!");
					return;	
				}
				try{
					int popust = Integer.parseInt(textField.getText());
					if(popust<0){
						JOptionPane.showMessageDialog(null, "Popust mora biti nenegativan cijeli broj!");
						return;
					}
					else if (popust>=100){
						JOptionPane.showMessageDialog(null, "Popust mora biti manji od 100(u procentima)"
								+ "");
						return;
					}
					double cijenaOd=0;
					double cijenaDo=0;
					
					if (ind ==0){
							
							cijenaOd=0;
							cijenaDo=9;}
							else if( ind== 1){
							cijenaOd=10;
							cijenaDo=19;}
							else if (ind==2){
							cijenaOd=20;
							cijenaDo=29;}
							else if (ind==3){
							cijenaOd=30;
							cijenaDo=39;
							}
							else if (ind==4){
							cijenaOd=40;
							cijenaDo=49;
							}
							else if (ind==5){
							cijenaOd=50;
							cijenaDo=10000;
							}
				
					
					UnosIzmjenaPopustaController c=new UnosIzmjenaPopustaController();
					c.izmjenaPopusta(cijenaOd, cijenaDo, popust);
					JOptionPane.showMessageDialog(null, "Popust uspjesno dodan/izmijenjen!");
					setVisible(false); //you can't see me!
					dispose(); //Destroy the JFrame object
				}
				catch(Exception ek)
				{
					logger.info(ek);
					JOptionPane.showMessageDialog(null, "Popust mora biti nenegativan cijeli broj!");
					return;
				}
				
			}
		});
	}
}
