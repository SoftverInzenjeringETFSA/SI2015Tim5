package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import Util.HibernateUtil;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Popust;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Sastojak;
import ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface.sef;

public class UnosIzmjenaPopustaController {
	final static Logger logger = Logger.getLogger(UnosIzmjenaPopustaController.class);
	public  void Odjava() throws Exception {
		try {Session s = HibernateUtil.getSessionFactory().openSession();
			s.close();

		} catch (Exception e) {
			logger.info(e);
			throw new Exception();
		}
	}
	public static Popust vratiPopust(String cijenaOd, String cijenaDo) {
		Popust p= null;
		try {
			Double od = Double.parseDouble(cijenaOd);
			Double doo = Double.parseDouble(cijenaDo);
			Session sesija = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = sesija.createCriteria(Popust.class).add(Restrictions.eq("od", od)).add(Restrictions.eq("doo", doo));
			List<Popust> lista = criteria.list();
			p=lista.get(0);
			sesija.close();
			return p;
		} catch (Exception e) {
			logger.info(e);
			JOptionPane.showMessageDialog(null, "Doslo je do greske!");
		}
		return p;
	}

	public static List<Popust> vratiSvePopuste() {
		Session sesija = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = sesija.createCriteria(Popust.class);
		List<Popust> lista = criteria.list();
		sesija.close();
		return lista;
	}

	public boolean izmjenaPopusta(double cijenaOd, double cijenaDo, Double popust) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction t = session.beginTransaction();
			if (session.createCriteria(Popust.class).add(Restrictions.eq("od", cijenaOd))
					.add(Restrictions.eq("doo", cijenaDo)).uniqueResult() == null) {

				Popust p = new Popust();
				p.setOd(cijenaOd);
				p.setDoo(cijenaDo);
				p.setIznos(popust);
				session.beginTransaction();
				session.save(p);
				session.getTransaction().commit();
				return true;
			} else {

				Criteria criteria = session.createCriteria(Popust.class).add(Restrictions.eq("od", cijenaOd))
						.add(Restrictions.eq("doo", cijenaDo));
				Popust p = (Popust) criteria.uniqueResult();
				p.setOd(cijenaOd);
				p.setDoo(cijenaDo);
				p.setIznos(popust);
				session.update(p);
				t.commit();
				sef.refreshTabelePopust();
				return true;
				
			}

		} catch (Exception e) {
			logger.info(e);//e.printStackTrace();
			return false;
		}
	}
}
