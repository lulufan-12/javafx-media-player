package online.luismartinsdev.mediaplayer.controller;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;
import lombok.AllArgsConstructor;
import lombok.Getter;
import online.luismartinsdev.mediaplayer.model.MediaPlayerModel;
import online.luismartinsdev.mediaplayer.service.MediaPlayerService;
import online.luismartinsdev.mediaplayer.state.MediaPlayerViewState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.File;

import static java.util.Objects.nonNull;

@Getter
@Controller
@Scope("singleton")
@AllArgsConstructor
public class MediaPlayerController {

    private MediaPlayerModel model;

    @Component
    public static class HandlerOpenFolderAction implements EventHandler<ActionEvent> {

        @Autowired
        private final MediaPlayerViewState viewState;
        private final MediaPlayerModel model;
        private final MediaPlayerService service;

        public HandlerOpenFolderAction(MediaPlayerModel model,
                                       MediaPlayerService service,
                                       MediaPlayerViewState viewState) {
            this.model = model;
            this.service = service;
            this.viewState = viewState;
            init();
        }

        private void init() {
            model.getSongList().addListener((observable, oldValue, newValue) ->
                    viewState.getSongList().setAll(newValue));
            model.getPlaying().addListener((observable, oldValue, newValue) ->
                    ((SimpleBooleanProperty) viewState.getPlaying()).set(newValue));
            model.getActiveSong().addListener((observable, oldValue, newValue) ->
                    ((SimpleObjectProperty<Media>) viewState.getActiveSong()).set(newValue));
        }

        @Override
        public void handle(ActionEvent event) {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select the songs directory");
            directoryChooser.setInitialDirectory(new File(System.getenv("HOME")));
            Window window =((Button) event.getSource()).getScene().getWindow();
            File directory = directoryChooser.showDialog(window);
            if (nonNull(directory)) {
                try {
                    service.loadDirectory(directory, model);
                } catch (Exception e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error during files processing");
                    alert.setContentText(e.getMessage());
                    alert.show();
                }
            }
        }
    }

    @Component
    @AllArgsConstructor
    public static class HandlerPreviousAction implements EventHandler<ActionEvent> {

        private final MediaPlayerModel model;
        private final MediaPlayerService service;

        @Override
        public void handle(ActionEvent event) {
            service.previousSong(model);
        }
    }

    @Component
    @AllArgsConstructor
    public static class HandlerPlayPauseAction implements EventHandler<ActionEvent> {

        private final MediaPlayerModel model;
        private final MediaPlayerService service;

        @Override
        public void handle(ActionEvent event) {
            service.togglePlaying(model);
        }
    }

    @Component
    @AllArgsConstructor
    public static class HandlerStopAction implements EventHandler<ActionEvent> {

        private final MediaPlayerModel model;
        private final MediaPlayerService service;

        @Override
        public void handle(ActionEvent event) {
            service.stopSong(model);
        }
    }

    @Component
    @AllArgsConstructor
    public static class HandlerNextAction implements EventHandler<ActionEvent> {

        private final MediaPlayerModel model;
        private final MediaPlayerService service;

        @Override
        public void handle(ActionEvent event) {
            service.nextSong(model);
        }
    }

}
