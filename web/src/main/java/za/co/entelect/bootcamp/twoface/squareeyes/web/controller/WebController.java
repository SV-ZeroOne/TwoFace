package za.co.entelect.bootcamp.twoface.squareeyes.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sean.vienings on 2017/01/30.
 *
 */
@Controller
public class WebController {

    private String testVariable;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public @ResponseBody
    String getHelloWorldRest() {
        return "Hello, world!";
    }
}
