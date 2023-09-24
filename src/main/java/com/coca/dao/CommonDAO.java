package com.coca.dao;

import java.util.List;

public interface CommonDAO {
    <T> List<T> getAll(Class<T> clazz);
    <T> T findById(Class<T> type, Long id);
    public <T> T save(T o);
}
