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
import ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface.sef;
import Util.HibernateUtil;

public class BrisanjeRadnika {
	final static Logger logger = Logger.getLogger(BrisanjeRadnika.class);

	public static boolean BrisiRadnika(String imePrezime, Zaposlenik logovani) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction t = session.beginTransaction();
			Criteria criteria = session.createCriteria(Zaposlenik.class)
					.add(Restrictions.like("imePrezime", imePrezime).ignoreCase());
			List<Zaposlenik> lista = criteria.list();
			Zaposlenik z = lista.get(0);
			if (z.getImePrezime().equals(logovani.getImePrezime())) {
				JOptionPane.showMessageDialog(null, "Nije moguce izbrisati svoj nalog!");
				return false;
			}
			UnosIzmjenaRadnikaController c = new UnosIzmjenaRadnikaController();
			z.setRadnomjesto(c.vratiRadnoMjesto(z.getRadnomjesto().getId() + 4));
			session.update(z);
			t.commit();
			session.close();
			JOptionPane.showMessageDialog(null, "Radnik uspjesno izbrisan!");

		} catch (ConstraintViolationException e) {
			logger.info(e);
			// Ignore the exception here by doing nothing
		} catch (Exception e) {
			logger.info(e);
			return false;
		}
		return true;
	}

}
