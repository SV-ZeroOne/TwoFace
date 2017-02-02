package za.co.entelect.bootcamp.twoface.squareeyes.services;

import org.springframework.beans.factory.annotation.Autowired;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.customer.Customer;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.customer.ShoppingCart;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.customers.CustomersRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.customers.ShoppingCartsRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.stock.StockRepository;

import java.util.List;

/**
 * Created by mpho.mahase on 2017/02/01.
 */
public class AuthenticationService {
    CustomersRepository customersRepository;

    @Autowired
    public AuthenticationService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    public Customer getShoppingCart(int customerID)
    {
        return customersRepository.find(customerID);
    }

}
