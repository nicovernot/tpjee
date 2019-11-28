package montp.web.converters;

import montp.data.dao.StatuProjetDAO;
import montp.data.model.StatuProjet;
import montp.tools.Tools;

import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = StatuProjet.class)
public class StatuProjetConverter extends GenericConverter<StatuProjet>{
    public StatuProjetConverter(){ super(Tools.lookupBean(StatuProjetDAO.class));}
}
