package montp.data.model;

import javax.persistence.Entity;

/**
 * Class Adresse
 */
@Entity
public class Adresse extends GenericEntity{

  //
  // Fields
  //

  private String rue;
  private Integer numeroRue;
  private String codePostal;
  private String ville;

  //
  // Constructors
  //
  public Adresse () { };

  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of rue
   * @param newVar the new value of rue
   */
  private void setRue (String newVar) {
    rue = newVar;
  }

  /**
   * Get the value of rue
   * @return the value of rue
   */
  private String getRue () {
    return rue;
  }

  /**
   * Set the value of numeroRue
   * @param newVar the new value of numeroRue
   */
  private void setNumeroRue (Integer newVar) {
    numeroRue = newVar;
  }

  /**
   * Get the value of numeroRue
   * @return the value of numeroRue
   */
  private Integer getNumeroRue () {
    return numeroRue;
  }

  /**
   * Set the value of codePostal
   * @param newVar the new value of codePostal
   */
  private void setCodePostal (String newVar) {
    codePostal = newVar;
  }

  /**
   * Get the value of codePostal
   * @return the value of codePostal
   */
  private String getCodePostal () {
    return codePostal;
  }

  /**
   * Set the value of ville
   * @param newVar the new value of ville
   */
  private void setVille (String newVar) {
    ville = newVar;
  }

  /**
   * Get the value of ville
   * @return the value of ville
   */
  private String getVille () {
    return ville;
  }

  //
  // Other methods
  //

    @Override
    public String toString() {
        return "Adresse{" +
            "rue='" + rue + '\'' +
            ", numeroRue=" + numeroRue +
            ", codePostal='" + codePostal + '\'' +
            ", ville='" + ville + '\'' +
            '}';
    }
}
