package com.coca.dao;

import com.coca.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Product> list = session.createQuery("from Product").list();
        return list;
    }

    @Override
    public boolean add(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(product);
        return true;
    }
}
