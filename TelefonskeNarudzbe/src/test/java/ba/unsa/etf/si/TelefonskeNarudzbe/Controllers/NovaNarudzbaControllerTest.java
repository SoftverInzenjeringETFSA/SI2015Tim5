package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.NovaNarudzbaController;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.*;
import junit.framework.TestCase;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import Util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NovaNarudzbaControllerTest extends TestCase {
	private List<Jelo> jela;
	private Jelo jelo;
	private Zaposlenik zaposlenik;
	private Popust popust;
	private RadnoMjesto bezveze;
	//private Narudzba narudzba;
	private Kupac kupac;

	@Before
	public void setUp() throws Exception {
		Session s = null;
		Transaction t = null;
		
		try {
			s = HibernateUtil.getSessionFactory().openSession();
			t = s.beginTransaction();

			jelo = new Jelo();
			jelo.setNaziv("Pizza");
			jelo.setCijena(5);
			s.save(jelo);

			bezveze = new RadnoMjesto();
			bezveze.setNaziv("BZV ZAPOSLENIK");
			s.save(bezveze);

			zaposlenik = new Zaposlenik();
			zaposlenik.setImePrezime("Dean Cupovic");
			zaposlenik.setUsername("deanc");
			zaposlenik.setPassword("cdean");
			zaposlenik.setRadnomjesto(bezveze);
			s.save(zaposlenik);

			popust = new Popust();
			popust.setOd(50.0);
			popust.setDoo(100.0);
			popust.setIznos(20.0);
			s.save(popust);
			
			kupac=new Kupac();
			kupac.setAdresa("Moja adresa test");
			kupac.setBrojTelefona(123456);
			kupac.setInfo("info ovaj");
			s.save(kupac);
			
			/*narudzba=new Narudzba();
			narudzba.setCijena(100.5);
			narudzba.setKupac(kupac);
			narudzba.setStatus(1);
			narudzba.setVrijemePrijema(new Date(System.currentTimeMillis()));
			narudzba.setZaposlenikByZaposlenikOsobaIdPrimalac(zaposlenik);
			s.save(narudzba);*/
			

			t.commit();

		}

		catch (Exception e) {
			t.rollback();
			throw new Exception(e.getMessage());
		}

		finally {
			if (s != null)
				s.close();
		}

	}

	@After
	public void tearDown() throws Exception {
		Session s = null;
		Transaction t = null;
		try {
			s = HibernateUtil.getSessionFactory().openSession();
			t = s.beginTransaction();

			Jelo j = (Jelo) s.get(Jelo.class, jelo.getId());
			if (j != null)
				s.delete(j);

			Popust p = (Popust) s.get(Popust.class, popust.getId());
			if (p != null)
				s.delete(p);
			
			/*Narudzba n=(Narudzba) s.get(Narudzba.class, narudzba.getId());
			if(n!=null) {
				n.setKupac(null);
				n.setZaposlenikByZaposlenikOsobaIdPrimalac(null);
				s.delete(n);
			}*/
			
			Kupac k=(Kupac) s.get(Kupac.class, kupac.getId());
			if(k!=null) {
				s.delete(k);
			}
			
			Zaposlenik z = (Zaposlenik) s.get(Zaposlenik.class, zaposlenik.getId());
			if (z != null){
				s.delete(z);	
			}
				
			RadnoMjesto rm = (RadnoMjesto) s.get(RadnoMjesto.class, bezveze.getId());
			if (rm != null)
				s.delete(rm);
			
			t.commit();
			
		} catch (Exception e) {
			t.rollback();
			throw e;
		} finally {
			if (s != null)
			{
				s.close();
			}
				
		}
	}

	@Test
	public void testDajSvaJela() throws Exception {
		NovaNarudzbaController testirajMe = new NovaNarudzbaController();

		try {
			jela = testirajMe.dajSvaJela();
			assertEquals(Long.valueOf(jelo.getId()), Long.valueOf(jela.get(jela.size() - 1).getId()));
		}

		catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	@Test
	public void testDajJelo() throws Exception {
		NovaNarudzbaController testirajMe = new NovaNarudzbaController();

		try {
			Jelo vraceno = testirajMe.dajJelo(jelo.getNaziv());
			assertEquals(Long.valueOf(jelo.getId()), Long.valueOf(vraceno.getId()));
		}

		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Test
	public void testDajZaposlenika() throws Exception {
		NovaNarudzbaController testirajMe = new NovaNarudzbaController();

		try {
			Zaposlenik vraceni = testirajMe.dajZaposlenika(zaposlenik.getId());
			assertEquals(Long.valueOf(zaposlenik.getId()), Long.valueOf(vraceni.getId()));
		}

		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Test
	public void testDajPopust() throws Exception {
		NovaNarudzbaController testirajMe = new NovaNarudzbaController();

		try {
			Popust vraceni = testirajMe.dajPopust(55.5);
			assertEquals(Long.valueOf(popust.getId()), Long.valueOf(vraceni.getId()));
		}

		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	 /*@Test 
	 public void testSpremiNovuNarudzbu() throws Exception {
		 NovaNarudzbaController testirajMe=new NovaNarudzbaController();
		 
		 try{
			 	Narudzba nova=new Narudzba();
			 	nova.setKupac(kupac);
			 	nova.setCijena(20.5);
			 	nova.setStatus(1);
			 	nova.setVrijemePrijema(new Date(System.currentTimeMillis()));
			 	nova.setZaposlenikByZaposlenikOsobaIdPrimalac(zaposlenik);
			 	
			 	assertTrue(testirajMe.spremiNovuNarudzbu(nova));
			 	//assertEquals(nova.getOpis(),narudzba.getOpis());
		 }
		 
		 catch (Exception e) {
			 throw new Exception(e.getMessage());
		 }	 
	 }*/
	 
	@Test 
	 public void testSpremiNovogKupca() throws Exception{
		 NovaNarudzbaController testirajMe = new NovaNarudzbaController();
			try {
				Kupac k=new Kupac();
				k.setAdresa("Moja adresa test");
				k.setBrojTelefona(123456);
				
				assertTrue(testirajMe.spremiNovogKupca(k));
				assertEquals(k.getAdresa(),kupac.getAdresa());
			}

			catch (Exception e) {
				throw new Exception(e.getMessage());
			}
	 }


	 /* @Test public void testSpremiNarudzbaJeloVeza() { fail(
	 * "Not yet implemented"); // TODO }*/

}
