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

    // Fast forward
    public void fastforward(Slider timeslider, MediaPlayer player, int milliseconds) {

        if (player != null) {
            timeslider.setValue((player.getCurrentTime().toMillis() + milliseconds) / player.getTotalDuration().toMillis() * 100);
            player.seek(player.getMedia().getDuration().multiply(timeslider.getValue() / 100));
        }
    }

    // Fast backward
    public void fastbackward(Slider timeslider, MediaPlayer player, int milliseconds) {

        if (player != null) {
            timeslider.setValue((player.getCurrentTime().toMillis() - milliseconds) / player.getTotalDuration().toMillis() * 100);
            player.seek(player.getMedia().getDuration().multiply(timeslider.getValue() / 100));
        }
    }

    // Change speed
    public void playbackSpeed(MediaPlayer player, int rate) {
        if (player != null) {
            player.setRate((double) rate);
        }
    }

}
