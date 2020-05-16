package application.controllers.components;


import application.Main;
import application.tasks.VoiceSynthesisTask;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VoiceReader {

    private ExecutorService team = Executors.newSingleThreadExecutor();

    private PauseButton pauseButton;
    private MediaPlayer player;
    private Button pauseBtn;

    public VoiceReader(PauseButton pauseButton, MediaPlayer player, Button pauseBtn) {
        this.pauseButton = pauseButton;
        this.player = player;
        this.pauseBtn = pauseBtn;
    }

   public void readVoice() throws IOException {
       Configuration configuration = new Configuration();

       configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
       configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
       configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");


       LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);

       VoiceSynthesisTask task = new VoiceSynthesisTask(recognizer, pauseButton, player, pauseBtn);
       team.submit(task);

       task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
           @Override
           public void handle(WorkerStateEvent workerStateEvent) {
               if (task.getCommand().equals("Pause")) {
                   //System.out.println("reached");
                   try {
                       pauseButton.pauseVideo(player, pauseBtn);
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           }
           });
   }
}
