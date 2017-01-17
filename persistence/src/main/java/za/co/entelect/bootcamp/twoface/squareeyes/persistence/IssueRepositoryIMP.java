package za.co.entelect.bootcamp.twoface.squareeyes.persistence;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.Entity;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;

import java.util.Date;


/**
 * Created by sean.vienings on 2017/01/16.
 */
public class IssueRepositoryIMP extends RepositoryBase implements IssueRepository{

    public IssueRepositoryIMP(Issue issue)
    {
        tList.put(1,issue);
        tList.put(2,new Issue(2, new Date(),"SUPERGIRL","DC",20,"SUPERGIRL"));
        tList.put(3, new Issue(3, new Date(),"SUPERMAN","DC",101,"SUPERMAN"));
        tList.put(4,new Issue(4, new Date(),"WONDER WOMAN","DC",001,"WONDER WOMAN"));
        tList.put(5,new Issue(5, new Date(),"Title","Publisher",232,""));
    }
}
