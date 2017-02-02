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

    @RequestMapping("/search")
    public String Search(ModelMap modelMap, @RequestParam(value = "search", required = false)
            String pSearchTerm, HttpServletRequest request, HttpServletResponse response) {
        Model model;
        list = searchService.SearchService(pSearchTerm);
        System.out.println("------------------------------------");
        System.out.println("Here!!!!! " + pSearchTerm);
        System.out.println("------------------------------------");
        if (list.size() != 0) {
            modelMap.addAttribute("list", list);
            return "catalogue";
        } else {
            return "Item not Found";
        }




/*        list = searchService.SearchService(pSearchTerm);
//        System.out.println(list.size());
//        if (list.size() != 0)
//        {
//            modelMap.addAttribute("list", list);
//            return "catalogue";
//        }
//        else
//        {
//            return "catalogue";
//        }


    }
*/
    }
}
