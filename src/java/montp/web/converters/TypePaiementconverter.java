package montp.web.converters;

import montp.data.dao.TypePaiementDAO;
import montp.data.model.TypePaiement;
import montp.tools.Tools;

import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = TypePaiement.class)
public class TypePaiementconverter extends GenericConverter<TypePaiement> {
    public  TypePaiementconverter(){ super(Tools.lookupBean(TypePaiementDAO.class));}
}
