package application.controllers.components;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;

public class Timeslider {


    public void syncSlider(Slider timeslider, MediaPlayer player) {
        // Configure time slider to sync with video
        player.currentTimeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        timeslider.setValue(player.getCurrentTime().toMillis()/player.getTotalDuration().toMillis() * 100);

                    }
                });
            }
        });
        timeslider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (player == null) {
                    return;

                }
                else if (timeslider.isPressed()) {
                    player.seek(player.getMedia().getDuration().multiply(timeslider.getValue() / 100));

                }
            }
        });

    }


    // Update values of time slider to current video.

    protected void updateValues(Slider timeslider, MediaPlayer player) {
        Platform.runLater(new Runnable() {
            public void run() {
                timeslider.setValue(player.getCurrentTime().toMillis()/player.getTotalDuration().toMillis() * 100);

            }
        });
    }

}
