package stu.tpp.musicshop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import stu.tpp.musicshop.model.Disk;
import stu.tpp.musicshop.util.QueryUtil;

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
    private void onSelectAllDisks() {
        try {
            ObservableList<Disk> disks = QueryUtil.selectDisks();
            populateDisks(disks);
        } catch (SQLException e) {
            resultArea.setText("Error while getting information about disks:\n" + e.getMessage());
        }
    }

    private void populateDisks(ObservableList<Disk> disks) {
        diskTable.setItems(disks);
    }

    @FXML
    private void onSelectDisk() {
        try {
            Disk disk = QueryUtil.selectDisk(Integer.valueOf(diskIdField.getText()));
            populateAndShowDisk(disk);
        } catch (SQLException e) {
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
    private void onAddDisk() {
        try {
            QueryUtil.addDisk(Integer.valueOf(groupIdField.getText()), nameField.getText(),
                    presentDateField.getText(), Integer.valueOf(amountField.getText()),
                    Double.valueOf(priceField.getText()));
            resultArea.setText("Successfully add a new disk");
        } catch (SQLException e) {
            resultArea.setText("Error while adding: " + e.getMessage());
        }
    }

    @FXML
    private void onChangeInfo() {
        try {
            if (!newAmountField.getText().isEmpty() && !newPriceField.getText().isEmpty()) {
                QueryUtil.updateDisk(Integer.valueOf(diskIdField.getText()), Integer.valueOf(newAmountField.getText()), Double.valueOf(newPriceField.getText()));
            } else {
                QueryUtil.updateDisk(Integer.valueOf(diskIdField.getText()), Integer.valueOf(newAmountField.getText()));
            }
            resultArea.setText("Information successfully updated for: " + diskIdField.getText());
        } catch (SQLException e) {
            resultArea.setText("Error while updating information: " + e.getMessage());
        }
    }

    @FXML
    private void onDelete() {
        try {
            QueryUtil.deleteDisk(Integer.valueOf(diskIdField.getText()));
            resultArea.setText("Successfully delete group with disk id: " + diskIdField.getText());
        } catch (SQLException e) {
            resultArea.setText("Error while deleting disk: " + e.getMessage());
        }
    }
}
