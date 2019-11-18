package montp.services;


import montp.data.dao.ResourceDao;
import montp.data.model.Resource;
import org.primefaces.model.LazyDataModel;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

@ViewScoped
public class ResourcePaginator extends LazyDataModel<Resource> implements Serializable {
    @Inject
    private ResourceDao resourceDao;


    public List<Resource> load(int first, int pageSize,
                               String sortField, SortOrder sortOrder,
                               Map<String, Object> filters) {

        if (filters.isEmpty()) {
            setRowCount(resourceDao.getCount());
            return resourceDao.get(first, pageSize);
        } else {
           // boolean isactif = (Boolean)filters.get("actif");
            setRowCount(resourceDao.getCount());
            return resourceDao.get(first, pageSize);
        }
    }




}
