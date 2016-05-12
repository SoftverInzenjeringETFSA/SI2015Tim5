package ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;



import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Narudzba;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.hibernate.Transaction;
import org.hibernate.SharedSessionContract;

public class UnosIzmjenaPopusta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
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

	/**
	 * Create the frame.
	 */
	
	public UnosIzmjenaPopusta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 340, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCijenaOd = new JLabel("Cijena od:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblCijenaDo = new JLabel("Cijena do:");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblPopust = new JLabel("Popust:");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Zavr\u0161i ure\u0111ivanje");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				forma.dispose();
			}
		});
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
								.addComponent(lblPopust)
								.addComponent(lblCijenaDo)
								.addComponent(lblCijenaOd))
							.addGap(29)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_2)
								.addComponent(textField_1)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))))
					.addContainerGap(99, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCijenaOd)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCijenaDo)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPopust)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(74)
					.addComponent(btnNewButton)
					.addContainerGap(33, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	

}
