package montp.data.model;

import javax.persistence.Entity;

/**
 * Class TypePaiement
 */
@Entity
public class TypePaiement extends GenericEntity {

  //
  // Fields
  //

  private String typePaiement;

  //
  // Constructors
  //
  public TypePaiement () { };

  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of typePaiement
   * @param newVar the new value of typePaiement
   */
  private void setTypePaiement (String newVar) {
    typePaiement = newVar;
  }

  /**
   * Get the value of typePaiement
   * @return the value of typePaiement
   */
  private String getTypePaiement () {
    return typePaiement;
  }

  //
  // Other methods
  //

    @Override
    public String toString() {
        return "TypePaiement{" +
            "typePaiement='" + typePaiement + '\'' +
            '}';
    }
}
