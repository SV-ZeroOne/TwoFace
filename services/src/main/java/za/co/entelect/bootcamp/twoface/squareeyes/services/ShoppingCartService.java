package za.co.entelect.bootcamp.twoface.squareeyes.services;

import org.springframework.beans.factory.annotation.Autowired;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.customer.Customer;
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

    public List<ShoppingCart> getShoppingCart(String email)
    {
        return shoppingCartsRepository.search("customer.email", email);
    }

    public ShoppingCart addToShoppingCart(short quantity, String email, int stockID)
    {
        ShoppingCart shoppingCart = new ShoppingCart(quantity);
        shoppingCart.setCustomer(customersRepository.search("email", email).get(0));
        shoppingCart.setStock(stockRepository.find(stockID));
        return shoppingCartsRepository.create(shoppingCart);
    }

    public void removeFromShoppingCart(String email, int stockID)
    {
        List<ShoppingCart> shoppingCartList = shoppingCartsRepository.search("customer.email", email);
        for (ShoppingCart sc:shoppingCartList) {
            if(sc.getStock().getStockReferenceID() == stockID) {
                shoppingCartsRepository.delete(sc.getShoppingCartID());
                return;
            }
        }
    }

    public int decreaseQuantity(String email, int stockID)
    {
        List<ShoppingCart> shoppingCartList = shoppingCartsRepository.search("customer.email", email);
        for (ShoppingCart sc:shoppingCartList) {
            if(sc.getStock().getStockReferenceID() == stockID) {
                if(sc.getQuantity() != 1) {
                    shoppingCartsRepository.decreaseQty(sc.getShoppingCartID());
                    return sc.getQuantity();
                }
                return -1;
            }
        }
        return -1;
    }

    public int increaseQuantity(String email, int stockID)
    {
        List<ShoppingCart> shoppingCartList = shoppingCartsRepository.search("customer.email", email);
        for (ShoppingCart sc:shoppingCartList) {
            if(sc.getStock().getStockReferenceID() == stockID) {
                if(sc.getQuantity() != sc.getStock().getAvailableQty()) {
                    shoppingCartsRepository.increaseQty(sc.getShoppingCartID());
                    return sc.getQuantity();
                }
                return -1;
            }
        }
        return -1;
    }

    public void setIssuesRepository(ShoppingCartsRepository issuesRepository) {
        this.shoppingCartsRepository = shoppingCartsRepository;
    }

    public ShoppingCartsRepository getIssuesRepository() {
        return shoppingCartsRepository;
    }
}
