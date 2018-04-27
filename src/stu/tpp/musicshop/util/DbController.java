package stu.tpp.musicshop.util;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;
import java.util.Optional;

public class DbController {
    private Connection connection = null;
    private Statement statement = null;

    private static DbController instance = null;

    public static DbController getInstance() {
        if (instance == null) {
            instance = new DbController();
        }
        return instance;
    }

    private DbController() {
        try {
            DbSettings settings = DbSettings.getSettingsFromFile();
            connection = DriverManager.getConnection(settings.getUrl(), settings.getUser(), settings.getPassword());
            statement = connection.createStatement();
            System.out.println("Connection created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public Optional<ResultSet> executeQuery(String sql) throws SQLException {
        ResultSet resultSet = null;
        CachedRowSetImpl cachedRowSet;
        try {
            resultSet = statement.executeQuery(sql);
            cachedRowSet = new CachedRowSetImpl();
            cachedRowSet.populate(resultSet);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return Optional.of(cachedRowSet);
    }

    public void executeUpdate(String sql) throws SQLException {
        statement.executeUpdate(sql);
    }

    public void close() throws SQLException {
        instance.statement.close();
        instance.connection.close();
        instance = null;
    }
}
