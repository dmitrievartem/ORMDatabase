package ormdatabase.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Record implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private boolean favorites = false;
    private String name;
    private String car;
    private String uppercaseName;
    private String uppercaseCar;
    private Date date;
    private String phone;
    private String city;
    private List<ShimStackSet> shimStackSetList = new ArrayList<>();

    public Record() {
    }

    public Record(String name, String car, Date date, String phone, String city, List<ShimStackSet> shimStackSetList) {
        this.name = name;
        this.car = car;
        this.date = date;
        this.phone = phone;
        this.city = city;
        this.shimStackSetList = shimStackSetList;
    }

    public Long getId() {
        return id;
    }

    public boolean isFavorites() {
        return favorites;
    }

    public void setFavorites(boolean favorites) {
        this.favorites = favorites;
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

    public void setUppercaseName(String uppercaseName) {
        this.uppercaseName = uppercaseName;
    }

    public void setUppercaseCar(String uppercaseCar) {
        this.uppercaseCar = uppercaseCar;
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

    public List<ShimStackSet> getShimStackSetList() {
        return shimStackSetList;
    }

    public void setShimStackSetList(List<ShimStackSet> shimStackSetList) {
        this.shimStackSetList = shimStackSetList;
    }

    public void setVersion(int index, ShimStackSet shimStackSet) {
        this.shimStackSetList.set(index, shimStackSet);
    }

    public void addVersion(ShimStackSet shimStackSet) {
        this.shimStackSetList.add(shimStackSet);
    }

    public void deleteVersion(int shimStackSetNumber) {
        this.shimStackSetList.remove(shimStackSetNumber);
    }
}