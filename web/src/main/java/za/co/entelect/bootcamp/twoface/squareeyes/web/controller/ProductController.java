package za.co.entelect.bootcamp.twoface.squareeyes.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.services.CatalogueService;

import java.util.List;

/**
 * Created by quinton.weenink on 2017/02/01.
 */
@Controller
public class ProductController {

    private CatalogueService productService;

    public ProductController(CatalogueService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String SayHello(@RequestParam(value = "issue", required = false, defaultValue = "") String search,
                           @RequestParam(value = "stock",  required = false, defaultValue = "1") int page,
                           ModelMap modelMap){
        List<Issue> list = productService.getCataloguePage(page);
        modelMap.addAttribute("issue", issue);
        modelMap.addAttribute("page", page);
        return "product";
    }
}
