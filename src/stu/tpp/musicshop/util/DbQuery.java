package stu.tpp.musicshop.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stu.tpp.musicshop.model.Disk;
import stu.tpp.musicshop.model.Group;
import stu.tpp.musicshop.model.Model;

import java.sql.ResultSet;
import java.util.Optional;

public class DbQuery<T extends Model> {
    public static DbController database = null;
    private String typeName;

    public DbQuery(Class<T> type) {
        this.typeName = type.getSimpleName();
    }

    public void add(Object... props) throws Exception {
        switch (typeName) {
            case "Group":
                if (props.length == 3) {
                    String name = (String) props[0];
                    String musician = (String) props[1];
                    String style = (String) props[2];
                    database.executeUpdate("insert into Groups(Name, Musician, Style) " +
                            "values('" + name + "', '" + musician + "', '" + style + "');");
                } else {
                    throw new Exception("There must be 3 properties when adding to Group table");
                }
                break;
            case "Disk":
                if (props.length == 5) {
                    int id = (int) props[0];
                    String name = (String) props[1];
                    String presentDate = (String) props[2];
                    int amount = (int) props[3];
                    double price = (double) props[4];
                    database.executeUpdate("insert into Disk(GroupID, Name, PresentDate, Amount, Price) " +
                            "values(" + id + ", '" + name + "', '" + presentDate + "', " + amount + ", " + price + ");");
                } else {
                    throw new Exception("There must be 5 properties when adding to Disk table");
                }
                break;
            default:
                throw new Exception("Not supported type " + typeName);
        }
    }

    public T selectSingle(int id) throws Exception {
        switch (typeName) {
            case "Group":
                return getSingle(database.executeQuery("select * from Groups " +
                        "where GroupID = " + id + ";"));
            case "Disk":
                return getSingle(database.executeQuery("select * from Disk " +
                        "where DiskID = " + id + ";"));
            default: throw new Exception("Not supported type " + typeName);
        }
    }

    private T getSingle(Optional<ResultSet> resultSet) throws Exception {
        switch (typeName) {
            case "Group":
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
                return (T) group;
            case "Disk":
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
                return (T) disk;
            default: throw new Exception("Not supported type " + typeName);
        }
    }

    public ObservableList<T> selectAll() throws Exception {
        switch (typeName) {
            case "Group":
                return getAll(database.executeQuery("select * from Groups;"));
            case "Disk":
                return getAll(database.executeQuery("select * from Disk;"));
            default: throw new Exception("Not supported type " + typeName);
        }
    }

    private ObservableList<T> getAll(Optional<ResultSet> resultSet) throws Exception {
        switch (typeName) {
            case "Group":
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
                return (ObservableList<T>) groupList;
            case "Disk":
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
                return (ObservableList<T>) disks;
            default: throw new Exception("Not supported type " + typeName);
        }
    }
}
