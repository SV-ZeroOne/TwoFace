package za.co.entelect.bootcamp.twoface.squareeyes.services;

import org.springframework.beans.factory.annotation.Autowired;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.customer.ShoppingCart;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.customers.ShoppingCartsRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quinton.weenink on 2017/02/02.
 */
public class ShoppingCartService {
    private ShoppingCartsRepository shoppingCartsRepository;
    private int

    @Autowired
    public ShoppingCartService(ShoppingCartsRepository shoppingCartsRepository) {
        this.shoppingCartsRepository = shoppingCartsRepository;
    }

    public List<ShoppingCart> getCataloguePage(int customerID)
    {
        return shoppingCartsRepository.search("customer.customerID", customerID);
    }

    public void setIssuesRepository(ShoppingCartsRepository issuesRepository) {
        this.shoppingCartsRepository = shoppingCartsRepository;
    }

    public ShoppingCartsRepository getIssuesRepository() {
        return shoppingCartsRepository;
    }
}
