package montp.data.dao;


import montp.data.model.Client;
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
public class ClientDAO extends GenericDAO<Client> {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private Event<Client> ClientEvent;
    @Inject
    private
    UtilisateurDAO utilisateurDAO;
    private Utilisateur utilisateur;
    public Client get(int id) {
        return em.find(Client.class, id);
    }

    public ClientDAO() { super(Client.class);}

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

    public List<Client> getAll() {
        return em.createQuery("SELECT e FROM Client e where e.utilisateur=:user")
            .setParameter("user",utilisateur)
            .getResultList();
    }

    public List<Client> get(int start, int limit) {
        return em.createQuery("SELECT j FROM Client j where  j.utilisateur=:user ORDER BY j.nom, j.email")
            .setParameter("user",utilisateur)
            .setFirstResult(start)
            .setMaxResults(limit)
            .getResultList();
    }

    public List<Client> get(int satrt,int limit, String nom){
        return  em.createQuery("SELECT j from Client j WHERE j.nom=:nom and j.utilisateur=:user")
            .setParameter("nom",nom)
            .setParameter("user",utilisateur)
            .getResultList();
    }

    public Boolean isEmptyAdresse(Client client) {
        try {
            return ((Long) em.createQuery(
                "SELECT COUNT(e.adresse) FROM Client e WHERE e.id= :j")
                .setParameter("j", client.getId())
                .getSingleResult()) == 0;

        } catch (NoResultException e) {
            return null;
        }
    }

    public int getCountByNom(String nom ){
        System.out.print(nom);
        return ((Long) em.createQuery("SELECT COUNT(j) from Client  j WHERE j.nom=:nom and j.utilisateur=:user" )
            .setParameter("nom",nom)
            .setParameter("user",utilisateur)
            .getSingleResult()).intValue();
    }

    public int getCountbyMail(String nom ){
        System.out.print(nom);
        return ((Long) em.createQuery("SELECT COUNT(j) from Client  j WHERE j.email=:nom")
            .setParameter("nom",nom)
            .getSingleResult()).intValue();
    }
    public int getCountByTel(String nom ){
        System.out.print(nom);
        return ((Long) em.createQuery("SELECT COUNT(j) from Client  j WHERE j.tel=:nom")
            .setParameter("nom",nom)
            .getSingleResult()).intValue();
    }

    public int getCountbyContact(String nom ){
        System.out.print(nom);
        return ((Long) em.createQuery("SELECT COUNT(j) from Client  j WHERE j.nomContact=:nom")
            .setParameter("nom",nom)
            .getSingleResult()).intValue();
    }
    public int getCountByVille(String ville){
        return ((Long) em.createQuery("SELECT COUNT(j) from Client j WHERE j.adresse.ville=:ville")
            .setParameter("ville",ville)
            .getSingleResult()).intValue();
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
