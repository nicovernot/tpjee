package montp.web.controllers;

import montp.data.dao.EtatFactureDAO;
import montp.data.dao.FactureDAO;
import montp.data.dao.ProjetDAO;
import montp.data.dao.TypePaiementDAO;
import montp.data.model.*;
import montp.data.model.Facture;
import montp.locale.Messages;
import montp.services.FacturePaginator;
import montp.web.FacesTools;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.TransactionalException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@ViewScoped
@Named
public class FactureView implements Serializable {

    @Inject
    private FactureDAO res;
    private List<Facture> rst;
    private Messages messages;
    private Facture facture;
    @Inject
    private FacturePaginator facturePaginator;
    @Inject
    private TypePaiementDAO typePaiementDAO;
    private TypePaiement typePaiement;
    @Inject
    private EtatFactureDAO etatFactureDAO;
    private EtatFacture etatFacture;
    @Inject
    private ProjetDAO projetDAO;
    private Projet projet;
    private List<Projet> projetList;
    private List<TypePaiement> typePaiementList;
    private List<EtatFacture> etatFactureList;

    @PostConstruct
    public void init() {

        rst = res.getAll();
        projetList = projetDAO.getAll();
        typePaiementList = typePaiementDAO.getAll();
        etatFactureList = etatFactureDAO.getAll();

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
        etatFacture =new EtatFacture();
        typePaiement =new TypePaiement();
        projet = new Projet();
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

    public FacturePaginator getFacturePaginator() {
        return facturePaginator;
    }

    public void setFacturePaginator(FacturePaginator facturePaginator) {
        this.facturePaginator = facturePaginator;
    }

    public TypePaiement getTypePaiement() {
        return typePaiement;
    }

    public void setTypePaiement(TypePaiement typePaiement) {
        this.typePaiement = typePaiement;
    }

    public EtatFacture getEtatFacture() {
        return etatFacture;
    }

    public void setEtatFacture(EtatFacture etatFacture) {
        this.etatFacture = etatFacture;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public List<Projet> getProjetList() {
        return projetList;
    }

    public void setProjetList(List<Projet> projetList) {
        this.projetList = projetList;
    }

    public List<TypePaiement> getTypePaiementList() {
        return typePaiementList;
    }

    public void setTypePaiementList(List<TypePaiement> typePaiementList) {
        this.typePaiementList = typePaiementList;
    }

    public List<EtatFacture> getEtatFactureList() {
        return etatFactureList;
    }

    public void setEtatFactureList(List<EtatFacture> etatFactureList) {
        this.etatFactureList = etatFactureList;
    }
}
