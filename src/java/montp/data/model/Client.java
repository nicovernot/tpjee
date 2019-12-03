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
  private Boolean type;
  private String tel;
  private String email;
  @ManyToOne
  private Utilisateur utilisateur;
  @ManyToOne
  private Adresse adresse;

    public Client() {
    }

    public Client(String nom, String nomContact, String prenom, Boolean type, String tel, String email, Utilisateur utilisateur, Adresse adresse) {
        this.nom = nom;
        this.nomContact = nomContact;
        this.prenom = prenom;
        this.type = type;
        this.tel = tel;
        this.email = email;
        this.utilisateur = utilisateur;
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomContact() {
        return nomContact;
    }

    public void setNomContact(String nomContact) {
        this.nomContact = nomContact;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public String toString() {
        return nom;
    }
}
