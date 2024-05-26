package online.luismartinsdev.mediaplayer.context;

import lombok.Data;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Data
public class ApplicationContextHolder {

    private static ApplicationContextHolder INSTANCE = new ApplicationContextHolder();
    private AnnotationConfigApplicationContext context;

    private ApplicationContextHolder() {

    }

    public static ApplicationContextHolder getInstance() {
        return INSTANCE;
    }

}
