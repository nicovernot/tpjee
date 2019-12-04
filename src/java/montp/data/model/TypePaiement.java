package montp.data.model;

import javax.persistence.Entity;

/**
 * Class TypePaiement
 */
@Entity
public class TypePaiement extends GenericEntity {

  //
  // Fields
  //

  private String typePaiement;

  //
  // Constructors
  //
  public TypePaiement () { };

    public TypePaiement(String typePaiement) {
        this.typePaiement = typePaiement;
    }

    public String getTypePaiement() {
        return typePaiement;
    }

    public void setTypePaiement(String typePaiement) {
        this.typePaiement = typePaiement;
    }

    @Override
    public String toString() {
        return typePaiement;
    }
}
