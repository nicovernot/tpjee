package montp.web.converters;

import montp.data.dao.ProjetDAO;
import montp.data.model.Projet;
import montp.tools.Tools;

import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Projet.class)
public class ProjetConverter extends GenericConverter<Projet>{
public ProjetConverter(){ super(Tools.lookupBean(ProjetDAO.class));}
}
