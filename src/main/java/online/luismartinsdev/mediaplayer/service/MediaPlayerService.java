package online.luismartinsdev.mediaplayer.service;

import javafx.collections.FXCollections;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import online.luismartinsdev.mediaplayer.model.MediaPlayerModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static online.luismartinsdev.mediaplayer.util.Constants.ALLOWED_AUDIO_EXTENSIONS;

@Service
@Scope("singleton")
public class MediaPlayerService {

    public void loadDirectory(File directory, MediaPlayerModel model) throws Exception {
        validateDirectory(directory);
        model.getActiveDirectory().set(directory);
        loadSongs(filterByValidExtensions(directory), model);
    }

    public void doNothing() {
        System.out.println("Do nothing");
    }

    public void stopSong(MediaPlayerModel model) {
        if (nonNull(model.getActiveSong())) {
            model.getPlaying().set(false);
            model.getMediaPlayer().stop();
        }
    }

    public void togglePlaying(MediaPlayerModel model) {
        if (nonNull(model.getActiveSong())) {
            model.getPlaying().set(!model.getPlaying().get());
            if (model.getPlaying().get()) {
                model.getMediaPlayer().play();
            } else {
                model.getMediaPlayer().pause();
            }
        }
    }

    private void loadSongs(List<File> songs, MediaPlayerModel model) {
        model.getSongList().set(FXCollections.observableList(songs));
        if (!songs.isEmpty()) {
            Media activeSong = new Media(songs.get(0).toURI().toString());
            model.getActiveSong().set(activeSong);
            model.setMediaPlayer(new MediaPlayer(activeSong));
        }
    }

    private List<File> filterByValidExtensions(File directory) {
        return Arrays.stream(Objects.requireNonNull(directory.listFiles()))
                .filter(File::isFile)
                .filter(File::canRead)
                .filter(file -> {
                    String fileName = file.getName();
                    int indexOfPoint = fileName.lastIndexOf('.');
                    if (indexOfPoint == -1) {
                        return false;
                    }
                    String extension = fileName.substring(indexOfPoint);
                    return List.of(ALLOWED_AUDIO_EXTENSIONS).contains(extension);
                }).collect(Collectors.toList());
    }

    private static void validateDirectory(File directory) throws Exception {
        if (directory.isFile()) {
            throw new Exception();
        }
    }

}
