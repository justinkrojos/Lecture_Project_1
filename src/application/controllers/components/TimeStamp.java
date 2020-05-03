package application.controllers.components;

import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class TimeStamp {

    private String currentDuration;
    private String totalDuration;

    public TimeStamp(String currentDuration, String totalDuration) {
        currentDuration = this.currentDuration;
        totalDuration = this.totalDuration;
    }

    // convert duration to relevant hours, minutes seconds
    public void convertDuration(Duration currentDuration, boolean isTotalDuration) {
        int seconds = Integer.parseInt(currentDuration.toString().substring(0, currentDuration.toString().length() - 3).split("\\.", 2)[0]) / 1000;
        String duration;

        if (seconds <= 59) {
            duration = "00:" + String.format("%02d", seconds);
        } else if (seconds > 3599) {
            duration = String.format("%02d", seconds / 3600) + ":" + String.format("%02d", seconds % 3600 / 60)
                    + ":" + String.format("%02d", seconds % 60);
        } else {
            duration = String.format("%02d", seconds % 3600 / 60) + ":" + String.format("%02d", seconds % 60);
        }

        if (isTotalDuration == true) {
            this.totalDuration = duration;
        }
        else {
            this.currentDuration = duration;
        }
    }


    // merge current and total duration into a standardised format
    public String getFinalisedLabel() {
        return this.currentDuration + " / " + this.totalDuration;
    }
}
