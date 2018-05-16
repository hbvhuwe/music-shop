package com.hbvhuwe.musicshop.controller;

import com.hbvhuwe.musicshop.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class RootLayoutController {
    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void onExit() {
        try {
            mainApp.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onGroupView() {
        mainApp.showGroupView();
    }

    @FXML
    private void onDiskView() {
        mainApp.showDiskView();
    }

    @FXML
    private void onCompositionView() {
        mainApp.showCompositionView();
    }

    @FXML
    private void onAbout() {
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("About");
        about.setHeaderText("Music shop");
        about.setContentText("Application to work with music shop\n" +
                "Version 1.0\n" +
                "Author: Vladimir Chernonog");
        about.showAndWait();
    }
}
