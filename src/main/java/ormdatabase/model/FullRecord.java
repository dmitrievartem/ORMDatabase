package ormdatabase.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class FullRecord implements Serializable {
    @Id @GeneratedValue
    private long id;

    private String name;
    private String car;
    private String city;
    private String phoneNumber;
    private Date date;
    private String comment;
    private ShimStackList shimStackList;
}