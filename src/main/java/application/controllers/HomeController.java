package application.controllers;

// import application.controllers.components.*;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import application.controllers.components.FullscreenButton;
import application.controllers.components.PauseButton;
import application.controllers.components.Timeslider;

import java.io.File;
import java.io.IOException;

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
    private ChoiceBox<String> speedBtn;
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


    application.controllers.components.TimeStamp _timeStamp = new application.controllers.components.TimeStamp("0:00", "0:00");
    PauseButton _pauseButton = new PauseButton();
    FullscreenButton _fsButton = new FullscreenButton();
    Timeslider _timeSlider = new Timeslider();



    public void initialize() {

        // initialise things that can't be set using scene builder
        {
            videoBtnHb.setHgrow(blankRegion1, Priority.ALWAYS);
            videoBtnHb.setHgrow(blankRegion2, Priority.ALWAYS);


            speedBtn.getItems().addAll("1x", "2x", "3x");
            speedBtn.getSelectionModel().select(0);
        }


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

System.out.println("1");

               /* application.controllers.components.AudioReader _audioReader = new application.controllers.components.AudioReader();

                try {
                    _audioReader.start(_draggedFile.getPath(), dragBox);
                } catch (IOException e) {
                    e.printStackTrace();
                }

*/
                _player.play();

                //calculate total duration of video to display


                event.consume(); // not dispatched to further listeners

            }
        });

        speedBtn.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number number, Number t1) {
                _timeSlider.playbackSpeed(_player, speedBtn.getSelectionModel().getSelectedIndex() + 1);
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

    // fast backward
    @FXML
    private void handleBackwardBtn() {
        _timeSlider.fastbackward(slider, _player, 5000);
    }

}
