package montp.web.converters;

import montp.data.dao.LigneFacturationDAO;
import montp.data.model.LignesFacturation;
import montp.tools.Tools;

import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = LignesFacturation.class)
public class LignesFactureConverter extends GenericConverter<LignesFacturation> {
    public LignesFactureConverter(){super(Tools.lookupBean(LigneFacturationDAO.class));}
}
