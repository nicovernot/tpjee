package montp.data.dao;


import montp.data.model.Resource;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class ResourceDao extends GenericDAO<Resource> {
  @PersistenceContext
  private EntityManager em;

    public Resource get(int id) {
        return em.find(Resource.class, id);
    }

    public List<Resource> getAll() {
        return em.createQuery("SELECT e FROM ResourceType e")
            .getResultList();
    }
    public int getCount(boolean isactif) {
        return ((Long) em.createQuery("SELECT COUNT(j) FROM Resource j WHERE j.capacite=:actif")
            .setParameter("actif", isactif)
            .getSingleResult()).intValue();
    }

    public int getCount() {
        return ((Long) em.createQuery("SELECT COUNT(j) FROM Resource j")
            .getSingleResult()).intValue();
    }


}
