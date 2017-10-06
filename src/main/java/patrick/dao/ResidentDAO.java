package patrick.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import patrick.model.Resident;

import java.util.Collection;

@Repository("residentDAO")
@Transactional
public class ResidentDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public ResidentDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Collection<Resident> findAllResidents() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Resident")
                .list();
    }

}
