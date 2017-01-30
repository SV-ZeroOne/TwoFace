package za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.stock;

import org.springframework.stereotype.Repository;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.stock.Stock;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.RelationalRepository;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by quinton.weenink on 2017/01/19.
 */
@Repository
public class StockRepositoryIMP extends RelationalRepository<Stock> implements StockRepository{
}
