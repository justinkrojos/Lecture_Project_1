package application.controllers.components;

import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;

public class PauseButton {
    // Pause video and change text

    private VoiceReader voiceReader = null;
    public PauseButton(VoiceReader voiceReader) {

    }

    public void setVoiceReader(VoiceReader voiceReader) {
        this.voiceReader = voiceReader;
    }
    // Play video and change text
    public void pauseVideo(MediaPlayer _player, Button pauseBtn) throws IOException {
        if (_player == null) {
            return;
        }
        if (pauseBtn.getText().equals("Pause")) {
            _player.pause();
            pauseBtn.setText("Play");
            /*if (voiceReader != null) {
                voiceReader.readVoice();
            }*/
        }
        else {
            _player.play();
            pauseBtn.setText("Pause");
           /* if (voiceReader != null) {
                voiceReader.readVoice();
            }*/
        }
    }

}
