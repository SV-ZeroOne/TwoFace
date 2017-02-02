package za.co.entelect.bootcamp.twoface.squareeyes.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.services.CatalogueService;
import za.co.entelect.bootcamp.twoface.squareeyes.services.SearchService;
import za.co.entelect.bootcamp.twoface.squareeyes.services.ShoppingCartService;

import java.util.List;

/**
 * Created by sean.vienings on 2017/01/31.
 */
@Controller
public class CatalogueController {

    private CatalogueService catalogueService;
    private ShoppingCartService shoppingCartService;

    public CatalogueController(CatalogueService catalogueService, ShoppingCartService shoppingCartService) {
        this.catalogueService = catalogueService;
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping(value = "/catalogue", method = RequestMethod.GET)
    public String SayHello(@RequestParam(value = "search", required = false, defaultValue = "") String search,
                           @RequestParam(value = "page",  required = false, defaultValue = "1") int page,
                           ModelMap modelMap){
        List<Issue> list;
        if (search != "")
        {
            System.out.println(search);
            list = catalogueService.SearchService(search, page);
        }
        else
        {
            System.out.println("No Search");
            list = catalogueService.getCataloguePage(page);
        }
        modelMap.addAttribute("list", list);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("shoppingCart", shoppingCartService.getShoppingCart(1));
        modelMap.addAttribute("search", search);
        return "catalogue";
    }
}
