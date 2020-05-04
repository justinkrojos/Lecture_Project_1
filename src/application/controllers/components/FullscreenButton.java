package application.controllers.components;

import application.Main;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaView;

public class FullscreenButton {

    // make video fullscreen
    public void fullscreenVideo(Pane dragBox, AnchorPane window, HBox hb) {
        if (!Main.checkScreen(dragBox, window, hb)) {
            Main.setStage(dragBox, hb);
        }

    }

    //
}
