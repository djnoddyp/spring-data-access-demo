package patrick;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import patrick.config.AppConfig;
import patrick.dao.ResidentDAO;
import patrick.model.Resident;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public class Application {

    static DataSource dataSource;

    public static void main(String[] args) {
        // Create app context and DB
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        dataSource = (DataSource) ctx.getBean("dataSource");
        initialiseDatabase(dataSource);

        // Retrieve a resident
        ResidentDAO residentDAO = new ResidentDAO(dataSource);
        Resident resident = residentDAO.findResidentById(3055);
        System.out.println("Found: " + resident.getName());

        // Create and save a resident and then retrieve
        Resident sam = new Resident();
        sam.setId(9876);
        sam.setName("Sam Losco");
        sam.setAddress("15 Cave Street");
        residentDAO.saveResident(sam);
        System.out.println("Found: " + residentDAO.findResidentById(9876).getName());

        // Update a resident
        residentDAO.updateResidentName("Ricky", "Ray");
        System.out.println("Found: " + residentDAO.findResidentById(3055).getName());

        // List all residents
        List<Map<String, Object>> residents = residentDAO.findAllResidents();
        residents.forEach(r -> System.out.println(r.get("res_id") + " " + r.get("name") + " " + r.get("address")));
    }

    private static void initialiseDatabase(DataSource dataSource) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScripts(
                new ClassPathResource("sql/test-schema.sql"),
                new ClassPathResource("sql/test-data.sql"));
        populator.execute(dataSource);
    }

}
