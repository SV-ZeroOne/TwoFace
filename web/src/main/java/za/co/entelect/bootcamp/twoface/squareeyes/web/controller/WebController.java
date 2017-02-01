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

    @Autowired
    CatalogueService catalogueService;

    private String testVariable;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public @ResponseBody
    String getHelloWorldRest() {
        return "Hello, world!";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String SayHello(ModelMap modelMap){
        List<Issue> list = catalogueService.getCataloguePage(1);
        modelMap.addAttribute("list",list);
        return "homepage";
    }

    @RequestMapping(value = "/catalogue", method = RequestMethod.GET)
    public String PopulateCatalogue(ModelMap modelMap){
        List<Issue> list = catalogueService.getCataloguePage(1);
        for (Issue issue : list) {
            System.out.println("Title: " + issue.getIssueTitle());
        }
        modelMap.addAttribute("list",list);
        return "catalogue";
    }

    public CatalogueService getCatalogueService() {
        return catalogueService;
    }
    public void setCatalogueService(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }
}
