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

    public Date getDateNaisssance() {
        return dateNaisssance;
    }

    public void setDateNaisssance(Date dateNaisssance) {
        this.dateNaisssance = dateNaisssance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Double getCa() {
        return ca;
    }

    public void setCa(Double ca) {
        this.ca = ca;
    }

    public Integer getTauxCharges() {
        return tauxCharges;
    }

    public void setTauxCharges(Integer tauxCharges) {
        this.tauxCharges = tauxCharges;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
            "nom='" + nom + '\'' +
            ", prenom='" + prenom + '\'' +
            '}';
    }
}
