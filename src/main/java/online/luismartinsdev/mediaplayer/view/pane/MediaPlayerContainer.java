package online.luismartinsdev.mediaplayer.view.pane;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import online.luismartinsdev.mediaplayer.annotation.stereotype.View;
import online.luismartinsdev.mediaplayer.controller.MediaPlayerController;
import online.luismartinsdev.mediaplayer.view.control.button.*;
import online.luismartinsdev.mediaplayer.view.control.listview.SongListView;

@View
public class MediaPlayerContainer extends VBox {

    private final OpenFolderButton btnOpenFolder;
    private final PreviousButton btnPrevious;
    private final PlayPauseButton btnPlayPause;
    private final StopButton btnStop;
    private final NextButton btnNext;
    private final SongListView songListView;

    public MediaPlayerContainer(PlayPauseButton btnPlayPause,
                                OpenFolderButton btnOpenFolder,
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
        this.btnOpenFolder = btnOpenFolder;
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

}
