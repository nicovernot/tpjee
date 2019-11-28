package montp.data.dao;

import montp.data.model.Utilisateur;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UtilisateurDAO extends GenericDAO<Utilisateur> {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<Utilisateur> UtilisateurEvent;

    public Utilisateur get(int id) {
        return em.find(Utilisateur.class, id);
    }

    public UtilisateurDAO() { super(Utilisateur.class);}

    public List<Utilisateur> getAll() {
        return em.createQuery("SELECT e FROM Utilisateur e")
            .getResultList();
    }

    public int getCount() {
        return ((Long)em.createQuery("SELECT COUNT(e) FROM Utilisateur e")
            .getSingleResult()).intValue();
    }

    @Transactional
    public void insert(Utilisateur res) {
        em.persist(res);
        UtilisateurEvent.fire(res);
    }

    @Transactional
    public void update(Utilisateur res) {
        em.merge(res);
        UtilisateurEvent.fire(res);
    }

    @Transactional
    public void delete(Utilisateur res) {
        res = get(res.getId());
        em.remove(res);
        UtilisateurEvent.fire(res);
    }
}
