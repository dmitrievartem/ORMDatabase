package ormdatabase.model;

import javafx.util.Pair;

import javax.persistence.Embeddable;
import java.util.Date;
import java.util.List;

@Embeddable
public class ShimStackSet {
    Integer version;
    Date date;
    String comment;
    String author;
    Boolean favorites;
    List<StackPair> shimStackList;

    public ShimStackSet() {
    }

    public ShimStackSet(Integer version, Date date, String comment, String author, Boolean favorites,
                        List<StackPair> shimStackList) {
        this.version = version;
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