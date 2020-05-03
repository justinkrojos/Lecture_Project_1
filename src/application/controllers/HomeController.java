package application.controllers;

import application.controllers.components.PauseButton;
import application.controllers.components.TimeStamp;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.sql.Time;

public class HomeController {


    TimeStamp _timeStamp = new TimeStamp("0:00", "0:00");
    PauseButton _pauseButton = new PauseButton();


    @FXML
    private Pane dragBox;

    @FXML
    private Label timeStampLabel;

    @FXML
    private Button pauseBtn;

    private File _draggedFile;

    private Media _video;
    private MediaPlayer _player;
    private MediaView _mediaView;

    public void initialize() {
        dragBox.setOnDragOver(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != dragBox
                        && event.getDragboard().hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });


        dragBox.setOnDragDropped(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    _draggedFile = new File(db.getFiles().toString().substring(1, db.getFiles().toString().length() - 1));
                    success = true;
                    //String fileUrl = _draggedFile.getPath();
                    System.out.println(_draggedFile.getPath());
                }
                event.setDropCompleted(success);

                _video = new Media(_draggedFile.toURI().toString());
                //System.out.println(_draggedFile.toURI().toString());
                _player = new MediaPlayer(_video);

                _mediaView = new MediaView(_player);
                _mediaView.fitWidthProperty().bind(dragBox.widthProperty());
                _mediaView.fitHeightProperty().bind(dragBox.heightProperty());
                _mediaView.setPreserveRatio(false);
                dragBox.getChildren().add(_mediaView);

                final String[] currentTime = new String[1];

                // update timestamp to match video length
                _player.currentTimeProperty().addListener(new InvalidationListener() {
                    @Override
                    public void invalidated(Observable observable) {
                        if (dragBox!=null) {

                            _timeStamp.convertDuration(_player.getCurrentTime(),false);
                            _timeStamp.convertDuration(_player.getTotalDuration(), true);

                            timeStampLabel.setText(_timeStamp.getFinalisedLabel());

                        }

                    }
                });

                _player.play();

                //calculate total duration of video to display


                event.consume(); // not dispatched to further listeners

            }
        });
    }

    // play/pause video
    @FXML
    private void handlePauseBtn() {
        _pauseButton.pauseVideo(_player, pauseBtn);
    }


}
