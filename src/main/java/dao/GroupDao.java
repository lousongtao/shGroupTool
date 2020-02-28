package dao;

import bean.Group;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GroupDao {

    public List<Group> getAllGroup(){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("from Group", Group.class).list();
        } finally {
            session.close();
        }
    }

    public int saveGroup(Group group){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(group);
            transaction.commit();
            return group.getId();
        } catch (Exception e){
            if (transaction != null)
                transaction.rollback();
            return 0;
        } finally {
            if (session != null)
                session.close();
        }
    }
}
