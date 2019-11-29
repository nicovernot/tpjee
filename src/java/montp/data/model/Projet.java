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

    public Projet(String nom, String prenom, Utilisateur utilisateur, StatuProjet statuProjet, Client client) {
        this.nom = nom;
        this.prenom = prenom;
        this.utilisateur = utilisateur;
        this.statuProjet = statuProjet;
        this.client = client;
    }

    //
  // Methods
  //


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public StatuProjet getStatuProjet() {
        return statuProjet;
    }

    public void setStatuProjet(StatuProjet statuProjet) {
        this.statuProjet = statuProjet;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Projet{" +
            "nom='" + nom + '\'' +
            ", prenom='" + prenom + '\'' +
            '}';
    }
}
