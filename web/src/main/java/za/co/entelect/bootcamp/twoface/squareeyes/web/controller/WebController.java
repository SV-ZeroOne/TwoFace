package za.co.entelect.bootcamp.twoface.squareeyes.web.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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



    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String SayHello(ModelMap modelMap){
        modelMap.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
        return "hello";
    }
    /*
    Insert page number
    Search Criteria


    @RequestMapping(value = "/issue", method = RequestMethod.GET)
    public String SayHello(ModelMap modelMap){
        modelMap.addAttribute("greeting", "List of issues");
        return "catalogue";
    }

    @RequestMapping(value = "/issue/id?=01", method = RequestMethod.GET)
    public String SayHello(ModelMap modelMap){
        modelMap.addAttribute("greeting", "Individual Issue details");
        return "issue";
    }

    @RequestMapping(value = "/catalogue", method = RequestMethod.GET)
    public String SayHello(ModelMap modelMap){
        modelMap.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
        return "hello";
    }
    */
}
