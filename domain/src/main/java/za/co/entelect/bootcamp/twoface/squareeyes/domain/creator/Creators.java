package za.co.entelect.bootcamp.twoface.squareeyes.domain.creator;


import javax.persistence.*;

/**
 * Created by mpho.mahase on 2017/01/14.
 */
@Entity
@Table
public class Creators {

    @Id
    @GeneratedValue
    @Column(name="CreatorID")
    private int creatorID;

    @Column(name="Name")
    private String name;

    @Column(name="CountryOfResidence")
    private String countryOfResidence;

    @Column(name="TaxReferenceNumber")
    private String taxReference;

    @Column(name="EmailAddress")
    private String emailAddress;

    public Creators(){

    }

    public Creators(int creatorID, String name, String countryOfResidence, String taxReference, String emailAddress){
        this.creatorID = creatorID;
        this.name = name;
        this.countryOfResidence = countryOfResidence;
        this.taxReference = taxReference;
        this.emailAddress = emailAddress;
    }


    public int getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public String getTaxReference() {
        return taxReference;
    }

    public void setTaxReference(String taxReference) {
        this.taxReference = taxReference;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}