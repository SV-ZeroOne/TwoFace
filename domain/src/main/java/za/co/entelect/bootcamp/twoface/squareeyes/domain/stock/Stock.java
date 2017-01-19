package za.co.entelect.bootcamp.twoface.squareeyes.domain.stock;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issues;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by quinton.weenink on 2017/01/15.
 */

@Entity
@Table
public class Stock{

    @Id
    @GeneratedValue
    @Column(name="StockReferenceID")
    private int stockReferenceID;

    @ManyToOne
    @JoinColumn(name = "IssueID")
    private Issues issue;

    @Column(name="Condition", columnDefinition = "varchar(10)")
    private String condition;

    @Column(name="AvailableQty")
    private short availableQty;

    @Column(name="Price")
    private BigDecimal price;

    public Stock(){
    }
    public Stock(int stockReferenceID, Issues issue, String condition, short availableQty, BigDecimal price){
        this.stockReferenceID = stockReferenceID;
        this.issue = issue;
        this.condition = condition;
        this.availableQty = availableQty;
        this.price = price;
    }

    public int getStockReferenceID(){
        return this.stockReferenceID;
    }
    public void setStockReferenceID(int stockReferenceID){
        this.stockReferenceID = stockReferenceID;
    }

    public Issues getIssue(){
        return this.issue;
    }
    public void setIssue(Issues issue){
        this.issue = issue;
    }

    public String getCondition(){
        return this.condition;
    }
    public void setCondition(String condition){
        this.condition = condition;
    }

    public short getAvailableQty(){
        return this.availableQty;
    }
    public void setAvailableQty(short availableQty){
        this.availableQty = availableQty;
    }

    public BigDecimal getPrice(){
        return this.price;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }
}
