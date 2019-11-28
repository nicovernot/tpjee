package montp.data;

import montp.data.dao.*;
import montp.data.model.Person;
import montp.data.model.Resource;
import montp.data.model.ResourceType;
import montp.data.model.security.Group;
import montp.data.model.security.User;
import montp.services.ResourceTypeService;
import montp.services.UserService;
import montp.tools.Logger;
import montp.tools.Tools;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

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
    @Inject
    UserDAO userDAO;
    @Inject
    GroupDAO groupDAO;
    private Group group;
    @Inject
    ResourceDao resourceDao;
    private Resource resource;
    @Inject
    PersonDAO personDAO;


    @PostConstruct
    public void init() {
System.out.print("hola");
        String [] names = {"jean",  "piere",  "marie","tom","louis","monique","tina","louisa","jean1",  "piere1",  "marie1","tom1","mouis","monique","tina","louisa"} ;

        if (userService.getGroup("USER") == null) {
            Group groupUser = new Group("USER");
            em.persist(groupUser);
            Group groupAdmin = new Group("ADMIN");
            em.persist(groupAdmin);
            User userAdmin = new User("admin", "admin");
            List<Group> groupes = new ArrayList<>();
            groupes.add(groupUser);

            userAdmin.setGroups(groupes);
            for (int p=0 ; p<16 ;p++){
                User user = new User();
                user.setOldPassword("najv");
                user.setPassword(Tools.digestSHA256Hex("najv"));
                user.setUserName(names[p]+p);
                user.setGroups(groupes);
                userDAO.insert(user);
            }
            groupes.add(groupAdmin);
            List<User> userList = userDAO.getUsers();

            for (int l=0;l<userList.size();l++){
                Person person = new Person();
                person.setNom(userList.get(l).getUserName());
                person.setPrenom(userList.get(l).getUserName()+l);
                person.setUser(userList.get(l));
                personDAO.insert(person);
            }
        }



        String [] typrestab = {"Grande salle",  "Moyenne salle",  "petite sale","voiture","projecteur","bus"} ;
        String [] typrestab1 = {"satar",  "sune",  "esperpetile","carture","surjecteur","transbus"} ;
        if (resourceTypeDAO.getCount()==0) {
            for (int i=0;i<typrestab.length;i++){
                ResourceType res = new ResourceType();

                res.setType(typrestab[i]);
                resourceTypeDAO.insert(res);

             }
            for (int l=0;l<6;l++) {
                for (int t = 0; t < 6; t++) {

                    List<Person> personList = personDAO.getAll();
                    List<ResourceType> resourceTypeList = resourceTypeDAO.getAll();
                    Resource resource = new Resource();
                    resource.setAdminressource(personList.get(t));
                    int gencap = ((int) (Math.random() * (40 - 1)));
                    resource.setCapacite(gencap);
                    resource.setNom(typrestab1[t] + gencap);
                    resource.setPartage(true);
                    resource.setAdminressource(personList.get(l));
                    resource.setResourceType(resourceTypeList.get(t));
                    resourceDao.insert(resource);
                    System.out.print("test sortie----------->");
                    System.out.print(resourceTypeList.size());
                    System.out.print(personList.size());
                }
            }




         }

        }

    }



