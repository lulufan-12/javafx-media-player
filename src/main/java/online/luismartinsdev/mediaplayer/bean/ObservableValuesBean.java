package online.luismartinsdev.mediaplayer.bean;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableObjectValue;
import javafx.collections.FXCollections;
import javafx.scene.media.Media;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class ObservableValuesBean {

    @Bean
    public ObservableObjectValue<File> simpleFileProperty() {
        return new SimpleObjectProperty<>();
    }

    @Bean
    public ObservableObjectValue<Media> simpleMediaProperty() {
        return new SimpleObjectProperty<>();
    }

    @Bean
    public ObservableListValue<File> simpleFileListProperty() {
        return new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    @Bean
    public ObservableBooleanValue simpleObservableBoolenProperty() {
        return new SimpleBooleanProperty(false);
    }

}
