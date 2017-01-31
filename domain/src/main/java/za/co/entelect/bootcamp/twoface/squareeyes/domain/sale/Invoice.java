package za.co.entelect.bootcamp.twoface.squareeyes.domain.sale;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.stock.Stock;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.sale.CustomerOrder;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by quinton.weenink on 2017/01/31.
 */
@Entity
@Table(name="Invoices")
public class Invoice {

    private CustomerOrder order;
    private Stock stock;

    private int invoiceID;
    private short quantity;
    private BigDecimal price;

    public Invoice(){}

    public Invoice(short quantity, BigDecimal price){
        this.quantity = quantity;
        this.price = price;
    }

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "OrderID", referencedColumnName = "CustomerOrderID")
    public CustomerOrder getOrder() {
        return order;
    }
    public void setOrder(CustomerOrder order) {
        this.order = order;
    }

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "StockID", referencedColumnName = "StockReferenceID")
    public Stock getStock() {
        return stock;
    }
    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Id
    @GeneratedValue
    @Column(name="InvoiceID")
    public int getInvoiceID() {
        return invoiceID;
    }

    @Column(name="Quantity")
    public short getQuantity() {
        return quantity;
    }
    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    @Column(name="Price")
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}