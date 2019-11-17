package montp.data.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Resource extends GenericEntity {

    private String nom;
    private int capacite;
    @OneToOne
    private Person adminressource;
    @ManyToOne
    private ResourceType resourceType;

    public Resource() {
    }

    public Resource(String nom, int capacite, Person adminressource, ResourceType resourceType) {
        this.nom = nom;
        this.capacite = capacite;
        this.adminressource = adminressource;
        this.resourceType = resourceType;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public Person getAdminressource() {
        return adminressource;
    }

    public void setAdminressource(Person adminressource) {
        this.adminressource = adminressource;
    }
}
