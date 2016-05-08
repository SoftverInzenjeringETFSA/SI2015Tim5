package ba.unsa.etf.si.TelefonskeNarudzbe;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import Util.HibernateUtil;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Jelo;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Kupac;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Osoba;

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
        Transaction t = sesija.beginTransaction();
        String hql = "select imePrezime from Osoba"; 
		Query q = sesija.createQuery(hql);
		System.out.println( "Napravljen query" );

		for (Object o : q.list())
		{
			
			System.out.println( "Iz baze: " + o);
		}
			
		System.out.println( "Izlistano..." );
    }
}
