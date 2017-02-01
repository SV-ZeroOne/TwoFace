package za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.customers;

import org.springframework.stereotype.Repository;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.customer.ShoppingCart;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.RelationalRepository;

/**
 * Created by quinton.weenink on 2017/01/31.
 */
@Repository
public class RelationalShoppingCartsRepository extends RelationalRepository<ShoppingCart> implements ShoppingCartsRepository {
}