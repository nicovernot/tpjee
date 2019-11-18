package montp.data.model;


import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity


public class ResourceType extends GenericEntity {
    @Id
    @GeneratedValue
    @NotNull
    private Integer id;

    @NotNull
    private String type;

    @OneToMany
    private List<Resource> resourceList;

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

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }
}
