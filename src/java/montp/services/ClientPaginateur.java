package montp.services;

import montp.data.dao.ClientDAO;
import montp.data.model.Client;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ClientPaginateur extends LazyDataModel<Client> implements Serializable {

    @Inject
    private ClientDAO clientDAO;

    public List<Client> load(int first, int pageSize,
                             String sortField, SortOrder sortOrder,
                             Map<String, Object> filters) {

        if (filters.isEmpty()) {
            setRowCount(clientDAO.getCount());
            return clientDAO.get(first, pageSize);
        } else {
            String nom = (String) filters.get("nom");
            setRowCount(clientDAO.getCountByNom(nom));
            return clientDAO.get(first, pageSize,nom);
        }
    }

}
