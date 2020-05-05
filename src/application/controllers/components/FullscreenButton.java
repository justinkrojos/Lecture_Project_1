package application.controllers.components;

import application.Main;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;

import java.awt.*;

public class FullscreenButton {

    // make video fullscreen
    public void fullscreenVideo(Pane dragBox, AnchorPane window, VBox vb) {
        if (!Main.checkScreen()) {
            Main.setStage();
            dragBox.setLayoutX(0.0);
            dragBox.setLayoutY(0.0);

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            dragBox.setPrefHeight(screenSize.getHeight());
            dragBox.setPrefWidth(screenSize.getWidth());

            vb.setPrefWidth(screenSize.getWidth());
            vb.setLayoutY(screenSize.getHeight() - 60);

        }
        else {
            dragBox.setPrefHeight(450);
            dragBox.setPrefWidth(600);
            dragBox.setLayoutX(0.0);
            dragBox.setLayoutY(0.0);
            window.setPrefHeight(400);
            window.setPrefWidth(800);

            vb.setPrefWidth(600);
            vb.setLayoutY(dragBox.getLayoutY() + dragBox.getPrefHeight() - 60);

        }

    }

    //
}
