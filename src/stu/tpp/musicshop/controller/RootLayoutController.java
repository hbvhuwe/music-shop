package stu.tpp.musicshop.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class RootLayoutController extends Controller{
    @FXML
    private void handleExit() {
        try {
            mainApp.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleGroupView() {
        mainApp.showGroupView();
    }

    @FXML
    private void handleAbout() {
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("About");
        about.setHeaderText("Music shop");
        about.setContentText("Application to work with music shop\n" +
                "Version 1.0\n" +
                "Author: Vladimir Chernonog");
        about.showAndWait();
    }
}
