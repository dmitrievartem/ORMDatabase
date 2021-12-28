package ormdatabase.model;

import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Embeddable
public class ShimStackSet {
    Integer version;
    String type = "4 разные";
    Date date = new Date();
    String comment;
    String author;
    Boolean favorites;
    List<StackPair> shimStackList = new ArrayList<>();

    public ShimStackSet() {
    }

    public ShimStackSet(Integer version, String type, Date date, String comment, String author, Boolean favorites,
                        List<StackPair> shimStackList) {
        this.version = version;
        this.type = type;
        this.date = date;
        this.comment = comment;
        this.author = author;
        this.favorites = favorites;
        this.shimStackList = shimStackList;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
            default:
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
}