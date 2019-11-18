package montp.data.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class Resource extends GenericEntity {

    private String nom;
    @NotNull
    private Boolean partage;
    private int capacite;
    @OneToOne
    private Person adminressource;
    @ManyToOne
    private ResourceType resourceType;

    public Resource() {
    }

    public Resource(String nom, Boolean partage, int capacite, Person adminressource, ResourceType resourceType) {
        this.nom = nom;
        this.partage = partage;
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

    public Boolean getPartage() {
        return partage;
    }

    public void setPartage(Boolean partage) {
        this.partage = partage;
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

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }
}
