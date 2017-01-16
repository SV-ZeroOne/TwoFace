package za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier;

import java.util.Date;

/**
 * Created by mpho.mahase on 2017/01/16.
 */
public class SupplierQuotes {

    private int QuoteID;
    private int issueID;
    private Double price;
    private Date effectiveDate;

    public SupplierQuotes(int QuoteID, int issueID, Double price, Date effectiveDate){
        this.QuoteID = QuoteID;
        this.issueID = issueID;
        this.price = price;
        this.effectiveDate = effectiveDate;
    }


    public int getQuoteID() {
        return QuoteID;
    }

    public void setQuoteID(int quoteID) {
        QuoteID = quoteID;
    }

    public int getIssueID() {
        return issueID;
    }

    public void setIssueID(int issueID) {
        this.issueID = issueID;
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
