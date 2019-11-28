package montp.data.dao;

import montp.data.model.Statut;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class StatutDAO extends GenericDAO<Statut> {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<Statut> StatutEvent;

    public Statut get(int id) {
        return em.find(Statut.class, id);
    }

    public StatutDAO() { super(Statut.class);}

    public List<Statut> getAll() {
        return em.createQuery("SELECT e FROM Statut e")
            .getResultList();
    }


    public int getCount() {
        return ((Long)em.createQuery("SELECT COUNT(e) FROM Statut e")
            .getSingleResult()).intValue();
    }

    @Transactional
    public void insert(Statut res) {
        em.persist(res);
        StatutEvent.fire(res);
    }

    @Transactional
    public void update(Statut res) {
        em.merge(res);
        StatutEvent.fire(res);
    }

    @Transactional
    public void delete(Statut res) {
        res = get(res.getId());
        em.remove(res);
        StatutEvent.fire(res);
    }
}
