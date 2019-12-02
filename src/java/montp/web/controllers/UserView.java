package montp.web.controllers;


import montp.data.dao.UserDAO;
import montp.data.model.Utilisateur;
import montp.data.model.security.User;
import montp.web.FacesTools;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named
public class UserView implements Serializable {
    private User user;
    private Utilisateur utilisateur;
    @Inject
    private UserDAO userDAO;

    @PostConstruct
    public void  init (){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void  create(){
        user= new User();
    }

    public void save() {
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

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
