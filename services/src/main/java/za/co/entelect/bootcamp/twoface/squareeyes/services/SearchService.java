package za.co.entelect.bootcamp.twoface.squareeyes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.RelationalIssuesRepository;

import java.util.List;

/**
 * Created by sean.vienings on 2017/01/31.
 *
 */

public class SearchService {

    public List<Issue> SearchService(String searchTerm) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:za/co/entelect/bootcamp/twoface/squareeyes/services/root-context.xml");
        RelationalIssuesRepository ir = context.getBean(RelationalIssuesRepository.class);
        List<Issue> list = ir.search("Title",searchTerm);
        if(list.size() > 0)
        {
            return list;
        }
        else
        {
            throw new NullPointerException();
        }
    }
}
