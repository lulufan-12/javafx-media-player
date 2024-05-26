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
import static online.luismartinsdev.mediaplayer.util.Constants.Resources.IMAGES_ICONS_PREVIOUS_ICON_PNG;

@View
public class PreviousButton extends Button implements Cleaner.Cleanable {

    private final ChangeListener<Media> activeSongChangeListener;
    private final String iconUrl;
    private final MediaPlayerViewState state;

    public PreviousButton(MediaPlayerViewState state,
                          MediaPlayerController.HandlerPreviousAction handlerPreviousAction) {
        this.state = state;
        iconUrl = requireNonNull(this.getClass().getClassLoader()
                .getResource(IMAGES_ICONS_PREVIOUS_ICON_PNG)).toString();
        ImageView imageView = createButtonImage();

        setOnAction(handlerPreviousAction);
        setDisable(isNull(state.getActiveSong().get()));
        setGraphic(imageView);

        activeSongChangeListener = createActiveSongChangeListener();
        addListeners();
    }

    private ImageView createButtonImage() {
        final ImageView imageView;
        Image image = new Image(iconUrl);
        imageView = new ImageView(image);
        imageView.setFitWidth(Constants.Sizes.MD);
        imageView.setFitHeight(Constants.Sizes.MD);
        return imageView;
    }

    private ChangeListener<Media> createActiveSongChangeListener() {
        return (obs, oldValue, newValue) -> setDisable(isNull(newValue));
    }

    private void addListeners() {
        state.getActiveSong().addListener(activeSongChangeListener);
    }

    @Override
    public void clean() {
        state.getActiveSong().removeListener(activeSongChangeListener);
    }
}
