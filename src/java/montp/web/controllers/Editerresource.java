package montp.web.controllers;

import montp.data.dao.PersonDAO;
import montp.data.dao.ResourceDao;
import montp.data.dao.ResourceTypeDAO;
import montp.data.model.Resource;
import montp.data.model.ResourceType;
import montp.web.FacesTools;

import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@ViewScoped
@Named
public class Editerresource implements Serializable {
    @Produces
    @Named
    private Resource resource;
     @Inject
    private ResourceDao dao;
    @Inject
    private ResourceTypeDAO resourceTypeDAO;
    @Inject
    private PersonDAO personDAO;
    private  List<Resource> resourceList;

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    public void  Create(){
        resource = new Resource();
        resource.setResourceType(resourceTypeDAO.get(1303));
        resource.setAdminressource(personDAO.get(6712));
    }

    public  void save(){

        if (resource.getId() == null){
            dao.insert(resource);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,"resource créé");
        } else {
            dao.update(resource);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,"Modifications enregistréés");
        }
    }
    public  Resource getResource(){
        return resource;
    }

    public List<Resource> getResources() {
        return dao.getAll();
    }

    public  void setResource(Resource resource){
        this.resource = resource;
    }

}
