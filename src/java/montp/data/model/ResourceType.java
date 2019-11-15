package montp.data.model;


import com.sun.istack.internal.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class ResourceType extends GenericEntity {
    @Id
    @GeneratedValue
    @NotNull
    private Integer id;
    @NotNull
    private String nom;
    @NotNull
    private String type;
    @NotNull
    private Boolean partage;

    public ResourceType() {
    }

    public ResourceType(String nom, String type, Boolean partage) {
        this.nom = nom;
        this.type = type;
        this.partage = partage;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getPartage() {
        return partage;
    }

    public void setPartage(Boolean partage) {
        this.partage = partage;
    }

    @Override
    public String toString() {
        return "ResourceType{" +
            "nom='" + nom + '\'' +
            ", type='" + type + '\'' +
            ", partage=" + partage +
            '}';
    }
}
