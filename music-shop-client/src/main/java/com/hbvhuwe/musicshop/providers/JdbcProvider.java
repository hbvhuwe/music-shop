package com.hbvhuwe.musicshop.providers;

import com.hbvhuwe.musicshop.model.Composition;
import com.hbvhuwe.musicshop.model.Disk;
import com.hbvhuwe.musicshop.model.Group;
import com.hbvhuwe.musicshop.model.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcProvider<T extends Model> implements DataProvider<T> {
    private static Connection connection;
    private static Statement statement;
    private static boolean isConnected = false;
    private Class<T> tClass;

    public JdbcProvider(Class<T> tClass) {
        this.tClass = tClass;
        if (!isConnected) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/music_shop_db", "vova", "1029");
                statement = connection.createStatement();
                isConnected = true;
                System.out.println("New connection to db using jdbc");
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

    @Override
    public List<T> selectAll() throws Exception {
        if (tClass == Group.class) {
            return getAllFromResult(statement.executeQuery("select * from Groups;"));
        } else if (tClass == Disk.class) {
            return getAllFromResult(statement.executeQuery("select * from Disk;"));
        } else if (tClass == Composition.class) {
            return getAllFromResult(statement.executeQuery("select * from Composition;"));
        } else {
            throw new Exception("Unknown class");
        }
    }

    @Override
    public T select(int id) throws Exception {
        if (tClass == Group.class) {
            return getFromResult(statement.executeQuery("select * from Groups where GroupID = " + id + ";"));
        } else if (tClass == Disk.class) {
            return getFromResult(statement.executeQuery("select * from Disk where DiskID = " + id + ";"));
        } else if (tClass == Composition.class) {
            return getFromResult(statement.executeQuery("select * from Composition where CompositionID = " + id + ";"));
        } else {
            throw new Exception("Unknown class");
        }
    }

    @Override
    public void add(T object) throws Exception {
        if (tClass == Group.class) {
            Group group = (Group) object;
            statement.executeUpdate("insert into Groups(Name, Musician, Style) " +
                    "values('"
                    + group.getName() + "', '"
                    + group.getMusician() + "', '"
                    + group.getStyle() + "');");
        } else if (tClass == Disk.class) {
            Disk disk = (Disk) object;
            statement.executeUpdate("insert into Disk(GroupID, Name, PresentDate, Amount, Price) " +
                    "values("
                    + disk.getGroupId() + ", '"
                    + disk.getName() + "', '"
                    + disk.getPresentDate() + "', "
                    + disk.getAmount() + ", "
                    + disk.getPrice() + ");");
        } else if (tClass == Composition.class) {
            Composition composition = (Composition) object;
            statement.executeUpdate("insert into Composition(DiskID, Name, PresentDate, Duration) " +
                    "values("
                    + composition.getDiskId() + ", '"
                    + composition.getName() + "', '"
                    + composition.getPresentDate() + "', '"
                    + composition.getDuration() + "');");
        } else {
            throw new Exception("Unknown class");
        }
    }

    @Override
    public void delete(int id) throws Exception {
        if (tClass == Group.class) {
            statement.executeUpdate("delete from Groups where GroupID = " + id + ";");
        } else if (tClass == Disk.class) {
            statement.executeUpdate("delete from Disk where DiskID = " + id + ";");
        } else if (tClass == Composition.class) {
            statement.executeUpdate("delete from Composition where CompositionID = " + id + ";");
        }
    }

    @Override
    public void updateWith(T mask) throws Exception {
        // TODO: implement this
        throw new Exception("Not implemented");
    }


    public static void close() throws Exception {
        if (isConnected) {
            connection.close();
            statement.close();
            System.out.println("Closing jdbc connection");
        }
    }

    @SuppressWarnings("unchecked")
    private List<T> getAllFromResult(ResultSet resultSet) throws Exception {
        if (tClass == Group.class) {
            List<Group> groupList = new ArrayList<>();
            while (resultSet.next()) {
                Group group = new Group();
                group.setGroupId(resultSet.getInt(1));
                group.setName(resultSet.getString(2));
                group.setMusician(resultSet.getString(3));
                group.setStyle(resultSet.getString(4));
                groupList.add(group);
            }
            return (List<T>) groupList;
        } else if (tClass == Disk.class) {
            List<Disk> disks = new ArrayList<>();
            while (resultSet.next()) {
                Disk disk = new Disk();
                disk.setDiskId(resultSet.getInt(1));
                disk.setGroupId(resultSet.getInt(2));
                disk.setName(resultSet.getString(3));
                disk.setPresentDate(resultSet.getString(4));
                disk.setAmount(resultSet.getInt(5));
                disk.setPrice(resultSet.getDouble(6));
                disks.add(disk);
            }
            return (List<T>) disks;
        } else if (tClass == Composition.class) {
            List<Composition> compositions = new ArrayList<>();
            while (resultSet.next()) {
                Composition composition = new Composition();
                composition.setCompositionId(resultSet.getInt(1));
                composition.setDiskId(resultSet.getInt(2));
                composition.setName(resultSet.getString(3));
                composition.setPresentDate(resultSet.getString(4));
                composition.setDuration(resultSet.getString(5));
                compositions.add(composition);
            }
            return (List<T>) compositions;
        } else {
            throw new Exception("Unknown class");
        }
    }

    @SuppressWarnings("unchecked")
    private T getFromResult(ResultSet resultSet) throws Exception {
        if (tClass == Group.class) {
            Group group = null;
            if (resultSet.next()) {
                group = new Group();
                group.setGroupId(resultSet.getInt(1));
                group.setName(resultSet.getString(2));
                group.setMusician(resultSet.getString(3));
                group.setStyle(resultSet.getString(4));
            }
            return (T) group;
        } else if (tClass == Disk.class) {
            Disk disk = null;
            if (resultSet.next()) {
                disk = new Disk();
                disk.setDiskId(resultSet.getInt(1));
                disk.setGroupId(resultSet.getInt(2));
                disk.setName(resultSet.getString(3));
                disk.setPresentDate(resultSet.getString(4));
                disk.setAmount(resultSet.getInt(5));
                disk.setPrice(resultSet.getDouble(6));
            }
            return (T) disk;
        } else if (tClass == Composition.class) {
            Composition composition = null;
            while (resultSet.next()) {
                composition = new Composition();
                composition.setCompositionId(resultSet.getInt(1));
                composition.setDiskId(resultSet.getInt(2));
                composition.setName(resultSet.getString(3));
                composition.setPresentDate(resultSet.getString(4));
                composition.setDuration(resultSet.getString(5));
            }
            return (T) composition;
        } else {
            throw new Exception("Unknown class");
        }
    }
}
