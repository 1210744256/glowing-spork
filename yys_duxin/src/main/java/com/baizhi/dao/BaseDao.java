package com.baizhi.dao;

public interface BaseDao<T,V> {
    T selectById(V id);
    T selectAll();
    void update(T t);
    void deleteById(V id);
    void insert(T t);
}
