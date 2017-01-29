package za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues;

import org.springframework.stereotype.Repository;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.RelationalRepository;


/**
 * Created by sean.vienings on 2017/01/16.
 */
@Repository
public class IssuesRepositoryIMP extends RelationalRepository<Issue> implements IssuesRepository {

}
