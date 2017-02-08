package za.co.entelect.bootcamp.twoface.squareeyes.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.customer.Customer;
import za.co.entelect.bootcamp.twoface.squareeyes.services.AuthenticationService;
import za.co.entelect.bootcamp.twoface.squareeyes.services.CatalogueService;
import za.co.entelect.bootcamp.twoface.squareeyes.services.ShoppingCartService;

import java.security.Principal;

/**
 * Created by mpho.mahase on 2017/02/01.
 */
@Controller
public class LoginContoller {

    AuthenticationService authenticationService;

    public LoginContoller(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String error403(ModelMap model) {
        return "403";
    }

    @RequestMapping("/homepage")
    public ModelAndView hello(ModelMap model, Principal principal) {

        String loggedInUserName = principal.getName();

        return new ModelAndView("hello", "userName", loggedInUserName);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";

    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@RequestParam(value = "email", required = true) String email,
                         @RequestParam(value = "password", required = true) String password,
                         @RequestParam(value = "title", required = true) String title,
                         @RequestParam(value = "firstName", required = true) String firstName,
                         @RequestParam(value = "surname", required = true) String surname,
            ModelMap model) {
        System.out.println("In the login method");

        Customer customer = new Customer(email, title, firstName, surname, "salt", password);
        authenticationService.createCustomer(customer);

        return "login";

    }

    @RequestMapping(value = "/loginError", method = RequestMethod.GET)
    public String loginError(ModelMap model) {
        model.addAttribute("error", "true");
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {
        return "homepage";
    }
}
