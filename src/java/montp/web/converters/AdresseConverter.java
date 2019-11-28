package montp.web.converters;

import montp.data.dao.AdresseDAO;
import montp.data.model.Adresse;
import montp.tools.Tools;

import javax.faces.convert.FacesConverter;
import java.io.Serializable;

@FacesConverter(forClass = Adresse.class)
public class AdresseConverter extends GenericConverter<Adresse> {
public  AdresseConverter(){ super(Tools.lookupBean(AdresseDAO.class));};

}
