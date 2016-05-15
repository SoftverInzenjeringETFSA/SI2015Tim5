package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;


import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Zaposlenik;
import Util.HibernateUtil;

public class BrisanjeRadnika {
	final static Logger logger = Logger.getLogger(BrisanjeRadnika.class);

	public static boolean BrisiRadnika(String imePrezime) {
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction t = session.beginTransaction();
			Criteria criteria = session.createCriteria(Zaposlenik.class).add(Restrictions.like("imePrezime", imePrezime).ignoreCase());
			List<Zaposlenik> lista = criteria.list();
			Zaposlenik z = lista.get(0);
			UnosIzmjenaRadnikaController c = new UnosIzmjenaRadnikaController();
			z.setRadnomjesto(c.vratiRadnoMjesto(z.getRadnomjesto().getId()+3));
			session.update(z);
			t.commit();
			session.close();
			JOptionPane.showMessageDialog(null, "Radnik uspjesno izbrisan!");
			
		} 
		catch (ConstraintViolationException e) {
		    // Ignore the exception here by doing nothing
		}catch (Exception e) {
			logger.error(e);
			return false;
		}
		return true;		
	}

}
