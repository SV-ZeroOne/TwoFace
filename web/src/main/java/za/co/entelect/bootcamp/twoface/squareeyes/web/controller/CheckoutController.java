package za.co.entelect.bootcamp.twoface.squareeyes.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by sean.vienings on 2017/01/31.
 *
 */
@Controller
public class CheckoutController {

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String PopulateCheckout(@RequestParam(value = "search", required = false, defaultValue = "") String search,
                           @RequestParam(value = "page",  required = false, defaultValue = "1") int page,
                           ModelMap modelMap){
        return "checkout";
    }
}
