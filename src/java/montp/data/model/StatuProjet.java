package montp.data.model;

import javax.persistence.Entity;

/**
 * Class StatuProjet
 */
@Entity
public class StatuProjet extends GenericEntity{

  //
  // Fields
  //

  private String type;

  //
  // Constructors
  //
  public StatuProjet () { };

    public StatuProjet(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
