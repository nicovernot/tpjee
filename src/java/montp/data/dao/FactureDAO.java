package montp.data.dao;

import montp.data.model.Facture;
import montp.data.model.Utilisateur;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.faces.context.FacesContext;
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
    @Inject
    UtilisateurDAO utilisateurDAO;
    Utilisateur utilisateur;


    public Facture get(int id) {
        return em.find(Facture.class, id);
    }

    public FactureDAO() { super(Facture.class);}

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

    public List<Facture> getAll() {
        return em.createQuery("SELECT e FROM Facture e where e.projet.utilisateur=:user")
            .setParameter("user",utilisateur)
            .getResultList();
    }

    public List<Facture> get(int satrt,int limit, String nom){
        return  em.createQuery("SELECT j from Facture j WHERE j.projet.nom=:nom and j.projet.utilisateur=:user ")
            .setParameter("nom",nom)
            .setParameter("user",utilisateur)
            .getResultList();
    }

    public List<Facture> getbyEtat(int satrt,int limit, String nom){
        return  em.createQuery("SELECT j from Facture j WHERE j.etatFacture.etat=:nom and j.projet.utilisateur=:user ")
            .setParameter("nom",nom)
            .setParameter("user",utilisateur)
            .getResultList();
    }

    public List<Facture> get(int start, int limit) {
        return em.createQuery("SELECT j FROM Facture j where j.projet.utilisateur=:user ORDER BY j.etatFacture.etat")
            .setParameter("user",utilisateur)
            .setFirstResult(start)
            .setMaxResults(limit)
            .getResultList();
    }

    public boolean esPaye(Facture facture){
return ((Long) em.createQuery("select count (e) from Facture e where e.etatFacture.etat='payée' and e=:fact")
.setParameter("fact",facture)
.getSingleResult()>0);
 }


    public boolean alignes(Facture facture){
        return ((Long) em.createQuery("select count (e) from LignesFacturation e where e.facture=:fact")
            .setParameter("fact",facture)
            .getSingleResult()>0);
    }


    public int getCount() {
        return ((Long)em.createQuery("SELECT COUNT(e) FROM Facture e where e.projet.utilisateur=:user")
            .setParameter("user",utilisateur)
            .getSingleResult()).intValue();
    }

    public int getCountByProjet(String nom ){
        System.out.print(nom);
        return ((Long) em.createQuery("SELECT COUNT(j) from Facture  j WHERE j.projet.nom=:nom and j.projet.utilisateur=:user")
            .setParameter("nom",nom)
            .setParameter("user",utilisateur)
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
