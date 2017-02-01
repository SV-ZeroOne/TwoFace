package za.co.entelect.bootcamp.twoface.squareeyes.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.services.CatalogueService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by sean.vienings on 2017/01/31.
 */
public class HomeController {

    private CatalogueService catalogueService;

    @RequestMapping(value = "/Catalogue", method = RequestMethod.GET)
    public String SayHello(ModelMap modelMap){
        modelMap.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
        return "Catalogue";
    }
/*
    @Autowired
    public String HomeController(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
        List<Issue> list;
        list = catalogueService.HomePage();
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("list",list);
        return "homepage";
    }*/
}
