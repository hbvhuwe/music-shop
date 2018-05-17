package com.hbvhuwe.musicshop.controller;

import com.hbvhuwe.musicshop.model.Disk;
import com.hbvhuwe.musicshop.providers.DataProvider;
import com.hbvhuwe.musicshop.providers.Providers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class DiskViewController extends BaseController<Disk> {
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


    @FXML
    private void initialize() {
        provider = DataProvider.of(Providers.JDBC, Disk.class);

        diskIdColumn.setCellValueFactory(new PropertyValueFactory<>("diskId"));
        groupIdColumn.setCellValueFactory(new PropertyValueFactory<>("groupId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        presentDateColumn.setCellValueFactory(new PropertyValueFactory<>("presentDate"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    @Override
    void onSelectAll() {
        try {
            ObservableList<Disk> disks = FXCollections.observableArrayList(provider.selectAll());
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
    void onSelectSingle() {
        try {
            Disk disk = provider.select(Integer.parseInt(diskIdField.getText()));
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
    void onAdd() {
        try {
            Disk disk = new Disk();
            disk.setName(nameField.getText());
            disk.setAmount(Integer.valueOf(amountField.getText()));
            disk.setPresentDate(presentDateField.getText());
            disk.setPrice(Double.valueOf(priceField.getText()));
            disk.setGroupId(Integer.valueOf(groupIdField.getText()));
            provider.add(disk);
            resultArea.setText("Successfully onAdd a new disk");
        } catch (Exception e) {
            resultArea.setText("Error while adding: " + e.getMessage());
        }
    }

    @FXML
    private void onChangeInfo() {
        try {
            Disk mask = new Disk();
            mask.setAmount(Integer.parseInt(newAmountField.getText()));
            mask.setPrice(Double.parseDouble(newPriceField.getText()));
            provider.updateWith(mask);
            resultArea.setText("Information successfully updated for: " + diskIdField.getText());
        } catch (Exception e) {
            resultArea.setText("Error while updating information: " + e.getMessage());
        }
    }

    @FXML
    @Override
    void onDelete() {
        try {
            provider.delete(Integer.parseInt(diskIdField.getText()));
            resultArea.setText("Successfully onDelete disk with disk id: " + diskIdField.getText());
        } catch (Exception e) {
            resultArea.setText("Error while deleting disk: " + e.getMessage());
        }
    }
}
