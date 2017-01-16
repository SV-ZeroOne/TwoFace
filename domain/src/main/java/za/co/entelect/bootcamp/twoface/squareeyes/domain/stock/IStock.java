package za.co.entelect.bootcamp.twoface.squareeyes.domain.stock;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.comic.IIssue;

import java.math.BigDecimal;

/**
 * Created by quinton.weenink on 2017/01/15.
 */
interface IStock {
    int getStockReferenceID();
    void setStockReferenceID(int stockReferenceID);

    IIssue getIssue();
    void setIssue(IIssue issue);

    String getCondition();
    void setCondition(String condition);

    int getAvailableQty();
    void setAvailableQty(int availableQty);

    BigDecimal getPrice();
    void setPrice(BigDecimal price);
}
