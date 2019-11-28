package montp.data.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Class Client
 */
@Entity
public class Client extends GenericEntity{

  //
  // Fields
  //

  private String nom;
  private String nomContact;
  private String prenom;
  private String tel;
  private String email;
  @ManyToOne
  private Adresse adresse;

  //
  // Constructors
  //
  public Client () { };

  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of nom
   * @param newVar the new value of nom
   */
  private void setNom (String newVar) {
    nom = newVar;
  }

  /**
   * Get the value of nom
   * @return the value of nom
   */
  private String getNom () {
    return nom;
  }

  /**
   * Set the value of nomContact
   * @param newVar the new value of nomContact
   */
  private void setNomContact (String newVar) {
    nomContact = newVar;
  }

  /**
   * Get the value of nomContact
   * @return the value of nomContact
   */
  private String getNomContact () {
    return nomContact;
  }

  /**
   * Set the value of prenom
   * @param newVar the new value of prenom
   */
  private void setPrenom (String newVar) {
    prenom = newVar;
  }

  /**
   * Get the value of prenom
   * @return the value of prenom
   */
  private String getPrenom () {
    return prenom;
  }

  /**
   * Set the value of tel
   * @param newVar the new value of tel
   */
  private void setTel (String newVar) {
    tel = newVar;
  }

  /**
   * Get the value of tel
   * @return the value of tel
   */
  private String getTel () {
    return tel;
  }

  /**
   * Set the value of email
   * @param newVar the new value of email
   */
  private void setEmail (String newVar) {
    email = newVar;
  }

  /**
   * Get the value of email
   * @return the value of email
   */
  private String getEmail () {
    return email;
  }

  /**
   * Set the value of adresse
   * @param newVar the new value of adresse
   */
  private void setAdresse (Adresse newVar) {
    adresse = newVar;
  }

  /**
   * Get the value of adresse
   * @return the value of adresse
   */
  private Adresse getAdresse () {
    return adresse;
  }

  //
  // Other methods
  //

}
