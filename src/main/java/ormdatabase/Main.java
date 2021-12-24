package ormdatabase;

import javafx.stage.Stage;

import java.io.IOException;

public class Main extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneSwitcher sceneSwitcher = new SceneSwitcher();
        sceneSwitcher.start(stage);
    }

    public static void main(String[] args) {

        System.setProperty("objectdb.home", "./src/main/resources/odb/");

        /*System.setProperty("objectdb.home", "./src/main/resources/odb/");

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("$objectdb/db/shimstack.odb");
        EntityManager em = emf.createEntityManager();


        // Store objects in the database:
        Record record = new Record();
        record.setName("Имя");
        record.setCar("Машина");



        Query backupQuery = em.createQuery("objectdb backup");
        backupQuery.setParameter("target", new java.io.File("c:\\backup"));
        backupQuery.getSingleResult();


        System.out.println(results.get(0).getName());
        System.out.println(results.get(0).getName());
        List<ShimStack> shimStackList = results.get(0).getShimStackList();
        ShimStack shimStack = shimStackList.get(0);
        System.out.println("0000000000000000000000000000");
        System.out.println(shimStack);

        // Close the database connection:
        em.close();
        emf.close();*/

        launch();
    }
}