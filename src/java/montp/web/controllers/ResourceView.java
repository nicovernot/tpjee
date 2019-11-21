package montp.web.controllers;

import montp.data.dao.ResourceDao;
import montp.data.model.Resource;
import montp.services.ResourcePaginator;
import montp.web.FacesTools;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.TransactionalException;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class ResourceView implements Serializable {

    @Inject
    private ResourceDao resourceDao;
    @Inject
    private ResourcePaginator resourcesPaginator;
    private List<Resource> res;

    @PostConstruct
    public void init() {
        res = resourceDao.getAll();
    }

    public List<Resource> getRes() {
        return res;
    }

    public void setRes(List<Resource> res) {
        this.res = res;
    }

    public boolean canDelete(Resource resource) {
        return !resourceDao.canDelete(resource);
    }



    public void delete(Resource resource) {
        try {
            resourceDao.delete(resource);
        } catch (TransactionalException txe) {
            FacesTools.addMessage(FacesMessage.SEVERITY_ERROR,
                "Impossible de supprimer le joueur %s car il fait partie d'une équipe",
                resource);
        }
    }

    public void setResources(ResourcePaginator resourcesPaginator) {
        this.resourcesPaginator = resourcesPaginator;
    }

    public ResourcePaginator getResources(){
        return resourcesPaginator;
 }
}
