package za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.customers;

import org.springframework.stereotype.Repository;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.customer.Customer;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.RelationalRepository;

/**
 * Created by quinton.weenink on 2017/01/31.
 */
@Repository
public class RelationalCustomersRepository extends RelationalRepository<Customer> implements CustomersRepository {
}
