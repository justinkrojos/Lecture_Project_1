package application.tasks;

import application.Main;
import application.controllers.components.PauseButton;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;

/**
 * This task merges the audio and video files together. With bash commands.
 */
public class VoiceSynthesisTask extends Task<Void> {

    private LiveSpeechRecognizer recognizer;
    private PauseButton pauseButton;
    private MediaPlayer player;
    private Button pauseBtn;
    private String command = null;

    public VoiceSynthesisTask(LiveSpeechRecognizer recognizer, PauseButton pauseButton, MediaPlayer player, Button pauseBtn) {
        this.recognizer = recognizer;
        this.pauseButton = pauseButton;
        this.player = player;
        this.pauseBtn = pauseBtn;
    }

    /**
     * Make the merged creation in creation folder, not in any of the sub folders under creation.
     * @return
     * @throws Exception
     */
    @Override
    protected Void call() throws Exception {
        while (true) {
            System.out.println("a");
            recognizer.startRecognition(true);
            String result = recognizer.getResult().getHypothesis();
            if (result.startsWith("stop")) {
                System.out.println("Stopping");
                command = "Pause";
                recognizer.stopRecognition();
                break;
            } else if (result.length() != 0) {
                System.out.println(result);
                System.out.println("Printed");
                recognizer.stopRecognition();
            } else {
                System.out.println("Nothing happening");
                recognizer.stopRecognition();
            }

        }
        return null;
    }

    public String getCommand() {
        return command;
    }
}