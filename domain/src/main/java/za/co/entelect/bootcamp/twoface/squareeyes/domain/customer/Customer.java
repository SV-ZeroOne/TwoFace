package za.co.entelect.bootcamp.twoface.squareeyes.domain.customer;

import javax.persistence.*;

/**
 * Created by quinton.weenink on 2017/01/31.
 */
@Entity
@Table(name="Customers")
public class Customer {

    private int customerID;
    private String email;
    private String title;
    private String firstName;
    private String surname;
    private String salt;
    private String passwordHash;

    public Customer() {}

    public Customer(String email, String title, String firstName, String surname, String salt, String passwordHash){
        this.email = email;
        this.title = title;
        this.firstName = firstName;
        this.surname = surname;
        this.salt = salt;
        this.passwordHash = passwordHash;
    }

    public Customer(String email, String passwordHash){
        this.email = email;
        this.passwordHash = passwordHash;
    }

    @Id
    @GeneratedValue
    @Column(name = "CustomerID")
    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    @Column(name = "Email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "Title")
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "FirstName")
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "Surname")
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "Salt")
    public String getSalt() {
        return salt;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Column(name = "PasswordHash")
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

}
