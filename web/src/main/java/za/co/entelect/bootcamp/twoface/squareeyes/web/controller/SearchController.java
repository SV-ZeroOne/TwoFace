package za.co.entelect.bootcamp.twoface.squareeyes.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.services.SearchService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by sean.vienings on 2017/01/31.
 */
@Controller
public class SearchController {

    List<Issue> list;

    private SearchService searchService;

    public SearchController(SearchService serchService) {
        this.searchService = searchService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView Search(@RequestParam(value = "search", required = false)
                                       String pSearchTerm, HttpServletRequest request, HttpServletResponse response) {
        Model model;
        list = searchService.SearchService(pSearchTerm);
        if (list.size() != 0)
        {
            //Catalog with new Issues
        }
        else
        {
            //Default Page
        }
        return null;
    }

}
