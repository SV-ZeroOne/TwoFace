package za.co.entelect.bootcamp.twoface.squareeyes.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.stock.Stock;
import za.co.entelect.bootcamp.twoface.squareeyes.services.CatalogueService;
import za.co.entelect.bootcamp.twoface.squareeyes.services.ProductService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by quinton.weenink on 2017/02/01.
 */
@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String getProductPage(@RequestParam(value = "issue", required = false, defaultValue="-1") int issueID,
                           @RequestParam(value = "stock", required = false, defaultValue="-1") int stockID,
                           ModelMap modelMap){
        Issue issue = productService.getIssue(issueID);
        Stock stock = productService.getStock(stockID, issueID);
        List<Stock> stockList = productService.getStockList(issueID);

        modelMap.addAttribute("issue", issue);
        modelMap.addAttribute("stock", stock);
        modelMap.addAttribute("stockList", stockList);

        return "product";
    }
}
