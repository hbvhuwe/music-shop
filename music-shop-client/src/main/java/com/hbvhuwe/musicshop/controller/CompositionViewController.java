package com.hbvhuwe.musicshop.controller;

import com.hbvhuwe.musicshop.providers.DataProvider;
import com.hbvhuwe.musicshop.providers.JpaProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import com.hbvhuwe.musicshop.model.Composition;
import com.hbvhuwe.musicshop.network.MusicShopService;
import javafx.scene.control.cell.PropertyValueFactory;

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

    private DataProvider<Composition> provider = new JpaProvider<>(Composition.class);

    @FXML
    private void initialize() {
        diskIdColumn.setCellValueFactory(new PropertyValueFactory<>("diskId"));
        compositionIdColumn.setCellValueFactory(new PropertyValueFactory<>("compositionId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        presentDateColumn.setCellValueFactory(new PropertyValueFactory<>("presentDate"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
    }


    @FXML
    @Override
    void selectAll() {
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
    void selectSingle() {
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
            Composition composition = new Composition();
            composition.setDuration(newDurationField.getText());
            composition.setName(newNameField.getText());
            resultArea.setText("Information successfully updated for: " + compositionIdField.getText());
        } catch (Exception e) {
            resultArea.setText("Error while updating information: " + e.getMessage());
        }
    }

    @FXML
    @Override
    void delete() {
        try {
           provider.delete(Integer.parseInt(compositionIdField.getText()));
           resultArea.setText("Successfully delete composition with composition id: " + compositionIdField.getText());
        } catch (Exception e) {
            resultArea.setText("Error while deleting composition: " + e.getMessage());
        }
    }
}
