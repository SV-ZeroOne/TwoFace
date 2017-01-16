package za.co.entelect.bootcamp.twoface.squareeyes.domain.creator;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.comic.IIssue;

/**
 * Created by quinton.weenink on 2017/01/15.
 */
interface IComicCreator {
    String getCreatorRole();
    void setCreatorRole(String referenceNumber);

    IIssue getIssue();
    void setIssue(IIssue issue);

    ICreator getCreator();
    void setCreator(ICreator creator);
}
