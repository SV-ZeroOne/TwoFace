package za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.suppliers;


import org.springframework.stereotype.Repository;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Supplier;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.RelationalRepository;

/**
 * Created by sean.vienings on 2017/01/16.
 */
@Repository
public class SuppliersRepositoryIMP extends RelationalRepository<Supplier> implements SuppliersRepository {
}
