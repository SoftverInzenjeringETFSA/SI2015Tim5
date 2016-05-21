package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import Util.HibernateUtil;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Jelo;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Kupac;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Sastojak;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.SastojciJeloVeza;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Zaposlenik;
import ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface.sef;

public class UnosIzmjenaSastojkaController {
	final static Logger logger = Logger.getLogger(UnosIzmjenaSastojkaController.class);

	public static List<Sastojak> vratiSveSastojke() {
		Session sesija = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = sesija.createCriteria(Sastojak.class);
		List<Sastojak> lista = criteria.list();
		List<Sastojak> vrati=new ArrayList<Sastojak> ();
		
		for(Sastojak s:lista) {
			if(s.isIzbrisan()) continue;
			vrati.add(s);
		}
		sesija.close();
		return vrati;
	}

	public static List<String> vratiListuSastojakaJela(Jelo jelo) {
		Session sesija = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = sesija.createCriteria(SastojciJeloVeza.class);
		List<SastojciJeloVeza> lista = criteria.list();
		List<String> novalista = new ArrayList<String>();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getJelo().getId() == jelo.getId())
				novalista.add(lista.get(i).getSastojak().getNaziv());
		}

		sesija.close();
		return novalista;
	}

	public static List<String> vratiListuKolicinaSastojakaJela(Jelo jelo) {
		Session sesija = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = sesija.createCriteria(SastojciJeloVeza.class);
		List<SastojciJeloVeza> lista = criteria.list();
		List<String> novalista = new ArrayList<String>();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getJelo().getId() == jelo.getId())
				novalista.add(String.valueOf(lista.get(i).getKolicina()));
		}

		sesija.close();
		return novalista;
	}

	public static List<SastojciJeloVeza> vratiKolicineSastojakaJela(Jelo jelo) {
		Session sesija = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = sesija.createCriteria(SastojciJeloVeza.class);
		List<SastojciJeloVeza> lista = criteria.list();
		List<SastojciJeloVeza> novalista = new ArrayList<SastojciJeloVeza>();
		double epsilon = 0.0001;
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getJelo().getId() == jelo.getId()
					&& lista.get(i).getKolicina() - new Double(0.0) > epsilon)
				novalista.add(lista.get(i));
		}
		return novalista;
	}

	public static String vratiSastojkeJela(Jelo jelo) {
		Session sesija = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = sesija.createCriteria(SastojciJeloVeza.class);
		List<SastojciJeloVeza> lista = criteria.list();
		List<SastojciJeloVeza> novalista = new ArrayList<SastojciJeloVeza>();
		double epsilon = 0.0001;
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getJelo().getId() == jelo.getId()
					&& lista.get(i).getKolicina() - new Double(0.0) > epsilon)
				novalista.add(lista.get(i));
		}

		String sastojci = "";
		for (SastojciJeloVeza oo : novalista) {
			if(oo.getSastojak().isIzbrisan()) continue;
			sastojci += oo.getSastojak().getNaziv() + ", ";
		}
		sesija.close();
		return sastojci;
	}

	public static Sastojak vratiSastojak(String naziv) {
		Session sesija = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = sesija.createCriteria(Sastojak.class).add(Restrictions.eq("naziv", naziv));
		Sastojak s = (Sastojak) criteria.uniqueResult();
		sesija.close();
		return s;
	}

	public static boolean izmjenaSastojka(String naziv, String opis, String mjernaJedinica, int dodajNovi, sef Sef) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		int ide = -1;
		int ids = dodajNovi;
		boolean izbrisan = false;
		if (ids==-1){
		if (session.createCriteria(Sastojak.class).add(Restrictions.like("naziv", naziv).ignoreCase()).list()
				.size() != 0) {
			Sastojak postojeci = (Sastojak) session.createCriteria(Sastojak.class)
					.add(Restrictions.like("naziv", naziv).ignoreCase()).list().get(0);
			if (!postojeci.isIzbrisan()) {
				JOptionPane.showMessageDialog(null, "Već postoji sastojak sa tim nazivom.");
				session.close();
				return false;
			}
			ide = postojeci.getId();
			izbrisan = true;
		}
		}
		try {
			if (session.createCriteria(Sastojak.class).add(Restrictions.eq("id", ids)).uniqueResult() == null) {
				if (izbrisan) {
					Criteria criteria = session.createCriteria(Sastojak.class).add(Restrictions.like("id", ide));
					List<Sastojak> lista = criteria.list();
					Sastojak s = lista.get(0);
					s.setIzbrisan(false);
					s.setNaziv(naziv);
					s.setOpis(opis);
					s.setMjernaJedinica(mjernaJedinica);

					session.update(s);
					t.commit();
					session.close();
					Sef.refreshTabeleSastojci();
					System.out.println("Sastojak je izmijenjen");
					return true;
				}
				System.out.println("Tu sam 1");
				Sastojak s = new Sastojak();
				s.setNaziv(naziv);
				s.setOpis(opis);
				s.setMjernaJedinica(mjernaJedinica);
				s.setIzbrisan(false);
				session.beginTransaction();
				session.save(s);
				session.getTransaction().commit();
				Sef.refreshTabeleSastojci();
			}

			else {

				System.out.println("Tu sam 2");
				Criteria criteria = session.createCriteria(Sastojak.class).add(Restrictions.like("id", ids));
				List<Sastojak> lista = criteria.list();
				Sastojak s = lista.get(0);
				s.setIzbrisan(false);
				s.setNaziv(naziv);
				s.setOpis(opis);
				s.setMjernaJedinica(mjernaJedinica);

				session.update(s);
				t.commit();
				session.close();
				Sef.refreshTabeleSastojci();
				System.out.println("Sastojak je izmijenjen");
				return true;
			}
		} catch (Exception e) {
			logger.info(e);
			// e.printStackTrace();
			return false;
		}
		return true;

	}
}
