package stu.tpp.musicshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import stu.tpp.musicshop.controller.Controller;
import stu.tpp.musicshop.util.DbController;
import stu.tpp.musicshop.util.QueryUtil;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Music shop");
        this.primaryStage.getIcons().add(
                new Image(getClass().getResource(
                        "images/ic_library_music_white_48dp_1x.png").toString()));
        initRootLayout();
        showGroupView();
        QueryUtil.database = DbController.getInstance();
    }

    @Override
    public void stop() {
        try {
            QueryUtil.database.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        primaryStage.close();
    }

    public void showDiskView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/disk_view.fxml"));

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
        loader.setLocation(getClass().getResource("view/composition_view.fxml"));

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
        loader.setLocation(getClass().getResource("view/group_view.fxml"));

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
        loader.setLocation(getClass().getResource("view/root_layout.fxml"));

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
