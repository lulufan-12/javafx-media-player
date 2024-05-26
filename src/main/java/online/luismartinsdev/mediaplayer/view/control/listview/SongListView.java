package online.luismartinsdev.mediaplayer.view.control.listview;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import online.luismartinsdev.mediaplayer.annotation.stereotype.View;
import online.luismartinsdev.mediaplayer.state.MediaPlayerViewState;

import java.io.File;

@View
public class SongListView extends ListView<File> {

    public SongListView(MediaPlayerViewState state) {
        setItems(state.getSongList());
        setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(File item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
    }

}
