package montp.web.converters;

import montp.data.dao.ResourceDao;
import montp.data.model.Resource;
import montp.tools.Tools;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Resource.class)
public class ResourceConverter extends GenericConverter<Resource> {

    public ResourceConverter() {
        super(Tools.lookupBean(ResourceDao.class));
    }
}
