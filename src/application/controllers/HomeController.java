package application.controllers;

import application.controllers.components.TimeStamp;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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


    TimeStamp timeStamp = new TimeStamp("0:00", "0:00");


    @FXML
    private Pane dragBox;

    @FXML
    private Label timeStampLabel;

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

                _player.currentTimeProperty().addListener(new InvalidationListener() {
                    @Override
                    public void invalidated(Observable observable) {
                        if (dragBox!=null) {
                           // System.out.println(Integer.parseInt(_player.getCurrentTime().toString().substring(0, _player.getCurrentTime().toString().length() - 3)
                                   // .split("\\.", 2)[0]) / 1000);
                            timeStamp.calculateCurrentDuration(_player.getCurrentTime());

                            timeStampLabel.setText(timeStamp.getFinalisedLabel());

                        }

                    }
                });

                _player.play();
                event.consume(); // not dispatched to further listeners

            }
        });
    }
}
