package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
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
	final static Logger logger = Logger.getLogger(UnosIzmjenaJelaController.class);

	public List<Jelo> vratiSvaJela() {
		Session sesija = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = sesija.createCriteria(Jelo.class);
		List<Jelo> r = criteria.list();
		sesija.close();
		return r;
	}

	public static Jelo vratiJelo(String naziv) {
		Session sesija = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = sesija.createCriteria(Jelo.class).add(Restrictions.like("naziv", naziv).ignoreCase());

		List<Jelo> r = criteria.list();
		Jelo j = r.get(0);
		sesija.close();
		return j;
	}
	
	private byte[] vratiArrayByte(File f) {
		try {
			FileInputStream inputStream = new FileInputStream(f);
			byte[] fileBytes = new byte[(int) f.length()];
			int broj=0;
			while((broj=inputStream.read(fileBytes))>0) {
				//radi
			}
			inputStream.close();
			byte[] sacuvaj = fileBytes;
			return sacuvaj;
		}

		catch (Exception e) {
			logger.info(e);
			return null;
		}
	}
	
	public boolean izmjenaJela(String naziv, String opis, Double cijena, List<Sastojak> listaSastojaka,
			List<Double> listaKolicina, int dodajNovi, sef Sef, File slika) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();

		Session session2 = HibernateUtil.getSessionFactory().openSession();
		Transaction t2 = session2.beginTransaction();
		int ids = -1;
		if (dodajNovi != -1)
			ids = dodajNovi;
		int ide = -1;
		boolean izbrisan = false;
		if (ids == -1) {
			if (session.createCriteria(Jelo.class).add(Restrictions.like("naziv", naziv).ignoreCase()).list()
					.size() != 0) {
				Jelo postojeci = (Jelo) session.createCriteria(Jelo.class)
						.add(Restrictions.like("naziv", naziv).ignoreCase()).list().get(0);
				if (!postojeci.getIzbrisano()) {
					JOptionPane.showMessageDialog(null, "Već postoji jelo sa tim nazivom.");
					session.close();
					return false;
				}
				ide = postojeci.getId();
				izbrisan = true;
			}
		}
			try {
				if (session.createCriteria(Jelo.class).add(Restrictions.eq("id", ids))
						.setProjection(Projections.property("id")).uniqueResult() == null) {
					System.out.println("Novo jelo");
					if (izbrisan) {
						Criteria criteria = session.createCriteria(Jelo.class).add(Restrictions.like("id", ide));
						List<Jelo> l = criteria.list();
						Jelo j = l.get(0);
						j.setIzbrisano(false);
						j.setNaziv(naziv);
						j.setOpis(opis);
						j.setCijena(cijena);
						j.setSlika(vratiArrayByte(slika));
						session.beginTransaction();
						session.update(j);
						session.getTransaction().commit();
						session2.createCriteria(SastojciJeloVeza.class);
						for (int i = 0; i < listaSastojaka.size(); i++) {
							if (listaKolicina.get(i).equals(0.0))
								continue;
							SastojciJeloVeza sjv = new SastojciJeloVeza();
							sjv.setJelo(j);
							sjv.setSastojak(listaSastojaka.get(i));
							sjv.setKolicina(listaKolicina.get(i));
							session2.beginTransaction();
							session2.save(sjv);
							session2.getTransaction().commit();
						}
						Sef.refreshTabeleJelo();
						System.out.println("Jelo je izmijenjeno");
						return true;
					}
					Jelo j = new Jelo();

					j.setNaziv(naziv);
					j.setOpis(opis);
					j.setCijena(cijena);
					j.setIzbrisano(false);
					j.setSlika(vratiArrayByte(slika));
					session.beginTransaction();
					session.save(j);
					session.getTransaction().commit();
					session2.createCriteria(SastojciJeloVeza.class);
					for (int i = 0; i < listaSastojaka.size(); i++) {
						if (listaKolicina.get(i).equals(0))
							continue;
						SastojciJeloVeza sjv = new SastojciJeloVeza();
						sjv.setJelo(j);
						sjv.setSastojak(listaSastojaka.get(i));
						sjv.setKolicina(listaKolicina.get(i));
						session2.beginTransaction();
						session2.save(sjv);
						session2.getTransaction().commit();
					}

				} else {
					System.out.println("Postoji jelo");
					Criteria criteria = session.createCriteria(Jelo.class).add(Restrictions.like("id", ids));
					List<Jelo> lista = criteria.list();
					Jelo j = lista.get(0);
					j.setNaziv(naziv);
					j.setOpis(opis);
					j.setCijena(cijena);
					j.setIzbrisano(false);
					j.setSlika(vratiArrayByte(slika));
					session.update(j);
					t.commit();
					session.close();
					session2.getTransaction().begin();
					int i = -1;
					for (Sastojak s : listaSastojaka) {
						i++;
						if (s.isIzbrisan())
							continue;
						session2.getTransaction().begin();
						Criteria criteria2 = session2.createCriteria(SastojciJeloVeza.class);
						List<SastojciJeloVeza> l = criteria2.list();
						boolean postoji = false;
						for (SastojciJeloVeza sjv : l) {
							if (sjv.getJelo().getId() == j.getId() && sjv.getSastojak().getId() == s.getId()) // veza
							// postoji
							{
								if (listaKolicina.get(i).equals(0.0))
									session2.delete(sjv);
								else {
									sjv.setJelo(j);
									sjv.setSastojak(s);
									sjv.setKolicina(listaKolicina.get(i));
									session2.update(sjv);
								}
								postoji = true;
								break;
							}
						}
						if (listaKolicina.get(i).equals(0.0))
							continue;
						if (!postoji) {
							session2.createCriteria(SastojciJeloVeza.class);
							SastojciJeloVeza sjv = new SastojciJeloVeza();
							sjv.setJelo(j);
							sjv.setSastojak(s);
							sjv.setKolicina(listaKolicina.get(i));
							session2.save(sjv);
							session2.getTransaction().commit();
						}

					}
					session2.close();
					System.out.println("Jelo je izmijenjeno");
					return true;
				}
				Sef.refreshTabeleJelo();

			} catch (Exception e) {
				logger.info(e);
				return false;
			}
			return true;

		}
	}

