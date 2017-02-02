package za.co.entelect.bootcamp.twoface.squareeyes.services;

import org.springframework.beans.factory.annotation.Autowired;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.customer.ShoppingCart;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.customers.CustomersRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.customers.ShoppingCartsRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.IssuesRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.stock.StockRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quinton.weenink on 2017/02/02.
 */
public class ShoppingCartService {
    private ShoppingCartsRepository shoppingCartsRepository;
    private CustomersRepository customersRepository;
    private StockRepository stockRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartsRepository shoppingCartsRepository, CustomersRepository customersRepository, StockRepository stockRepository) {
        this.shoppingCartsRepository = shoppingCartsRepository;
        this.customersRepository = customersRepository;
        this.stockRepository = stockRepository;
    }

    public List<ShoppingCart> getShoppingCart(int customerID)
    {
        return shoppingCartsRepository.search("customer.customerID", customerID);
    }

    public ShoppingCart addToShoppingCart(short quantity, String email, int stockID)
    {
        ShoppingCart shoppingCart = new ShoppingCart(quantity);
        shoppingCart.setCustomer(customersRepository.search("email", "quinton@gmail.com").get(0));
        shoppingCart.setStock(stockRepository.find(stockID));
        return shoppingCartsRepository.create(shoppingCart);
    }

    public ShoppingCart removeFromShoppingCart(short quantity, String email, int stockID)
    {
        ShoppingCart shoppingCart = new ShoppingCart(quantity);
        shoppingCart.setCustomer(customersRepository.search("email", "quinton@gmail.com").get(0));
        shoppingCart.setStock(stockRepository.find(stockID));
        return shoppingCartsRepository.create(shoppingCart);
    }

    public void setIssuesRepository(ShoppingCartsRepository issuesRepository) {
        this.shoppingCartsRepository = shoppingCartsRepository;
    }

    public ShoppingCartsRepository getIssuesRepository() {
        return shoppingCartsRepository;
    }
}
