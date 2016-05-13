package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import Util.HibernateUtil;

public class UnosIzmjenaPopustaController {
	public boolean izmjenaPopusta(int cijenaOd, int cijenaDo, int popust)
	{
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction t = session.beginTransaction();
			/*Criteria criteria = session.createCriteria(Popust.class).add(Restrictions.like("od", cijenaOd).ignoreCase()).add(Restrictions.like("do", cijenaDo).ignoreCase());
			List<Popust> lista = criteria.list();
			Popust p = lista.get(0);
			p.setOd(cijenaOd);
			p.setDo(cijenaDo);
			p.setPopust(popust);
			session.update(p);		
			t.commit();*/
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
