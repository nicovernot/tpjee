package montp.data.model;

import javax.persistence.Entity;

/**
 * Class EtatFacture
 */
@Entity
public class EtatFacture extends GenericEntity{

  //
  // Fields
  //

  private String etat;

  //
  // Constructors
  //
  public EtatFacture () { };

  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of etat
   * @param newVar the new value of etat
   */
  private void setEtat (String newVar) {
    etat = newVar;
  }

  /**
   * Get the value of etat
   * @return the value of etat
   */
  private String getEtat () {
    return etat;
  }

  //
  // Other methods
  //

    @Override
    public String toString() {
        return "EtatFacture{" +
            "etat='" + etat + '\'' +
            '}';
    }
}
