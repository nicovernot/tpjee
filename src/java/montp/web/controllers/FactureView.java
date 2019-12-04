package montp.web.controllers;

import montp.data.dao.*;
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
    @Inject
    private LigneFacturationDAO ligneFacturationDAO;
    private LignesFacturation lignesFacturation;
    private List<LignesFacturation> lignesFacturationList;
    @PostConstruct
    public void init() {

        rst = res.getAll();
        projetList = projetDAO.getAll();
        typePaiementList = typePaiementDAO.getAll();
        etatFactureList = etatFactureDAO.getAll();
        lignesFacturationList = ligneFacturationDAO.getAll();
        lignesFacturation = new LignesFacturation();
    }


    public Messages getMessages() {
        return messages;
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

    public void deleteLigne(LignesFacturation lignesFacturation) {
        try {
            ligneFacturationDAO.delete(lignesFacturation);
        } catch (TransactionalException txe) {
            FacesTools.addMessage(FacesMessage.SEVERITY_ERROR,
                messages.get("app.delete.error"),
                lignesFacturation);
        }
    }


    public void  create(){
        projet = new Projet();
        typePaiement = new TypePaiement();
        etatFacture = new EtatFacture();
        facture = new Facture();

    }

    public String reinit() {
        lignesFacturation = new LignesFacturation();
        lignesFacturationList.add(lignesFacturation);

        return null;
    }
    public void save() {
        if (facture.getId() == null) {
            System.out.print("id null");
            facture.setEtatFacture(etatFacture);
            facture.setProjet(projet);
            facture.setTypePaiement(typePaiement);
            res.insert(facture);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "facture créé");
        } else {
            facture.setEtatFacture(etatFacture);
            facture.setProjet(projet);
            facture.setTypePaiement(typePaiement);
            res.update(facture);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Modifications enregistrées");
        }
    }

    public void saveLigne() {
        if (lignesFacturation.getId() == null) {
            lignesFacturation.setFacture(facture);
            ligneFacturationDAO.insert(lignesFacturation);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "ligne créé");
        } else {
            ligneFacturationDAO.update(lignesFacturation);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Modifications enregistrées");
        }
    }

    public Facture getFacture(){

        return facture;
    }

    public void setFacture(Facture facture) {
        if(facture==null)
            create();
        this.facture = facture;

    }

    public List<Facture> getFactures() {
        return res.getAll();
    }



    public void updateLigne(Facture facture) {
        try {
            ligneFacturationDAO.update(lignesFacturation);
        } catch (TransactionalException txe) {
            FacesTools.addMessage(FacesMessage.SEVERITY_ERROR,
                "Impossible de metre à jour la ligne %s car il fait partie des resources de ce type",
                lignesFacturation);
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

    public LignesFacturation getLignesFacturation() {
        return lignesFacturation;
    }

    public void setLignesFacturation(LignesFacturation lignesFacturation) {
        this.lignesFacturation = lignesFacturation;
    }

    public List<LignesFacturation> getLignesFacturationList() {
        return lignesFacturationList;
    }

    public void setLignesFacturationList(List<LignesFacturation> lignesFacturationList) {
        this.lignesFacturationList = lignesFacturationList;
    }

    public List<Facture> getRst() {
        return rst;
    }

    public void setRst(List<Facture> rst) {
        this.rst = rst;
    }
}
