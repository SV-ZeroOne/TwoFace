package za.co.entelect.bootcamp.twoface.squareeyes.web.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by sean.vienings on 2017/01/30.
 *
 */

@Configuration
@ComponentScan(basePackages = "za.co.entelect.bootcamp.web.controllers")
public class WebConfig {

    @Bean
    InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/views/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }
}
