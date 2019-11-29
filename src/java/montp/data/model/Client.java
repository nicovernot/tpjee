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

    public Client() {
    }

    public Client(String nom, String nomContact, String prenom, String tel, String email, Adresse adresse) {
        this.nom = nom;
        this.nomContact = nomContact;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;
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

    @Override
    public String toString() {
        return "Client{" +
            "nom='" + nom + '\'' +
            ", prenom='" + prenom + '\'' +
            '}';
    }
}
