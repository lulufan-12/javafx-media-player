package online.luismartinsdev.mediaplayer.view.scene;

import javafx.scene.Scene;
import online.luismartinsdev.mediaplayer.annotation.stereotype.View;
import online.luismartinsdev.mediaplayer.view.pane.MediaPlayerContainer;
import org.springframework.context.annotation.Scope;

@View
@Scope("singleton")
public class MediaPlayerView extends Scene {

    public MediaPlayerView(MediaPlayerContainer mediaPlayerContainer) {
        super(mediaPlayerContainer, 400, 200);
    }

}
