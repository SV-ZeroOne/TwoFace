package za.co.entelect.bootcamp.twoface.squareeyes.services.factory;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issues;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Orders;
import za.co.entelect.bootcamp.twoface.squareeyes.services.AbstractAdapter;
import za.co.entelect.bootcamp.twoface.squareeyes.services.supplier.IssueOrderAdapter;

/**
 * Created by quinton.weenink on 2017/01/16.
 */
public class ConcreteIssueOrderAdapterFactory {
    Issues issue;
    Orders orders;

    public ConcreteIssueOrderAdapterFactory(Issues issue, Orders orders)
    {
        this.issue = issue;
        this.orders = orders;
    }

    public AbstractAdapter createAdapter()
    {
        return new IssueOrderAdapter(issue, orders);
    }
}
