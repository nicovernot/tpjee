package montp.data.model;

import javax.persistence.Entity;

/**
 * Class Statut
 */
@Entity
public class Statut extends GenericEntity {

  //
  // Fields
  //

  private String type;

  //
  // Constructors
  //
  public Statut () { };

    public Statut(String type) {
        this.type = type;
    }

    //
  // Methods
  //

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Statut{" +
            "type='" + type + '\'' +
            '}';
    }
}
