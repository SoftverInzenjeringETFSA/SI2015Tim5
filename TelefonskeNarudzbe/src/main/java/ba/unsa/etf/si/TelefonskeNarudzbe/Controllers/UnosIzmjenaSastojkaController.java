package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import java.util.List;
import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import Util.HibernateUtil;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Jelo;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Kupac;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Sastojak;

public class UnosIzmjenaSastojkaController {
	public static List<Sastojak> vratiSveSastojke()
	{
		  Session sesija = HibernateUtil.getSessionFactory().openSession();
	        Criteria criteria = sesija.createCriteria(Sastojak.class);
			List<Sastojak> lista = criteria.list();
			sesija.close();
			return lista;
	}
	public static Sastojak vratiSastojak(String naziv)
	{
		Session sesija = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sesija.createCriteria(Sastojak.class).add(Restrictions.eq("naziv", naziv));
		Sastojak s = (Sastojak) criteria.uniqueResult();
		sesija.close();
		return s;
	}
	public static boolean izmjenaSastojka(String naziv, String opis, String mjernaJedinica)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t= session.beginTransaction();
		
		try{
			if (session.createCriteria(Sastojak.class).add(Restrictions.eq("naziv", naziv)).setProjection(Projections.property("naziv")).uniqueResult() == null){
				System.out.println("Tu sam 1");
				Sastojak s = new Sastojak();
				s.setNaziv(naziv);
				s.setOpis(opis);
				s.setMjernaJedinica(mjernaJedinica);
				
				session.beginTransaction();
				session.save(s);
				session.getTransaction().commit();
				
			}
			
			
	
		else {
			System.out.println("Tu sam 2");
			
			Criteria criteria = session.createCriteria(Sastojak.class).add(Restrictions.like("naziv", naziv).ignoreCase());
			
			List<Sastojak> lista = criteria.list();
			Sastojak s = lista.get(0);
			s.setNaziv(naziv);
			s.setOpis(opis);
			s.setMjernaJedinica(mjernaJedinica);
		
	
			session.update(s);		
			t.commit();
			session.close();
			System.out.println("Sastojak je izmijenjen");
			return true;
		}
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
}

