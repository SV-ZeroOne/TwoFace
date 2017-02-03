package za.co.entelect.bootcamp.twoface.squareeyes.domain.issue;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.creator.ComicCreator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sean.vienings on 2017/01/14.
 */
@Entity
@Table(name="Issues")
public class Issue {

    private List<ComicCreator> comicCreators = new ArrayList<ComicCreator>();

    private int issueID;
    private Date publicationDate;
    private String issueTitle;
    private String publisher;
    private short seriesNumber;
    private String description;
    private String imageRef;

    public Issue(){}

    public Issue(Date date, String title, String publisher, short seriesNumber, String description){
        this.publicationDate = date;
        this.issueTitle = title;
        this.publisher = publisher;
        this.seriesNumber = seriesNumber;
        this.description = description;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.issues", cascade = CascadeType.MERGE)
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
    @Column(name="IssueID")
    public int getIssueID() {
        return issueID;
    }
    public void setIssueID(int issueID) {
        this.issueID = issueID;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name="PublicationDate")
    public Date getPublicationDate() {
        return publicationDate;
    }
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Column(name="Title")
    public String getIssueTitle() {
        return issueTitle;
    }
    public void setIssueTitle(String issueTitle) {
        this.issueTitle = issueTitle;
    }

    @Column(name="Publisher")
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Column(name="SeriesNumber")
    public short getSeriesNumber() {
        return seriesNumber;
    }
    public void setSeriesNumber(short seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    @Column(name="Description")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageRef(String imageRef) {this.imageRef = imageRef;}

    @Column(name="imageRef")
    public String getImageRef() {
        return imageRef;
    }
}
