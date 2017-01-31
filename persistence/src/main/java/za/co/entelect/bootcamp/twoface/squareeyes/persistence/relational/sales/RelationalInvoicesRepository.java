package za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.sales;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.sale.Invoice;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.generic.Repository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.RelationalRepository;

/**
 * Created by quinton.weenink on 2017/01/31.
 */
public class RelationalInvoicesRepository extends RelationalRepository<Invoice> implements InvoicesRepository{
}
