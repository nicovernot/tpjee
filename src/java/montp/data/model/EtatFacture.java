package montp.data.model;

import javax.persistence.Entity;

/**
 * Class EtatFacture
 */
@Entity
public class EtatFacture extends GenericEntity{



  private String etat;

    public EtatFacture() {
    }

    public EtatFacture(String etat) {
        this.etat = etat;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return etat ;
    }
}
