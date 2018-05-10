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
import stu.tpp.musicshop.model.Composition;
import stu.tpp.musicshop.network.MusicShopService;
import stu.tpp.musicshop.util.DbQuery;
import stu.tpp.musicshop.util.UpdateUtil;

import java.sql.SQLException;

public class CompositionViewController extends Controller {
    @FXML
    private TextField diskIdField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField presentDateField;
    @FXML
    private TextField durationField;
    @FXML
    private TextArea resultArea;
    
    @FXML
    private TextField compositionIdField;
    @FXML
    private TextField newNameField;
    @FXML
    private TextField newDurationField;
    
    @FXML
    private TableView<Composition> compositionTable;
    @FXML
    private TableColumn<Composition, Integer> compositionIdColumn;
    @FXML
    private TableColumn<Composition, Integer> diskIdColumn;
    @FXML
    private TableColumn<Composition, String> nameColumn;
    @FXML
    private TableColumn<Composition, String> presentDateColumn;
    @FXML
    private TableColumn<Composition, String> durationColumn;

//    private DbQuery<Composition> query = new DbQuery<>(Composition.class);

    @FXML
    private void initialize() {
        diskIdColumn.setCellValueFactory(cellData -> cellData.getValue().diskIdProperty().asObject());
        compositionIdColumn.setCellValueFactory(cellData -> cellData.getValue().compositionIdProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        presentDateColumn.setCellValueFactory(cellData -> cellData.getValue().presentDateProperty());
        durationColumn.setCellValueFactory(cellData -> cellData.getValue().durationProperty());
    }


    @FXML
    @Override
    void selectAll() {
        try {
            ObservableList<Composition> compositions = getListFromJson(MusicShopService.getApi().getCompositions().execute().body().string());
            populateCompositions(compositions);
        } catch (Exception e) {
            resultArea.setText("Error while getting information about compositions:\n" + e.getMessage());
        }
    }

    private void populateCompositions(ObservableList<Composition> compositions) {
        compositionTable.setItems(compositions);
    }

    @FXML
    @Override
    void selectSingle() {
        try {
            Composition composition = getFromJson(MusicShopService.getApi().getComposition(Integer.valueOf(compositionIdField.getText())).execute().body().string());
            populateAndShowComposition(composition);
        } catch (Exception e) {
            resultArea.setText("Error while getting info about composition:\n" + e.getMessage());
        }
    }

    private void populateAndShowComposition(Composition composition) {
        if (composition != null) {
            ObservableList<Composition> compositions = FXCollections.observableArrayList();
            compositions.add(composition);
            compositionTable.setItems(compositions);
            resultArea.setText("Found composition:\nComposition name: " + composition.getName() + "\n" +
                    "Composition present date: " + composition.getPresentDate() + "\n" +
                    "Compositions duration: " + composition.getDuration());
        } else {
            resultArea.setText("Composition with: " + compositionIdField.getText() + " composition id not found");
        }
    }

    @FXML
    @Override
    void add() {
        try {
            MusicShopService.getApi().addComposition(nameField.getText(),
                    durationField.getText(),
                    presentDateField.getText(),
                    Integer.valueOf(diskIdField.getText()));
            resultArea.setText("Successfully add a new composition");
        } catch (Exception e) {
            resultArea.setText("Error while adding: " + e.getMessage());
        }
    }

    @FXML
    private void onChangeInfo() {
        try {
            if (!newNameField.getText().isEmpty() && !newDurationField.getText().isEmpty()) {
                UpdateUtil.updateComposition(Integer.valueOf(compositionIdField.getText()), newNameField.getText(), newDurationField.getText());
            } else {
                UpdateUtil.updateComposition(Integer.valueOf(compositionIdField.getText()), newDurationField.getText());
            }
            resultArea.setText("Information successfully updated for: " + compositionIdField.getText());
        } catch (SQLException e) {
            resultArea.setText("Error while updating information: " + e.getMessage());
        }
    }

    @FXML
    @Override
    void delete() {
        try {
            MusicShopService.getApi().deleteComposition(Integer.valueOf(compositionIdField.getText()));
            resultArea.setText("Successfully delete composition with composition id: " + compositionIdField.getText());
        } catch (Exception e) {
            resultArea.setText("Error while deleting composition: " + e.getMessage());
        }
    }

    private ObservableList<Composition> getListFromJson(String json) {
        JsonParser parser = new JsonParser();
        JsonArray list = parser.parse(json).getAsJsonArray();
        ObservableList<Composition> compositions = FXCollections.observableArrayList();
        list.forEach((it) -> compositions.add(getFromJson(it.getAsJsonObject())));
        return compositions;
    }

    private Composition getFromJson(JsonObject object) {
        Composition composition = new Composition();
        composition.setCompositionId(object.get("CompositionID").getAsInt());
        composition.setName(object.get("Name").getAsString());
        composition.setDuration(object.get("Duration").getAsString());
        composition.setPresentDate(object.get("PresentDate").getAsString());
        composition.setDiskId(object.get("DiskID").getAsInt());
        return composition;
    }

    private Composition getFromJson(String json) {
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(json).getAsJsonObject();

        Composition composition = new Composition();
        composition.setCompositionId(object.get("CompositionID").getAsInt());
        composition.setName(object.get("Name").getAsString());
        composition.setDuration(object.get("Duration").getAsString());
        composition.setPresentDate(object.get("PresentDate").getAsString());
        composition.setDiskId(object.get("DiskID").getAsInt());
        return composition;
    }
}
