package za.co.entelect.bootcamp.twoface.squareeyes.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.customer.ShoppingCart;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.services.CatalogueService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import za.co.entelect.bootcamp.twoface.squareeyes.services.ShoppingCartService;

import java.security.Principal;
import java.util.List;

/**
 * Created by sean.vienings on 2017/01/31.
 */
@Controller
public class HomeController {

    private CatalogueService catalogueService;
    private ShoppingCartService shoppingCartService;

    public HomeController(CatalogueService catalogueService, ShoppingCartService shoppingCartService) {
        this.catalogueService = catalogueService;
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public String getHomepage(ModelMap modelMap, Principal principal){
        List<Issue> list;
        list = catalogueService.getCataloguePage(1);
        modelMap.addAttribute("list", list);
        modelMap.addAttribute("page", 1);
        if(principal != null)
            modelMap.addAttribute("shoppingCart", shoppingCartService.getShoppingCart(principal.getName()));
        return "homepage";
    }

}
