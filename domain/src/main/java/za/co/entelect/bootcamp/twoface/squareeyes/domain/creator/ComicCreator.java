package za.co.entelect.bootcamp.twoface.squareeyes.domain.creator;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;

/**
 * Created by mpho.mahase on 2017/01/14.
 */
public class ComicCreator {

    private Issue issue;
    private Creator creator;
    private String creatorRole;


    public String getCreatorRole() {
        return this.creatorRole;
    }
    public void setCreatorRole(String referenceNumber) {
        this.creatorRole = referenceNumber;
    }

    public Issue getIssue(){
        return this.issue;
    }
    public void setIssue(Issue issue){
        this.issue = issue;
    }

    public Creator getCreator(){
        return this.creator;
    }
    public void setCreator(Creator creator){
        this.creator = creator;
    }
}
