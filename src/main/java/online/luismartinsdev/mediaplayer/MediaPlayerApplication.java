package online.luismartinsdev.mediaplayer;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.val;
import online.luismartinsdev.mediaplayer.config.ApplicationCDIStartupConfig;
import online.luismartinsdev.mediaplayer.context.ApplicationContextHolder;
import online.luismartinsdev.mediaplayer.view.scene.MediaPlayerView;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
public class MediaPlayerApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ApplicationCDIStartupConfig.run(MediaPlayerApplication.class);
        val context = (AnnotationConfigApplicationContext) ApplicationContextHolder.getInstance().getContext();
        MediaPlayerView scene = context.getBean(MediaPlayerView.class);
        primaryStage.setTitle("JavaFX Media Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}