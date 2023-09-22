package com.coca.dao;

import com.coca.model.Contacts;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ContactsDAOImpl implements ContactsDAO{
    private static final Logger logger = LoggerFactory.getLogger(ContactsDAOImpl.class);

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Contacts> getAll() {
        logger.info("getAll Contacts");
        Session session = sessionFactory.getCurrentSession();
        String sql = "select * from CONTACTS order by id desc";
        Query query = session.createSQLQuery(sql).addEntity(Contacts.class);
        List<Contacts> list = query.list();
        return list;
    }

    @Override
    public Long add(Contacts contacts) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.persist(contacts);
            session.flush();
            return 1L;
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0L;
    }
}
