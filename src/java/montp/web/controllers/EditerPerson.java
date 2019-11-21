package montp.web.controllers;

import montp.data.dao.PersonDAO;
import montp.data.model.Person;
import montp.web.FacesTools;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class EditerPerson implements Serializable {

    private Person person;
    @Inject
    private PersonDAO personDAO;
    public void Create(){
        person = new Person();
    }
    public  void save(){

        if (person.getId() == null){
            personDAO.insert(person);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,"resource créé");
        } else {
            personDAO.update(person);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,"Modifications enregistréés");
        }
    }
    public  Person getResource(){
        return person;
    }

    public List<Person> getPersons() {
        return personDAO.getAll();
    }

    public  void setResource(Person person){
        this.person = person;
    }
}
