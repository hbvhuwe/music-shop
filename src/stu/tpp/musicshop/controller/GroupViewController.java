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
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import stu.tpp.musicshop.model.Group;
import stu.tpp.musicshop.network.MusicShopService;
import stu.tpp.musicshop.util.DbQuery;
import stu.tpp.musicshop.util.UpdateUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

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

//    private DbQuery<Group> query = new DbQuery<>(Group.class);

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
            ObservableList<Group> groups = getListFromJson(MusicShopService.getApi().getGroups().execute().body().string());
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
            Group group = getFromJson(MusicShopService.getApi().getGroup(Integer.valueOf(groupIdField.getText())).execute().body().string());
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
    void add() {
        try {
            System.out.println(MusicShopService.getApi().addGroup(nameField.getText(), musicianField.getText(), styleField.getText()).execute().raw().toString());
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
            MusicShopService.getApi().deleteGroup(Integer.valueOf(groupIdField.getText())).execute();
            resultArea.setText("Successfully delete group with group id: " + groupIdField.getText());
        } catch (Exception e) {
            resultArea.setText("Error while deleting group: " + e.getMessage());
        }
    }

    private ObservableList<Group> getListFromJson(String json) {
        JsonParser parser = new JsonParser();
        JsonArray list = parser.parse(json).getAsJsonArray();
        ObservableList<Group> groups = FXCollections.observableArrayList();
        list.forEach((it) -> groups.add(getFromJson(it.getAsJsonObject())));
        return groups;
    }

    private Group getFromJson(JsonObject object) {
        Group group = new Group();
        group.setGroupId(object.get("GroupID").getAsInt());
        group.setName(object.get("Name").getAsString());
        group.setMusician(object.get("Musician").getAsString());
        group.setStyle(object.get("Style").getAsString());
        return group;
    }

    private Group getFromJson(String json) {
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(json).getAsJsonObject();

        Group group = new Group();
        group.setGroupId(object.get("GroupID").getAsInt());
        group.setName(object.get("Name").getAsString());
        group.setMusician(object.get("Musician").getAsString());
        group.setStyle(object.get("Style").getAsString());
        return group;
    }
}
