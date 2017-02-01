package za.co.entelect.bootcamp.twoface.squareeyes.services;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.IssuesRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.RelationalIssuesRepository;

import java.util.List;

/**
 * Created by quinton.weenink on 2017/02/01.
 */
public class HomePageService {
    private IssuesRepository issuesRepository;

    public HomePageService() {
        //
    }

    public List<Issue> getSpecials()
    {
        return null;
    }


}
