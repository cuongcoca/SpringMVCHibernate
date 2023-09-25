package com.coca.dao;

import com.coca.model.PagingResult;

import java.util.List;

public interface CommonDAO {
    <T> PagingResult getPage(PagingResult pagingResult, Class<T> clazz);
    <T> List<T> getAll(Class<T> clazz);
    <T> T findById(Class<T> type, Long id);
    public <T> T save(T o);
    public <T> T update(T o);
}
