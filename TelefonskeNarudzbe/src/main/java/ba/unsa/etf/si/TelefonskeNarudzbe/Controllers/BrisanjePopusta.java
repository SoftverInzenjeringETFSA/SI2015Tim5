package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import Util.HibernateUtil;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Popust;
public class BrisanjePopusta {
	final static Logger logger = Logger.getLogger(BrisanjeSastojka.class);

	public static boolean BrisiPopust(Double cijenaOd, Double cijenaDo) {
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction t = session.beginTransaction();
			Criteria criteria = session.createCriteria(Popust.class).add(Restrictions.eq("od", cijenaOd)).add(Restrictions.eq("doo", cijenaDo));
			List<Popust> lista = criteria.list();
			Popust p = lista.get(0);
			p.setIznos(0);
			t.commit();
			session.close();

			JOptionPane.showMessageDialog(null, "Popust uspjesno izbrisan!");
		} catch (Exception e) {
			logger.info(e);
			return false;
		}
		return true;		
	}
}
