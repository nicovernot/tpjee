package montp.data.dao;

import montp.data.model.Facture;
import montp.data.model.LignesFacturation;
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
public class LigneFacturationDAO extends GenericDAO<LignesFacturation> {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<LignesFacturation> LignesFacturationEvent;
    @Inject
    private UtilisateurDAO utilisateurDAO;
    private Utilisateur utilisateur;

    @PostConstruct
    public void init(){
        getUtilisateur();
    }

    public LigneFacturationDAO() { super(LignesFacturation.class);}

    public Utilisateur getUtilisateur() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        utilisateur = utilisateurDAO.getByName(facesContext.getExternalContext().getRemoteUser());
        System.out.print("tpr" + utilisateur.getNom());
        return utilisateur;
    }

    public LignesFacturation get(int id) {
        return em.find(LignesFacturation.class, id);
    }



    public List<LignesFacturation> getAll() {
        return em.createQuery("SELECT e FROM LignesFacturation e")
            .getResultList();
    }
    public List<LignesFacturation> getPaye(){
        return em.createQuery("select e from LignesFacturation e where e.facture.etatFacture='payée' and e.facture.projet.utilisateur=:user ")
            .setParameter("user",utilisateur)
            .getResultList();
    }

    public List<LignesFacturation> getAllByFacture(Facture facture) {
        return em.createQuery("SELECT e FROM LignesFacturation e where e.facture=:fact")
            .setParameter("fact",facture)
            .getResultList();
    }

    public int getCount() {
        return ((Long)em.createQuery("SELECT -- COUNT(e) FROM LignesFacturation e")
            .getSingleResult()).intValue();
    }

    public double total(Facture facture){
        return (double) em.createQuery("select (e.quantite*e.prixUnitaire)  as result from LignesFacturation e where e.facture=:fact")
                     .setParameter("fact",facture)
            .getSingleResult();
    }

    @Transactional
    public void insert(LignesFacturation res) {
        em.persist(res);
        LignesFacturationEvent.fire(res);
    }

    @Transactional
    public void update(LignesFacturation res) {
        em.merge(res);
        LignesFacturationEvent.fire(res);
    }

    @Transactional
    public void delete(LignesFacturation res) {
        res = get(res.getId());
        em.remove(res);
        LignesFacturationEvent.fire(res);
    }
}
