package za.co.entelect.bootcamp.twoface.squareeyes.domain.stock;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by quinton.weenink on 2017/01/15.
 */

@javax.persistence.Entity
@Table
public class Stock{

    @Id
    @GeneratedValue
    private int stockReferenceID;

    @OneToOne
    private Issue issue;

    @Column
    private String condition;

    @Column
    private int availableQty;

    @Column
    private BigDecimal price;

    public Stock(){
    }
    public Stock(int stockReferenceID, Issue issue, String condition, int availableQty, BigDecimal price){
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

    public Issue getIssue(){
        return this.issue;
    }
    public void setIssue(Issue issue){
        this.issue = issue;
    }

    public String getCondition(){
        return this.condition;
    }
    public void setCondition(String condition){
        this.condition = condition;
    }

    public int getAvailableQty(){
        return this.availableQty;
    }
    public void setAvailableQty(int availableQty){
        this.availableQty = availableQty;
    }

    public BigDecimal getPrice(){
        return this.price;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }
}
