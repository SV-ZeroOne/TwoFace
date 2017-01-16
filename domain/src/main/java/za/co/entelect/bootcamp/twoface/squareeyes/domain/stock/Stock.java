package za.co.entelect.bootcamp.twoface.squareeyes.domain.stock;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.comic.IIssue;

import java.math.BigDecimal;

/**
 * Created by quinton.weenink on 2017/01/15.
 */
public class Stock implements IStock{
    private int stockReferenceID;
    private IIssue issue;
    private String condition;
    private int availableQty;
    private BigDecimal price;

    public Stock(){
    }
    public Stock(int stockReferenceID, IIssue issue, String condition, int availableQty, BigDecimal price){
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

    public IIssue getIssue(){
        return this.issue;
    }
    public void setIssue(IIssue issue){
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
