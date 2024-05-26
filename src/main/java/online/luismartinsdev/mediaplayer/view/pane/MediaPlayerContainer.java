package online.luismartinsdev.mediaplayer.view.pane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import online.luismartinsdev.mediaplayer.annotation.stereotype.View;
import online.luismartinsdev.mediaplayer.controller.MediaPlayerController;
import online.luismartinsdev.mediaplayer.view.control.buttons.PlayPauseButton;
import online.luismartinsdev.mediaplayer.view.control.buttons.StopButton;
import online.luismartinsdev.mediaplayer.view.control.listview.SongListView;

@View
public class MediaPlayerContainer extends VBox {

    private Button btnOpenFolder;
    private Button btnBack;
    private final PlayPauseButton btnPlayPause;
    private final StopButton btnStop;
    private Button btnForward;
    private final SongListView songListView;
    private final MediaPlayerController.HandlerOpenFolderAction handlerOpenFolderAction;
    private final MediaPlayerController.HandlerBackAction handlerBackAction;
    private final MediaPlayerController.HandlerForwardAction handlerForwardAction;

    public MediaPlayerContainer(PlayPauseButton btnPlayPause,
                                StopButton btnStop,
                                SongListView songListView,
                                MediaPlayerController.HandlerOpenFolderAction handlerOpenFolderAction,
                                MediaPlayerController.HandlerBackAction handlerBackAction,
                                MediaPlayerController.HandlerForwardAction handlerForwardAction) {
        this.handlerBackAction = handlerBackAction;
        this.handlerForwardAction = handlerForwardAction;
        this.songListView = songListView;
        this.btnPlayPause = btnPlayPause;
        this.btnStop = btnStop;
        this.handlerOpenFolderAction = handlerOpenFolderAction;
        createButtons();
        defineLayout();
    }

    private void defineLayout() {
        HBox hboxControlButtons = new HBox(btnBack, btnPlayPause, btnStop, btnForward);
        hboxControlButtons.setSpacing(8);
        hboxControlButtons.setAlignment(Pos.CENTER);
        this.getChildren().addAll(btnOpenFolder, hboxControlButtons, songListView);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(16);
    }

    private void createButtons() {
        btnOpenFolder = createButton("Open Folder", handlerOpenFolderAction);
        btnBack = createButton("Back", handlerBackAction);
        btnForward = createButton("Forward", handlerForwardAction);
    }

    private static Button createButton(String text, EventHandler<ActionEvent> handler) {
        Button button = new Button();
        button.setText(text);
        button.setOnAction(handler);
        return button;
    }

}
