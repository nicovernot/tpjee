package montp.web.controllers;

import montp.data.dao.UtilisateurDAO;
import montp.data.model.Utilisateur;
import montp.data.model.Utilisateur;
import montp.locale.Messages;
import montp.web.FacesTools;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.TransactionalException;
import java.util.Collection;
import java.util.List;

@ViewScoped
@Named
public class UtilisateurView {

    @Inject
    private UtilisateurDAO res;
    private List<Utilisateur> rst;
    private Messages messages;
    private Utilisateur utilisateur;
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
                "Utilisateur créé");
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
}
