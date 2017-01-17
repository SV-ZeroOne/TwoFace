package za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;

import java.util.Date;

/**
 * Created by mpho.mahase on 2017/01/16.
 */
public class SupplierQuotes {

    private int QuoteID;
    private Issue issue;
    private Double price;
    private Date effectiveDate;

    public SupplierQuotes(int QuoteID, Issue issue, Double price, Date effectiveDate){
        this.QuoteID = QuoteID;
        this.issue = issue;
        this.price = price;
        this.effectiveDate = effectiveDate;
    }


    public int getQuoteID() {
        return QuoteID;
    }

    public void setQuoteID(int quoteID) {
        QuoteID = quoteID;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


}
