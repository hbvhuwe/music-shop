package stu.tpp.musicshop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import stu.tpp.musicshop.model.Composition;
import stu.tpp.musicshop.util.QueryUtil;

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
    
    @FXML
    private void initialize() {
        diskIdColumn.setCellValueFactory(cellData -> cellData.getValue().diskIdProperty().asObject());
        compositionIdColumn.setCellValueFactory(cellData -> cellData.getValue().compositionIdProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        presentDateColumn.setCellValueFactory(cellData -> cellData.getValue().presentDateProperty());
        durationColumn.setCellValueFactory(cellData -> cellData.getValue().durationProperty());
    }


    @FXML
    private void onSelectAllCompositions() {
        try {
            ObservableList<Composition> compositions = QueryUtil.selectCompositions();
            populateCompositions(compositions);
        } catch (SQLException e) {
            resultArea.setText("Error while getting information about compositions:\n" + e.getMessage());
        }
    }

    private void populateCompositions(ObservableList<Composition> compositions) {
        compositionTable.setItems(compositions);
    }

    @FXML
    private void onSelectComposition() {
        try {
            Composition composition = QueryUtil.selectComposition(Integer.valueOf(compositionIdField.getText()));
            populateAndShowComposition(composition);
        } catch (SQLException e) {
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
    private void onAddComposition() {
        try {
            QueryUtil.addComposition(Integer.valueOf(diskIdField.getText()), nameField.getText(),
                    presentDateField.getText(), durationField.getText());
            resultArea.setText("Successfully add a new composition");
        } catch (SQLException e) {
            resultArea.setText("Error while adding: " + e.getMessage());
        }
    }

    @FXML
    private void onChangeInfo() {
        try {
            if (!newNameField.getText().isEmpty() && !newDurationField.getText().isEmpty()) {
                QueryUtil.updateComposition(Integer.valueOf(compositionIdField.getText()), newNameField.getText(), newDurationField.getText());
            } else {
                QueryUtil.updateComposition(Integer.valueOf(compositionIdField.getText()), newDurationField.getText());
            }
            resultArea.setText("Information successfully updated for: " + compositionIdField.getText());
        } catch (SQLException e) {
            resultArea.setText("Error while updating information: " + e.getMessage());
        }
    }

    @FXML
    private void onDelete() {
        try {
            QueryUtil.deleteComposition(Integer.valueOf(compositionIdField.getText()));
            resultArea.setText("Successfully delete group with composition id: " + compositionIdField.getText());
        } catch (SQLException e) {
            resultArea.setText("Error while deleting composition: " + e.getMessage());
        }
    }
}
