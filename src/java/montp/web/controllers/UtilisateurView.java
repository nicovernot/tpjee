package montp.web.controllers;

import montp.data.dao.AdresseDAO;
import montp.data.dao.GroupDAO;
import montp.data.dao.UserDAO;
import montp.data.dao.UtilisateurDAO;
import montp.data.model.Adresse;
import montp.data.model.Utilisateur;
import montp.data.model.Utilisateur;
import montp.data.model.security.Group;
import montp.data.model.security.User;
import montp.locale.Messages;
import montp.tools.Tools;
import montp.web.FacesTools;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.TransactionalException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ViewScoped
@Named
public class UtilisateurView implements Serializable {

    @Inject
    private UtilisateurDAO res;
    private List<Utilisateur> rst;
    private Messages messages;
    private Utilisateur utilisateur;
    private User user;
     @Inject
    private UserDAO userDAO;
    @Inject
    AdresseDAO adresseDAO;
    private Adresse adresse;
    @Inject
    GroupDAO groupDAO;
    private Group group;
    @PostConstruct
    public void init() {
        rst = res.getAll();
    }
    public Collection<Utilisateur> getRestyp() {

        return res.getAll();
    }




    public Messages getMessages() {
        return messages;
    }


    public int getCoutrestype(){
        return res.getCount();
    }

    public void delete(Utilisateur utilisateur) {
        try {
            res.delete(utilisateur);
        } catch (TransactionalException txe) {
            FacesTools.addMessage(FacesMessage.SEVERITY_ERROR,
                messages.get("app.delete.error"),
                utilisateur);
        }
    }

    public void  create(){
        utilisateur = new Utilisateur();
    }

    public void save() {
        if (utilisateur.getId() == null) {
            res.insert(utilisateur);

            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Compte créé");
        } else {
            res.update(utilisateur);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Modifications enregistrées");
        }
    }

    public Utilisateur getUtilisateur(){
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Utilisateur> getUtilisateurs() {
        return res.getAll();
    }


    public void update(Utilisateur utilisateur) {
        try {

            res.update(utilisateur);
        } catch (TransactionalException txe) {
            FacesTools.addMessage(FacesMessage.SEVERITY_ERROR,
                "Impossible de metre à jour l'Utilisateur %s car il fait partie il y a des resources de ce type",
                utilisateur);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void  createUser(){
        user= new User();
    }

    public void saveUser() {
        if (user.getId() == null) {

            userDAO.insert(user);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Utilisateur créé");
        } else {
            userDAO.update(user);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Modifications enregistrées");
        }
    }
    public void createAdresse(){
        adresse = new Adresse();
    }
    public void saveAdresse() {
        if (adresse.getId() == null) {
            adresseDAO.insert(adresse);
            utilisateur.setAdresse(adresse);
            user.setPassword(Tools.digestSHA256Hex(user.getPassword().trim()));
            List<Group> groupes = new ArrayList<>();
            Group groupUser = new Group("USER");
            groupes.add(groupUser);
            user.setGroups(groupes);
            userDAO.insert(user);
            utilisateur.setUser(user);
            res.update(utilisateur);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Compte créé");
        } else {
            adresseDAO.update(adresse);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Modifications enregistrées");
        }
    }

    public  void etapeUnAction(){
        createUser();
        user.setUserName(utilisateur.getEmail());
        System.out.print(user.getUserName());
    }
    public void  etapeDeuxAction(){
        createAdresse();
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}
