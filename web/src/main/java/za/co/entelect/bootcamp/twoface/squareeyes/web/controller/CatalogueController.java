package za.co.entelect.bootcamp.twoface.squareeyes.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.services.CatalogueService;
import za.co.entelect.bootcamp.twoface.squareeyes.services.SearchService;

import java.util.List;

/**
 * Created by sean.vienings on 2017/01/31.
 */
@Controller
public class CatalogueController {

    private CatalogueService catalogueService;
    private SearchService searchService;

    public CatalogueController(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
        //this.searchService = SearchService;
    }

    @RequestMapping(value = "/catalogue", method = RequestMethod.GET)
    public String SayHello(@RequestParam(value = "search", required = false, defaultValue = "") String search,
                           @RequestParam(value = "page",  required = false, defaultValue = "1") int page,
                           ModelMap modelMap){
        List<Issue> list;
        if (search == "")
        {
            System.out.println(search);
            list = searchService.SearchService(search);
            modelMap.addAttribute("list", list);
        }
        else
        {
            System.out.println("No Search");
            list = catalogueService.getCataloguePage(page);
            modelMap.addAttribute("list", list);
            modelMap.addAttribute("page", page);
        }
        return "catalogue";
    }
}
