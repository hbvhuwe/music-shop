package com.hbvhuwe.musicshop.providers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hbvhuwe.musicshop.model.Disk;
import com.hbvhuwe.musicshop.model.Group;
import com.hbvhuwe.musicshop.model.Model;

import java.util.ArrayList;
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
