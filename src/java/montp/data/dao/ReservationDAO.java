package montp.data.dao;

import montp.data.model.Reservation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ReservationDAO extends GenericDAO<Reservation> {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<Reservation> reservationEvent;
    public List<Reservation> get(int start, int limit) {
        return em.createQuery("SELECT j FROM Reservation j ORDER BY j.dateDebut")
            .setFirstResult(start)
            .setMaxResults(limit)
            .getResultList();
    }

    public int getCount() {
        return ((Long) em.createQuery("SELECT COUNT(j) FROM Reservation j")
            .getSingleResult()).intValue();
    }

    public List<Reservation> getAll() {
        return em.createQuery("SELECT e FROM Reservation e")
            .getResultList();
    }
    @Transactional
    public void delete(Reservation res) {
        res = get(res.getId());
        em.remove(res);
        reservationEvent.fire(res);
    }

    @Transactional
    public void insert(Reservation reservation) {
        em.persist(reservation);
        reservationEvent.fire(reservation);
    }

    @Transactional
    public void update(Reservation resource) {
        em.merge(resource);
        reservationEvent.fire(resource);
    }

}
