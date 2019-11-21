package montp.web.converters;

import montp.data.dao.PersonDAO;
import montp.data.model.Person;
import montp.tools.Tools;

import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Person.class)
public class PersonConverter extends GenericConverter<Person> {
    public PersonConverter() {
        super(Tools.lookupBean(PersonDAO.class));
    }
}
