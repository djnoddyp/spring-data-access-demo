package patrick;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import patrick.config.AppConfig;
import patrick.dao.ResidentDAO;
import patrick.model.Resident;

import java.util.ArrayList;
import java.util.Collection;

public class Application {

    public static void main(String[] args) {
        // Create app context and DB
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ResidentDAO residentDAO = (ResidentDAO) ctx.getBean("residentDAO");

        // List all residents
        Collection<Resident> residents = new ArrayList<>(residentDAO.findAllResidents());
        residents.forEach(r -> System.out.println(r.getName()));
    }

}
