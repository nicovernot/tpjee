package montp.data.dao;

import montp.data.model.Client;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ClientDAO extends GenericDAO<Client> {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<Client> ClientEvent;

    public Client get(int id) {
        return em.find(Client.class, id);
    }

    public ClientDAO() { super(Client.class);}

    public List<Client> getAll() {
        return em.createQuery("SELECT e FROM Client e")
            .getResultList();
    }


    public int getCount() {
        return ((Long)em.createQuery("SELECT COUNT(e) FROM Client e")
            .getSingleResult()).intValue();
    }

    @Transactional
    public void insert(Client res) {
        em.persist(res);
        ClientEvent.fire(res);
    }

    @Transactional
    public void update(Client res) {
        em.merge(res);
        ClientEvent.fire(res);
    }

    @Transactional
    public void delete(Client res) {
        res = get(res.getId());
        em.remove(res);
        ClientEvent.fire(res);
    }

}
