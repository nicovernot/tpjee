package montp.data.model;




import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity


public class ResourceType extends GenericEntity {
    @Id
    @GeneratedValue

    private Integer id;


    private String type;



    public ResourceType() {
    }

    public ResourceType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
