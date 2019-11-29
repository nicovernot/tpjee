package montp.services;

import montp.data.dao.ProjetDAO;
import montp.data.model.Projet;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ProjetPaginator extends LazyDataModel<Projet> implements Serializable {
@Inject
    ProjetDAO projetDAO;

    public List<Projet> load(int first, int pageSize,
                             String sortField, SortOrder sortOrder,
                             Map<String, Object> filters) {

        if (filters.isEmpty()) {
            setRowCount(projetDAO.getCount());
            return projetDAO.get(first, pageSize);
        } else {
            String nom = (String) filters.get("nom");
            setRowCount(projetDAO.getCountByNom(nom));
            return projetDAO.get(first, pageSize,nom);
        }
    }



}
