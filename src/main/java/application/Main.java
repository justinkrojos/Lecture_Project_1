package application;

import application.tasks.VoiceSynthesisTask;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.concurrent.WorkerStateEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.io.IOException;




public class Main extends Application {

    private static Stage stage;
    private static Stage notFs;
    private static Scene scene;
    private ExecutorService team = Executors.newSingleThreadExecutor();

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        notFs = stage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("home.fxml"));
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
