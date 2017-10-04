package patrick;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import patrick.config.AppConfig;
import patrick.dao.ResidentDAO;
import patrick.model.Resident;

import javax.sql.DataSource;

public class Application {

    static DataSource dataSource;

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        dataSource = (DataSource) ctx.getBean("dataSource");
        initialiseDatabase(dataSource);
        Resident resident = new ResidentDAO(dataSource).findResidentById(3055);
        System.out.println("Found: " + resident.getName());
    }

    private static void initialiseDatabase(DataSource dataSource) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScripts(
                new ClassPathResource("sql/test-schema.sql"),
                new ClassPathResource("sql/test-data.sql"));
        populator.execute(dataSource);
    }

}
