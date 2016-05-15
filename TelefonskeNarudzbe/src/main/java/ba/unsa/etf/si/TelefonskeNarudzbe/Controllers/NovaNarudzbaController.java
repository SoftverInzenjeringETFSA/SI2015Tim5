package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.*;
import ba.unsa.etf.si.TelefonskeNarudzbe.*;
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

	public NovaNarudzbaController() {

	}

	public void Odjava() throws Exception {
		Session s = null;
		try {
			s = HibernateUtil.getSessionFactory().openSession();
			s.close();

		} catch (Exception e) {
			// logger.info(e);
			throw new Exception();
		}
	}

	public List<Jelo> dajSvaJela() throws Exception {
		Session s = null;
		try {
			s = HibernateUtil.getSessionFactory().openSession();
			List<Jelo> lista = new ArrayList<Jelo>();
			Criteria k = s.createCriteria(Jelo.class);

			lista = (List<Jelo>) k.list();

			return lista;
		}

		catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		finally {
			if (s != null)
				s.close();
		}
	}

	public Jelo dajJelo(String nazivJela) throws Exception {
		Session s = null;

		try {
			s = HibernateUtil.getSessionFactory().openSession();
			Criteria c = s.createCriteria(Jelo.class).add(Restrictions.eq("naziv", nazivJela));
			Jelo vraceno = (Jelo) c.uniqueResult();

			if (vraceno == null)
				throw new JeloNotFound("Odaberite korektno jelo iz liste!");

			return vraceno;
		}

		catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		finally {
			if (s != null)
				s.close();
		}

	}

	public Zaposlenik dajZaposlenika(Integer i) throws Exception {
		Session s = null;

		try {
			s = HibernateUtil.getSessionFactory().openSession();
			Criteria c = s.createCriteria(Zaposlenik.class).add(Restrictions.eq("id", i));

			Zaposlenik vraceni = (Zaposlenik) c.uniqueResult();

			if (vraceni == null)
				throw new ZaposlenikNotFound("Ne postoji zaposlenik sa datim id-em!");

			return vraceni;
		}

		catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		finally {
			if (s != null)
				s.close();
		}

	}

	public Popust dajPopust(Double cijena) throws Exception {
		Session s = null;

		try {
			s = HibernateUtil.getSessionFactory().openSession();
			Criteria c = s.createCriteria(Popust.class).add(Restrictions.between("od", 0.00, cijena))
					.add(Restrictions.between("doo", cijena, 1000000.00));

			Popust vraceni = (Popust) c.uniqueResult();

			return vraceni;
		}

		catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		finally {
			if (s != null)
				s.close();
		}
	}

	public Boolean spremiNovuNarudzbu(Narudzba n) throws Exception {
		Session s = null;
		Boolean vrati = false;

		try {
			s = HibernateUtil.getSessionFactory().openSession();
			Transaction t = s.beginTransaction();

			s.save(n);
			t.commit();
			vrati = true;
		}

		catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		finally {
			if (s != null)
				s.close();
			return vrati;
		}
	}

	public Boolean spremiNovogKupca(Kupac k) throws Exception {
		Session s = null;
		Boolean vrati = false;

		try {
			s = HibernateUtil.getSessionFactory().openSession();
			Transaction t = s.beginTransaction();

			s.save(k);
			t.commit();
			vrati = true;
		}

		catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		finally {
			if (s != null)
				s.close();
			return vrati;
		}

	}

	public Boolean spremiNarudzbaJeloVeza(NarudzbaJeloVeza njv) throws Exception {
		Session s = null;
		Boolean vrati = false;

		try {
			s = HibernateUtil.getSessionFactory().openSession();
			Transaction t = s.beginTransaction();

			s.save(njv);
			t.commit();
			vrati = true;
		}

		catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		finally {
			if (s != null)
				s.close();
			return vrati;
		}
	}

}
