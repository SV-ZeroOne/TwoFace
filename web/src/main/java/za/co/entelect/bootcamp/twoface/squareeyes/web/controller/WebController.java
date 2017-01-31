package za.co.entelect.bootcamp.twoface.squareeyes.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    //IssuesRepositoryIMP is = new IssuesRepositoryIMP();
    //Issue tempIssue = is.find(32);

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public @ResponseBody
    String getHelloWorldRest() {
        return "Hello, world!";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String SayHello(ModelMap modelMap){
        modelMap.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
        return "homepage";
    }

    @RequestMapping(value = "/catalogue", method = RequestMethod.GET)
    public String PopulateCatalogue(ModelMap modelMap){
        modelMap.addAttribute("Issues", "List of issues");
        return "catalogue";
    }
/*
    @RequestMapping(value = "/issue", method = RequestMethod.GET)
    public String TestIssue(ModelMap modelMap){
        modelMap.addAttribute("issueTitle", tempIssue.getIssueTitle());
        modelMap.addAttribute("publisher", tempIssue.getPublisher());
        return "issue";
    }


    Insert page number
    Search Criteria






    @RequestMapping(value = "/catalogue", method = RequestMethod.GET)
    public String SayHello(ModelMap modelMap){
        modelMap.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
        return "hello";
    }
    */
}
