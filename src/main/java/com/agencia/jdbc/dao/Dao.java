package com.agencia.jdbc.dao;

import java.util.List;

public interface Dao<T> {
    boolean save(T t);
    boolean update(T t);
    boolean delete(int id);

    List<T> findAll();



}
