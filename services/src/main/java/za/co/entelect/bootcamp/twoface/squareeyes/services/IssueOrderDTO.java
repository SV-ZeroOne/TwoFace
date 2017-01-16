package za.co.entelect.bootcamp.twoface.squareeyes.services;

public class IssueOrderDTO {

    private int issueID;
    private String seriesNumber;
    private int quantity;

    public IssueOrderDTO(int issueID, String seriesNumber, int quantity) {
    }

    public int getIssueID() {
        return issueID;
    }

    public void setIssueID(int issueID) {
        this.issueID = issueID;
    }

    public String getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(String seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
