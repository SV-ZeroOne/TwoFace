package za.co.entelect.bootcamp.twoface.squareeyes.domain.issue;

import javax.persistence.*;
import java.util.Date;

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

    @Column
    private String publisher;


    @Column
    private Integer seriesNumber;

    @Column
    private String description;

    public Issue(){

    }

    public Issue(Date date, String title, String publisher, Integer seriesNumber, String description){
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

    public Integer getSeriesNumber() {
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

    public boolean setSeriesNumber(Integer seriesNumber) {
        if((this.seriesNumber = seriesNumber)!=null) {
            return true;
        }else{return false;}
    }

    public boolean setDescription(String description) {
        if((this.description = description)!=null) {
            return true;
        }else{return false;}
    }
}
