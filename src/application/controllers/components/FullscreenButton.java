package application.controllers.components;

import application.Main;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaView;

import java.awt.*;

public class FullscreenButton {

    // make video fullscreen
    public void fullscreenVideo(Pane dragBox, AnchorPane window, HBox hb) {
        if (!Main.checkScreen(dragBox, window, hb)) {
            Main.setStage(dragBox, hb);
            dragBox.setLayoutX(0.0);
            dragBox.setLayoutY(0.0);

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            dragBox.setMinHeight(screenSize.getHeight());
            dragBox.setMinWidth(screenSize.getWidth());

            hb.setMinWidth(screenSize.getWidth());
            hb.setLayoutY(screenSize.getHeight() - 40);
        }
        else {
            dragBox.setMinHeight(450);
            dragBox.setMinWidth(600);
            dragBox.setLayoutX(0.0);
            dragBox.setLayoutY(0.0);
            window.setPrefHeight(400);
            window.setPrefWidth(800);

            hb.setMinWidth(600);
            hb.setLayoutY(dragBox.getLayoutY() + dragBox.getMinHeight() - 40);
        }

    }

    //
}
