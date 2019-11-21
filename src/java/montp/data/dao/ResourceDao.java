package montp.data.dao;


import montp.data.model.Resource;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ResourceDao extends GenericDAO<Resource> {
  @PersistenceContext
  private EntityManager em;
    @Inject
    private Event<Resource> resourceEvent;

    public Resource get(int id) {
        return em.find(Resource.class, id);
    }
    public ResourceDao(){super(Resource.class);}
    public List<Resource> get(int start, int limit, boolean isactif) {
        return em.createQuery("SELECT j FROM Resource j ORDER BY j.nom, j.capacite")

            .setFirstResult(start)
            .setMaxResults(limit)
            .getResultList();
    }

    public List<Resource> get(int start, int limit) {
        return em.createQuery("SELECT j FROM Resource j ORDER BY j.nom, j.capacite")
            .setFirstResult(start)
            .setMaxResults(limit)
            .getResultList();
    }

    public List<Resource> get(int satrt,int limit, String nom){
     return  em.createQuery("SELECT j from Resource j WHERE j.nom=:nom ")
     .setParameter("nom",nom)
     .getResultList();
    }
    public List<Resource> getAll() {
        return em.createQuery("SELECT e FROM Resource e")
            .getResultList();
    }


    public int getCount() {
        return ((Long) em.createQuery("SELECT COUNT(j) FROM Resource j")
            .getSingleResult()).intValue();
    }
    public int getCount(String nom ){
        System.out.print(nom);
        return (int) em.createQuery("SELECT j from Resource  j WHERE j.nom=:nom")
            .setParameter("nom",nom)
            .getSingleResult();
    }

    @Transactional
    public void delete(Resource res) {
        res = get(res.getId());
        em.remove(res);
        resourceEvent.fire(res);
    }

    @Transactional
    public void insert(Resource resource) {
        em.persist(resource);
        resourceEvent.fire(resource);
    }

    @Transactional
    public void update(Resource resource) {
        em.merge(resource);
        resourceEvent.fire(resource);
    }



}
