package montp.data.dao;


import montp.data.model.Adresse;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AdresseDAO extends GenericDAO<Adresse> {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<Adresse> AdresseEvent;

    public Adresse get(int id) {
        return em.find(Adresse.class, id);
    }

    public AdresseDAO() { super(Adresse.class);}

    public List<Adresse> getAll() {
        return em.createQuery("SELECT e FROM Adresse e")
            .getResultList();
    }

    public int getCount() {
        return ((Long)em.createQuery("SELECT COUNT(e) FROM Adresse e")
            .getSingleResult()).intValue();
    }

    public Adresse getAdresse(String ville){
        return ((Adresse) em.createQuery("SELECT e FROM Adresse e where e.ville=:ville")
            .setParameter("ville",ville )
            .getSingleResult());
    }

    @Transactional
    public void insert(Adresse res) {
        em.persist(res);
        AdresseEvent.fire(res);
    }

    @Transactional
    public void update(Adresse res) {
        em.merge(res);
        AdresseEvent.fire(res);
    }

    @Transactional
    public void delete(Adresse res) {
        res = get(res.getId());
        em.remove(res);
        AdresseEvent.fire(res);
    }

}
