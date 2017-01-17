package za.co.entelect.bootcamp.twoface.squareeyes.domain.creator;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by mpho.mahase on 2017/01/14.
 */
public class ComicCreator {

    @Id
    @OneToOne
    private Issue issue;
    @Id
    @OneToOne
    private Creator creator;
    @Column
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
