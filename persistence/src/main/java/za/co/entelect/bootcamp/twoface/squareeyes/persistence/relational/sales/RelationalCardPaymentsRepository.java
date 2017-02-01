package za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.sales;

import org.springframework.stereotype.Repository;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.sale.CardPayment;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.RelationalRepository;

/**
 * Created by quinton.weenink on 2017/01/31.
 */
@Repository
public class RelationalCardPaymentsRepository extends RelationalRepository<CardPayment> implements CardPaymentsRepository {
}
