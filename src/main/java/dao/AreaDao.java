package dao;

import bean.Area;
import bean.Group;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AreaDao {

    public List<Area> getAreas(){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("from Area", Area.class).list();
        } finally {
            session.close();
        }
    }
    public int saveArea(Area area){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(area);
            transaction.commit();
            return area.getId();
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
            return 0;
        } finally {
            if (session != null)
                session.close();
        }
    }
}
