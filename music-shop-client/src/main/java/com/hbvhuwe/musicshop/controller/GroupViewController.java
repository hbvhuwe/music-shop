package com.hbvhuwe.musicshop.controller;
import com.hbvhuwe.musicshop.model.Group;
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

public class GroupViewController extends BaseController<Group> {
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


    @FXML
    private void initialize() {
        provider = DataProvider.of(Providers.NET, Group.class);

        groupIdColumn.setCellValueFactory(new PropertyValueFactory<>("groupId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        musicianColumn.setCellValueFactory(new PropertyValueFactory<>("musician"));
        styleColumn.setCellValueFactory(new PropertyValueFactory<>("style"));
    }

    @FXML
    @Override
    void onSelectAll() {
        try {
            ObservableList<Group> groups = FXCollections.observableArrayList(provider.selectAll());
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
    void onSelectSingle() {
        try {
            Group group = provider.select(Integer.parseInt(groupIdField.getText()));
            populateAndShowGroup(group);
        } catch (Exception e) {
            resultArea.setText("Error while getting info about group:\n" + e.getMessage() + e.getClass().getName());
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
    void onAdd() {
        try {
            Group group = new Group();
            group.setName(nameField.getText());
            group.setMusician(musicianField.getText());
            group.setStyle(styleField.getText());
            provider.add(group);
            resultArea.setText("Successfully onAdd a new group");
        } catch (Exception e) {
            resultArea.setText("Error while adding: " + e.getMessage());
        }
    }

    @FXML
    private void onChangeMusician() {
        try {
            Group mask = new Group();
            mask.setId(Integer.parseInt(groupIdField.getText()));
            mask.setMusician(newMusicianField.getText());
            provider.updateWith(mask);
            resultArea.setText("Information successfully updated for: " + groupIdField.getText());
        } catch (Exception e) {
            resultArea.setText("Error while updating information: " + e.getMessage());
        }
    }

    @FXML
    @Override
    void onDelete() {
        try {
            provider.delete(Integer.parseInt(groupIdField.getText()));
            resultArea.setText("Successfully onDelete group with group id: " + groupIdField.getText());
        } catch (Exception e) {
            resultArea.setText("Error while deleting group: " + e.getMessage());
        }
    }
}
