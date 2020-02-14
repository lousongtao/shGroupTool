package dao;

import bean.Area;
import org.hibernate.Session;
import java.util.List;

public class AreaDao {

    public List<Area> getAreas(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from Area", Area.class).list();
        }
    }
}
