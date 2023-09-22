package com.coca.dao;

import com.coca.model.Contacts;

import java.util.List;

public interface ContactsDAO {
    List<Contacts> getAll();
    Long add(Contacts contacts);
}
