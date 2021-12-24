package ormdatabase.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
public class Record implements Serializable {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private String car;
    private Date date;
    private String phoneNumber;
    private String city;
    private String comment;
    private List<ShimStack> shimStackList;

    public Record() {
    }

    public Record(String name, String car, Date date, String phobeNumber, String city) {
        this.name = name;
        this.car = car;
        this.date = date;
        this.phoneNumber = phobeNumber;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public List<ShimStack> getShimStackList() {
        return shimStackList;
    }

    public void setShimStackList(List<ShimStack> shimStackList) {
        this.shimStackList = shimStackList;
    }
}