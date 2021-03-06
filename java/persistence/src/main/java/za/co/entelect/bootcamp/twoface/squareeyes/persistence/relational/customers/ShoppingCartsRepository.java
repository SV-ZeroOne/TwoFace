package za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.customers;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.customer.ShoppingCart;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.generic.Repository;

/**
 * Created by quinton.weenink on 2017/01/31.
 */
public interface ShoppingCartsRepository extends Repository<ShoppingCart>{
    public void increaseQty(Object id);
    public void decreaseQty(Object id);
}
