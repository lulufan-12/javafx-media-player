package online.luismartinsdev.mediaplayer.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import lombok.Data;
import online.luismartinsdev.mediaplayer.annotation.stereotype.Model;
import org.springframework.context.annotation.Scope;

import java.io.File;

@Data
@Model
@Scope("singleton")
public class MediaPlayerModel {

    private SimpleBooleanProperty playing;
    private SimpleListProperty<File> songList;
    private SimpleObjectProperty<Media> activeSong;
    private SimpleObjectProperty<File> activeDirectory;
    private MediaPlayer mediaPlayer;

    public MediaPlayerModel() {
        playing = new SimpleBooleanProperty(false);
        songList = new SimpleListProperty<>(FXCollections.emptyObservableList());
        activeSong = new SimpleObjectProperty<>(null);
        activeDirectory = new SimpleObjectProperty<>(null);
        mediaPlayer = null;
    }
}
