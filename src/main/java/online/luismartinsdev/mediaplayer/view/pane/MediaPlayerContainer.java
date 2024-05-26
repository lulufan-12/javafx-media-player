package online.luismartinsdev.mediaplayer.view.pane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import online.luismartinsdev.mediaplayer.annotation.stereotype.View;
import online.luismartinsdev.mediaplayer.controller.MediaPlayerController;
import online.luismartinsdev.mediaplayer.view.control.button.NextButton;
import online.luismartinsdev.mediaplayer.view.control.button.PlayPauseButton;
import online.luismartinsdev.mediaplayer.view.control.button.PreviousButton;
import online.luismartinsdev.mediaplayer.view.control.button.StopButton;
import online.luismartinsdev.mediaplayer.view.control.listview.SongListView;

@View
public class MediaPlayerContainer extends VBox {

    private final Button btnOpenFolder;
    private final PreviousButton btnPrevious;
    private final PlayPauseButton btnPlayPause;
    private final StopButton btnStop;
    private final NextButton btnNext;
    private final SongListView songListView;

    public MediaPlayerContainer(PlayPauseButton btnPlayPause,
                                StopButton btnStop,
                                NextButton btnNext,
                                PreviousButton btnPrevious,
                                SongListView songListView,
                                MediaPlayerController.HandlerOpenFolderAction handlerOpenFolderAction) {
        this.songListView = songListView;
        this.btnPlayPause = btnPlayPause;
        this.btnNext = btnNext;
        this.btnStop = btnStop;
        this.btnPrevious = btnPrevious;
        btnOpenFolder = createButton(handlerOpenFolderAction);
        defineLayout();
    }

    private void defineLayout() {
        HBox hboxControlButtons = new HBox(btnPrevious, btnPlayPause, btnStop, btnNext);
        hboxControlButtons.setSpacing(8);
        hboxControlButtons.setAlignment(Pos.CENTER);
        this.getChildren().addAll(btnOpenFolder, hboxControlButtons, songListView);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(16);
    }

    private static Button createButton(EventHandler<ActionEvent> handler) {
        Button button = new Button();
        button.setText("Open Folder");
        button.setOnAction(handler);
        return button;
    }

}
