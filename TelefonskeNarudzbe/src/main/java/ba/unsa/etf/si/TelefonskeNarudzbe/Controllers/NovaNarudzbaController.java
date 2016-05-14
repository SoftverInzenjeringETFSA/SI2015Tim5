package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.*;
import Util.HibernateUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JList;
import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;


public class NovaNarudzbaController {
	
	public NovaNarudzbaController(){
		
	}
		
	public List<Jelo> dajSvaJela() {
		Session s=HibernateUtil.getSessionFactory().openSession();
		List<Jelo> lista=new ArrayList<Jelo> ();
		Criteria k=s.createCriteria(Jelo.class);
		
		lista=(List<Jelo>)k.list();
		
		return lista;
	}
	
	public Jelo dajJelo(String nazivJela)
	{
		Session s=HibernateUtil.getSessionFactory().openSession();
		Criteria c=s.createCriteria(Jelo.class).add(Restrictions.eq("naziv", nazivJela));	
		Jelo vraceno=(Jelo)c.uniqueResult();
		
		return vraceno;
	}
	
	
	public Zaposlenik dajZaposlenika(Integer i)
	{
		Session s=HibernateUtil.getSessionFactory().openSession();
		Criteria c=s.createCriteria(Zaposlenik.class).add(Restrictions.eq("id", i));
		
		Zaposlenik vraceni=(Zaposlenik)c.uniqueResult();
		
		return vraceni;	
	}
	
	public Popust dajPopust(Double cijena) {
		Session s=HibernateUtil.getSessionFactory().openSession();
		
		Criteria c=s.createCriteria(Popust.class).add(Restrictions.between("od", 0.00, cijena)).add(Restrictions.between("doo", cijena, 1000000.00));
		
		Popust vraceni=(Popust)c.uniqueResult();
		
		return vraceni;
	}
	

	public void spremiNovuNarudzbu(Narudzba n)
	{
		Session s=HibernateUtil.getSessionFactory().openSession();
		Transaction t=s.beginTransaction();
		
		s.save(n);
		t.commit();
	}
	
	public void spremiNovogKupca(Kupac k)
	{
		Session s=HibernateUtil.getSessionFactory().openSession();
		Transaction t=s.beginTransaction();
		
		s.save(k);
		t.commit();

	}


	public void spremiNarudzbaJeloVeza(NarudzbaJeloVeza njv){
		Session s=HibernateUtil.getSessionFactory().openSession();
		Transaction t=s.beginTransaction();
		
		s.save(njv);
		t.commit();	
	}


}
