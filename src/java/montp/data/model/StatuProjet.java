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

  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of type
   * @param newVar the new value of type
   */
  private void setType (String newVar) {
    type = newVar;
  }

  /**
   * Get the value of type
   * @return the value of type
   */
  private String getType () {
    return type;
  }

  //
  // Other methods
  //

    @Override
    public String toString() {
        return "StatuProjet{" +
            "type='" + type + '\'' +
            '}';
    }
}
