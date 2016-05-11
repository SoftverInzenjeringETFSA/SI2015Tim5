package ba.unsa.etf.si.TelefonskeNarudzbe;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        String hql = "select id from Narudzba"; 
		Query q = sesija.createQuery(hql);
		System.out.println( "Napravljen query" );

		for (Object o : q.list())
		{
			
			System.out.println( "Iz baze: " + o);
		}
			
		System.out.println( "Izlistano..." );
    }
}
