package application.controllers.components;

import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;

public class PauseButton {

    // Pause video and change text

    // Play video and change text
    public void pauseVideo(MediaPlayer _player, Button pauseBtn) {
        if (pauseBtn.getText().equals("Pause")) {
            _player.pause();
            pauseBtn.setText("Play");
        }
        else {
            _player.play();
            pauseBtn.setText("Pause");
        }
    }

}
