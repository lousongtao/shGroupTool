package dao;

import bean.GroupMember;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GroupMemberDao {
    public List<GroupMember> getAllGroupMember(){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("from GroupMember", GroupMember.class).list();
        } finally {
            session.close();
        }
    }

    public List<GroupMember> save(List<GroupMember> gms){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            for (int i = 0; i < gms.size(); i++) {
                session.save(gms.get(i));
            }

            transaction.commit();
            return gms;
        } catch (Exception e){
            if (transaction != null)
                transaction.rollback();
            return null;
        } finally {
            if (session != null)
                session.close();
        }
    }
}
