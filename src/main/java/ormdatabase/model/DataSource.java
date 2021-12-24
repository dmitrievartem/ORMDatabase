package ormdatabase.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class DataSource {

    public EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("$objectdb/db/shimstack.odb");
    public EntityManager em = emf.createEntityManager();

    public List<Record> select(String name, String car) {
        String queryString;
        if (name.equals("") && car.equals("")) {
            queryString = "SELECT r FROM Record r";
        } else {
            queryString = String.format("SELECT r FROM Record r WHERE r.name = '%s' OR r.car = '%s'", name, car);
        }
        TypedQuery<Record> selectQuery = em.createQuery(queryString, Record.class);
        return selectQuery.getResultList();
    }

    public void insert(Record record) {
        em.getTransaction().begin();
        em.persist(record);
        em.getTransaction().commit();
    }
}
