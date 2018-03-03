package stu.tpp.musicshop.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stu.tpp.musicshop.model.Disk;
import stu.tpp.musicshop.model.Group;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class QueryUtil {
    public static void addGroup(String name, String musician, String style) throws SQLException {
        DbController.getInstance().executeUpdate("insert into Groups(Name, Musician, Style) " +
                "values('" + name + "', '" + musician + "', '" + style + "');");
    }
    public static void deleteGroup(int id) throws SQLException {
        //TODO: safely delete group
    }
    public static void updateGroup(int id, String newMusician) throws SQLException {
        DbController.getInstance().executeUpdate("update Groups " +
                "set Musician = '" + newMusician + "' " +
                "where GroupID = " + id + ";");
    }
    public static Group selectGroup(int id) throws SQLException {
        return getGroup(DbController.getInstance().executeQuery("select * from Groups " +
                "where GroupID = " + id + ";"));
    }
    private static Group getGroup(Optional<ResultSet> resultSet) throws SQLException {
        Group group = null;
        if (resultSet.isPresent()) {
            if (resultSet.get().next()) {
                group = new Group();
                group.setGroupId(resultSet.get().getInt(1));
                group.setName(resultSet.get().getString(2));
                group.setMusician(resultSet.get().getString(3));
                group.setStyle(resultSet.get().getString(4));
            }
        }
        return group;
    }
    public static ObservableList<Group> selectGroups() throws SQLException {
        return getGroupsList(DbController.getInstance().executeQuery("select * from Groups"));
    }
    private static ObservableList<Group> getGroupsList(Optional<ResultSet> resultSet) throws SQLException {
        ObservableList<Group> groupList = FXCollections.observableArrayList();
        if (resultSet.isPresent()) {
            while (resultSet.get().next()) {
                Group group = new Group();
                group.setGroupId(resultSet.get().getInt(1));
                group.setName(resultSet.get().getString(2));
                group.setMusician(resultSet.get().getString(3));
                group.setStyle(resultSet.get().getString(4));
                groupList.add(group);
            }
        }
        return groupList;
    }
    public static void addDisk(int id, String name, String presentDate, int amount, double price) throws SQLException {
        DbController.getInstance().executeUpdate("insert into Disk(GroupID, Name, PresentDate, Amount, Price) " +
                "values(" + id + ", '" + name +"', '" + presentDate + "', " + amount + ", " + price + ");");
    }
    public static void deleteDisk(int id) throws SQLException {
        //TODO: safely delete disk
    }
    public static void updateDisk(int id, int newAmount, double newPrice) throws SQLException {
        DbController.getInstance().executeUpdate("update Disk " +
                "set Amount = " + newAmount + ", Price = " + newPrice + " " +
                "where DiskID = " + id + ";");
    }
    public static void updateDisk(int id, int newAmount) throws SQLException {
        DbController.getInstance().executeUpdate("update Disk " +
                "set Amount = " + newAmount + " " +
                "where DiskID = " + id + ";");
    }
    public static Disk selectDisk(int id) throws SQLException {
        return getDisk(DbController.getInstance().executeQuery("select * from Disk " +
                "where DiskID = " + id + ";"));
    }

    private static Disk getDisk(Optional<ResultSet> resultSet) throws SQLException {
        Disk disk = null;
        if (resultSet.isPresent()) {
            if (resultSet.get().next()) {
                disk = new Disk();
                disk.setDiskId(resultSet.get().getInt(1));
                disk.setGroupId(resultSet.get().getInt(2));
                disk.setName(resultSet.get().getString(3));
                disk.setPresentDate(resultSet.get().getString(4));
                disk.setAmount(resultSet.get().getInt(5));
                disk.setPrice(resultSet.get().getDouble(6));
            }
        }
        return disk;
    }
    public static ObservableList<Disk> selectDisks() throws SQLException {
        return getDiskList(DbController.getInstance().executeQuery("select * from Disk;"));
    }

    private static ObservableList<Disk> getDiskList(Optional<ResultSet> resultSet) throws SQLException {
        ObservableList<Disk> disks = FXCollections.observableArrayList();
        if (resultSet.isPresent()) {
            while (resultSet.get().next()) {
                Disk disk = new Disk();
                disk.setDiskId(resultSet.get().getInt(1));
                disk.setGroupId(resultSet.get().getInt(2));
                disk.setName(resultSet.get().getString(3));
                disk.setPresentDate(resultSet.get().getString(4));
                disk.setAmount(resultSet.get().getInt(5));
                disk.setPrice(resultSet.get().getDouble(6));
                disks.add(disk);
            }
        }
        return disks;
    }

    public static void addComposition(int diskId, String name, String presentDate, String duration) throws SQLException {
        DbController.getInstance().executeUpdate("insert into Composition(DiskID, Name, PresentDate, Duration) " +
                "values(" + diskId + ", '" + name + "', '" + presentDate + "', '" + duration + "');");
    }
    public static void deleteComposition(int id) throws SQLException {
        DbController.getInstance().executeUpdate("delete from Composition " +
                "where CompositionID = " + id + ";");
    }
    public static void updateComposition(int id, String name, String duration) throws SQLException {
        DbController.getInstance().executeUpdate("update Composition " +
                "set Name = '" + name + "', Duration = '" + duration + "' " +
                "where CompositionID = " + id + ";");
    }
    public static void updateComposition(int id, String duration) throws SQLException {
        DbController.getInstance().executeUpdate("update Composition " +
                "set Duration = '" + duration + "' " +
                "where CompositionID = " + id + ";");
    }
}
