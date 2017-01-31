package za.co.entelect.bootcamp.twoface.squareeyes.domain.customer;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.stock.Stock;

import javax.persistence.*;

/**
 * Created by quinton.weenink on 2017/01/31.
 */
@Entity
@Table(name="ShoppingCarts")
public class ShoppingCart {

    private Customer customer;
    private Stock stock;

    private int shoppingCartID;
    private short quantity;

    public ShoppingCart(){}

    public ShoppingCart(short quantity){
        this.quantity = quantity;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID")
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "StockID", referencedColumnName = "StockReferenceID")
    public Stock getStock() {
        return stock;
    }
    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Id
    @GeneratedValue
    @Column(name = "ShoppingCartID")
    public int getShoppingCartID() {
        return shoppingCartID;
    }
    public void setShoppingCartID(int shoppingCartID) {
        this.shoppingCartID = shoppingCartID;
    }

    @Column(name = "Quantity")
    public short getQuantity() {
        return quantity;
    }
    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }
}
