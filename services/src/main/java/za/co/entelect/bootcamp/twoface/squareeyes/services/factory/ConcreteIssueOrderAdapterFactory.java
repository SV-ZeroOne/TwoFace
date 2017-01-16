package za.co.entelect.bootcamp.twoface.squareeyes.services.factory;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Order;
import za.co.entelect.bootcamp.twoface.squareeyes.services.AbstractAdapter;
import za.co.entelect.bootcamp.twoface.squareeyes.services.supplier.IssueOrderAdapter;

/**
 * Created by quinton.weenink on 2017/01/16.
 */
public class ConcreteIssueOrderAdapterFactory {
    AbstractAdapter abstractAdapter(Issue issue, Order order)
    {
        return new IssueOrderAdapter(issue, order);
    }
}
