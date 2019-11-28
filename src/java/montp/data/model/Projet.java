package montp.data.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Class Projet
 */
@Entity
public class Projet extends GenericEntity {

  //
  // Fields
  //

  private String nom;
  private String prenom;
  @ManyToOne
  private Utilisateur utilisateur;
  @ManyToOne
  private StatuProjet statuProjet;
  @ManyToOne
  private Client client;

  //
  // Constructors
  //
  public Projet () { };

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
   * Set the value of utilisateur
   * @param newVar the new value of utilisateur
   */
  private void setUtilisateur (Utilisateur newVar) {
    utilisateur = newVar;
  }

  /**
   * Get the value of utilisateur
   * @return the value of utilisateur
   */
  private Utilisateur getUtilisateur () {
    return utilisateur;
  }

  /**
   * Set the value of statuProjet
   * @param newVar the new value of statuProjet
   */
  private void setStatuProjet (StatuProjet newVar) {
    statuProjet = newVar;
  }

  /**
   * Get the value of statuProjet
   * @return the value of statuProjet
   */
  private StatuProjet getStatuProjet () {
    return statuProjet;
  }

  /**
   * Set the value of client
   * @param newVar the new value of client
   */
  private void setClient (Client newVar) {
    client = newVar;
  }

  /**
   * Get the value of client
   * @return the value of client
   */
  private Client getClient () {
    return client;
  }

  //
  // Other methods
  //

    @Override
    public String toString() {
        return "Projet{" +
            "nom='" + nom + '\'' +
            ", prenom='" + prenom + '\'' +
            '}';
    }
}
