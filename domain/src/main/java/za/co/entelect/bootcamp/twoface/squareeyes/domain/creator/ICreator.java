package za.co.entelect.bootcamp.twoface.squareeyes.domain.creator;

/**
 * Created by quinton.weenink on 2017/01/15.
 */
interface ICreator {
    int getCreatorID();

    void setCreatorID(int creatorID);

    String getName();

    void setName(String name);

    String getCountryOfResidence();

    void setCountryOfResidence(String countryOfResidence);

    String getTaxReference();

    void setTaxReference(String taxReference);

    String getEmailAddress();

    void setEmailAddress(String emailAddress);
}
