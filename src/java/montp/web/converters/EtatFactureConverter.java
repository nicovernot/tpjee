package montp.web.converters;

import montp.data.dao.EtatFactureDAO;
import montp.data.model.EtatFacture;
import montp.tools.Tools;

import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = EtatFacture.class)
public class EtatFactureConverter extends  GenericConverter<EtatFacture>{
    public EtatFactureConverter(){ super(Tools.lookupBean(EtatFactureDAO.class));}
}
