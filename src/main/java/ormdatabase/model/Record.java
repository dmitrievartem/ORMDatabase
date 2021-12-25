package ormdatabase.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
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
    private String phone;
    private String city;
    private String type;
    private List<ShimStackSet> shimStackSetList;

    public Record() {
    }

    public Record(String name, String car, Date date, String phone, String city, String type, List<ShimStackSet> shimStackSetList) {
        this.name = name;
        this.car = car;
        this.date = date;
        this.phone = phone;
        this.city = city;
        this.type = type;
        this.shimStackSetList = shimStackSetList;
    }

    public Long getId() {
        return id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTypeNumber() {
        switch (type) {
            case "4 одинаковых":
                return 1;
            case "перед-зад":
                return 2;
            case "4 разных":
            default:
                return 4;
        }
    }

    public List<ShimStackSet> getShimStackSetList() {
        return shimStackSetList;
    }

    public void setShimStackSetList(List<ShimStackSet> shimStackSetList) {
        this.shimStackSetList = shimStackSetList;
    }
}