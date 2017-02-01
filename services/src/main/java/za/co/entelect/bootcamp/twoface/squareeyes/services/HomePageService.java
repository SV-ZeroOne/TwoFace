package za.co.entelect.bootcamp.twoface.squareeyes.services;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.IssuesRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.RelationalIssuesRepository;

import java.util.List;

/**
 * Created by quinton.weenink on 2017/02/01.
 */
public class HomePageService {
    static IssuesRepository singleIssueRepository;

    public HomePageService() {
        //
    }

    public List<Issue> HomePage()
    {
        singleIssueRepository = new RelationalIssuesRepository();

        Issue issue;
        List list = null;
        issue = singleIssueRepository.find(1);
        list.add(issue);
        issue = singleIssueRepository.find(5);
        list.add(issue);
        issue = singleIssueRepository.find(1);
        list.add(issue);
        return list;
       /* public ApplicationContext context = new ClassPathXmlApplicationContext("classpath:za/co/entelect/bootcamp/twoface/squareeyes/services/root-context.xml");
        Issue issue = singleIssueRepository.find(32);
        return issue.getIssueTitle();*/
    }


}
