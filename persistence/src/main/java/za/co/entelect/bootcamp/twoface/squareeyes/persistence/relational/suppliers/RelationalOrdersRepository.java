package za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.suppliers;

import org.springframework.stereotype.Repository;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Order;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.RelationalRepository;

/**
 * Created by sean.vienings on 2017/01/16.
 */
@Repository
public class RelationalOrdersRepository extends RelationalRepository<Order> implements OrdersRepository {
}
