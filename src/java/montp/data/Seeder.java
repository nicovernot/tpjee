package montp.data;

import montp.data.dao.ResourceTypeDAO;
import montp.data.model.ResourceType;
import montp.data.model.security.Group;
import montp.data.model.security.User;
import montp.services.ResourceTypeService;
import montp.services.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Startup
public class Seeder {

    @Inject
    private ResourceTypeService resourceTypeService;
    @Inject
    private ResourceTypeDAO resourceTypeDAO;
    @Inject
    private UserService userService;
    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {

        ResourceType res = new ResourceType();
        res.setNom("salle 1");
        res.setPartage(true);
        res.setType("salle");
        resourceTypeDAO.insert(res);

        if (userService.getGroup("USER") == null) {
            Group groupUser = new Group("USER");
            em.persist(groupUser);
            Group groupAdmin = new Group("ADMIN");
            em.persist(groupAdmin);
            User userAdmin = new User("admin", "admin");
            List<Group> groupes = new ArrayList<>();
            groupes.add(groupUser);
            groupes.add(groupAdmin);
            userAdmin.setGroups(groupes);

        }
    }
    }


