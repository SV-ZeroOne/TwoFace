package za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mpho.mahase on 2017/01/16.
 */

@javax.persistence.Entity
@Table
public class SupplierQuotes {

    @Id
    @GeneratedValue
    private int QuoteID;

    @OneToOne
    private Issue issue;

    @Column
    private Double price;

    @Temporal(value = TemporalType.DATE)
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
