package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import Util.HibernateUtil;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.RadnoMjesto;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Sastojak;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Zaposlenik;
import ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface.sef;

public class UnosIzmjenaRadnikaController {
	public static Zaposlenik vratiRadnika(String imePrezime){
		Session sesija = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sesija.createCriteria(Zaposlenik.class).add(Restrictions.like("imePrezime", imePrezime));
		List<Zaposlenik> r = criteria.list();
		Zaposlenik z = r.get(0);
		sesija.close();
		return z;
	}
	public List<Zaposlenik> vratiSveRadnike(){
		Session sesija = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sesija.createCriteria(Zaposlenik.class);
		List<Zaposlenik> r = criteria.list();
		
		sesija.close();
		return r;
	}
	public RadnoMjesto vratiRadnoMjesto(int rm){
		Session sesija = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sesija.createCriteria(RadnoMjesto.class).add(Restrictions.eq("id", rm));
		RadnoMjesto r = (RadnoMjesto) criteria.uniqueResult();
		sesija.close();
		return r;
	}
	public boolean parsirajDatum(String datum) {
		String format = "dd/MM/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(datum);
			if (!sdf.format(date).equals(datum)) {
				throw new ParseException(datum + " nije validan format za " + format, 0);
			}
			return true;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Datum mora biti u formatu dd/MM/yyyy");
			return false;
		}
	}
	public boolean izmijeniRadnika(String imePrezime, String datum, String username, String password, int radnoMjesto, String opis ){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t= session.beginTransaction();
		int ids =-1;
		if(!sef.dodajNovi) ids=sef.vratiIzabranogRadnika();
		try{
			if (session.createCriteria(Zaposlenik.class).add(Restrictions.eq("id", ids)).setProjection(Projections.property("id")).uniqueResult() == null){
				Zaposlenik z= new Zaposlenik();
				z.setDatumRodenja(datum);
				z.setUsername(username);
				z.setPassword(password);
				RadnoMjesto rm = vratiRadnoMjesto(radnoMjesto);
				z.setRadnomjesto(rm);
				z.setImePrezime(imePrezime);
			//	z.setDodatneInformacije(opis);
				session.beginTransaction();
				session.saveOrUpdate(z);
				session.getTransaction().commit();	
				sef.refreshTabeleZaposlenici();
			}
			
			
	
		else {
			Criteria criteria = session.createCriteria(Zaposlenik.class).add(Restrictions.eq("id", ids));
			
			List<Zaposlenik> lista = criteria.list();
			Zaposlenik z = lista.get(0);
			z.setDatumRodenja(datum);
			z.setUsername(username);
			z.setPassword(password);
			RadnoMjesto rm = vratiRadnoMjesto(radnoMjesto);
			z.setRadnomjesto(rm);
			z.setImePrezime(imePrezime);
		//	z.setDodatneInformacije(opis);
	
			session.update(z);		
			t.commit();
			session.close();
			sef.refreshTabeleZaposlenici();
		return true;
		}
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
