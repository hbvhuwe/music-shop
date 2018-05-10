package stu.tpp.musicshop.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import stu.tpp.musicshop.model.Disk;
import stu.tpp.musicshop.network.MusicShopService;
import stu.tpp.musicshop.util.DbQuery;
import stu.tpp.musicshop.util.UpdateUtil;

import java.sql.SQLException;

public class DiskViewController extends Controller {
    @FXML
    private TextField groupIdField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField presentDateField;
    @FXML
    private TextField amountField;
    @FXML
    private TextField priceField;
    @FXML
    private TextArea resultArea;

    @FXML
    private TextField diskIdField;
    @FXML
    private TextField newAmountField;
    @FXML
    private TextField newPriceField;

    @FXML
    private TableView<Disk> diskTable;
    @FXML
    private TableColumn<Disk, Integer> diskIdColumn;
    @FXML
    private TableColumn<Disk, Integer> groupIdColumn;
    @FXML
    private TableColumn<Disk, String> nameColumn;
    @FXML
    private TableColumn<Disk, String> presentDateColumn;
    @FXML
    private TableColumn<Disk, Integer> amountColumn;
    @FXML
    private TableColumn<Disk, Double> priceColumn;

//    private DbQuery<Disk> query = new DbQuery<>(Disk.class);

    @FXML
    private void initialize() {
        diskIdColumn.setCellValueFactory(cellData -> cellData.getValue().diskIdProperty().asObject());
        groupIdColumn.setCellValueFactory(cellData -> cellData.getValue().groupIdProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        presentDateColumn.setCellValueFactory(cellData -> cellData.getValue().presentDateProperty());
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
    }

    @FXML
    @Override
    void selectAll() {
        try {
            ObservableList<Disk> disks = getListFromJson(MusicShopService.getApi().getDisks().execute().body().string());
            populateDisks(disks);
        } catch (Exception e) {
            resultArea.setText("Error while getting information about disks:\n" + e.getMessage());
        }
    }

    private void populateDisks(ObservableList<Disk> disks) {
        diskTable.setItems(disks);
    }

    @FXML
    @Override
    void selectSingle() {
        try {
            Disk disk = getFromJson(MusicShopService.getApi().getDisk(Integer.valueOf(diskIdField.getText())).execute().body().string());
            populateAndShowDisk(disk);
        } catch (Exception e) {
            resultArea.setText("Error while getting info about disk:\n" + e.getMessage());
        }
    }

    private void populateAndShowDisk(Disk disk) {
        if (disk != null) {
            ObservableList<Disk> disks = FXCollections.observableArrayList();
            disks.add(disk);
            diskTable.setItems(disks);
            resultArea.setText("Found disk:\nDisk name: " + disk.getName() + "\n" +
                    "Disk present date: " + disk.getPresentDate() + "\n" +
                    "Available amount of disks in store: " + disk.getAmount() + "\n" +
                    "Price of this disk:" + disk.getPrice());
        } else {
            resultArea.setText("Disk with: " + diskIdField.getText() + " disk id not found");
        }
    }

    @FXML
    @Override
    void add() {
        try {
            MusicShopService.getApi().addDisk(nameField.getText(),
                    Integer.valueOf(amountField.getText()),
                    presentDateField.getText(),
                    Double.valueOf(priceField.getText()),
                    Integer.valueOf(groupIdField.getText()));
            resultArea.setText("Successfully add a new disk");
        } catch (Exception e) {
            resultArea.setText("Error while adding: " + e.getMessage());
        }
    }

    @FXML
    private void onChangeInfo() {
        try {
            if (!newAmountField.getText().isEmpty() && !newPriceField.getText().isEmpty()) {
                UpdateUtil.updateDisk(Integer.valueOf(diskIdField.getText()), Integer.valueOf(newAmountField.getText()), Double.valueOf(newPriceField.getText()));
            } else {
                UpdateUtil.updateDisk(Integer.valueOf(diskIdField.getText()), Integer.valueOf(newAmountField.getText()));
            }
            resultArea.setText("Information successfully updated for: " + diskIdField.getText());
        } catch (SQLException e) {
            resultArea.setText("Error while updating information: " + e.getMessage());
        }
    }

    @FXML
    @Override
    void delete() {
        try {
            MusicShopService.getApi().deleteDisk(Integer.valueOf(diskIdField.getText()));
            resultArea.setText("Successfully delete disk with disk id: " + diskIdField.getText());
        } catch (Exception e) {
            resultArea.setText("Error while deleting disk: " + e.getMessage());
        }
    }

    private ObservableList<Disk> getListFromJson(String json) {
        JsonParser parser = new JsonParser();
        JsonArray list = parser.parse(json).getAsJsonArray();
        ObservableList<Disk> disks = FXCollections.observableArrayList();
        list.forEach((it) -> disks.add(getFromJson(it.getAsJsonObject())));
        return disks;
    }

    private Disk getFromJson(JsonObject object) {
        Disk disk = new Disk();
        disk.setDiskId(object.get("DiskID").getAsInt());
        disk.setName(object.get("Name").getAsString());
        disk.setAmount(object.get("Amount").getAsInt());
        disk.setPresentDate(object.get("PresentDate").getAsString());
        disk.setPrice(object.get("Price").getAsDouble());
        disk.setGroupId(object.get("GroupID").getAsInt());
        return disk;
    }

    private Disk getFromJson(String json) {
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(json).getAsJsonObject();

        Disk disk = new Disk();
        disk.setDiskId(object.get("DiskID").getAsInt());
        disk.setName(object.get("Name").getAsString());
        disk.setAmount(object.get("Amount").getAsInt());
        disk.setPresentDate(object.get("PresentDate").getAsString());
        disk.setPrice(object.get("Price").getAsDouble());
        disk.setGroupId(object.get("GroupID").getAsInt());
        return disk;
    }
}
