package montp.data.model;

import montp.data.model.security.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

/**
 * Class Utilisateur
 */
@Entity
public class Utilisateur extends GenericEntity{

  //
  // Fields
  //

  private String nom;
  private String prenom;
  private Date dateNaisssance;
  private String email;
  private String tel;
  private Double ca;
  private Integer tauxCharges;
  @ManyToOne
  private Statut statut;
  @ManyToOne
  private Adresse adresse;
    @OneToOne
    private User user;

  //
  // Constructors
  //
  public Utilisateur () { };

  //
  // Methods
  //


  //
  // Accessor methods
  //


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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
   * Set the value of dateNaisssance
   * @param newVar the new value of dateNaisssance
   */
  private void setDateNaisssance (Date newVar) {
    dateNaisssance = newVar;
  }

  /**
   * Get the value of dateNaisssance
   * @return the value of dateNaisssance
   */
  private Date getDateNaisssance () {
    return dateNaisssance;
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
   * Set the value of ca
   * @param newVar the new value of ca
   */
  private void setCa (Double newVar) {
    ca = newVar;
  }

  /**
   * Get the value of ca
   * @return the value of ca
   */
  private Double getCa () {
    return ca;
  }

  /**
   * Set the value of tauxCharges
   * @param newVar the new value of tauxCharges
   */
  private void setTauxCharges (Integer newVar) {
    tauxCharges = newVar;
  }

  /**
   * Get the value of tauxCharges
   * @return the value of tauxCharges
   */
  private Integer getTauxCharges () {
    return tauxCharges;
  }

  /**
   * Set the value of statut
   * @param newVar the new value of statut
   */
  private void setStatut (Statut newVar) {
    statut = newVar;
  }

  /**
   * Get the value of statut
   * @return the value of statut
   */
  private Statut getStatut () {
    return statut;
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

    @Override
    public String toString() {
        return "Utilisateur{" +
            "nom='" + nom + '\'' +
            ", prenom='" + prenom + '\'' +
            '}';
    }
}
