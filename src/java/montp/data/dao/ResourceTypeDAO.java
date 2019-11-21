package montp.data.dao;

import montp.data.model.ResourceType;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@ApplicationScoped
public class ResourceTypeDAO extends GenericDAO<ResourceType> {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<ResourceType> resourceTypeEvent;

    public ResourceType get(int id) {
        return em.find(ResourceType.class, id);
    }

    public ResourceTypeDAO() { super(ResourceType.class);}

    public List<ResourceType> getAll() {
        return em.createQuery("SELECT e FROM ResourceType e")
            .getResultList();
    }

    public Boolean isEmpty(ResourceType resourceType){
try {
    return ((Long) em.createQuery(
        "SELECT COUNT(e) FROM Resource e WHERE e.resourceType = :j")
        .setParameter("j", resourceType)
        .getSingleResult()) > 0;

} catch (NoResultException e){
    return null;
}

    }

    public int getCount() {
        return ((Long)em.createQuery("SELECT COUNT(e) FROM ResourceType e")
            .getSingleResult()).intValue();
    }

    @Transactional
    public void insert(ResourceType res) {
        em.persist(res);
        resourceTypeEvent.fire(res);
    }

    @Transactional
    public void update(ResourceType res) {
        em.merge(res);
        resourceTypeEvent.fire(res);
    }

    @Transactional
    public void delete(ResourceType res) {
        res = get(res.getId());
        em.remove(res);
        resourceTypeEvent.fire(res);
    }

}
