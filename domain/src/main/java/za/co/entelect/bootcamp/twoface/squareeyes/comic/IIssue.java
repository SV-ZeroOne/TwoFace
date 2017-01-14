package za.co.entelect.bootcamp.twoface.squareeyes.comic;

import java.util.Date;

/**
 * Created by sean.vienings on 2017/01/14.
 */
public interface IIssue {
    public Date getPublicationDate();
    public String getIssueTitle();
    public String getPublisher();
    public Byte getSeriesNumber();
    public String getDescription();
    public int getIssueID();

    public boolean setPublicationDate(Date date);
    public boolean setIssuesTitle(String issueTitle);
    public boolean setPublisher(String publisher);
    public boolean setSeriesNumber(Byte seriesNumber);
    public boolean setDescription(String description);

    }
