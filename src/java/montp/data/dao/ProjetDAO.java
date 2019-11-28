package montp.data.dao;

import montp.data.model.Projet;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProjetDAO extends GenericDAO<Projet> {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<Projet> ProjetEvent;

    public Projet get(int id) {
        return em.find(Projet.class, id);
    }

    public ProjetDAO() { super(Projet.class);}

    public List<Projet> getAll() {
        return em.createQuery("SELECT e FROM Projet e")
            .getResultList();
    }

    public int getCount() {
        return ((Long)em.createQuery("SELECT COUNT(e) FROM Projet e")
            .getSingleResult()).intValue();
    }

    @Transactional
    public void insert(Projet res) {
        em.persist(res);
        ProjetEvent.fire(res);
    }

    @Transactional
    public void update(Projet res) {
        em.merge(res);
        ProjetEvent.fire(res);
    }

    @Transactional
    public void delete(Projet res) {
        res = get(res.getId());
        em.remove(res);
        ProjetEvent.fire(res);
    }
}
