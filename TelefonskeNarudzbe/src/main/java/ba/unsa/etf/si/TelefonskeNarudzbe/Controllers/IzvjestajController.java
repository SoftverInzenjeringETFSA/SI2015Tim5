package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
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
	private Session s = null;
	final Logger logger = Logger.getLogger(IzvjestajController.class);
	
	public IzvjestajController()
	{
	}
	
	public  Object[][] dajVremenaIsporuke(){
		s = HibernateUtil.getSessionFactory().openSession();
		Criteria crit = s.createCriteria(Narudzba.class).add(Restrictions.isNotNull("vrijemeDostave"));
	    List<Narudzba> narudzbe = crit.list();
	    int[] vremena = new int[narudzbe.size()]; 
	    int i = 0;
	    for(Narudzba n : narudzbe)
	    {
	    	long milisec = (n.getVrijemeDostave().getTime()-n.getVrijemePrijema().getTime());
	    	long sec = milisec / 1000;
	    	int min = (int)(sec / 60);
	    	vremena[i] = min;
	    	i++;
	    }
	    
	    for(int v : vremena)
	    {
	    	System.out.println(v);
	    }
	    int[] manjeOd = new int[7];
	    int poc = 0, kraj = 10, j = 0;
	    for(int k = 0; k < 7; k++)
	    {
	    	manjeOd[j] = zbirNarudzbiPoMinutama(vremena, poc, kraj);
	    	if(j != 0) manjeOd[j] = manjeOd[j] + manjeOd[j-1];
	    	poc = poc + 10; kraj = kraj + 10;
	    	if(kraj == 70) kraj = 9999;
	    	j++;
	    }
	    
	    double[] postoci = new double[7];
	    i = 0;
	    for(int m : manjeOd)
	    {
	    	if(m != 0) postoci[i] = (double)manjeOd[i] * 100 / (double)narudzbe.size();
	    	else postoci[i] = 0;
	    	i++;
	    }
	    DecimalFormat df = new DecimalFormat("####0.00");
	    	
	    Object[][] vremenaRaspored = {
	    		{"Manje od 10 minuta", String.valueOf(manjeOd[0]), String.valueOf(df.format(postoci[0])) + "%"},
	    		{"Manje od 20 minuta", String.valueOf(manjeOd[1]), String.valueOf(df.format(postoci[1]))+ "%"},
	    		{"Manje od 30 minuta", String.valueOf(manjeOd[2]), String.valueOf(df.format(postoci[2]))+ "%"},
	    		{"Manje od 40 minuta", String.valueOf(manjeOd[3]), String.valueOf(df.format(postoci[3]))+ "%"},
	    		{"Manje od 50 minuta", String.valueOf(manjeOd[4]), String.valueOf(df.format(postoci[4]))+ "%"},
	    		{"Manje od 60 minuta", String.valueOf(manjeOd[5]), String.valueOf(df.format(postoci[5]))+ "%"},
	    		{"Preko 60 minuta", String.valueOf(narudzbe.size()), "100%"},
	    		};
	    return vremenaRaspored;
	}
	

	
	private static int zbirNarudzbiPoMinutama(int[] kolekcija, int pocetak, int kraj)
	{
		int zbir = 0;
		for(int i : kolekcija)
		{
			if(i > pocetak && i <= kraj) zbir++;
		}
		return zbir;
	}
	
	public Object[][] dajBrojNarudzbiPoJelu(String jelo) throws Exception
	{
		s = HibernateUtil.getSessionFactory().openSession();
		List<Jelo> jela = s.createCriteria(Jelo.class)
        		.add(Restrictions.eq("naziv", jelo))
				.list();
		if(jela.size() == 0) throw new Exception();
        List<NarudzbaJeloVeza>  veza = new ArrayList<NarudzbaJeloVeza> (jela.get(0).getNarudzbajelovezas());
        List<Narudzba> narj = new ArrayList<Narudzba>();
        int kolicine = 0;
		for (NarudzbaJeloVeza o : veza)
		{
			kolicine = kolicine + o.getKolicina();
		}
	    Object [][] kol = {{jelo,String.valueOf(kolicine)}};
	    return kol;
	}
	
	//daje podatke na osnovu intervala
	public Object[][] dajNaruzbePoDatumu(String datumOdString, String datumDoString) throws Exception {
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy.", Locale.ENGLISH);
		Date datumOdStringBAZA = format.parse(datumOdString);
		Date datumDoStringBAZA = format.parse(datumDoString);
		s = HibernateUtil.getSessionFactory().openSession();
		Criteria crit = s.createCriteria(Narudzba.class);
	    crit.add( Restrictions.between("vrijemePrijema", datumOdStringBAZA, datumDoStringBAZA) );
	    List<Narudzba> narudzbe = crit.list();
	    if(narudzbe.size() == 0) throw new Exception();
		return dajZaTabelu(narudzbe);
	}
	
	//daje narudzbe po dostavljacu
	public Object[][] dajNaruzbePoDostavljacu(String ime) throws Exception {
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
	public Object[][] dajNaruzbePoJelu(String jelo) throws Exception {
		s = HibernateUtil.getSessionFactory().openSession();
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
	private Object[][]  dajZaTabelu(List<Narudzba> lista)
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
	private List<String> dajJelaNarudzbe(List<NarudzbaJeloVeza> veza)
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
	public Boolean validirajDatum(String datum){
		String[] parts = datum.split(Pattern.quote("."));
		String dan = parts[0];
		String mjesec = parts[1];
		String godina = parts[2];
		try {
			int dd = Integer.parseInt(dan);
			int mm = Integer.parseInt(mjesec);
			int yyyy = Integer.parseInt(godina);
			
			if(mm == 1 || mm == 3 || mm == 05 || mm == 07 
					|| mm == 8 || mm == 10 || mm == 12)
			{
				if(dd < 1 || dd> 31) return false; 
			}
		
			if(mm == 4 || mm == 6 || mm == 9 || mm == 11)
			{
				if(dd < 1 || dd> 30) return false; 
			}	
			if(mm == 2)
			{
				if(dd < 1 || dd> 29) return false; 
				if(dd == 29)
				{
					if(yyyy % 4 == 0 && yyyy % 100 != 0) return true;
					if(yyyy % 4 == 0 && yyyy % 400 == 0) return true;
					else return false;
				}
			}
			return true;
		} catch (Exception e) {
			logger.info(e);
			return false;
		}
	}

}
