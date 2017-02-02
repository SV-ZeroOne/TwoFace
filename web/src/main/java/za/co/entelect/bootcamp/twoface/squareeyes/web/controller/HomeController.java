package za.co.entelect.bootcamp.twoface.squareeyes.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.services.CatalogueService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by sean.vienings on 2017/01/31.
 */
@Controller
public class HomeController {

    private CatalogueService catalogueService;

    public HomeController(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public String getHomepage(ModelMap modelMap){
        List<Issue> list = catalogueService.getCataloguePage(1);
        modelMap.addAttribute("list", list);
        return "homepage";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomepageAtDefault(ModelMap modelMap){
        List<Issue> list = catalogueService.getCataloguePage(1);
        modelMap.addAttribute("list", list);
        return "homepage";
    }
}
