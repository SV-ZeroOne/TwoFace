package za.co.entelect.bootcamp.twoface.squareeyes.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import za.co.entelect.bootcamp.twoface.squareeyes.services.HomePageService;

/**
 * Created by sean.vienings on 2017/01/31.
 */
public class HomeController {

    private HomePageService homePageService;

    @Autowired
    public HomeController(HomePageService homePageService) {
        this.homePageService = homePageService;
    }
}
