package com.hbvhuwe.musicshop;

import com.hbvhuwe.musicshop.providers.JdbcProvider;
import com.hbvhuwe.musicshop.providers.JpaProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import com.hbvhuwe.musicshop.controller.Controller;
//import DbController;
//import UpdateUtil;

import java.io.IOException;

public final class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Music shop");
        this.primaryStage.getIcons().add(
                new Image(ClassLoader.getSystemResource("images/ic_library_music_white_48dp_1x.png").toString()));
        initRootLayout();
        showGroupView();
    }

    @Override
    public void stop() {
        try {
            JdbcProvider.close();
            JpaProvider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        primaryStage.close();
    }

    public void showDiskView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ClassLoader.getSystemResource("view/disk_view.fxml"));

        AnchorPane groupView = null;
        try {
            groupView = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        rootLayout.setCenter(groupView);

        Controller controller = loader.getController();
        controller.setMainApp(this);
    }

    public void showCompositionView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ClassLoader.getSystemResource("view/composition_view.fxml"));

        AnchorPane groupView = null;
        try {
            groupView = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        rootLayout.setCenter(groupView);

        Controller controller = loader.getController();
        controller.setMainApp(this);
    }

    public void showGroupView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ClassLoader.getSystemResource("view/group_view.fxml"));

        AnchorPane groupView = null;
        try {
            groupView = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        rootLayout.setCenter(groupView);

        Controller controller = loader.getController();
        controller.setMainApp(this);
    }

    private void initRootLayout() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ClassLoader.getSystemResource("view/root_layout.fxml"));

        try {
            rootLayout = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);

        Controller controller = loader.getController();
        controller.setMainApp(this);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
