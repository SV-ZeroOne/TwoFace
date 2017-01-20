package za.co.entelect.bootcamp.twoface.squareeyes.domain.creator;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mpho.mahase on 2017/01/14.
 */
@Entity
@Table(name="Creators")
public class Creator {

    List<ComicCreator> comicCreators = new ArrayList<ComicCreator>();

    private int creatorID;
    private String name;
    private String countryOfResidence;
    private String taxReference;
    private String emailAddress;

    public Creator(){}

    public Creator(String name, String countryOfResidence, String taxReference, String emailAddress){
        this.name = name;
        this.countryOfResidence = countryOfResidence;
        this.taxReference = taxReference;
        this.emailAddress = emailAddress;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.creators", cascade=CascadeType.ALL)
    public List<ComicCreator> getComicCreators() {
        return comicCreators;
    }
    public void setComicCreators(List<ComicCreator> comicCreators) {
        this.comicCreators = comicCreators;
    }
    public ComicCreator getComicCreators(int id){ return comicCreators.get(id); }
    public void addComicCreator(ComicCreator comicCreator){ this.comicCreators.add(comicCreator); }

    @Id
    @GeneratedValue
    @Column(name="CreatorID")
    public int getCreatorID() {
        return creatorID;
    }
    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }

    @Column(name="Name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name="CountryOfResidence")
    public String getCountryOfResidence() {
        return countryOfResidence;
    }
    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    @Column(name="TaxReferenceNumber", columnDefinition="varbinary(512)")
    public String getTaxReference() {
        return taxReference;
    }
    public void setTaxReference(String taxReference) {
        this.taxReference = taxReference;
    }

    @Column(name="EmailAddress")
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
