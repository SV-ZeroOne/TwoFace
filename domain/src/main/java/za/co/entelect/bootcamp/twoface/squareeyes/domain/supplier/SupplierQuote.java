package za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by mpho.mahase on 2017/01/16.
 */

@Entity
@Table(name="SupplierQuotes")
public class SupplierQuote {

    private Issue issue;
    private Supplier supplier;

    private int quoteID;
    private BigDecimal price;
    private Date effectiveDate;

    public SupplierQuote(){}

    public SupplierQuote(BigDecimal price, Date effectiveDate){
        this.price = price;
        this.effectiveDate = effectiveDate;
    }

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "IssueID")
    public Issue getIssue() {
        return issue;
    }
    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "SupplierID")
    public Supplier getSupplier() {return supplier;}
    public void setSupplier(Supplier supplier) {this.supplier = supplier;}

    @Id
    @GeneratedValue
    @Column(name="QuoteID")
    public int getQuoteID() {
        return quoteID;
    }
    public void setQuoteID(int quoteID) {
        this.quoteID = quoteID;
    }

    @Column(name="Price")
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name="EffectiveDate")
    public Date getEffectiveDate() {
        return effectiveDate;
    }
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

}
