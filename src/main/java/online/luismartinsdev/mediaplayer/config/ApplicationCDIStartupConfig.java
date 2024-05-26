package online.luismartinsdev.mediaplayer.config;

import online.luismartinsdev.mediaplayer.context.ApplicationContextHolder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

@Configuration
public class ApplicationCDIStartupConfig {

    public static <T> void run(Class<T> clazz) {
        Assert.isTrue(clazz.isAnnotationPresent(ComponentScan.class), "Annotation @ComponentScan has to be present");
        Assert.isTrue(clazz.isAnnotationPresent(Configuration.class), "Annotation @Configutaion has to be present");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan(clazz.getPackageName());
        context.refresh();

        ApplicationContextHolder.getInstance().setContext(context);
    }

}
