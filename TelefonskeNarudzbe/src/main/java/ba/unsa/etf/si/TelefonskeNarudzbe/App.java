package ba.unsa.etf.si.TelefonskeNarudzbe;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Util.HibernateUtil;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Jelo;

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
    //   String hql = "select id from Jelo"; 
      Query q = sesija.createQuery("select p.id from " + Jelo.class.getName() + " p");
		System.out.println( "Napravljen query" );

		for (Object o : q.list())
		{
			
			System.out.println( "Iz baze: " + o);
		}
        //t.commit();
        sesija.close();
			
		System.out.println( "Izlistano..." );
    }
}
