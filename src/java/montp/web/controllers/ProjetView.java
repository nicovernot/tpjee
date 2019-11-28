package montp.web.controllers;

import montp.data.dao.ProjetDAO;
import montp.data.model.Projet;
import montp.data.model.Projet;
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
public class ProjetView {


    @Inject
    private ProjetDAO res;
    private List<Projet> rst;
    private Messages messages;
    private Projet projet;
    @PostConstruct
    public void init() {
        rst = res.getAll();
    }
    public Collection<Projet> getRestyp() {

        return res.getAll();
    }

    // public String Mes(String cle) {
    //    return messages.get(cle);
    // }


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
    }

    public void save() {
        if (projet.getId() == null) {
            res.insert(projet);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Projet créé");
        } else {
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
}
