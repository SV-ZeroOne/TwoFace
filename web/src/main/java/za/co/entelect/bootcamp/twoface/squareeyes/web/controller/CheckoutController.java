package za.co.entelect.bootcamp.twoface.squareeyes.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import za.co.entelect.bootcamp.twoface.squareeyes.services.ShoppingCartService;

import java.security.Principal;

/**
 * Created by sean.vienings on 2017/01/31.
 *
 */
@Controller
public class CheckoutController {

    private ShoppingCartService shoppingCartService;

    public CheckoutController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String PopulateCheckout(
            @RequestParam(value = "cart", required = false, defaultValue = "-1") int cartID,
            @RequestParam(value = "customer",  required = false, defaultValue = "-1") int customerID,
            ModelMap modelMap, Principal principal){

        modelMap.addAttribute("customer", shoppingCartService.getShoppingCart(principal.getName()));
        modelMap.addAttribute("shoppingCart", shoppingCartService.getShoppingCart(principal.getName()));
        return "checkout";
    }
}
