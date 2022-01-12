package ormdatabase;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ormdatabase.entity.Record;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DataSource {

    public EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("$objectdb/db/shimstack.odb");

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
                recordList.add(new Record(record));
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
        } finally {
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
        } finally {
            em.close();
        }
    }

    public void backup(Stage stage) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(stage);
        EntityManager em = emf.createEntityManager();
        try {
            Query backupQuery = em.createQuery("objectdb backup");
            backupQuery.setParameter("target", new java.io.File(String.valueOf(selectedDirectory)));
            backupQuery.getSingleResult();
        } finally {
            em.close();
        }
    }

    public void recovery(Stage stage) {
        emf.close();
        try {
            Path dir = Files.createDirectories(Paths.get("src/main/resources/odb/db"));
            OutputStream out = Files.newOutputStream(dir.resolve("shimstack.odb"));
            final FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(stage);
            byte[] fileContent = Files.readAllBytes(file.toPath());
            out.write(fileContent);
            emf = Persistence.createEntityManagerFactory("$objectdb/db/shimstack.odb");
        } catch (IOException e) {
            e.printStackTrace();
            emf = Persistence.createEntityManagerFactory("$objectdb/db/shimstack.odb");
        }
    }
}
