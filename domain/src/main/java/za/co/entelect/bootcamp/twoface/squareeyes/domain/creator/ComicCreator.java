package za.co.entelect.bootcamp.twoface.squareeyes.domain.creator;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.comic.IIssue;

/**
 * Created by mpho.mahase on 2017/01/14.
 */
public class ComicCreator {

    private IIssue issue;
    private ICreator creator;
    private String creatorRole;


    public String getCreatorRole() {
        return this.creatorRole;
    }
    public void setCreatorRole(String referenceNumber) {
        this.creatorRole = referenceNumber;
    }

    public IIssue getIssue(){
        return this.issue;
    }
    public void setIssue(IIssue issue){
        this.issue = issue;
    }

    public ICreator getCreator(){
        return this.creator;
    }
    public void setCreator(ICreator creator){
        this.creator = creator;
    }
}
