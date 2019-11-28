package montp.data.model;

import javax.persistence.Entity;

/**
 * Class LignesFacturation
 */
@Entity
public class LignesFacturation extends GenericEntity {

  //
  // Fields
  //

  private String libelle;
  private Double prixUnitaire;
  private Integer quantite;

  //
  // Constructors
  //
  public LignesFacturation () { };

  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of libelle
   * @param newVar the new value of libelle
   */
  private void setLibelle (String newVar) {
    libelle = newVar;
  }

  /**
   * Get the value of libelle
   * @return the value of libelle
   */
  private String getLibelle () {
    return libelle;
  }

  /**
   * Set the value of prixUnitaire
   * @param newVar the new value of prixUnitaire
   */
  private void setPrixUnitaire (Double newVar) {
    prixUnitaire = newVar;
  }

  /**
   * Get the value of prixUnitaire
   * @return the value of prixUnitaire
   */
  private Double getPrixUnitaire () {
    return prixUnitaire;
  }

  /**
   * Set the value of quantite
   * @param newVar the new value of quantite
   */
  private void setQuantite (Integer newVar) {
    quantite = newVar;
  }

  /**
   * Get the value of quantite
   * @return the value of quantite
   */
  private Integer getQuantite () {
    return quantite;
  }

  //
  // Other methods
  //

    @Override
    public String toString() {
        return "LignesFacturation{" +
            "libelle='" + libelle + '\'' +
            '}';
    }
}
