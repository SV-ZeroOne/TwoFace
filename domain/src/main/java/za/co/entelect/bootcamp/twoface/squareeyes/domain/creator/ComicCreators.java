package za.co.entelect.bootcamp.twoface.squareeyes.domain.creator;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issues;

import javax.persistence.*;

/**
 * Created by mpho.mahase on 2017/01/14.
 */
@Entity
@Table
public class ComicCreators {

    @Id
    @ManyToOne
    private Issues issue;

    @Id
    @ManyToOne
    private Creators creators;

    @Column(name="CreatorRole")
    private String creatorRole;

    public ComicCreators(){

    }


    public String getCreatorRole() {
        return this.creatorRole;
    }
    public void setCreatorRole(String referenceNumber) {
        this.creatorRole = referenceNumber;
    }

    public Issues getIssue(){
        return this.issue;
    }
    public void setIssue(Issues issue){
        this.issue = issue;
    }

    public Creators getCreators(){
        return this.creators;
    }
    public void setCreators(Creators creators){
        this.creators = creators;
    }
}
