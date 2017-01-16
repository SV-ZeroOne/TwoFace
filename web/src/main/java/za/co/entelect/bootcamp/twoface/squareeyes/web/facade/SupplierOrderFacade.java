package za.co.entelect.bootcamp.twoface.squareeyes.web.facade;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Order;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Supplier;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.IssueRepositoryIMP;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.Repository;

import java.util.Map;

public class SupplierOrderFacade
{
    void placeSupplierOrder(Issue issue, Order order, Supplier supplier){
        Repository issues = new IssueRepositoryIMP();
        issues.create(issue);
        
    }
}
