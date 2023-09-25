package com.coca.dao;

import com.coca.model.PagingResult;
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
public class CommonDAOImpl implements CommonDAO{
    private static final Logger logger = LoggerFactory.getLogger(CommonDAOImpl.class);
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public <T> PagingResult getPage(PagingResult page, Class<T> clazz) {
        Session session = this.sessionFactory.getCurrentSession();

        try {
            Query query = session.createQuery("from " + clazz.getName());
            Query queryCount = session.createQuery("select count(o.id) from " + clazz.getName() + " o");

            if(page.getPageNumber() > 0){
                int offset = (page.getPageNumber() - 1) * page.getNumberPerPage();
                query.setFirstResult(offset).setMaxResults(page.getNumberPerPage());

                int rowCount = Integer.parseInt(queryCount.list().get(0).toString());
                page.setRowCount(rowCount);
            }

            List<T> list = query.list();
            page.setItems(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return page;
    }

    @Override
    public <T> List<T> getAll(Class<T> clazz) {
        logger.info("getAll " + clazz.getName());

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

    @Override
    public <T> T update(T o) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(o);
//            session.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
        return o;
    }
}
