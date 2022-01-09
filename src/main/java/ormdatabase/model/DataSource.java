package ormdatabase.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DataSource {

    public EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("$objectdb/db/shimstack.odb");
//    public EntityManager em = emf.createEntityManager();

    public List<Record> select(Boolean favorites, String... param) {
        List<Record> recordList = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        try {
            String queryString = "SELECT r FROM Record r WHERE r.id > 0"
                    .concat(param[0].isBlank() ? "" : String.format(" AND r.id = %d", Long.parseLong(param[0])))
                    .concat(param[1].isBlank() ? "" : " AND r.uppercaseName LIKE '%".concat(param[1].toUpperCase(Locale.ROOT)).concat("%'"))
                    .concat(param[2].isBlank() ? "" : " AND r.uppercaseCar LIKE '%".concat(param[2].toUpperCase(Locale.ROOT)).concat("%'"))
                    .concat(param[3].isBlank() ? "" : " AND r.uppercaseCity LIKE '%".concat(param[3].toUpperCase(Locale.ROOT)).concat("%'"))
                    .concat(favorites ? " AND r.favorites = true" : "");

            TypedQuery<Record> selectQuery = em.createQuery(queryString, Record.class);
            for (Record record : selectQuery.getResultList()) {
                recordList.add(record.clone());
            }
            return recordList;
        } finally {
            em.close();
        }
    }

    public void insert(Record record) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(record);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
    }

    public void update(Record editedRecord, Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Record record = em.find(Record.class, id);
            em.getTransaction().begin();
            record.copy(editedRecord);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
    }

    public void backup() {
        EntityManager em = emf.createEntityManager();
        try {
            Query backupQuery = em.createQuery("objectdb backup");
            backupQuery.setParameter("target", new java.io.File("d:\\backup"));
            backupQuery.getSingleResult();
        }
        finally {
            em.close();
        }
    }
}
