package montp.web.converters;

import montp.data.dao.ClientDAO;
import montp.data.model.Client;
import montp.tools.Tools;

import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Client.class)
public class ClientConverter extends GenericConverter<Client> {
    public ClientConverter(){ super(Tools.lookupBean(ClientDAO.class));}
}
