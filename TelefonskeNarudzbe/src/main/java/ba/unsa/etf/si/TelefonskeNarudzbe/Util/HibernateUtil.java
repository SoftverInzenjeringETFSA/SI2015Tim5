package ba.unsa.etf.si.TelefonskeNarudzbe.Util;
import org.apache.log4j.Logger; 
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	 private static final SessionFactory sessionFactory = buildSessionFactory();
	 final static Logger logger = Logger.getLogger(HibernateUtil.class);

	    private static SessionFactory buildSessionFactory() {
	        try {
	            // Create the SessionFactory from hibernate.cfg.xml
	            return new Configuration().configure().buildSessionFactory();
	            //new StandardServiceRegistryBuilder().build()
	        }
	        catch (Exception ex) {
	            // Make sure you log the exception, as it might be swallowed
	        	//logger.info(ex);
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }

	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
}
