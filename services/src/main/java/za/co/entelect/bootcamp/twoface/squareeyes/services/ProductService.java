package za.co.entelect.bootcamp.twoface.squareeyes.services;

import org.springframework.beans.factory.annotation.Autowired;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.stock.Stock;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.IssuesRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.stock.StockRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quinton.weenink on 2017/02/01.
 */
public class ProductService {

    private StockRepository stockRepository;
    private IssuesRepository issuesRepository;

    @Autowired
    public ProductService(IssuesRepository issuesRepository, StockRepository stockRepository) {
        this.issuesRepository = issuesRepository;
        this.stockRepository = stockRepository;
    }

    public void getStockIssue(List<Stock> stockList, Stock stock, Issue issue)
    {
        if(issue.getIssueID() != -1) {
            issue = issuesRepository.find(issue.getIssueID());
            stockList = stockRepository.search("issue.issueID", issue.getIssueID());
            if(stock.getStockReferenceID() != -1)
                stock = stockRepository.find(stock.getStockReferenceID());
            else
                stock  = stockRepository.search("issue.issueID", issue.getIssueID()).get(0);
        }

        System.out.println("rIssue: " + issue.getIssueTitle());
    }

    public Issue getIssue(int issueID)
    {
        if(issueID != -1) {
            return issuesRepository.find(issueID);
        }
        return null;
    }

    public List<Stock> getStockList(int issueID)
    {
        if(issueID != -1) {
            return stockRepository.search("issue.issueID", issueID);
        }
        return null;
    }

    public Stock getStock(int stockID, int issueID)
    {
            if(stockID != -1)
                return stockRepository.find(stockID);
            else
                return stockRepository.search("issue.issueID", issueID).get(0);
    }

    public void setStockRepository(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }
    public StockRepository getStockRepository() {
        return stockRepository;
    }

    public void setIssuesRepository(IssuesRepository issuesRepository) {
        this.issuesRepository = issuesRepository;
    }
    public IssuesRepository getIssuesRepository() {
        return issuesRepository;
    }
}
