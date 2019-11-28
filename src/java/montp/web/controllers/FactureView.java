package montp.web.controllers;

import montp.data.dao.FactureDAO;
import montp.data.model.Facture;
import montp.data.model.Facture;
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
public class FactureView {

    @Inject
    private FactureDAO res;
    private List<Facture> rst;
    private Messages messages;
    private Facture facture;
    @PostConstruct
    public void init() {
        rst = res.getAll();
    }
    public Collection<Facture> getRestyp() {
        return res.getAll();
    }

    public Messages getMessages() {
        return messages;
    }

    public int getCoutrestype(){
        return res.getCount();
    }

    public void delete(Facture facture) {
        try {
            res.delete(facture);
        } catch (TransactionalException txe) {
            FacesTools.addMessage(FacesMessage.SEVERITY_ERROR,
                messages.get("app.delete.error"),
                facture);
        }
    }
    public void  create(){
        facture = new Facture();
    }

    public void save() {
        if (facture.getId() == null) {
            res.insert(facture);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "facture créé");
        } else {
            res.update(facture);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Modifications enregistrées");
        }
    }

    public Facture getFacture(){
        return facture;
    }

    public void setFacture(Facture Facture) {
        this.facture = facture;
    }

    public List<Facture> getFactures() {
        return res.getAll();
    }


    public void update(Facture facture) {
        try {

            res.update(facture);
        } catch (TransactionalException txe) {
            FacesTools.addMessage(FacesMessage.SEVERITY_ERROR,
                "Impossible de metre à jour la Facture %s car il fait partie il y a des resources de ce type",
                facture);
        }
    }
}
