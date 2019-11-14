package montp.data.dao;

import montp.data.model.ResourceType;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@ApplicationScoped
public class ResourceTypeDAO extends GenericDAO<ResourceType> {
    @PersistenceContext

    private EntityManager em;

    public ResourceType get(int id) {
        return em.find(ResourceType.class, id);
    }

    public List<ResourceType> getAll() {
        return em.createQuery("SELECT e FROM ResourceType e")
            .getResultList();
    }

    public int getCount() {
        return ((Long)em.createQuery("SELECT COUNT(e) FROM ResourceType e")
            .getSingleResult()).intValue();
    }

    @Transactional
    public void insert(ResourceType res) {
        em.persist(res);
    }

    @Transactional
    public void update(ResourceType res) {
        em.merge(res);
    }

    @Transactional
    public void delete(ResourceType res) {
        em.remove(res);
    }

}
