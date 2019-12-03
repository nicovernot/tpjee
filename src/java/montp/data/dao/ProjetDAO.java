package montp.data.dao;

import montp.data.model.Projet;
import montp.data.model.Utilisateur;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProjetDAO extends GenericDAO<Projet> {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<Projet> ProjetEvent;
    @Inject
    private UtilisateurDAO utilisateurDAO;
    private Utilisateur utilisateur;
    public Projet get(int id) {
        return em.find(Projet.class, id);
    }

    public ProjetDAO() { super(Projet.class);}

    @PostConstruct
    public void init(){
        getUtilisateur();
    }

    public Utilisateur getUtilisateur() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        utilisateur = utilisateurDAO.getByName(facesContext.getExternalContext().getRemoteUser());
        System.out.print("tpr" + utilisateur.getNom());
        return utilisateur;
    }

    public List<Projet> getAll() {
        return em.createQuery("SELECT e FROM Projet e where e.utilisateur=:user")
            .setParameter("user",utilisateur)
            .getResultList();
    }

    public List<Projet> get(int satrt,int limit, String nom){
        return  em.createQuery("SELECT j from Projet j WHERE j.nom=:nom and j.utilisateur=:user")
            .setParameter("nom",nom)
            .setParameter("user",utilisateur)
            .getResultList();
    }

    public List<Projet> getClient(int satrt,int limit, Integer cl_id){
        return  em.createQuery("SELECT j from Projet j WHERE j.client.id=:nom ")
            .setParameter("nom",cl_id)
            .getResultList();
    }

    public List<Projet> getStatus(int satrt,int limit, Integer cl_id){
        return  em.createQuery("SELECT j from Projet j WHERE j.statuProjet.id=:nom ")
            .setParameter("nom",cl_id)
            .getResultList();
    }

    public List<Projet> getUser(int satrt,int limit, Integer cl_id){
        return  em.createQuery("SELECT j from Projet j WHERE j.utilisateur.id=:nom ")
            .setParameter("nom",cl_id)
            .getResultList();
    }


    public List<Projet> get(int start, int limit) {
        return em.createQuery("SELECT j FROM Projet j where j.utilisateur=:user ORDER BY j.nom")
            .setParameter("user",utilisateur)
            .setFirstResult(start)
            .setMaxResults(limit)
            .getResultList();
    }


    public int getCount() {
        return ((Long)em.createQuery("SELECT COUNT(e) FROM Projet e where e.utilisateur=:user")
            .setParameter("user",utilisateur)
            .getSingleResult()).intValue();
    }
    public int getCountByNom(String nom ){
        return ((Long) em.createQuery("SELECT COUNT(j) from Projet  j WHERE j.nom=:nom and j.utilisateur=:user")
            .setParameter("nom",nom)
            .setParameter("user",utilisateur)
            .getSingleResult()).intValue();
    }


    public int getCountByClient(String nom ){
        System.out.print(nom);
        return ((Long) em.createQuery("SELECT COUNT(j) from Projet  j WHERE j.client.nom=:nom")
            .setParameter("nom",nom)
            .getSingleResult()).intValue();
    }

    public int getCountByStatut(String nom ){
        System.out.print(nom);
        return ((Long) em.createQuery("SELECT COUNT(j) from Projet  j WHERE j.statuProjet.type=:nom")
            .setParameter("nom",nom)
            .getSingleResult()).intValue();
    }

    @Transactional
    public void insert(Projet res) {
        em.persist(res);
        ProjetEvent.fire(res);
    }

    @Transactional
    public void update(Projet res) {
        em.merge(res);
        ProjetEvent.fire(res);
    }

    @Transactional
    public void delete(Projet res) {
        res = get(res.getId());
        em.remove(res);
        ProjetEvent.fire(res);
    }
}
