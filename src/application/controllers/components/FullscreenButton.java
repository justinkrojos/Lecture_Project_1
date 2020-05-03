package application.controllers.components;

import application.Main;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaView;

public class FullscreenButton {

    // make video fullscreen
    public void fullscreenVideo(MediaView mv, Pane dragBox, AnchorPane window) {
        if (!Main.checkScreen(mv, dragBox, window)) {
            Main.setStage(mv, dragBox);
        }

    }

    //
}
