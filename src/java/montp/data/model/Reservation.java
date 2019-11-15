package montp.data.model;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

@Entity
public class Reservation extends GenericEntity {
    private Date dateDebut;
    private Date dateFin;
    @OneToOne
    private Resource resource;
    @OneToMany
    private List<Person> personList;

}
