package montp.data.dao;

import montp.data.model.EtatFacture;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class EtatFactureDAO extends GenericDAO<EtatFacture> {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<EtatFacture> EtatFactureEvent;

    public EtatFacture get(int id) {
        return em.find(EtatFacture.class, id);
    }

    public EtatFactureDAO() { super(EtatFacture.class);}

    public List<EtatFacture> getAll() {
        return em.createQuery("SELECT e FROM EtatFacture e")
            .getResultList();
    }


    public int getCount() {
        return ((Long)em.createQuery("SELECT COUNT(e) FROM EtatFacture e")
            .getSingleResult()).intValue();
    }

    @Transactional
    public void insert(EtatFacture res) {
        em.persist(res);
        EtatFactureEvent.fire(res);
    }

    @Transactional
    public void update(EtatFacture res) {
        em.merge(res);
        EtatFactureEvent.fire(res);
    }

    @Transactional
    public void delete(EtatFacture res) {
        res = get(res.getId());
        em.remove(res);
        EtatFactureEvent.fire(res);
    }
}
