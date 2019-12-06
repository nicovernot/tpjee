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
import java.util.HashMap;
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
    private Double total;

    @PostConstruct
    public void init() {

        rst = res.getAll();
        projetList = projetDAO.getAll();
        typePaiementList = typePaiementDAO.getAll();
        etatFactureList = etatFactureDAO.getAll();
        lignesFacturation = new LignesFacturation();
         total = 0.00;
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
            setTotal(total);
            lignesFacturationList = ligneFacturationDAO.getAllByFacture(facture);

        } catch (TransactionalException txe) {
            FacesTools.addMessage(FacesMessage.SEVERITY_ERROR,
                messages.get("app.delete.error"),
                lignesFacturation);
        }
    }
public void getResult(){
        setTotal(total);
        Double total1 ;
        total1=0.00;
    for (LignesFacturation facturation : lignesFacturationList) {
        total1= facturation.getQuantite()*facturation.getPrixUnitaire()+total1;
         }
    System.out.print("total"+total1);
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

    public void removeLine(LignesFacturation lignesFacturation){
    lignesFacturationList.remove(lignesFacturation);
    System.out.print("remove"+lignesFacturationList.size());
    }

    public void save() {
        if (facture.getId() == null) {
            facture.setEtatFacture(etatFacture);
            facture.setProjet(projet);
            facture.setTypePaiement(typePaiement);
            res.insert(facture);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "facture créé");
        } else {
            facture.setEtatFacture(etatFacture);

            facture.setTypePaiement(typePaiement);
                  res.update(facture);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Modifications enregistrées");
        }
    }

    public Boolean ispaye(Facture facture){
        return res.esPaye(facture);
    }

    public Boolean aLignes(Facture facture){
        return res.alignes(facture);
    }

    public void saveLigne() {
        if (lignesFacturation.getId() == null) {
            lignesFacturation.setFacture(facture);
            ligneFacturationDAO.insert(lignesFacturation);
            lignesFacturationList = ligneFacturationDAO.getAllByFacture(facture);
            lignesFacturation = null;
            setTotal(total);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "ligne créé");
        } else {
            ligneFacturationDAO.update(lignesFacturation);

            setTotal(total);
            lignesFacturationList = ligneFacturationDAO.getAllByFacture(facture);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Modifications enregistrées");
            lignesFacturation=null;
        }
    }

    public Facture getFacture(){

        return facture;
    }

    public void setFacture(Facture facture) {
        lignesFacturationList = ligneFacturationDAO.getAllByFacture(facture);
        setTotal(total);
        projet=facture.getProjet();
        etatFacture = facture.getEtatFacture();
        typePaiement=facture.getTypePaiement();
        this.facture = facture;

    }

    public List<Facture> getFactures() {
        return res.getAll();
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

    public void setEtatFacture(EtatFacture etatFacture)  {
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

    public Double getTotal() {

        return total;
    }

    public void setTotal(Double total) {
         total=0.00;
        for (LignesFacturation facturation : lignesFacturationList) {
            total= facturation.getQuantite()*facturation.getPrixUnitaire()+total;
        }
        this.total = total;
    }
}
