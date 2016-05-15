package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Set;

import Util.HibernateUtil;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Jelo;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Sastojak;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.SastojciJeloVeza;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Zaposlenik;
import ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface.sef;

public class UnosIzmjenaJelaController {
	
	public List<Jelo> vratiSvaJela(){
		Session sesija = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sesija.createCriteria(Jelo.class);
		List<Jelo> r = criteria.list();
		sesija.close();
		return r;
	}
	public static Jelo vratiJelo(String naziv){
		Session sesija = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sesija.createCriteria(Jelo.class).add(Restrictions.like("naziv", naziv).ignoreCase());
		List<Jelo> r = criteria.list();
		Jelo j=r.get(0);
		sesija.close();
		return j;
	}
public static boolean izmjenaJela(String naziv, String opis, Double cijena, List<Sastojak> listaSastojaka,
			List<Double> listaKolicina) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();

		Session session2 = HibernateUtil.getSessionFactory().openSession();
		Transaction t2 = session2.beginTransaction();

		try {
			if (session.createCriteria(Jelo.class).add(Restrictions.eq("naziv", naziv))
					.setProjection(Projections.property("naziv")).uniqueResult() == null) {
				System.out.println("Novo jelo");
				Jelo j = new Jelo();
				j.setNaziv(naziv);
				j.setOpis(opis);
				j.setCijena(cijena);
				session.beginTransaction();
				session.save(j);
				session.getTransaction().commit();
				session2.createCriteria(SastojciJeloVeza.class);
				session2.beginTransaction();
				for (int i = 0; i < listaSastojaka.size(); i++) {
					if (listaKolicina.get(i).equals(0))
						continue;
					SastojciJeloVeza sjv = new SastojciJeloVeza();
					sjv.setJelo(j);
					sjv.setSastojak(listaSastojaka.get(i));
					sjv.setKolicina(listaKolicina.get(i));
					session2.save(sjv);
					session2.getTransaction().commit();
					sef.refreshTabeleJelo();
				}
			}

			else {
				System.out.println("Postoji jelo");
				Criteria criteria = session.createCriteria(Jelo.class)
						.add(Restrictions.like("naziv", naziv).ignoreCase());
				List<Jelo> lista = criteria.list();
				Jelo j = lista.get(0);
				j.setNaziv(naziv);
				j.setOpis(opis);
				j.setCijena(cijena);
				session.update(j);
				t.commit();
				session.close();
				
				for (int i = 0; i < listaSastojaka.size(); i++) {
					if (listaKolicina.get(i) == 0)
						continue;
					Criteria criteria2 = session2.createCriteria(SastojciJeloVeza.class).add(Restrictions.like("jelo", j)).add(Restrictions.like("sastojak", listaSastojaka.get(i)));
					if(criteria2!=null){
					List<SastojciJeloVeza> l = criteria2.list();
					SastojciJeloVeza sjv = l.get(0);
					sjv.setJelo(j);
					sjv.setSastojak(listaSastojaka.get(i));
					sjv.setKolicina(listaKolicina.get(i));
					session2.getTransaction().begin();
					session2.update(sjv);		
					session2.getTransaction().commit();
					}
					else{
						if (listaKolicina.get(i) == 0)
							continue;
						session2.createCriteria(SastojciJeloVeza.class);
						SastojciJeloVeza sjv = new SastojciJeloVeza();
						sjv.setJelo(j);
						sjv.setSastojak(listaSastojaka.get(i));
						sjv.setKolicina(listaKolicina.get(i));
						session2.beginTransaction();
						session2.save(sjv);
						session2.getTransaction().commit();
						sef.refreshTabeleJelo();
					}
					
				}
				session2.close();
				System.out.println("Jelo je izmijenjeno");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}
}
