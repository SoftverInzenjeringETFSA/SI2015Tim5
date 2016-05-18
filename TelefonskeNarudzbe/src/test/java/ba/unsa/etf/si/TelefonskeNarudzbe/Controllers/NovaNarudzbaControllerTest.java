package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import ba.unsa.etf.si.TelefonskeNarudzbe.Controllers.NovaNarudzbaController;
import ba.unsa.etf.si.TelefonskeNarudzbe.DomainModels.*;
import junit.framework.TestCase;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import Util.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
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
	private Narudzba narudzba;
	private Kupac kupac;
	private NarudzbaJeloVeza njv;

	private List<Kupac> dodaniKupci;
	private List<Narudzba> dodaneNarudzbe;
	private List<NarudzbaJeloVeza> dodaneVezeNJ;

	@Before
	public void setUp() throws Exception {
		Session s = null;
		Transaction t = null;

		try {
			s = HibernateUtil.getSessionFactory().openSession();
			t = s.beginTransaction();

			dodaniKupci = new ArrayList<Kupac>();
			dodaneNarudzbe = new ArrayList<Narudzba>();
			dodaneVezeNJ = new ArrayList<NarudzbaJeloVeza>();

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
			popust.setIznos(20);
			s.save(popust);

			kupac = new Kupac();
			kupac.setAdresa("Moja adresa test");
			s.save(kupac);
			dodaniKupci.add(kupac);

			narudzba = new Narudzba();
			narudzba.setCijena(100.5);
			narudzba.setKupac(kupac);
			narudzba.setStatus(1);
			narudzba.setVrijemePrijema(new Date(System.currentTimeMillis()));
			narudzba.setZaposlenikByZaposlenikOsobaIdPrimalac(zaposlenik);
			s.save(narudzba);
			dodaneNarudzbe.add(narudzba);

			njv = new NarudzbaJeloVeza();
			njv.setJelo(jelo);
			njv.setNarudzba(narudzba);
			njv.setKolicina(4);
			s.save(njv);
			dodaneVezeNJ.add(njv);

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

			Popust p = (Popust) s.get(Popust.class, popust.getId());
			if (p != null)
				s.delete(p);

			// brisi sve veze narudzbi i jela
			for (NarudzbaJeloVeza i : dodaneVezeNJ) {
				NarudzbaJeloVeza brisi = s.get(NarudzbaJeloVeza.class, i.getId());
				if (brisi != null)
					s.delete(brisi);
			}

			// brisi sva jela i njv koje imaju fk na to jelo
			Jelo j = s.get(Jelo.class, jelo.getId());
			if (j != null) {
				Criteria c = s.createCriteria(NarudzbaJeloVeza.class).add(Restrictions.eq("jelo", j));
				List<NarudzbaJeloVeza> lista = (List<NarudzbaJeloVeza>) c.list();

				if (!lista.isEmpty()) {
					for (NarudzbaJeloVeza i : lista) {
						if (i != null)
							s.delete(i);
					}
				}
				s.delete(j);
			}

			// brisi sve narudzbe i njv koje imaju fk na tu narudzbu
			for (Narudzba i : dodaneNarudzbe) {
				Narudzba brisi = s.get(Narudzba.class, i.getId());

				if (brisi != null) {
					Criteria c = s.createCriteria(NarudzbaJeloVeza.class).add(Restrictions.eq("narudzba", brisi));
					List<NarudzbaJeloVeza> lista = (List<NarudzbaJeloVeza>) c.list();

					if (!lista.isEmpty()) {
						for (NarudzbaJeloVeza n : lista) {
							if (n != null)
								s.delete(n);
						}
					}

					s.delete(brisi);
				}
			}

			// brisi sve kupce i narudzbe koje imaju fk na njega itd..do
			// narudzbajeloveze(za sv slucaj)
			for (Kupac i : dodaniKupci) {
				Kupac brisi = (Kupac) s.get(Kupac.class, i.getId());

				if (brisi != null) {
					Criteria c1 = s.createCriteria(Narudzba.class).add(Restrictions.eq("kupac", brisi));
					List<Narudzba> lista = (List<Narudzba>) c1.list();

					if (!lista.isEmpty()) {
						for (Narudzba n : lista) {
							if (n != null) {
								Criteria c2 = s.createCriteria(NarudzbaJeloVeza.class)
										.add(Restrictions.eq("narudzba", brisi));
								List<NarudzbaJeloVeza> lista2 = (List<NarudzbaJeloVeza>) c2.list();

								if (!lista2.isEmpty()) {
									for (NarudzbaJeloVeza nn : lista2) {
										if (nn != null)
											s.delete(nn);
									}
								}

								s.delete(n);
							}
						}
					}

					s.delete(brisi);

				}
			}

			// brisi sve zaposlenike+narudzbe+narudzbajeloveza
			Zaposlenik z = s.get(Zaposlenik.class, zaposlenik.getId());
			if (z != null) {
				Criteria c1 = s.createCriteria(Narudzba.class)
						.add(Restrictions.eq("zaposlenikByZaposlenikOsobaIdPrimalac", z));
				List<Narudzba> lista = (List<Narudzba>) c1.list();

				if (!lista.isEmpty()) {
					for (Narudzba n : lista) {
						if (n != null) {
							Criteria c2 = s.createCriteria(NarudzbaJeloVeza.class).add(Restrictions.eq("narudzba", n));
							List<NarudzbaJeloVeza> lista2 = (List<NarudzbaJeloVeza>) c2.list();

							if (!lista2.isEmpty()) {
								for (NarudzbaJeloVeza nn : lista2) {
									if (nn != null)
										s.delete(nn);
								}
							}

							s.delete(n);
						}
					}

				}
				s.delete(z);
			}

			// brisi sva radna mjesta, zaposlenike na datom rm, narudzbe vezane
			// za te zaposlenike i na kraju narudzbaJeloVeze
			RadnoMjesto rm = s.get(RadnoMjesto.class, bezveze.getId());
			if (rm != null) {
				Criteria c1 = s.createCriteria(Zaposlenik.class).add(Restrictions.eq("radnomjesto", bezveze));
				List<Zaposlenik> lista = (List<Zaposlenik>) c1.list();

				for (Zaposlenik i : lista) {
					Criteria c2 = s.createCriteria(Narudzba.class)
							.add(Restrictions.eq("zaposlenikByZaposlenikOsobaIdPrimalac", i));
					List<Narudzba> lista2 = (List<Narudzba>) c2.list();

					if (!lista.isEmpty()) {
						for (Narudzba n : lista2) {
							if (n != null) {
								Criteria c3 = s.createCriteria(NarudzbaJeloVeza.class)
										.add(Restrictions.eq("narudzba", n));
								List<NarudzbaJeloVeza> lista3 = (List<NarudzbaJeloVeza>) c3.list();

								if (!lista2.isEmpty()) {
									for (NarudzbaJeloVeza nn : lista3) {
										if (nn != null)
											s.delete(nn);
									}
								}

								s.delete(n);
							}
						}

					}
					s.delete(i);
				}

				s.delete(rm);
			}

			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw e;
		} finally {
			if (s != null) {
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
		} catch (Exception e) {
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

	@Test
	public void testSpremiNovuNarudzbu() throws Exception {
		NovaNarudzbaController testirajMe = new NovaNarudzbaController();

		try {
			Narudzba nova = new Narudzba();
			nova.setKupac(kupac);

			nova.setCijena(20.5);
			nova.setStatus(1);
			nova.setVrijemePrijema(new Date(System.currentTimeMillis()));
			nova.setZaposlenikByZaposlenikOsobaIdPrimalac(zaposlenik);

			assertTrue(testirajMe.spremiNovuNarudzbu(nova));
			dodaneNarudzbe.add(nova);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Test
	public void testSpremiNovogKupca() throws Exception {
		NovaNarudzbaController testirajMe = new NovaNarudzbaController();
		try {
			Kupac k = new Kupac();
			k.setAdresa("Moja adresa test");
			k.setBrojTelefona(123456);

			assertTrue(testirajMe.spremiNovogKupca(k));
			dodaniKupci.add(k);
		}

		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Test
	public void testSpremiNarudzbaJeloVeza() throws Exception {
		NovaNarudzbaController testirajMe = new NovaNarudzbaController();
		try {
			NarudzbaJeloVeza spremi = new NarudzbaJeloVeza();
			spremi.setJelo(jelo);
			spremi.setNarudzba(narudzba);
			spremi.setKolicina(5);

			assertTrue(testirajMe.spremiNarudzbaJeloVeza(spremi));
			dodaneVezeNJ.add(spremi);
		}

		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
