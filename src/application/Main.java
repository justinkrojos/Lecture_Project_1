package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
    public static void setStage() {

        stage.setMaximized(true);
        stage.setFullScreen(true);
        //stage.setScene(new Scene(root, screenSize.getWidth(), screenSize.getHeight()));
        //stage.show();

    }

    // check if it is fullscreen
    public static boolean checkScreen() {
        if (stage.isFullScreen()) {

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
