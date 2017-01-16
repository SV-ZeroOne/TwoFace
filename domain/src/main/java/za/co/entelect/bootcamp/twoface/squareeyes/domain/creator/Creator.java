package za.co.entelect.bootcamp.twoface.squareeyes.domain.creator;

/**
 * Created by mpho.mahase on 2017/01/14.
 */
public class Creator {

    private int creatorID;
    private String name;
    private String countryOfResidence;
    private String taxReference;
    private String emailAddress;

    public Creator(){

    }

    public Creator(int creatorID, String name, String countryOfResidence, String taxReference, String emailAddress){
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
