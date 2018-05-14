package com.hbvhuwe.musicshop.providers;

import com.hbvhuwe.musicshop.model.Model;

import java.util.List;

/**
 * Part of strategy pattern (behavior interface)
 * @param <T> - table from which data is taken
 */
public interface DataProvider<T extends Model> {
    /**
     * Equivalent of sql: Select * from table;
     * @return - list of object
     * @throws Exception if error occurred during getting information
     */
    List<T> selectAll() throws Exception;

    /**
     * Equivalent of sql: Select * from table where table.id = id;
     * @param id - id of object
     * @return - found object
     * @throws Exception if error occurred during getting information
     */
    T select(int id) throws Exception;

    /**
     * Insert object to data store
     * @param object - object to insert
     * @throws Exception if error occurred during adding information
     */
    void add(T object) throws Exception;

    /**
     * Deletes specified object
     * @param id - id of object
     * @throws Exception if error occurred
     */
    void delete(int id) throws Exception;

    /**
     * Update columns specified in mask object
     * @param mask - new information
     * @throws Exception if error occurred
     */
    void updateWith(T mask) throws Exception;

    /**
     * Factory method to get specific data provider
     * @param type - type of provider
     * @param tClass - class of type of data(need for internal use)
     * @param <T> - type of data
     * @return specific provider based on parameters
     */
    static <T extends Model> DataProvider<T> of(Providers type, Class<T> tClass) {
        DataProvider<T> provider = null;
        if (type == Providers.JDBC) {
            provider = new JdbcProvider<>(tClass);
        } else if (type == Providers.JPA) {
            provider = new JpaProvider<>(tClass);
        } else if (type == Providers.NET) {
            provider = new NetProvider<>();
        }
        return provider;
    }

    /**
     * Closes all used connections
     * All DataProvider implementations must provide static close() method
     */
    static void closeAllOpened() {
        //close jdbc connection
        JdbcProvider.close();
        //close jpa connection
        JpaProvider.close();
        //network connection don't need to be closed
    }
}
