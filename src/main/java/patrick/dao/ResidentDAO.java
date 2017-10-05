package patrick.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class ResidentDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public ResidentDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Collection findAllResidents() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM resident res")
                .list();
    }

}
