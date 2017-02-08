package za.co.entelect.bootcamp.twoface.squareeyes.domain.stock;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by quinton.weenink on 2017/01/15.
 */

@Entity
@Table(name="Stock")
public class Stock{

    private Issue issue;

    private int stockReferenceID;
    private String condition;
    private short availableQty;
    private BigDecimal price;

    public Stock(){}

    public Stock(String condition, short availableQty, BigDecimal price){
        this.condition = condition;
        this.availableQty = availableQty;
        this.price = price;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "IssueID", referencedColumnName = "IssueID")
    public Issue getIssue(){
        return this.issue;
    }
    public void setIssue(Issue issue){
        this.issue = issue;
    }

    @Id
    @GeneratedValue
    @Column(name="StockReferenceID")
    public int getStockReferenceID(){
        return this.stockReferenceID;
    }
    public void setStockReferenceID(int stockReferenceID){
        this.stockReferenceID = stockReferenceID;
    }

    @Column(name="Condition", columnDefinition = "varchar(10)")
    public String getCondition(){
        return this.condition;
    }
    public void setCondition(String condition){
        this.condition = condition;
    }

    @Column(name="AvailableQty")
    public short getAvailableQty(){
        return this.availableQty;
    }
    public void setAvailableQty(short availableQty){
        this.availableQty = availableQty;
    }

    @Column(name="Price")
    public BigDecimal getPrice(){
        return this.price;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }
}
