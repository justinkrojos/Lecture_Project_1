package application;

import application.controllers.components.FullscreenButton;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    private static Stage stage;
    private static Stage notFs;
    private static Scene scene;
    private static Parent root;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        notFs = stage;
        root = FXMLLoader.load(getClass().getResource("resources/Home.fxml"));
        primaryStage.setTitle("Hello World");
        scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // make full screen
    public static void setStage(MediaView mv, Pane dragBox) {

        dragBox.setLayoutX(0.0);
        dragBox.setLayoutY(0.0);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        dragBox.setMinHeight(screenSize.getHeight());
        dragBox.setMinWidth(screenSize.getWidth());

        stage.setMaximized(true);
        stage.setFullScreen(true);
        //stage.setScene(new Scene(root, screenSize.getWidth(), screenSize.getHeight()));
        //stage.show();

    }

    // check if it is fullscreen
    public static boolean checkScreen(MediaView mv, Pane dragBox, AnchorPane window) {
        if (stage.isFullScreen()) {

            dragBox.setMinHeight(450);
            dragBox.setMinWidth(600);
            dragBox.setLayoutX(0.0);
            dragBox.setLayoutY(0.0);
            window.setPrefHeight(600);
            window.setPrefWidth(800);

            notFs.setMaximized(false);
            notFs.setFullScreen(false);
            notFs.setScene(scene);
            stage.setWidth(800);
            stage.setHeight(600);



            notFs.show();
            return true;
        }

        return false;

    }



    public static void main(String[] args) {
        launch(args);
    }
}
