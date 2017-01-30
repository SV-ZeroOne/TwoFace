package za.co.entelect.bootcamp.twoface.squareeyes.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * Created by sean.vienings on 2017/01/30.
 *
 */
@Configuration
@ImportResource("classpath:services-root-context.xml")
public class ServiceRootConfig {

    @RequestMapping(value = "/test/rest", method = RequestMethod.GET)
    public @ResponseBody String getHelloWorldRest() {
        return "Hello, world!";
    }

}