package za.co.entelect.bootcamp.twoface.squareeyes.web.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by sean.vienings on 2017/01/30.
 *
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  //implements WebApplicationInitializer
{

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


    public void onStartup(ServletContext container) throws ServletException {

            AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
            ctx.register(WebConfig.class);
            ctx.setServletContext(container);

            ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));

            servlet.setLoadOnStartup(1);
            servlet.addMapping("/");
        }

    }
