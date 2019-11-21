package montp.data.model;

import montp.data.model.security.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Person extends GenericEntity {
@Column(length=40)
private String nom;
@Column(length=40)
private  String prenom;
@OneToOne
private User user;


    public Person() {
    }

    public Person(String nom, String prenom, User user) {
        this.nom = nom;
        this.prenom = prenom;
        this.user = user;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return nom;
    }
}
