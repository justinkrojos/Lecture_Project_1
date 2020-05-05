package application.controllers;

import application.controllers.components.FullscreenButton;
import application.controllers.components.PauseButton;
import application.controllers.components.Timeslider;
import application.controllers.components.TimeStamp;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class HomeController {





    @FXML
    private AnchorPane window;
    @FXML
    private AnchorPane videoWindow;

    @FXML
    private Pane dragBox;

    @FXML
    private HBox videoBtnHb;
    @FXML
    private VBox videoBtnVb;

    @FXML
    private Label timeStampLabel;

    @FXML
    private Button forwardBtn;
    @FXML
    private Button pauseBtn;
    @FXML
    private Button backwardBtn;

    @FXML
    private MenuButton speedBtn;
    @FXML
    private Button fsBtn;

    @FXML
    private Region blankRegion1;
    @FXML
    private Region blankRegion2;

    @FXML
    private Slider slider;

    private File _draggedFile;

    private Media _video;
    private MediaPlayer _player;
    private MediaView _mediaView;


    TimeStamp _timeStamp = new TimeStamp("0:00", "0:00");
    PauseButton _pauseButton = new PauseButton();
    FullscreenButton _fsButton = new FullscreenButton();
    Timeslider _timeSlider = new Timeslider();

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

        {
            videoBtnHb.setHgrow(blankRegion1, Priority.ALWAYS);
            videoBtnHb.setHgrow(blankRegion2, Priority.ALWAYS);
        }

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

                _timeSlider.syncSlider(slider, _player);

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

    // make video full screen
    @FXML
    private void handleFsBtn() {
        _fsButton.fullscreenVideo(dragBox, window, videoBtnVb);
    }

    // fast forward
    @FXML
    private void handleForwardBtn() {
        _timeSlider.fastforward(slider, _player, 5000);
    }
}
