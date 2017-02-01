package za.co.entelect.bootcamp.twoface.squareeyes.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.services.CatalogueService;

import java.util.List;

/**
 * Created by sean.vienings on 2017/01/31.
 */
@Controller
public class CatalogueController {

    private CatalogueService catalogueService;

    public CatalogueController(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    @RequestMapping(value = "/catalogue", method = RequestMethod.GET)
    public String SayHello(ModelMap modelMap){
        List<Issue> list = catalogueService.getCataloguePage(1);
        modelMap.addAttribute("list", list);
        return "catalogue";
    }
}
