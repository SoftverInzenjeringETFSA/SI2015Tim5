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
import ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface.LoginGUI;
import ba.unsa.etf.si.TelefonskeNarudzbe.UserInterface.sef;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    //	sef s = new sef();
        	LoginGUI l = new LoginGUI();  
    }
}