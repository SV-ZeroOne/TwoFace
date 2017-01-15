package za.co.entelect.bootcamp.twoface.squareeyes.domain.creator;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.comic.Issue;

/**
 * Created by mpho.mahase on 2017/01/14.
 */
public class ComicCreator {

    private Issue issue;
    private Creator creator;
    private String referenceNumber;


    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
}
