package montp.web.controllers;


import montp.data.dao.ResourceTypeDAO;
import montp.data.model.ResourceType;
import montp.web.FacesTools;

import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;


@ViewScoped
@Named
public class EditerRessourceType implements Serializable {
    @Produces
    @Named
    ResourceType resourceType;
    @Inject
    ResourceTypeDAO dao;


public void  create(){
    resourceType = new ResourceType();
}

    public void save() {
        if (resourceType.getId() == null) {
            dao.insert(resourceType);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Joueur créé");
        } else {
            dao.update(resourceType);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Modifications enregistrées");
        }
    }

public ResourceType getResourceType(){
    return resourceType;
}

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }
}
