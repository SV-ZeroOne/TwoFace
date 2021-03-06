package za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.customers;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.customer.ShoppingCart;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.RelationalRepository;

import javax.persistence.Query;

/**
 * Created by quinton.weenink on 2017/01/31.
 */
@Repository
public class RelationalShoppingCartsRepository extends RelationalRepository<ShoppingCart> implements ShoppingCartsRepository {

    @Transactional
    public void increaseQty(Object id){
        Query query = this.entityManager.createQuery("update " + type.getSimpleName() +
                " set quantity"+
                " = quantity+1 where shoppingCartID = " + id);
        query.executeUpdate();
    }

    @Transactional
    public void decreaseQty(Object id){
        Query query = this.entityManager.createQuery("update " + type.getSimpleName() +
                " set quantity"+
                " = quantity-1 where shoppingCartID = " + id);
        query.executeUpdate();
    }

}
