package za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.suppliers;

import org.springframework.stereotype.Repository;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.SupplierPayment;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.RelationalRepository;

/**
 * Created by quinton.weenink on 2017/01/19.
 */
@Repository
public class SupplierPaymentsRepositoryIMP extends RelationalRepository<SupplierPayment> implements SupplierPaymentsRepository{
}
