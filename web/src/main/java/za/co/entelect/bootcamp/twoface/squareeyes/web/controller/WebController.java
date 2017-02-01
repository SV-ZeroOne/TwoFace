package za.co.entelect.bootcamp.twoface.squareeyes.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.services.CatalogueService;

import java.util.List;

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
