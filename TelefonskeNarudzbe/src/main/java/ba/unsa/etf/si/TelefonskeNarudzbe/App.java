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
        Criteria criteria = sesija.createCriteria(Kupac.class);
		//List<Kupac> lista = criteria.list();
		//List<Narudzba> listan = new ArrayList<Narudzba> (lista.get(0).getNarudzbas());
		Criteria c1 = sesija.createCriteria(Jelo.class);
        List<Jelo> j = c1.list();
		for (Jelo o : j)
		{
			
			System.out.println( "Iz baze: " + o.getNaziv());
		}
			
		System.out.println( "Izlistano..." );

    }
}