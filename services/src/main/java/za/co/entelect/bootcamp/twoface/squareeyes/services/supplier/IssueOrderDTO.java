package za.co.entelect.bootcamp.twoface.squareeyes.services.supplier;


public class IssueOrderDTO {

    private int issueID;
    private String seriesNumber;
    private int quantity;

    public IssueOrderDTO() {}

    public IssueOrderDTO(int issueID, String seriesNumber, int quantity) {
        this.issueID = issueID;
        this.seriesNumber = seriesNumber;
        this.quantity = quantity;
    }

    public int getIssueID() {
        return issueID;
    }

    public String getSeriesNumber() {
        return seriesNumber;
    }

    public int getQuantity() {
        return quantity;
    }
}
