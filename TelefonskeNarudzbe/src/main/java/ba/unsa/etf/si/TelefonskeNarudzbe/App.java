package ba.unsa.etf.si.TelefonskeNarudzbe;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import Util.HibernateUtil;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Jelo;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Kupac;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Narudzba;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        System.out.println( "RADIIII" );
        Session sesija = HibernateUtil.getSessionFactory().openSession();
      	//List<Kupac> lista = criteria.list();
		//List<Narudzba> listan = new ArrayList<Narudzba> (lista.get(0).getNarudzbas());
		Criteria c1 = sesija.createCriteria(Narudzba.class);
        List<Narudzba> j = c1.list();
		for (Narudzba o : j)
		{
			
			System.out.println( "Iz baze: " + o.getZaposlenikByZaposlenikOsobaIdPrimalac().getImePrezime());
		}
			
		System.out.println( "Izlistano..." );

    }
}