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

  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of projet
   * @param newVar the new value of projet
   */
  private void setProjet (Projet newVar) {
    projet = newVar;
  }

  /**
   * Get the value of projet
   * @return the value of projet
   */
  private Projet getProjet () {
    return projet;
  }

  /**
   * Set the value of dateEdition
   * @param newVar the new value of dateEdition
   */
  private void setDateEdition (Date newVar) {
    dateEdition = newVar;
  }

  /**
   * Get the value of dateEdition
   * @return the value of dateEdition
   */
  private Date getDateEdition () {
    return dateEdition;
  }

  /**
   * Set the value of datePaiement
   * @param newVar the new value of datePaiement
   */
  private void setDatePaiement (Date newVar) {
    datePaiement = newVar;
  }

  /**
   * Get the value of datePaiement
   * @return the value of datePaiement
   */
  private Date getDatePaiement () {
    return datePaiement;
  }

  /**
   * Set the value of notePage
   * @param newVar the new value of notePage
   */
  private void setNotePage (String newVar) {
    notePage = newVar;
  }

  /**
   * Get the value of notePage
   * @return the value of notePage
   */
  private String getNotePage () {
    return notePage;
  }

  /**
   * Set the value of etatFacture
   * @param newVar the new value of etatFacture
   */
  private void setEtatFacture (EtatFacture newVar) {
    etatFacture = newVar;
  }

  /**
   * Get the value of etatFacture
   * @return the value of etatFacture
   */
  private EtatFacture getEtatFacture () {
    return etatFacture;
  }

  /**
   * Set the value of typePaiement
   * @param newVar the new value of typePaiement
   */
  private void setTypePaiement (TypePaiement newVar) {
    typePaiement = newVar;
  }

  /**
   * Get the value of typePaiement
   * @return the value of typePaiement
   */
  private TypePaiement getTypePaiement () {
    return typePaiement;
  }

    public List<LignesFacturation> getLignesFacturation() {
        return lignesFacturation;
    }

    public void setLignesFacturation(List<LignesFacturation> lignesFacturation) {
        this.lignesFacturation = lignesFacturation;
    }
}
