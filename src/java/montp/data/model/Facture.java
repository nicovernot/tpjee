package montp.data.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

/**
 * Class Facture
 */
@Entity
public class Facture extends GenericEntity {

  //
  // Fields
  //
  @ManyToOne
  private Projet projet;
  private Date dateEdition;
  private Date datePaiement;
  private String notePage;
  @ManyToOne
  private EtatFacture etatFacture;
  @ManyToOne
  private TypePaiement typePaiement;
  @OneToMany
  private List<LignesFacturation> lignesFacturation;

  //
  // Constructors
  //
  public Facture () { };

    public Facture(Projet projet, Date dateEdition, Date datePaiement, String notePage, EtatFacture etatFacture, TypePaiement typePaiement, List<LignesFacturation> lignesFacturation) {
        this.projet = projet;
        this.dateEdition = dateEdition;
        this.datePaiement = datePaiement;
        this.notePage = notePage;
        this.etatFacture = etatFacture;
        this.typePaiement = typePaiement;
        this.lignesFacturation = lignesFacturation;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public Date getDateEdition() {
        return dateEdition;
    }

    public void setDateEdition(Date dateEdition) {
        this.dateEdition = dateEdition;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    public String getNotePage() {
        return notePage;
    }

    public void setNotePage(String notePage) {
        this.notePage = notePage;
    }

    public EtatFacture getEtatFacture() {
        return etatFacture;
    }

    public void setEtatFacture(EtatFacture etatFacture) {
        this.etatFacture = etatFacture;
    }

    public TypePaiement getTypePaiement() {
        return typePaiement;
    }

    public void setTypePaiement(TypePaiement typePaiement) {
        this.typePaiement = typePaiement;
    }

    public List<LignesFacturation> getLignesFacturation() {
        return lignesFacturation;
    }

    public void setLignesFacturation(List<LignesFacturation> lignesFacturation) {
        this.lignesFacturation = lignesFacturation;
    }

    @Override
    public String toString() {
        return "Facture{" +
            "etatFacture=" + etatFacture +
            '}';
    }
}
