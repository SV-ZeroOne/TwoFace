package za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mpho.mahase on 2017/01/16.
 */

@Entity
@Table
public class SupplierQuote {

    @Id
    @GeneratedValue
    private int QuoteID;

    @ManyToOne
    private Issue issue;

    @ManyToOne
    private Supplier supplier;

    @Column
    private Double price;

    @Temporal(value = TemporalType.DATE)
    private Date effectiveDate;

    public SupplierQuote(int QuoteID, Issue issue, Supplier supplier, Double price, Date effectiveDate){
        this.QuoteID = QuoteID;
        this.issue = issue;
        this.price = price;
        this.supplier = supplier;
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

    public Supplier getSupplier() {return supplier;}

    public void setSupplier(Supplier supplier) {this.supplier = supplier;}

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
