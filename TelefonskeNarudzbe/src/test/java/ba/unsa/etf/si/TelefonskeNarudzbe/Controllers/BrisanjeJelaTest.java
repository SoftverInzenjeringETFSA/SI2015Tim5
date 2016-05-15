package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Util.HibernateUtil;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.Jelo;

public class BrisanjeJelaTest {
	BrisanjeJela b;
	Session sesija;
	Jelo j;
	Transaction t = null;

	@Test
	public void testBrisiJelo() {	 
   		try
		{
   			b = new BrisanjeJela();
   			sesija = HibernateUtil.getSessionFactory().openSession();
   			t = sesija.beginTransaction();

   			j = new Jelo();
   			j.setNaziv("Pizza");
   	        j.setCijena(5);
   			sesija.save(j);
   			t.commit();

			assertEquals(j.getIzbrisano(), b.BrisiJelo(j.getNaziv()));
			
			sesija.delete(j);
			t.commit();
		}
		catch (Exception e) 
		{
			
		}
	}

}