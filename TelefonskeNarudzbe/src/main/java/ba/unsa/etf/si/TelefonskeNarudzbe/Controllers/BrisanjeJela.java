package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import Util.HibernateUtil;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Jelo;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.SastojciJeloVeza;

public class BrisanjeJela {
	final static Logger logger = Logger.getLogger(BrisanjeJela.class);

	public static boolean BrisiJelo(String naziv) {
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction t = session.beginTransaction();
			Criteria criteria = session.createCriteria(Jelo.class).add(Restrictions.like("naziv", naziv).ignoreCase());
			List<Jelo> lista = criteria.list();
			Jelo j = lista.get(0);
			j.setIzbrisano(true);
			session.update(j);
			t.commit();
			session.close();
			Session session2 = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria2 = session2.createCriteria(SastojciJeloVeza.class);
			List<SastojciJeloVeza> l = criteria2.list();
			t = session2.beginTransaction();
			for (SastojciJeloVeza sjv: l){
				if(sjv.getJelo().getId()==j.getId()){
					JOptionPane.showMessageDialog(null, "sastojak: "+sjv.getSastojak().getNaziv());
					t = session2.beginTransaction();
					t.begin();
					session2.delete(sjv);
					t.commit();
				}
			}
			
			//session2.close();
			JOptionPane.showMessageDialog(null, "Jelo uspjesno obrisano!");
		} catch (Exception e) {
			logger.info(e);
			return false;
		}
		return true;		
	}
}
