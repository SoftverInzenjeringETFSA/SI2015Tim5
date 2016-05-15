package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;



import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Set;

import Util.HibernateUtil;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Jelo;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Narudzba;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.NarudzbaJeloVeza;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Zaposlenik;

public class IzvjestajController {
	private static Session s = null;

	
	public IzvjestajController()
	{
	}
	
	//daje podatke na osnovu intervala
	public static Object[][] dajNaruzbePoDatumu(String datumOdString, String datumDoString) throws ParseException {
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy.", Locale.ENGLISH);
		Date datumOdStringBAZA = format.parse(datumOdString);
		Date datumDoStringBAZA = format.parse(datumDoString);
		s = HibernateUtil.getSessionFactory().openSession();
		Criteria crit = s.createCriteria(Narudzba.class);
	    crit.add( Restrictions.between("vrijemePrijema", datumOdStringBAZA, datumDoStringBAZA) );
	    List<Narudzba> narudzbe = crit.list();
		return dajZaTabelu(narudzbe);
	}
	
	//daje narudzbe po dostavljacu
	public static Object[][] dajNaruzbePoDostavljacu(String ime) throws Exception {
		s = HibernateUtil.getSessionFactory().openSession();
		Criteria crit = s.createCriteria(Zaposlenik.class)
				.add(Restrictions.eq("imePrezime", ime));
		List<Zaposlenik> dostavljac = crit.list();
		if(dostavljac.size() == 0 || dostavljac.get(0).getRadnomjesto().getId() != 4) throw new Exception();
		Zaposlenik dost = dostavljac.get(0);
		
		crit = s.createCriteria(Narudzba.class);
	    crit.add(Restrictions.eq("zaposlenikByZaposlenikOsobaIdDostavljac", dost));
	    List<Narudzba> narudzbe = crit.list();
	    return dajZaTabelu(narudzbe);
	}
	
	//narudzbe po jelu
	public static Object[][] dajNaruzbePoJelu(String jelo) throws Exception {
		s = HibernateUtil.getSessionFactory().openSession();
		/*Criteria crit = s.createCriteria(Jelo.class)
				.add(Restrictions.like("naziv", jelo));
		List<Jelo> jela = crit.list();
		if(jela.size() == 0) throw new Exception();
		Jelo trazenoJelo = jela.get(0);*/
		
		
		List<Jelo> jela = s.createCriteria(Jelo.class)
        		.add(Restrictions.eq("naziv", jelo))
				.list();
        List<NarudzbaJeloVeza>  veza = new ArrayList<NarudzbaJeloVeza> (jela.get(0).getNarudzbajelovezas());
        List<Narudzba> narj = new ArrayList<Narudzba>();
		for (NarudzbaJeloVeza o : veza)
		{
			Integer narudzba = o.getNarudzba().getId();
			List<Narudzba> pom = s.createCriteria(Narudzba.class)
	        		.add(Restrictions.eq("id", narudzba))
					.list();
			Narudzba pomocna = pom.get(0);
			narj.add(pomocna);
		}
	    return dajZaTabelu(narj);
	}

	//daje sadržaj tabele za narudzbe po bilo kom kriteriju 
	private static Object[][]  dajZaTabelu(List<Narudzba> lista)
	{
		Object[][] listaListi = new Object[lista.size()][8];
		int i = 0;
		for(Narudzba red : lista)
		{
			listaListi[i][0] = red.getId().toString();
			List<NarudzbaJeloVeza>  veza= new ArrayList<NarudzbaJeloVeza> (red.getNarudzbajelovezas());
			System.out.println(veza.size());
			List<String> listaJela = dajJelaNarudzbe(veza);
			String joined = String.join(", ", listaJela); 
			listaListi[i][1] = joined;
			listaListi[i][2] = red.getVrijemePrijema().toString();
			listaListi[i][3] = red.getOpis();
			listaListi[i][4] = red.getZaposlenikByZaposlenikOsobaIdPrimalac().getImePrezime();
			listaListi[i][5] = red.getKupac().getAdresa();
			listaListi[i][6] = red.getKupac().getBrojTelefona().toString();
			listaListi[i][7] = String.valueOf(red.getCijena());
			i++;	
		}
		return listaListi;
	}
	
	
	//daje jela iz jedne narudzbe (naziv+kolicina)
	private static List<String> dajJelaNarudzbe(List<NarudzbaJeloVeza> veza)
	{
		List<String> narj = new ArrayList<String>();
		for (NarudzbaJeloVeza o : veza)
		{
			//jelo
			Integer jelo = o.getJelo().getId();
			List<Jelo> pom = s.createCriteria(Jelo.class)
	        		.add(Restrictions.eq("id", jelo))
					.list();
			String kol = String.valueOf(o.getKolicina());
			narj.add(pom.get(0).getNaziv() + "x" + kol+ "kom");
		}
		return narj;
	}
	
	
	
	
	//treba dodati još validacije
	public static Boolean validirajDatum(String datum){
		String[] parts = datum.split(Pattern.quote("."));
		String dan = parts[0];
		String mjesec = parts[1];
		String godina = parts[2];
		try {
			int d = Integer.parseInt(dan);
			int m = Integer.parseInt(mjesec);
			int g = Integer.parseInt(godina);
			if(d < 1 || d > 31) return false;
			if (m < 1 || m > 12) return false;
			if(g < 1900) return false;
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

}
