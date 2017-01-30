package za.co.entelect.bootcamp.twoface.squareeyes.web.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by sean.vienings on 2017/01/30.
 *
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //Root context:
    @Override protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { ServiceRootConfig.class };
    }
    //DispatcherServlet context:
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }

    protected String[] getServletMappings() {
        return new String[0];
    }
}
