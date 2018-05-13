package com.hbvhuwe.musicshop.providers;

import java.util.List;

public interface DataProvider<T> {
    List<T> selectAll() throws Exception;
    T select(int id) throws Exception;
    void add(T object) throws Exception;
    void delete(int id) throws Exception;
    void updateWith(T mask) throws Exception;
}
