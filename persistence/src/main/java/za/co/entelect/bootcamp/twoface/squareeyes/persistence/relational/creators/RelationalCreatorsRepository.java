package za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.creators;

import org.springframework.stereotype.Repository;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.creator.Creator;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.RelationalRepository;

/**
 * Created by quinton.weenink on 2017/01/19.
 */
@Repository
public class RelationalCreatorsRepository extends RelationalRepository<Creator> implements CreatorsRepository{
}
