package montp.data.model;

import javax.persistence.Entity;

/**
 * Class Adresse
 */
@Entity
public class Adresse extends GenericEntity{

  private String rue;
  private Integer numeroRue;
  private String codePostal;
  private String ville;

    public Adresse() {
    }

    public Adresse(String rue, Integer numeroRue, String codePostal, String ville) {
        this.rue = rue;
        this.numeroRue = numeroRue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public Integer getNumeroRue() {
        return numeroRue;
    }

    public void setNumeroRue(Integer numeroRue) {
        this.numeroRue = numeroRue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Adresse{" +
            "rue='" + rue + '\'' +
            ", numeroRue=" + numeroRue +
            ", codePostal='" + codePostal + '\'' +
            ", ville='" + ville + '\'' +
            '}';
    }
}
