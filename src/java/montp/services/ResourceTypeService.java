package montp.services;

import montp.data.dao.ResourceTypeDAO;
import montp.data.model.ResourceType;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ResourceTypeService extends GenericService<ResourceType, ResourceTypeDAO>{
    public List<ResourceType> getresourcetypes() {
        return dao.getAll();
    }


}
