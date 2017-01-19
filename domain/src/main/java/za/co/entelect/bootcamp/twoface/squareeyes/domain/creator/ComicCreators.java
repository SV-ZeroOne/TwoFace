package za.co.entelect.bootcamp.twoface.squareeyes.domain.creator;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issues;

import javax.persistence.*;

/**
 * Created by mpho.mahase on 2017/01/14.
 */
@Entity
@Table
@AssociationOverrides({
        @AssociationOverride(name = "pk.issues",
                joinColumns = @JoinColumn(name = "IssueID")),
        @AssociationOverride(name = "pk.creators",
                joinColumns = @JoinColumn(name = "CreatorID")) })
public class ComicCreators {
    private ComicCreatorsId pk = new ComicCreatorsId();
    private String creatorRole;

    public ComicCreators() {
    }

    @EmbeddedId
    public ComicCreatorsId getPk() {
        return pk;
    }

    public void setPk(ComicCreatorsId pk) {
        this.pk = pk;
    }

    @Transient
    public Issues getIssues() {
        return getPk().getIssues();
    }

    public void setIssues(Issues issue) {
        getPk().setIssues(issue);
    }

    @Transient
    public Creators getCreators() {
        return getPk().getCreators();
    }

    public void setCreators(Creators category) {
        getPk().setCreators(category);
    }

    @Column(name = "CreatorRole", nullable = false, length = 10)
    public String getCreatorRole() {
        return this.creatorRole;
    }

    public void setCreatorRole(String creatorRole) {
        this.creatorRole = creatorRole;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ComicCreators that = (ComicCreators) o;

        if (getPk() != null ? !getPk().equals(that.getPk())
                : that.getPk() != null)
            return false;

        return true;
    }

    public int hashCode() {
        return (getPk() != null ? getPk().hashCode() : 0);
    }
}
