package application.controllers.components;

import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class TimeStamp {

    private String currentDuration;
    private String totalDuration;

    public TimeStamp(String currentDuration, String totalDuration) {
        currentDuration = this.currentDuration;
        totalDuration = this.totalDuration;
    }

    public void calculateCurrentDuration(Duration currentDuration) {
        int seconds = Integer.parseInt(currentDuration.toString().substring(0, currentDuration.toString().length() - 3).split("\\.", 2)[0]) / 1000;
        if (seconds <= 59) {
            this.currentDuration = "00:" + String.format("%02d", seconds);
        }
        else if (seconds > 3599) {
            this.currentDuration = String.format("%02d", seconds/3600) + ":" + String.format("%02d", seconds%3600/60)
                    + ":"  + String.format("%02d", seconds%60);
        }
        else {
            this.currentDuration = String.format("%02d", seconds%3600/60) + ":" + String.format("%02d", seconds%60);
        }
    }

    public String getFinalisedLabel() {
        return this.currentDuration + " / " + this.totalDuration;
    }
}
