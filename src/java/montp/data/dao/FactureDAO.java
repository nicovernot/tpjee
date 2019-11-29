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

    public List<Facture> get(int satrt,int limit, String nom){
        return  em.createQuery("SELECT j from Facture j WHERE j.projet.nom=:nom ")
            .setParameter("nom",nom)
            .getResultList();
    }

    public List<Facture> get(int start, int limit) {
        return em.createQuery("SELECT j FROM Facture j ORDER BY j.etatFacture.etat")
            .setFirstResult(start)
            .setMaxResults(limit)
            .getResultList();
    }



    public int getCount() {
        return ((Long)em.createQuery("SELECT COUNT(e) FROM Facture e")
            .getSingleResult()).intValue();
    }

    public int getCountByProjet(String nom ){
        System.out.print(nom);
        return ((Long) em.createQuery("SELECT COUNT(j) from Facture  j WHERE j.projet.nom=:nom")
            .setParameter("nom",nom)
            .getSingleResult()).intValue();
    }

    public int getCountByClient(String nom ){
        System.out.print(nom);
        return ((Long) em.createQuery("SELECT COUNT(j) from Facture  j WHERE j.projet.client.nom=:nom")
            .setParameter("nom",nom)
            .getSingleResult()).intValue();
    }

    public int getCountByEtat(String nom ){
        System.out.print(nom);
        return ((Long) em.createQuery("SELECT COUNT(j) from Facture  j WHERE j.etatFacture.etat=:nom")
            .setParameter("nom",nom)
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
