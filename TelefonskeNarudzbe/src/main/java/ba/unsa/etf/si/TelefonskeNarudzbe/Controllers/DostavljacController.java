package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
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
import ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface.KuharGUI;
import Util.HibernateUtil;
import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.RacunPDF;

public class DostavljacController {

	final static Logger logger = Logger.getLogger(DostavljacController.class);
	private int brojNarudzbi;
	public Transaction t;
	public Session session = HibernateUtil.getSessionFactory().openSession();

	// ovog nece bit kad rijesimo login tako da nema potrebe za test
	public Zaposlenik dajZaposlenika() {
		return (Zaposlenik) session.createCriteria(Zaposlenik.class).add(Restrictions.eq("id", 1)).uniqueResult();
	}

	// mijenja status u preuzeta , ako narudzba ili dostavljac ne postoji u bazi
	// nece moc set
	// tako da baca izuzetak
	// funkcija mijenja status, postavlja dostavljaca i postavlja vrijeme
	// preuzimanja narudzbe za dostavu
	public boolean promijeniStatusUPreuzeta(int id, int idDostavljaca) throws Exception {
		try {
			Narudzba narudzba = dajNarudzbuIzBaze(id);
			Zaposlenik dostavljac = (Zaposlenik) session.createCriteria(Zaposlenik.class)
					.add(Restrictions.eq("id", idDostavljaca)).uniqueResult();
			if (narudzba != null && dostavljac != null) {
				Transaction t = session.beginTransaction();
				narudzba.setVrijemePreuzimanja(new java.util.Date());
				narudzba.setStatus(4);
				narudzba.setZaposlenikByZaposlenikOsobaIdDostavljac(dostavljac);
				session.update(narudzba);
				t.commit();
				return true;

			} else {
				throw new NullPointerException("Null izuzetak");
			}
		} catch (Exception e) {
			logger.info(e);
			throw e;
		}
	}

	// mijenja status u spremna, ako postoji u baziako ne baca izuzetak
	// takodje setuje vrijeme dostave
	public boolean promijeniStatusUSpremna(Integer integer) throws Exception {
		try {
			Narudzba narudzba = dajNarudzbuIzBaze(integer);
			if (narudzba != null) {
				Transaction t = session.beginTransaction();
				narudzba.setVrijemeDostave(new java.util.Date());
				narudzba.setStatus(5);
				session.update(narudzba);
				t.commit();
				return true;
			} else {
				throw new NullPointerException("Null izuzetak");
			}
		} catch (Exception e) {
			logger.info(e);
			throw e;

		}

	}

	// trazi kupca koji se veze za narudzbu, id je id kupca
	public Kupac dajKupca(int id) throws Exception {
		try {
			Kupac kupac = (Kupac) session.createCriteria(Kupac.class).add(Restrictions.eq("id", id)).uniqueResult();
			if (kupac != null)
				return kupac;
			else
				throw new NullPointerException("Null izuzetak");
		} catch (Exception e) {
			logger.info(e);
			throw e;
		}
	}

	// narudzba je vec preuzeta ako je status <4
	// baca izuzetak ako u bazi nije nadjena narudzba
	public boolean provjeriDaLiJePreuzeta(Integer integer) throws Exception {
		try {
			Narudzba narudzba = dajNarudzbuIzBaze(integer);
			if (narudzba != null) {
				if (narudzba.getStatus() >= 4)
					return true;

				else
					return false;
			} else
				throw new NullPointerException("Null izuzetak");
		} catch (Exception e) {
			logger.info(e);
			throw e;
		}
	}
// trazi narudzbu u bazi sa zadatim idom 
// baca izuzetak ako ne postoji u bazi
	public Narudzba dajNarudzbuIzBaze(int id) throws Exception {
		try {
			Narudzba narudzba = (Narudzba) session.createCriteria(Narudzba.class).add(Restrictions.eq("id", id))
					.uniqueResult();
			if (narudzba != null)
				return narudzba;
			else
				throw new NullPointerException("Null izuzetak");
		} catch (Exception e) {
			logger.info(e);
			throw e;
		}

	}

	// sve narudzbe koje nisu jos preuzete ali su spremne za dostavljaca, tj narudzbe gdje 
	// je status =3 , ali takodje prikazuju se i narudzbe koje je preuzeo dostavljac koji ih prikazuje
	// jer samo ih on moze oznaciti dostalvjenim , znaci status moze biti 4 u slucaju da je 
	// dostavljac koji je preuzeo isti kao i dostavljac koji zeli oznaciti narudzbu dostavljenom 
	public List<Narudzba> dajSveNarudzbeIzBaze(int idZap) throws Exception {
		try {
			Criteria criteria = session.createCriteria(Narudzba.class, "n");
			Criterion restr1 = Restrictions.eq("status", new Integer(3));
			criteria.createAlias("n.zaposlenikByZaposlenikOsobaIdDostavljac", "z",
					org.hibernate.sql.JoinType.LEFT_OUTER_JOIN);// .add(Restrictions.isNotNull("zaposlenikByZaposlenikOsobaIdDostavljac"));
			Criterion restr2 = Restrictions.and(Restrictions.isNotNull("n.zaposlenikByZaposlenikOsobaIdDostavljac"),
					Restrictions.eq("z.id", idZap), Restrictions.eq("status", new Integer(4)));
			criteria.add(Restrictions.or(restr1, restr2));
			List<Narudzba> listaNarudzbi = criteria.list();
			if (listaNarudzbi != null)
				return listaNarudzbi;
			else
				throw new NullPointerException("Null izuzetak");
		} catch (Exception e) {
			logger.info(e);
			throw e;
		}
	}

}
