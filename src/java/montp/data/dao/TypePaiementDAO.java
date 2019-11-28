package montp.data.dao;

import montp.data.model.TypePaiement;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class TypePaiementDAO extends GenericDAO<TypePaiement> {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<TypePaiement> TypePaiementEvent;

    public TypePaiement get(int id) {
        return em.find(TypePaiement.class, id);
    }

    public TypePaiementDAO() { super(TypePaiement.class);}

    public List<TypePaiement> getAll() {
        return em.createQuery("SELECT e FROM TypePaiement e")
            .getResultList();
    }


    public int getCount() {
        return ((Long)em.createQuery("SELECT COUNT(e) FROM TypePaiement e")
            .getSingleResult()).intValue();
    }

    @Transactional
    public void insert(TypePaiement res) {
        em.persist(res);
        TypePaiementEvent.fire(res);
    }

    @Transactional
    public void update(TypePaiement res) {
        em.merge(res);
        TypePaiementEvent.fire(res);
    }

    @Transactional
    public void delete(TypePaiement res) {
        res = get(res.getId());
        em.remove(res);
        TypePaiementEvent.fire(res);
    }
}
