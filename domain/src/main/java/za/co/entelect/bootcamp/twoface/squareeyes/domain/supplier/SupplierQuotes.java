package za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issues;

import javax.persistence.*;
import java.math.BigDecimal;
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
    @JoinColumn(name = "IssueID")
    private Issues issue;

    @ManyToOne
    @JoinColumn(name = "SupplierID")
    private Suppliers suppliers;

    @Column(name="Price")
    private BigDecimal price;

    @Temporal(value = TemporalType.DATE)
    @Column(name="EffectiveDate")
    private Date effectiveDate;

    public SupplierQuotes(){

    }

    public SupplierQuotes(int QuoteID, Issues issue, Suppliers suppliers, BigDecimal price, Date effectiveDate){
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


}
