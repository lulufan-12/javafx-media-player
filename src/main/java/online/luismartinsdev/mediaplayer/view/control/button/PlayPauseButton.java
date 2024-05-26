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
import static online.luismartinsdev.mediaplayer.util.Constants.Resources.IMAGES_ICONS_PAUSE_ICON_PNG;
import static online.luismartinsdev.mediaplayer.util.Constants.Resources.IMAGES_ICONS_PLAY_ICON_PNG;

@View
public class PlayPauseButton extends Button implements Cleaner.Cleanable {

    private final ImageView imageView;
    private final ChangeListener<Boolean> playingChangeListener;
    private final ChangeListener<Media> activeSongChangeListener;
    private final String playIconUrl;
    private final String pauseIconUrl;
    private final MediaPlayerViewState state;

    public PlayPauseButton(MediaPlayerViewState state,
                           MediaPlayerController.HandlerPlayPauseAction handlerPlayPauseAction) {
        this.state = state;
        playIconUrl = requireNonNull(this.getClass().getClassLoader()
                .getResource(IMAGES_ICONS_PLAY_ICON_PNG)).toString();
        pauseIconUrl = requireNonNull(this.getClass().getClassLoader()
                .getResource(IMAGES_ICONS_PAUSE_ICON_PNG)).toString();
        imageView = createButtonImage();

        setOnAction(handlerPlayPauseAction);
        setDisable(isNull(state.getActiveSong().get()));
        setGraphic(imageView);

        playingChangeListener = createPlayingChangeListener();
        activeSongChangeListener = createActiveSongChangeListener();
        addListeners();
    }

    private ImageView createButtonImage() {
        final ImageView imageView;
        Image image = new Image(playIconUrl);
        imageView = new ImageView(image);
        imageView.setFitWidth(Constants.Sizes.XL);
        imageView.setFitHeight(Constants.Sizes.XL);
        return imageView;
    }

    private ChangeListener<Boolean> createPlayingChangeListener() {
        return (_c, _oldValue, newValue) -> imageView.setImage(new Image(newValue ? pauseIconUrl : playIconUrl));
    }

    private ChangeListener<Media> createActiveSongChangeListener() {
        return (obs, oldValue, newValue) -> setDisable(isNull(obs.getValue()));
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
