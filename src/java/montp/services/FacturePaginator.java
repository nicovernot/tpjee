package montp.services;

import montp.data.dao.FactureDAO;
import montp.data.model.Facture;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class FacturePaginator extends LazyDataModel<Facture> implements Serializable {
@Inject
    private FactureDAO factureDAO;

    public List<Facture> load(int first, int pageSize,
                              String sortField, SortOrder sortOrder,
                              Map<String, Object> filters) {

        if (filters.isEmpty()) {
            setRowCount(factureDAO.getCount());
            return factureDAO.get(first, pageSize);
        } else {
            String nom = (String) filters.get("nom");
            setRowCount(factureDAO.getCountByProjet(nom));
            return factureDAO.getbyEtat(first, pageSize,nom);
        }
    }

}
