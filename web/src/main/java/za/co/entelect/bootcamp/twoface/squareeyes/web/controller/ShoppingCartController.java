package za.co.entelect.bootcamp.twoface.squareeyes.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.customer.ShoppingCart;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.stock.Stock;
import za.co.entelect.bootcamp.twoface.squareeyes.services.ProductService;
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
    public String getProductPage(@RequestParam(value = "stockID", required = false, defaultValue="-1") int stockID,
                                 @RequestParam(value = "stockID", required = false, defaultValue="-1") int quantity,
                                 @RequestParam(value = "stockID", required = false, defaultValue="-1") int userID,
                                 ModelMap modelMap){
        shoppingCartService.addToShoppingCart((short)1, 1, stockID);

        return "product?stock=" + stockID;
    }
}
