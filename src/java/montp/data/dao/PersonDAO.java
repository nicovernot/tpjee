package montp.data.dao;

import montp.data.model.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PersonDAO extends GenericDAO<Person>{
    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<Person> personEvent;
    public Person get(int id) {
        return em.find(Person.class, id);
    }
    public List<Person> getAll() {
        return em.createQuery("SELECT e FROM Person e")
            .getResultList();
    }

    public int getCount() {
        return ((Long) em.createQuery("SELECT COUNT(j) FROM Person j")
            .getSingleResult()).intValue();
    }

    @Transactional
    public void delete(Person res) {
        res = get(res.getId());
        em.remove(res);
        personEvent.fire(res);
    }
    @Transactional
    public void insert(Person person) {
        em.persist(person);
        personEvent.fire(person);
    }
    @Transactional
    public void update(Person person) {
        em.merge(person);
        personEvent.fire(person);
    }








}
