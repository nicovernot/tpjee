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
        if (userDAO.getCount()==0) {
            try {
                User user = new User();
                user.setOldPassword("najv");
                user.setPassword("najv");
                user.setUserName("nico");
                userDAO.insert(user);
                em.createNativeQuery("insert into security_user (id, oldpassword, password, username) values (2, 'njJ4jTjR', 'ie37afDVcpv', 'Birch');\n" +
                    "insert into security_user (id, oldpassword, password, username) values (3, '7mm8RExo', 'kthYe0', 'Elmer');\n" +
                    "insert into security_user (id, oldpassword, password, username) values (4, 'f3rfWrRPH9UM', 'NLs1YG2X2V', 'Ansell');\n" +
                    "insert into security_user (id, oldpassword, password, username) values (5, 'juNHrs', '8RU5YOMqe', 'Demetrius');\n" +
                    "insert into security_user (id, oldpassword, password, username) values (6, '5iwXHcu9Z', 'sJeb9Mg', 'Therese');\n" +
                    "insert into security_user (id, oldpassword, password, username) values (7, 'pIr0h77', 'b8JvFLUw', 'Holly-anne');\n" +
                    "insert into security_user (id, oldpassword, password, username) values (8, 'pRlzmgnd', 'e4LIOIIsi', 'Moina');\n" +
                    "insert into security_user (id, oldpassword, password, username) values (9, 'tcDn5egdEY4m', 'F8iBfFibkO', 'Eyde');\n" +
                    "insert into security_user (id, oldpassword, password, username) values (10, 'FfJNc7U', 'LksP8DjK', 'Lissa');\n" +
                    "insert into security_user (id, oldpassword, password, username) values (11, '6S7JpWjFz0', '2UlIMV', 'Olivette');\n" +
                    "insert into security_user (id, oldpassword, password, username) values (12, '1hudcCts', 'z5cP87d', 'Etty');\n" +
                    "insert into security_user (id, oldpassword, password, username) values (13, 'Aiu6pQAV', '9Ac92uoy9v7p', 'Rudyard');\n" +
                    "insert into security_user (id, oldpassword, password, username) values (14, 'Tp60DZpWfED', '8ieoyrioEzOC', 'Viviyan');\n" +
                    "insert into security_user (id, oldpassword, password, username) values (15, 'lZZf9BHOSChw', 'uvOy0srBLd6e', 'Talbert');\n" +
                    "insert into security_user (id, oldpassword, password, username) values (16, 'cHCiNxYVm23', 'laUyPrqK', 'Jean');\n" +
                    "insert into security_user (id, oldpassword, password, username) values (17, 'Xux0i6mKipDf', 'oSHxYOzf2unT', 'Roderick')").executeUpdate();

            } catch (Throwable e){

                throw e;
            }
        List<User> userList = userDAO.getUsers();
            System.out.print("test sortie----------->");
            System.out.print("test sortie----------->");
            System.out.print(userList.size());
        for (int l=0;l<userList.size();l++){
            Person person = new Person();
            person.setNom(userList.get(l).getUserName());
            person.setPrenom(userList.get(l).getUserName()+l);
            person.setUser(userList.get(l));
            personDAO.insert(person);
        }


        }

      //List<User> userList = userDAO.getUsers();
      //List<Group> groupList = null;
      //groupList.add(groupDAO.getByName("USER"));
     //System.out.print(groupList.get(1).getGroupName()+"testnom");

       // for (User user : userList){

         //user.setGroups(groupList);
        // userDAO.insert(user);

        //}


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



