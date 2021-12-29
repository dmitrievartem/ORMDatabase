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

    public List<Record> select(String... param) {
        String queryString = "SELECT r FROM Record r";
        String paramString = "";
        if (!param[0].isBlank()) {
            paramString = paramString.concat(String.format(" r.id = %d", Long.parseLong(param[0])));
        }
        if (!param[1].isBlank()) {
            if (!paramString.isBlank()) {
                paramString = paramString.concat(" OR");
            }
            paramString = paramString.concat(" r.uppercaseName LIKE '%" + param[1].toUpperCase(Locale.ROOT) + "%'");
        }
        if (!param[2].isBlank()) {
            if (!paramString.isBlank()) {
                paramString = paramString.concat(" OR");
            }
            paramString = paramString.concat(" r.uppercaseCar LIKE '%" + param[2].toUpperCase(Locale.ROOT) + "%'");
        }
        if (!paramString.isBlank()) {
            queryString = queryString.concat(" WHERE").concat(paramString);
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
