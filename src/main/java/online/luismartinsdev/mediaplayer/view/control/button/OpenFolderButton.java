package online.luismartinsdev.mediaplayer.view.control.button;

import javafx.scene.control.Button;
import online.luismartinsdev.mediaplayer.annotation.stereotype.View;
import online.luismartinsdev.mediaplayer.controller.MediaPlayerController;

@View
public class OpenFolderButton extends Button {

    public OpenFolderButton(MediaPlayerController.HandlerOpenFolderAction handlerOpenFolderAction) {
        setOnAction(handlerOpenFolderAction);
        setText("Open Folder");
    }
}
