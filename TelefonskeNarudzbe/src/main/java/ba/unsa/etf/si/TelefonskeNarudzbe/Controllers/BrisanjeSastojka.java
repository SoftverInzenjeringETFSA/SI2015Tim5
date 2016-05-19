package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import Util.HibernateUtil;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Sastojak;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.SastojciJeloVeza;

public class BrisanjeSastojka {
	final static Logger logger = Logger.getLogger(BrisanjeSastojka.class);

	public static boolean BrisiSastojak(String naziv) {
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction t = session.beginTransaction();
			Criteria criteria = session.createCriteria(Sastojak.class).add(Restrictions.like("naziv", naziv).ignoreCase());
			List<Sastojak> lista = criteria.list();
			Sastojak s = lista.get(0);
			s.setIzbrisan(true);
			session.update(s);
			t.commit();
			session.close();
			Session session2 = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria2 = session2.createCriteria(SastojciJeloVeza.class);
			List<SastojciJeloVeza> l = criteria2.list();
			t = session2.beginTransaction();
			for (SastojciJeloVeza sjv: l){
				if(sjv.getSastojak().getId()==s.getId()){
					t = session2.beginTransaction();
					t.begin();
					session2.delete(sjv);
					t.commit();
				}
			}
			JOptionPane.showMessageDialog(null, "Sastojak uspjesno izbrisan!");
		} catch (Exception e) {
			logger.info(e);
			return false;
		}
		return true;		
	}
}
