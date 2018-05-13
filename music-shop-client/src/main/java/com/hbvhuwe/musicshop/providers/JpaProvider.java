package com.hbvhuwe.musicshop.providers;

import com.hbvhuwe.musicshop.model.Composition;
import com.hbvhuwe.musicshop.model.Disk;
import com.hbvhuwe.musicshop.model.Group;
import com.hbvhuwe.musicshop.model.Model;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class JpaProvider<T> implements DataProvider<T> {
    private Class<T> tClass;
    private static SessionFactory sessionFactory = null;
    private static Session session = null;

    public JpaProvider(Class<T> tClass) {
        this.tClass = tClass;
        getSessionFactory();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> selectAll() throws Exception {
        if (tClass == Group.class) {
            Query query = getSession().createQuery("from Group order by GroupID");
            List<Group> groups = (List<Group>) query.list();
            return (List<T>) groups;
        } else if (tClass == Disk.class) {
            Query query = getSession().createQuery("from Disk order by DiskID");
            List<Disk> disks = (List<Disk>) query.list();
            return (List<T>) disks;
        } else if (tClass == Composition.class) {
            Query query = getSession().createQuery("from Composition order by CompositionID");
            List<Composition> compositions = (List<Composition>) query.list();
            return (List<T>) compositions;
        } else {
            throw new Exception("Unknown class");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T select(int id) throws Exception {
        if (tClass == Group.class) {
            Group group = (Group) getSession().get(tClass, id);
            return (T) group;
        } else if (tClass == Disk.class) {
            Disk disk = (Disk) getSession().get(tClass, id);
            return (T) disk;
        } else if (tClass == Composition.class) {
            Composition composition = (Composition) getSession().get(tClass, id);
            return (T) composition;
        } else {
            throw new Exception("Unknown class");
        }
    }

    @Override
    public void add (T object) throws Exception {
        Transaction transaction = getSession().beginTransaction();
        try {
            getSession().save(object);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new Exception("Error while adding");
        }
    }

    @Override
    public void delete(int id) throws Exception {
        Transaction transaction = getSession().beginTransaction();
        try {
            Model object = (Model) tClass.getConstructors()[0].newInstance();
            object.setId(id);
            getSession().delete(object);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new Exception("Error while deleting");
        }
    }

    @Override
    public void updateWith(T mask) throws Exception {
        // TODO: implement this
        throw new Exception("Not implemented");
    }

    public static void close() {
        if (sessionFactory != null && session != null) {
            if (session.isOpen()) {
                session.close();
            }
            if (sessionFactory.isOpen()) {
                sessionFactory.close();
            }
        }
    }

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration cfg = new Configuration();
            cfg.configure();
            sessionFactory = cfg.buildSessionFactory();
        }
        return sessionFactory;
    }

    private static Session getSession() {
        if (session == null) {
            session = getSessionFactory().openSession();
        }
        return session;
    }
}