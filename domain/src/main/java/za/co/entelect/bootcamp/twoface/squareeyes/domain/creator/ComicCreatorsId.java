package za.co.entelect.bootcamp.twoface.squareeyes.domain.creator;

import java.io.Serializable;

/**
 * Created by quinton.weenink on 2017/01/19.
 */
public class ComicCreatorsId implements Serializable {
    private long creatorID;

    private long issueID;

    public ComicCreatorsId(){

    }

    public int hashCode() {
        return (int)(creatorID + issueID);
    }

    public boolean equals(Object object) {
        if (object instanceof ComicCreatorsId) {
            ComicCreatorsId otherId = (ComicCreatorsId) object;
            return (otherId.creatorID == this.creatorID) && (otherId.issueID == this.issueID);
        }
        return false;
    }
}
