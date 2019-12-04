package montp.data.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Class LignesFacturation
 */
@Entity
public class LignesFacturation extends GenericEntity {

  //
  // Fields
  //

  private String libelle;
  private Double prixUnitaire;
  private Integer quantite;
  @ManyToOne
  private  Facture facture;

    public LignesFacturation() {
    }

    public LignesFacturation(String libelle, Double prixUnitaire, Integer quantite, Facture facture) {
        this.libelle = libelle;
        this.prixUnitaire = prixUnitaire;
        this.quantite = quantite;
        this.facture = facture;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    @Override
    public String toString() {
        return "LignesFacturation{" +
            "libelle='" + libelle + '\'' +
            '}';
    }
}
