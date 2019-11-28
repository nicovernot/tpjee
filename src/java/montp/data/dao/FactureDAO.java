package montp.data.dao;

import montp.data.model.Facture;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class FactureDAO extends GenericDAO<Facture>{
    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<Facture> FactureEvent;

    public Facture get(int id) {
        return em.find(Facture.class, id);
    }

    public FactureDAO() { super(Facture.class);}

    public List<Facture> getAll() {
        return em.createQuery("SELECT e FROM Facture e")
            .getResultList();
    }

    public int getCount() {
        return ((Long)em.createQuery("SELECT COUNT(e) FROM Facture e")
            .getSingleResult()).intValue();
    }

    @Transactional
    public void insert(Facture res) {
        em.persist(res);
        FactureEvent.fire(res);
    }

    @Transactional
    public void update(Facture res) {
        em.merge(res);
        FactureEvent.fire(res);
    }

    @Transactional
    public void delete(Facture res) {
        res = get(res.getId());
        em.remove(res);
        FactureEvent.fire(res);
    }
}
