package online.luismartinsdev.mediaplayer.view.control.button;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import online.luismartinsdev.mediaplayer.annotation.stereotype.View;
import online.luismartinsdev.mediaplayer.controller.MediaPlayerController;
import online.luismartinsdev.mediaplayer.state.MediaPlayerViewState;
import online.luismartinsdev.mediaplayer.util.Constants;

import java.lang.ref.Cleaner;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static online.luismartinsdev.mediaplayer.util.Constants.Resources.IMAGES_ICONS_STOP_ICON_PNG;

@View
public class StopButton extends Button implements Cleaner.Cleanable {

    private final ChangeListener<Boolean> playingChangeListener;
    private final ChangeListener<Media> activeSongChangeListener;
    private final MediaPlayerViewState state;

    public StopButton(MediaPlayerController.HandlerStopAction handlerStopAction,
                      MediaPlayerViewState state) {
        this.state = state;
        ImageView imageView = createButtonImage();

        setOnAction(handlerStopAction);
        setDisable(isNull(state.getActiveSong().get()));
        setGraphic(imageView);

        playingChangeListener = createPlayingChangeListener();
        activeSongChangeListener = createActiveSongChangeListener();

        addListeners();
    }

    private ImageView createButtonImage() {
        final ImageView imageView;
        String stopImageUrl = requireNonNull(this.getClass()
                .getClassLoader().getResource(IMAGES_ICONS_STOP_ICON_PNG)).toString();
        Image image = new Image(stopImageUrl);
        imageView = new ImageView(image);
        imageView.setFitHeight(Constants.Sizes.MD);
        imageView.setFitWidth(Constants.Sizes.MD);
        return imageView;
    }

    private ChangeListener<Boolean> createPlayingChangeListener() {
        return (_c, _oldValue, newValue) -> setDisable(!newValue);
    }

    private ChangeListener<Media> createActiveSongChangeListener() {
        return (_c, _oldValue, newValue) -> setDisable(isNull(newValue));
    }

    private void addListeners() {
        state.getPlaying().addListener(playingChangeListener);
        state.getActiveSong().addListener(activeSongChangeListener);
    }

    @Override
    public void clean() {
        state.getPlaying().removeListener(playingChangeListener);
        state.getActiveSong().removeListener(activeSongChangeListener);
    }
}
