package ormdatabase.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Locale;

public class DataSource {

    public EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("$objectdb/db/shimstack.odb");
    public EntityManager em = emf.createEntityManager();

    public List<Record> select(Boolean favorites, String... param) {
        String queryString = "SELECT r FROM Record r WHERE r.id > 0"
                .concat(param[0].isBlank() ? "" : String.format(" AND r.id = %d", Long.parseLong(param[0])))
                .concat(param[1].isBlank() ? "" : " AND r.uppercaseName LIKE '%".concat(param[1].toUpperCase(Locale.ROOT)).concat("%'"))
                .concat(param[2].isBlank() ? "" : " AND r.uppercaseCar LIKE '%".concat(param[2].toUpperCase(Locale.ROOT)).concat("%'"))
                .concat(favorites ? " AND r.favorites = true" : "");

        TypedQuery<Record> selectQuery = em.createQuery(queryString, Record.class);
        return selectQuery.getResultList();
    }

    public void insert(Record record) {
        em.getTransaction().begin();
        em.persist(record);
        em.getTransaction().commit();
    }

    public void update(Record editedRecord, Long id) {
        Record record = em.find(Record.class, id);
        em.getTransaction().begin();
        record.clone(editedRecord);
        em.getTransaction().commit();
    }
}
