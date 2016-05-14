package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.*;
import Util.HibernateUtil;

public class KuharController {

	private int brojNarudzbi;
	private Transaction t;
	private Session session = HibernateUtil.getSessionFactory().openSession();

	final static Logger logger = Logger.getLogger(KuharController.class);

	public boolean promijeniStatusUPreuzeta(int id, int idKuhara) {
		try {
			Zaposlenik kuhar = (Zaposlenik) session.createCriteria(Zaposlenik.class)
					.add(Restrictions.eq("id", idKuhara)).uniqueResult();
			Narudzba narudzba = dajNarudzbuIzBaze(id);
			Transaction t = session.beginTransaction();
			narudzba.setZaposlenikByZaposlenikOsobaIdKuhar(kuhar);
			narudzba.setStatus(2);
			session.update(narudzba);
			t.commit();
			return true;
		} catch (Exception e) {
			logger.info(e);
			return false;
		}
	}

	public Zaposlenik dajZaposlenika() {
		return (Zaposlenik) session.createCriteria(Zaposlenik.class).add(Restrictions.eq("id", 1)).uniqueResult();
	}

	public boolean promijeniStatusUSpremna(Integer id, int idKuhara) throws Exception {
		try {
			Narudzba narudzba = dajNarudzbuIzBaze(id);
			Transaction t = session.beginTransaction();
			narudzba.setVrijemePocetkaPripreme(new java.util.Date());
			narudzba.setStatus(3);
			session.update(narudzba);
			t.commit();
			return true;
		} catch (Exception e) {
			logger.info(e);
			throw e;

		}
	}

	public String dajNarudzbe(int id) throws Exception {
		try {
			String narudzba = "";
			Integer brJela = 0;
			ArrayList<String> nazivi = new ArrayList<String>();
			ArrayList<String> jedinice = new ArrayList<String>();
			ArrayList<Double> kolicine = new ArrayList<Double>();
			ArrayList<Integer> idovi = new ArrayList<Integer>();
			ArrayList<String> nazivijela = new ArrayList<String>();
			ArrayList<Double> kolicinejela = new ArrayList<Double>();
			ArrayList<Integer> idoviJela = new ArrayList<Integer>();
			ArrayList<String> sastojci = new ArrayList<String>();
			System.out.println("RADIIII");
			Session sesija = HibernateUtil.getSessionFactory().openSession();
			List<Narudzba> nar = sesija.createCriteria(Narudzba.class).add(Restrictions.eq("id", id)).list();
			List<NarudzbaJeloVeza> veza = new ArrayList<NarudzbaJeloVeza>(nar.get(0).getNarudzbajelovezas());

			for (NarudzbaJeloVeza o : veza) {
				brJela = o.getKolicina();
				Integer jelo = o.getJelo().getId();
				List<Jelo> narj = sesija.createCriteria(Jelo.class).add(Restrictions.eq("id", jelo)).list();
				List<SastojciJeloVeza> vezasj = new ArrayList<SastojciJeloVeza>(narj.get(0).getSastojciJeloVezas());

				int indekss = idoviJela.indexOf(o.getJelo().getId());
				if (indekss == -1) {
					idoviJela.add(o.getJelo().getId());
					kolicinejela.add((double) o.getKolicina());
					nazivijela.add(o.getJelo().getNaziv());
					String sastojak = "";
					for (SastojciJeloVeza oo : vezasj) {
						sastojak += oo.getSastojak().getNaziv() + " " + Double.toString(oo.getKolicina()) + " * "
								+ oo.getSastojak().getMjernaJedinica() + "\n";

						int indeks = idovi.indexOf(oo.getSastojak().getId());
						if (indeks == -1) {
							idovi.add(oo.getSastojak().getId());
							kolicine.add(brJela * oo.getKolicina());
							nazivi.add(oo.getSastojak().getNaziv());
							jedinice.add(oo.getSastojak().getMjernaJedinica());
						} else {
							kolicine.set(indeks,  (kolicine.get(indeks) + brJela * oo.getKolicina()));
						}
					}
					sastojci.add(sastojak);
				} else {
					kolicinejela.set(indekss, kolicinejela.get(indekss) + o.getKolicina());
				}

			}
			for (int i = 0; i < idovi.size(); i++) {
				narudzba += nazivi.get(i) + " " + Double.toString(kolicine.get(i)) + " * " + jedinice.get(i) + "\n";
			}
			narudzba += "\nSpisak jela\n\n";
			for (int i = 0; i < idoviJela.size(); i++) {
				narudzba += kolicinejela.get(i) + " " + nazivijela.get(i) + "\n\n";
				narudzba += sastojci.get(i) + "\n\n";
			}
			return narudzba;
		} catch (Exception e) {
			logger.info(e);
			throw e;

		}
	}

	public boolean provjeriDaLiJePreuzeta(Integer integer) throws Exception {
		try {
			Narudzba narudzba = dajNarudzbuIzBaze(integer);
			if (narudzba.getStatus() >= 2)
				return true;
			else
				return false;
		} catch (Exception e) {
			throw e;
		}
	}

	public Narudzba dajNarudzbuIzBaze(int id) throws Exception {
		try {
			Narudzba narudzba = (Narudzba) session.createCriteria(Narudzba.class).add(Restrictions.eq("id", id))
					.uniqueResult();
			return narudzba;
		} catch (Exception e) {
			logger.info(e);
			throw e;
		}

	}

	// sve narudzbe
	public List<Narudzba> dajSveNarudzbeIzBaze(int idZap) throws Exception {
		try {
			Criteria criteria = session.createCriteria(Narudzba.class, "n");
			Criterion restr1 = Restrictions.eq("status", new Integer(1));
			criteria.createAlias("n.zaposlenikByZaposlenikOsobaIdKuhar", "z",
					org.hibernate.sql.JoinType.LEFT_OUTER_JOIN);// .add(Restrictions.isNotNull("zaposlenikByZaposlenikOsobaIdDostavljac"));
			Criterion restr2 = Restrictions.and(Restrictions.isNotNull("n.zaposlenikByZaposlenikOsobaIdKuhar"),
					Restrictions.eq("z.id", idZap), Restrictions.eq("status", new Integer(2)));
			criteria.add(Restrictions.or(restr1, restr2));
			List<Narudzba> listaNarudzbi = criteria.list();
			return listaNarudzbi;
		} catch (Exception e) {
			logger.info(e);
			throw new Exception();
		}
	}
}
