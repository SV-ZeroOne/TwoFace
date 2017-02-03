package za.co.entelect.bootcamp.twoface.squareeyes.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import za.co.entelect.bootcamp.twoface.squareeyes.services.ShoppingCartService;

import java.security.Principal;
import java.util.List;

/**
 * Created by quinton.weenink on 2017/02/02.
 */
@Controller
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping(value = "/shoppingcart", method = RequestMethod.POST)
    public String getProductPage(@RequestParam(value = "stockID", required = true, defaultValue="-1") int stockID,
                                 @RequestParam(value = "quantity", required = true, defaultValue="-1") int quantity,
                                 ModelMap modelMap, Principal principal){

        shoppingCartService.addToShoppingCart((short)quantity, principal.getName(), stockID);

        return "redirect:product?stock=" + stockID;
    }

    @RequestMapping(value = "/shoppingcart/remove", method = RequestMethod.POST)
    public String removeItem(@RequestParam(value = "stock", required = true) int stockID,
                                 ModelMap modelMap, Principal principal){

        shoppingCartService.removeFromShoppingCart(principal.getName(), stockID);

        return "catalogue";
    }

    @RequestMapping(value = "/shoppingcart/removeall", method = RequestMethod.POST)
    public String removeAllItem(ModelMap modelMap, Principal principal){

        shoppingCartService.removeAllFromShoppingCart(principal.getName());

        return "catalogue";
    }

    @RequestMapping(value = "/shoppingcart/increase", method = RequestMethod.POST)
    public String increaseQuantity(@RequestParam(value = "stock", required = true) int stockID,
                                 ModelMap modelMap, Principal principal){

        int flag = shoppingCartService.increaseQuantity(principal.getName(), stockID);

        if(flag == -1)
            return "Invailid quantity!";
        return "catalogue";
    }

    @RequestMapping(value = "/shoppingcart/decrease", method = RequestMethod.POST)
    public String decreaseQuantity(@RequestParam(value = "stock", required = true) int stockID,
                                 ModelMap modelMap, Principal principal){

        int flag = shoppingCartService.decreaseQuantity(principal.getName(), stockID);

        if(flag == -1)
            return "Invailid quantity!";
        return "catalogue";
    }
}
