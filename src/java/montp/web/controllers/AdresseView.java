package montp.web.controllers;

import montp.data.dao.AdresseDAO;
import montp.data.model.Adresse;
import montp.data.model.Adresse;
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
public class AdresseView {

    @Inject
    private AdresseDAO res;
    private List<Adresse> rst;
    private Messages messages;
    private  Adresse adresse;
    @PostConstruct
    public void init() {
        rst = res.getAll();
    }
    public Collection<Adresse> getRestyp() {

        return res.getAll();
    }




    public Messages getMessages() {
        return messages;
    }



    public int getCoutrestype(){
        return res.getCount();
    }

    public void delete(Adresse adresse) {
        try {
            res.delete(adresse);
        } catch (TransactionalException txe) {
            FacesTools.addMessage(FacesMessage.SEVERITY_ERROR,
                messages.get("app.delete.error"),
                adresse);
        }
    }
    public void  create(){
        adresse = new Adresse();
    }

    public void save() {
        if (adresse.getId() == null) {
            res.insert(adresse);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Adresse créé");
        } else {
            res.update(adresse);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Modifications enregistrées");
        }
    }

    public Adresse getAdresse(){
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public List<Adresse> getAdresses() {
        return res.getAll();
    }


    public void update(Adresse adresse) {
        try {

            res.update(adresse);
        } catch (TransactionalException txe) {
            FacesTools.addMessage(FacesMessage.SEVERITY_ERROR,
                "Impossible de metre à jour l'adresse %s car il fait partie il y a des resources de ce type",
                adresse);
        }
    }

}
