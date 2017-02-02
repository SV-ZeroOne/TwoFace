package za.co.entelect.bootcamp.twoface.squareeyes.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import za.co.entelect.bootcamp.twoface.squareeyes.services.ShoppingCartService;

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
                                 ModelMap modelMap){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        shoppingCartService.addToShoppingCart((short)quantity, auth.getName(), stockID);

        return "redirect:product?stock=" + stockID;
    }
}
