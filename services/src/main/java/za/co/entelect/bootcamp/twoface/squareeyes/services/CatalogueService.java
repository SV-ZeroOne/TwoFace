package za.co.entelect.bootcamp.twoface.squareeyes.services;

import org.springframework.beans.factory.annotation.Autowired;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.IssuesRepository;

import java.util.List;

/**
 * Created by sean.vienings on 2017/01/31.
 *
 */
public class CatalogueService {

    private IssuesRepository issuesRepository;

    @Autowired
    public CatalogueService(IssuesRepository issuesRepository) {
        this.issuesRepository = issuesRepository;
    }

    public List<Issue> getCataloguePage(int page)
    {
        return issuesRepository.findAll(8, page);
    }

    public List<Issue> SearchService(String searchTerm, int page) {
        List<Issue> list = null;
        if (searchTerm == "") {
            System.out.println("No search");
        } else {
            list = issuesRepository.search("issueTitle", searchTerm, 8, page);
        }
        return list;
    }

    public void setIssuesRepository(IssuesRepository issuesRepository) {
        this.issuesRepository = issuesRepository;
    }

    public IssuesRepository getIssuesRepository() {
        return issuesRepository;
    }
}
