package ormdatabase.model;

import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Embeddable
public class ShimStackSet implements Cloneable {
    String type = "4 разные";
    Date date = new Date();
    String comment;
    String author;
    Boolean favorites;
    List<StackPair> shimStackList;

    public ShimStackSet() {
        shimStackList = new ArrayList<>(List.of(new StackPair(), new StackPair(), new StackPair(), new StackPair()));
    }

    public ShimStackSet(String type, Date date, String comment, String author, Boolean favorites,
                        List<StackPair> shimStackList) {
        this.type = type;
        this.date = date;
        this.comment = comment;
        this.author = author;
        this.favorites = favorites;
        this.shimStackList = shimStackList;
    }

    public ShimStackSet(ShimStackSet shimStackSet) {
        this.type = shimStackSet.type;
        this.date = new Date(shimStackSet.date.getTime());
        this.comment = shimStackSet.comment;
        this.author = shimStackSet.author;
        this.favorites = shimStackSet.favorites;
        this.shimStackList = new ArrayList<>(shimStackSet.shimStackList);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTypeNumber() {
        switch (type) {
            case "4 одинаковые":
                return 1;
            case "перед-зад":
                return 2;
            case "4 разные":
                return 4;
            default:
                System.out.println("ПРОИЗОШЛА КАКАЯ-ТО ХУЙНЯ");
                return 4;
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getFavorites() {
        return favorites;
    }

    public void setFavorites(Boolean favorites) {
        this.favorites = favorites;
    }

    public List<StackPair> getShimStackList() {
        return shimStackList;
    }

    public void setShimStackList(List<StackPair> shimStackList) {
        this.shimStackList = shimStackList;
    }

    @Override
    public ShimStackSet clone() {
        try {
            ShimStackSet clone = (ShimStackSet) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}