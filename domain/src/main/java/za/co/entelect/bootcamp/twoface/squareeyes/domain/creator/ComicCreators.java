package za.co.entelect.bootcamp.twoface.squareeyes.domain.creator;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issues;

import javax.persistence.*;

/**
 * Created by mpho.mahase on 2017/01/14.
 */
@Entity
@Table
@IdClass(ComicCreatorsId.class)
public class ComicCreators {

    @Id
    @Column(name="CreatorID")
    private long creatorID;

    @Id
    @Column(name="IssueID")
    private long issueID;

    @Column(name="CreatorRole")
    private String creatorRole;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="CreatorID", referencedColumnName="CreatorID")
    private Creators creator;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="IssueID", referencedColumnName="IssueID")
    private Issues issue;

    public ComicCreators(){

    }


    public String getCreatorRole() {
        return this.creatorRole;
    }
    public void setCreatorRole(String creatorRole) {
        this.creatorRole = creatorRole;
    }

    public Issues getIssue(){
        return this.issue;
    }
    public void setIssue(Issues issue){
        this.issue = issue;
    }

    public Creators getCreators(){
        return this.creator;
    }
    public void setCreators(Creators creator){
        this.creator = creator;
    }
}
