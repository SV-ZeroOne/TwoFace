package za.co.entelect.bootcamp.twoface.squareeyes.services.supplier;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Order;
import za.co.entelect.bootcamp.twoface.squareeyes.services.AbstractAdapter;

/**
 * Created by mpho.mahase on 2017/01/16.
 */
public class IssueOrderAdapter extends IssueOrderDTO implements AbstractAdapter{
    private Issue issue;
    private Order order;

    public IssueOrderAdapter(Issue issue, Order order){
        this.issue = issue;
        this.order = order;
    }

    public int getIssueID() {
        return issue.getID();
    }

    public String getSeriesNumber() {
        return issue.getSeriesNumber().toString();
    }

    public int getQuantity() {
        return order.getQtyOrdered(issue);
    }
}
