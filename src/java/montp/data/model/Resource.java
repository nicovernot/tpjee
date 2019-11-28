package montp.data.model;



import javax.persistence.*;


@Entity
public class Resource extends GenericEntity {

    @Id
    @GeneratedValue

    private Integer id;
    @Col1

    private String nom;

    private Boolean partage;
    private int capacite;
    @ManyToOne
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

    @Override
    public String toString() {
        return nom;
    }
}
