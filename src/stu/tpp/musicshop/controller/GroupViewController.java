package stu.tpp.musicshop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import stu.tpp.musicshop.model.Group;
import stu.tpp.musicshop.util.DbQuery;
import stu.tpp.musicshop.util.UpdateUtil;

import java.sql.SQLException;

public class GroupViewController extends Controller {
    @FXML
    private TextField nameField;
    @FXML
    private TextField musicianField;
    @FXML
    private TextField styleField;
    @FXML
    private TextArea resultArea;

    @FXML
    private TextField groupIdField;
    @FXML
    private TextField newMusicianField;

    @FXML
    private TableView<Group> groupTable;
    @FXML
    private TableColumn<Group, Integer> groupIdColumn;
    @FXML
    private TableColumn<Group, String> nameColumn;
    @FXML
    private TableColumn<Group, String> musicianColumn;
    @FXML
    private TableColumn<Group, String> styleColumn;

    private DbQuery<Group> query = new DbQuery<>(Group.class);

    @FXML
    private void initialize() {
        groupIdColumn.setCellValueFactory(cellData -> cellData.getValue().groupIdProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        musicianColumn.setCellValueFactory(cellData -> cellData.getValue().musicianProperty());
        styleColumn.setCellValueFactory(cellData -> cellData.getValue().styleProperty());
    }

    @FXML
    @Override
    void selectAll() {
        try {
            ObservableList<Group> groups = query.selectAll();
            populateGroups(groups);
        } catch (Exception e) {
            resultArea.setText("Error while getting information about groups:\n" + e.getMessage());
        }
    }

    private void populateGroups(ObservableList<Group> groups) {
        groupTable.setItems(groups);
    }

    @FXML
    @Override
    void selectSingle() {
        try {
            Group group = query.selectSingle(Integer.valueOf(groupIdField.getText()));
            populateAndShowGroup(group);
        } catch (Exception e) {
            resultArea.setText("Error while getting info about group:\n" + e.getMessage());
        }
    }

    private void populateAndShowGroup(Group group) {
        if (group != null) {
            ObservableList<Group> groups = FXCollections.observableArrayList();
            groups.add(group);
            groupTable.setItems(groups);
            resultArea.setText("Found group:\nGroup name: " + group.getName() + "\n" +
                    "Group lead musician: " + group.getMusician() + "\n" +
                    "Group preferred style: " + group.getStyle());
        } else {
            resultArea.setText("Group with: " + groupIdField.getText() + " group id not found");
        }
    }

    @FXML
    @Override
    void add() {
        try {
            query.add(nameField.getText(), musicianField.getText(), styleField.getText());
            resultArea.setText("Successfully add a new group");
        } catch (Exception e) {
            resultArea.setText("Error while adding: " + e.getMessage());
        }
    }

    @FXML
    private void onChangeMusician() {
        try {
            UpdateUtil.updateGroup(Integer.valueOf(groupIdField.getText()), newMusicianField.getText());
            resultArea.setText("Information successfully updated for: " + groupIdField.getText());
        } catch (SQLException e) {
            resultArea.setText("Error while updating information: " + e.getMessage());
        }
    }

    @FXML
    @Override
    void delete() {
        try {
            query.deleteSingle(Integer.valueOf(groupIdField.getText()));
            resultArea.setText("Successfully delete group with group id: " + groupIdField.getText());
        } catch (Exception e) {
            resultArea.setText("Error while deleting group: " + e.getMessage());
        }
    }
}
