package stu.tpp.musicshop.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stu.tpp.musicshop.model.Composition;
import stu.tpp.musicshop.model.Disk;
import stu.tpp.musicshop.model.Group;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class UpdateUtil {
    public static DbController database = null;

    public static void updateGroup(int id, String newMusician) throws SQLException {
        database.executeUpdate("update Groups " +
                "set Musician = '" + newMusician + "' " +
                "where GroupID = " + id + ";");
    }

    public static void updateDisk(int id, int newAmount, double newPrice) throws SQLException {
        database.executeUpdate("update Disk " +
                "set Amount = " + newAmount + ", Price = " + newPrice + " " +
                "where DiskID = " + id + ";");
    }
    public static void updateDisk(int id, int newAmount) throws SQLException {
        database.executeUpdate("update Disk " +
                "set Amount = " + newAmount + " " +
                "where DiskID = " + id + ";");
    }

    public static void updateComposition(int id, String name, String duration) throws SQLException {
        database.executeUpdate("update Composition " +
                "set Name = '" + name + "', Duration = '" + duration + "' " +
                "where CompositionID = " + id + ";");
    }
    public static void updateComposition(int id, String duration) throws SQLException {
        database.executeUpdate("update Composition " +
                "set Duration = '" + duration + "' " +
                "where CompositionID = " + id + ";");
    }
}
