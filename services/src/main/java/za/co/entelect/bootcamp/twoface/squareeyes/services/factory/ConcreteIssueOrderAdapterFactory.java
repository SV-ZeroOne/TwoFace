package za.co.entelect.bootcamp.twoface.squareeyes.services.factory;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Order;
import za.co.entelect.bootcamp.twoface.squareeyes.services.AbstractAdapter;
import za.co.entelect.bootcamp.twoface.squareeyes.services.supplier.IssueOrderAdapter;

/**
 * Created by quinton.weenink on 2017/01/16.
 */
public class ConcreteIssueOrderAdapterFactory {
    Issue issue;
    Order order;

    public ConcreteIssueOrderAdapterFactory(Issue issue, Order order)
    {
        this.issue = issue;
        this.order = order;
    }

    public AbstractAdapter createAdapter()
    {
        return new IssueOrderAdapter(issue, order);
    }
}
