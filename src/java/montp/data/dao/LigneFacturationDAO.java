package montp.data.dao;

import montp.data.model.LignesFacturation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class LigneFacturationDAO extends GenericDAO<LignesFacturation> {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<LignesFacturation> LignesFacturationEvent;

    public LignesFacturation get(int id) {
        return em.find(LignesFacturation.class, id);
    }

    public LigneFacturationDAO() { super(LignesFacturation.class);}

    public List<LignesFacturation> getAll() {
        return em.createQuery("SELECT e FROM LignesFacturation e")
            .getResultList();
    }

    public int getCount() {
        return ((Long)em.createQuery("SELECT COUNT(e) FROM LignesFacturation e")
            .getSingleResult()).intValue();
    }

    @Transactional
    public void insert(LignesFacturation res) {
        em.persist(res);
        LignesFacturationEvent.fire(res);
    }

    @Transactional
    public void update(LignesFacturation res) {
        em.merge(res);
        LignesFacturationEvent.fire(res);
    }

    @Transactional
    public void delete(LignesFacturation res) {
        res = get(res.getId());
        em.remove(res);
        LignesFacturationEvent.fire(res);
    }
}