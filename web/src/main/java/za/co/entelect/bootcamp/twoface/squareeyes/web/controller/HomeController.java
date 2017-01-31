package za.co.entelect.bootcamp.twoface.squareeyes.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.services.HomePageService;

import java.util.List;

/**
 * Created by sean.vienings on 2017/01/31.
 */
public class HomeController {

    private HomePageService homePageService;

    @Autowired
    public String HomeController(HomePageService homePageService) {
        this.homePageService = homePageService;
        List<Issue> list;
        list = homePageService.HomePage();
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("list",list);
        return "homepage";
    }
}
