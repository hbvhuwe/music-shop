package com.hbvhuwe.musicshop.controller;

import com.hbvhuwe.musicshop.model.Composition;
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

public class CompositionViewController extends BaseController<Composition> {
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
        provider = DataProvider.of(Providers.JPA, Composition.class);

        diskIdColumn.setCellValueFactory(new PropertyValueFactory<>("diskId"));
        compositionIdColumn.setCellValueFactory(new PropertyValueFactory<>("compositionId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        presentDateColumn.setCellValueFactory(new PropertyValueFactory<>("presentDate"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
    }


    @FXML
    @Override
    void onSelectAll() {
        try {
            ObservableList<Composition> compositions = FXCollections.observableArrayList(provider.selectAll());
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
    void onSelectSingle() {
        try {
           Composition composition = provider.select(Integer.parseInt(compositionIdField.getText()));
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
    void onAdd() {
        try {
            Composition composition = new Composition();
            composition.setName(nameField.getText());
            composition.setDuration(durationField.getText());
            composition.setPresentDate(presentDateField.getText());
            composition.setDiskId(Integer.valueOf(diskIdField.getText()));
            provider.add(composition);
            resultArea.setText("Successfully onAdd a new composition");
        } catch (Exception e) {
            resultArea.setText("Error while adding: " + e.getMessage());
        }
    }

    @FXML
    private void onChangeInfo() {
        try {
            Composition composition = new Composition();
            composition.setDuration(newDurationField.getText());
            composition.setName(newNameField.getText());
            provider.updateWith(composition);
            resultArea.setText("Information successfully updated for: " + compositionIdField.getText());
        } catch (Exception e) {
            resultArea.setText("Error while updating information: " + e.getMessage());
        }
    }

    @FXML
    @Override
    void onDelete() {
        try {
           provider.delete(Integer.parseInt(compositionIdField.getText()));
           resultArea.setText("Successfully onDelete composition with composition id: " + compositionIdField.getText());
        } catch (Exception e) {
            resultArea.setText("Error while deleting composition: " + e.getMessage());
        }
    }
}
