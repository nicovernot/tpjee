package montp.web.converters;


import montp.data.dao.UtilisateurDAO;
import montp.data.model.Utilisateur;
import montp.tools.Tools;

import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Utilisateur.class)
public class UtilisateurConverter extends GenericConverter<Utilisateur> {

    public UtilisateurConverter(){ super(Tools.lookupBean(UtilisateurDAO.class));}
}
