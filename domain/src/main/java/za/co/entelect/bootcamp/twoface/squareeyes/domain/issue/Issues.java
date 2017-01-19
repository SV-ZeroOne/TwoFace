package za.co.entelect.bootcamp.twoface.squareeyes.domain.issue;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.creator.ComicCreators;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.creator.Creators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sean.vienings on 2017/01/14.
 */
@Entity
@Table
public class Issues{
    @Id
    @GeneratedValue
    @Column(name="IssueID")
    private int issueID;

    @Temporal(value = TemporalType.DATE)
    @Column(name="PublicationDate")
    private Date publicationDate;

    @Column(name="Title")
    private String issueTitle;

    @Column(name="Publisher")
    private String publisher;

    @Column(name="SeriesNumber")
    private short seriesNumber;

    @Column(name="Description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.issues", cascade=CascadeType.ALL)
    private List<ComicCreators> issues= new ArrayList<ComicCreators>();

    public List<ComicCreators> getIssues() {
        return issues;
    }

    public void setIssues(List<ComicCreators> issues) {
        this.issues = issues;
    }


    public Issues(){

    }

    public Issues(Date date, String title, String publisher, short seriesNumber, String description){
        this.publicationDate = date;
        this.issueTitle = title;
        this.publisher = publisher;
        this.seriesNumber = seriesNumber;
        this.description = description;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public String getIssueTitle() {
        return issueTitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public short getSeriesNumber() {
        return seriesNumber;
    }

    public String getDescription() {
        return description;
    }

    public Integer getID() {
        return issueID;
    }

    public boolean setPublicationDate(Date date) {
        if((this.publicationDate = date)!=null) {
            return true;
        }else{return false;}
    }

    public boolean setIssuesTitle(String issueTitle) {
        if((this.issueTitle = issueTitle)!=null) {
            return true;
        }else{return false;}
    }

    public boolean setPublisher(String publisher) {
        if ((this.publisher = publisher) != null){
            return true;
        }else{return false;}
    }

    public void setSeriesNumber(short seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public boolean setDescription(String description) {
        if((this.description = description)!=null) {
            return true;
        }else{return false;}
    }
}
