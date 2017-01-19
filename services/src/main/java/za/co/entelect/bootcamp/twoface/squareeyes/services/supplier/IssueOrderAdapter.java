package za.co.entelect.bootcamp.twoface.squareeyes.services.supplier;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issues;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Orders;
import za.co.entelect.bootcamp.twoface.squareeyes.services.AbstractAdapter;

/**
 * Created by mpho.mahase on 2017/01/16.
 */
public class IssueOrderAdapter extends IssueOrderDTO implements AbstractAdapter{
    private Issues issue;
    private Orders orders;

    public IssueOrderAdapter(Issues issue, Orders orders){
        this.issue = issue;
        this.orders = orders;
    }

    public int getIssueID() {
        return issue.getID();
    }

    public String getSeriesNumber() {
        return String.valueOf(issue.getSeriesNumber());
    }

    public int getQuantity() {
        return orders.getQty();
    }
}
