package ormdatabase;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ormdatabase.model.FullRecord;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.IOException;

public class Main extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ORM Database");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("$objectdb/db/points.odb");
        EntityManager em = emf.createEntityManager();

        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        for (int i = 0; i < 1000; i++) {
            FullRecord fullRecord = new FullRecord();
            em.persist(fullRecord);
        }
        em.getTransaction().commit();

        // Find the number of Point objects in the database:
        Query q1 = em.createQuery("SELECT COUNT(fullRecord) FROM FullRecord fullRecord");
        System.out.println("Total Points: " + q1.getSingleResult());

        // Close the database connection:
        em.close();
        emf.close();
    }
}