package za.co.entelect.bootcamp.twoface.squareeyes.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.services.CatalogueService;

import java.util.List;

/**
 * Created by sean.vienings on 2017/01/31.
 */
public class HomeController {

    public String HomeController(CatalogueService catalogueService) {
        return "homepage";
    }
}
