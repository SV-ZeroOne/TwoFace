package za.co.entelect.bootcamp.twoface.squareeyes.web.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sean.vienings on 2017/01/31.
 */
public class CatalogueController {

    @RequestMapping(value = "/Catalogue", method = RequestMethod.GET)
    public String SayHello(ModelMap modelMap){
        modelMap.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
        return "Catalogue";
    }
}
