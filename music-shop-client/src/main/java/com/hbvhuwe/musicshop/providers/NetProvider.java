package com.hbvhuwe.musicshop.providers;

import com.hbvhuwe.musicshop.model.Model;

import java.util.List;

public class NetProvider<T extends Model> implements DataProvider<T> {
    @Override
    public List<T> selectAll() throws Exception {
        return null;
    }

    @Override
    public T select(int id) throws Exception {
        return null;
    }

    @Override
    public void add(T object) throws Exception {

    }

    @Override
    public void delete(int id) throws Exception {

    }

    @Override
    public void updateWith(T mask) throws Exception {

    }
}
