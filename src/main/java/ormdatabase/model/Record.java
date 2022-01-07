package ormdatabase.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;

@Entity
public class Record implements Cloneable {
    @Id
    @GeneratedValue
    private Long id;
    private boolean favorites = false;
    private String name = "ФИО";
    private String car;
    private String uppercaseName;
    private String uppercaseCar;
    private Date date = new Date();
    private String phone;
    private String city;
    private String uppercaseCity;
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
        this.uppercaseName = Objects.nonNull(name) ? name.toUpperCase(Locale.ROOT) : null;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
        this.uppercaseCar = Objects.nonNull(car) ? car.toUpperCase(Locale.ROOT) : null;
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
        this.uppercaseCity = Objects.nonNull(city) ? city.toUpperCase(Locale.ROOT) : null;
    }

    public List<ShimStackSet> getShimStackSetList() {
        return shimStackSetList;
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

    public void copy(Record record) {
        // как клонируются uppercase?
        this.name = record.getName();
        this.uppercaseName = record.getName().toUpperCase(Locale.ROOT);
        this.car = record.getCar();
        this.uppercaseCar = Objects.nonNull(record.getCar()) ? record.getCar().toUpperCase(Locale.ROOT) : null;
        this.date = record.getDate();
        this.phone = record.getPhone();
        this.city = record.getCity();
        this.uppercaseCity = Objects.nonNull(record.getCity()) ? record.getCity().toUpperCase(Locale.ROOT) : null;
        this.favorites = record.isFavorites();
        this.shimStackSetList = record.getShimStackSetList();
    }

    @Override
    public Record clone() {
        try {
            Record clone = (Record) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            clone.shimStackSetList = new ArrayList<>(clone.shimStackSetList);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}