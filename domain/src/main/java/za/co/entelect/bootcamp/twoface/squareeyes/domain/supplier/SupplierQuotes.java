package za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issues;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mpho.mahase on 2017/01/16.
 */

@Entity
@Table
public class SupplierQuotes {

    @Id
    @GeneratedValue
    @Column(name="QuoteID")
    private int quoteID;

    @ManyToOne
    @Column(name="IssueID")
    private Issues issue;

    @Column(name="SupplierID")
    private Suppliers suppliers;

    @Column(name="Price")
    private Double price;

    @Temporal(value = TemporalType.DATE)
    @Column(name="EffectiveDate")
    private Date effectiveDate;

    public SupplierQuotes(){

    }

    public SupplierQuotes(int QuoteID, Issues issue, Suppliers suppliers, Double price, Date effectiveDate){
        this.quoteID = QuoteID;
        this.issue = issue;
        this.price = price;
        this.suppliers = suppliers;
        this.effectiveDate = effectiveDate;
    }


    public int getQuoteID() {
        return quoteID;
    }

    public void setQuoteID(int quoteID) {
        quoteID = quoteID;
    }

    public Issues getIssue() {
        return issue;
    }

    public void setIssue(Issues issue) {
        this.issue = issue;
    }

    public Suppliers getSuppliers() {return suppliers;}

    public void setSuppliers(Suppliers suppliers) {this.suppliers = suppliers;}

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
