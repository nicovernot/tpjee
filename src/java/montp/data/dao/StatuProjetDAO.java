package montp.data.dao;

import montp.data.model.StatuProjet;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class StatuProjetDAO extends GenericDAO<StatuProjet> {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<StatuProjet> StatuProjetEvent;

    public StatuProjet get(int id) {
        return em.find(StatuProjet.class, id);
    }

    public StatuProjetDAO() { super(StatuProjet.class);}

    public List<StatuProjet> getAll() {
        return em.createQuery("SELECT e FROM StatuProjet e")
            .getResultList();
    }


    public int getCount() {
        return ((Long)em.createQuery("SELECT COUNT(e) FROM StatuProjet e")
            .getSingleResult()).intValue();
    }

    @Transactional
    public void insert(StatuProjet res) {
        em.persist(res);
        StatuProjetEvent.fire(res);
    }

    @Transactional
    public void update(StatuProjet res) {
        em.merge(res);
        StatuProjetEvent.fire(res);
    }

    @Transactional
    public void delete(StatuProjet res) {
        res = get(res.getId());
        em.remove(res);
        StatuProjetEvent.fire(res);
    }
}
