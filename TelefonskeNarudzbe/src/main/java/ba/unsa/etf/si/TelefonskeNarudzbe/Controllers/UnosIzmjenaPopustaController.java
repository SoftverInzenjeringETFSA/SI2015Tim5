package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import Util.HibernateUtil;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Popust;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Sastojak;

public class UnosIzmjenaPopustaController {
	public List<Popust> vratiSvePopuste(){
		Session sesija = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sesija.createCriteria(Popust.class);
		List<Popust> lista = criteria.list();
		sesija.close();
		return lista;
	}
	public boolean izmjenaPopusta(double cijenaOd, double cijenaDo, Double popust)
	{
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction t = session.beginTransaction();
	if (session.createCriteria(Popust.class).add(Restrictions.eq("od", cijenaOd)).add(Restrictions.eq("doo", cijenaDo)).uniqueResult() == null){
			
			Popust p = new Popust();
			p.setOd(cijenaOd);
			p.setDoo(cijenaDo);
			p.setIznos(popust);
			session.beginTransaction();
			session.save(p);
			session.getTransaction().commit();
			return true;
		}
		else{

			Criteria criteria = session.createCriteria(Popust.class).add(Restrictions.eq("od", cijenaOd)).add(Restrictions.eq("doo", cijenaDo));
				Popust p = (Popust) criteria.uniqueResult();
			p.setOd(cijenaOd);
			p.setDoo(cijenaDo);
			p.setIznos(popust);
			session.update(p);
			t.commit();
			return true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
