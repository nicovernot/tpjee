package montp.web.converters;

import montp.data.dao.FactureDAO;
import montp.data.model.Facture;
import montp.tools.Tools;

import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Facture.class)
public class FactureConverter extends GenericConverter<Facture>{
    public FactureConverter(){ super(Tools.lookupBean(FactureDAO.class));}
}
