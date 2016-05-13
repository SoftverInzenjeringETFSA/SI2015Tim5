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

public class UnosIzmjenaRadnikaController {
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
		
		try{
			if (session.createCriteria(Zaposlenik.class).add(Restrictions.eq("imePrezime", imePrezime)).setProjection(Projections.property("imePrezime")).uniqueResult() == null){
				Zaposlenik z= new Zaposlenik();
				z.setDatumRodenja(datum);
				z.setUsername(username);
				z.setPassword(password);
				RadnoMjesto rm = vratiRadnoMjesto(radnoMjesto);
				z.setRadnomjesto(rm);
				z.setImePrezime(imePrezime);
				z.setDodatneInformacije(opis);
				session.beginTransaction();
				session.saveOrUpdate(z);
				session.getTransaction().commit();	
			}
			
			
	
		else {
			Criteria criteria = session.createCriteria(Zaposlenik.class).add(Restrictions.like("imePrezime", imePrezime).ignoreCase());
			
			List<Zaposlenik> lista = criteria.list();
			Zaposlenik z = lista.get(0);
			z.setDatumRodenja(datum);
			z.setUsername(username);
			z.setPassword(password);
			RadnoMjesto rm = vratiRadnoMjesto(radnoMjesto);
			z.setRadnomjesto(rm);
			z.setImePrezime(imePrezime);
			z.setDodatneInformacije(opis);
	
			session.update(z);		
			t.commit();
			session.close();
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
