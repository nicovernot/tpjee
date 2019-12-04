package montp.web.controllers;

import montp.data.dao.ClientDAO;
import montp.data.dao.ProjetDAO;
import montp.data.dao.StatuProjetDAO;
import montp.data.dao.UtilisateurDAO;
import montp.data.model.*;
import montp.data.model.Projet;
import montp.locale.Messages;
import montp.services.ProjetPaginator;
import montp.web.FacesTools;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.TransactionalException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@ViewScoped
@Named
public class ProjetView implements Serializable {


    @Inject
    private ProjetDAO res;
    private List<Projet> rst;
    private Messages messages;
    private Projet projet;
    @Inject
    ClientDAO clientDAO;
    @Inject
    StatuProjetDAO statuProjetDAO;
    @Inject
    UtilisateurDAO utilisateurDAO;
    @Inject
    ProjetPaginator projetPaginator;
    private Client client;
    private Utilisateur utilisateur;
    private StatuProjet statuProjet;
    private List<Client> clientList;
    private  List<StatuProjet> statuProjetList;

    @PostConstruct
    public void init() {
        rst = res.getAll();
        clientList = clientDAO.getAll();
        statuProjetList = statuProjetDAO.getAll();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        utilisateur = utilisateurDAO.getByName(facesContext.getExternalContext().getRemoteUser());
        System.out.print("rr" + utilisateur.getNom());

    }
    public Collection<Projet> getRestyp() {

        return res.getAll();
    }



    public Messages getMessages() {
        return messages;
    }



    public int getCoutrestype(){
        return res.getCount();
    }

    public void delete(Projet projet) {
        try {
            res.delete(projet);
        } catch (TransactionalException txe) {
            FacesTools.addMessage(FacesMessage.SEVERITY_ERROR,
                messages.get("app.delete.error"),
                projet);
        }
    }
    public void  create(){
        projet = new Projet();
        client = new Client();
        statuProjet = new StatuProjet();
    }

    public  void creatUser(){
        utilisateur = new Utilisateur();
    }

    public  void createClient(){
        client = new Client();
    }

    public  void  createStatus(){
        statuProjet = new StatuProjet();
    }

    public void save() {
        if (projet.getId() == null) {
            projet.setUtilisateur(utilisateur);
            projet.setStatuProjet(statuProjet);
            projet.setClient(client);
            res.insert(projet);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Projet créé");
        } else {
            projet.setStatuProjet(statuProjet);
            projet.setClient(client);
            res.update(projet);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Modifications enregistrées");
        }
    }

    public Projet getProjet(){

        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public List<Projet> getProjets() {
        return res.getAll();
    }


    public void update(Projet projet) {
        try {

            res.update(projet);
        } catch (TransactionalException txe) {
            FacesTools.addMessage(FacesMessage.SEVERITY_ERROR,
                "Impossible de metre à jour la Projet %s car il fait partie il y a des resources de ce type",
                projet);
        }
    }

    public ProjetPaginator getProjetPaginator() {
        return projetPaginator;
    }

    public void setProjetPaginator(ProjetPaginator projetPaginator) {
        this.projetPaginator = projetPaginator;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public StatuProjet getStatuProjet() {
        return statuProjet;
    }

    public void setStatuProjet(StatuProjet statuProjet) {
        this.statuProjet = statuProjet;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientDAO.getAll();
    }

    public List<StatuProjet> getStatuProjetList() {
        return statuProjetList;
    }

    public void setStatuProjetList(List<StatuProjet> statuProjetList) {
        this.statuProjetList = statuProjetList;
    }


}
