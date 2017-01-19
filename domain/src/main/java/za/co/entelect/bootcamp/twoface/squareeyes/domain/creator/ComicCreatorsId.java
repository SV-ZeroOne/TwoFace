package za.co.entelect.bootcamp.twoface.squareeyes.domain.creator;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issues;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by quinton.weenink on 2017/01/19.
 */
@Embeddable
public class ComicCreatorsId implements Serializable {

    private Issues issue;
    private Creators creator;

    @ManyToOne
    public Issues getIssues() {
        return issue;
    }

    public void setIssues(Issues issue) {
        this.issue = issue;
    }

    @ManyToOne
    public Creators getCreators() {
        return creator;
    }

    public void setCreators(Creators creator) {
        this.creator = creator;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComicCreatorsId that = (ComicCreatorsId) o;

        if (issue != null ? !issue.equals(that.issue) : that.issue != null) return false;
        if (creator != null ? !creator.equals(that.creator) : that.creator != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (issue != null ? issue.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        return result;
    }
}
