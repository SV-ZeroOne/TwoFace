package za.co.entelect.bootcamp.twoface.squareeyes.domain.creator;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;

import javax.persistence.*;

/**
 * Created by mpho.mahase on 2017/01/14.
 */
@Entity
@Table(name="ComicCreators")
@AssociationOverrides({
        @AssociationOverride(name = "pk.issues",
                joinColumns = @JoinColumn(name = "IssueID")),
        @AssociationOverride(name = "pk.creators",
                joinColumns = @JoinColumn(name = "CreatorID")) })
public class ComicCreator {

    private ComicCreatorsId pk = new ComicCreatorsId();
    private String creatorRole;

    public ComicCreator() {}

    @EmbeddedId
    public ComicCreatorsId getPk() {
        return pk;
    }
    public void setPk(ComicCreatorsId pk) {
        this.pk = pk;
    }

    @Transient
    public Issue getIssues() {
        return getPk().getIssues();
    }
    public void setIssues(Issue issue) {
        getPk().setIssues(issue);
    }

    @Transient
    public Creator getCreators() {
        return getPk().getCreators();
    }
    public void setCreators(Creator category) {
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

        ComicCreator that = (ComicCreator) o;

        if (getPk() != null ? !getPk().equals(that.getPk())
                : that.getPk() != null)
            return false;

        return true;
    }
    public int hashCode() {
        return (getPk() != null ? getPk().hashCode() : 0);
    }
}
