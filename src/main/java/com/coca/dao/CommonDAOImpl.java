package com.coca.dao;

import com.coca.model.Contacts;
import com.coca.model.Person;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CommonDAOImpl implements CommonDAO{
    @Autowired
    SessionFactory sessionFactory;

//    @Override
//    public <T> T findById(Class<T> type, long id) {
//        Session session = sessionFactory.getCurrentSession();
//        String sql = "select * from :Class where id = :id";
//        Query query = session.createSQLQuery(sql).addEntity(type);
//        query.setParameter("Class", type).setParameter("id", id);
//        List<T> list = query.list();
//        if(list != null && !list.isEmpty()) {
//            return list.get(0);
//        } else {
//            return null;
//        }
//    }


    @Override
    public <T> List<T> getAll(Class<T> clazz) {
        Session session = this.sessionFactory.getCurrentSession();
        List<T> list = session.createQuery("from " + clazz.getName()).list();
        return list;
    }

    @Override
    public <T> T findById(Class<T> clazz, Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        String sql = "from " + clazz.getName() + " e where e.id = :id";
        Query query = session.createQuery(sql).setParameter("id", id.intValue());
        List<T> list = query.list();
        if(list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public <T> T save(T o) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.persist(o);
            session.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
        return o;
    }
}
