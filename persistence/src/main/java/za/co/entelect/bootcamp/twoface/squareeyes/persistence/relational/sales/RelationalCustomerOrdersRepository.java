package za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.sales;

import org.springframework.stereotype.Repository;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.customer.Customer;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.sale.CustomerOrder;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.RelationalRepository;

/**
 * Created by quinton.weenink on 2017/01/31.
 */
@Repository
public class RelationalCustomerOrdersRepository extends RelationalRepository<CustomerOrder> implements CustomerOrdersRepository {
}
