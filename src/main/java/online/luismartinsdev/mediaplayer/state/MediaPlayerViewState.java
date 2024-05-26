package online.luismartinsdev.mediaplayer.state;

import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableObjectValue;
import javafx.scene.media.Media;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;

@Data
@Component
@Scope("singleton")
@AllArgsConstructor
public class MediaPlayerViewState {
    private final ObservableObjectValue<Media> activeSong;
    private final ObservableListValue<File> songList;
    private ObservableBooleanValue playing;
}
