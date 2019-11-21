package montp.web.converters;

import montp.data.dao.ResourceTypeDAO;
import montp.data.model.ResourceType;
import montp.tools.Tools;

import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = ResourceType.class)
public class ResourceTypeConverter extends  GenericConverter<ResourceType>{
    public ResourceTypeConverter() {
        super(Tools.lookupBean(ResourceTypeDAO.class));
    }

}
