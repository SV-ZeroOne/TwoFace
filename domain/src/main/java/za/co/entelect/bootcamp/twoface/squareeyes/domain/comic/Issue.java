package za.co.entelect.bootcamp.twoface.squareeyes.domain.comic;

import java.util.Date;

/**
 * Created by sean.vienings on 2017/01/14.
 */
public class Issue{
    private int issueID = 0;
    private Date publicationDate = null;
    private String issueTitle = "";
    private String publisher = "";
    private Byte seriesNumber = 0;
    private String description = "";

    public Issue(){

    }

    public Issue(Date date, String title, String publisher, Byte seriesNumber, String description){
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

    public Byte getSeriesNumber() {
        return seriesNumber;
    }

    public String getDescription() {
        return description;
    }

    public int getIssueID() {
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

    public boolean setSeriesNumber(Byte seriesNumber) {
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