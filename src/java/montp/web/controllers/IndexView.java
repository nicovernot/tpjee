package montp.web.controllers;

import montp.data.dao.FactureDAO;
import montp.data.dao.LigneFacturationDAO;
import montp.data.dao.ResourceTypeDAO;
import montp.data.dao.UtilisateurDAO;
import montp.data.model.LignesFacturation;
import montp.data.model.ResourceType;
import montp.data.model.Utilisateur;
import montp.data.model.security.User;
import montp.web.UserSession;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@ViewScoped
@Named("index")
public class IndexView implements Serializable {
@Inject
    private UserSession userSession;
    private User user;
    private Utilisateur utilisateur;
    private LineChartModel lineModel1;
    private  Double chargespayer;
    @Inject
    private UtilisateurDAO utilisateurDAO;
    @Inject
    LigneFacturationDAO ligneFacturationDAO;
    private Double caan;
    private LignesFacturation lignesFacturation;
    private List<LignesFacturation> lignesFacturationList;

@PostConstruct
    public void init(){
    userSession.init();
    createLineModels();
    FacesContext facesContext = FacesContext.getCurrentInstance();
    utilisateur = utilisateurDAO.getByName(facesContext.getExternalContext().getRemoteUser());
    chargespayer = (utilisateur.getCa()*utilisateur.getTauxCharges())/100;
    System.out.print("indexrr" + utilisateur.getNom());
    lignesFacturationList = ligneFacturationDAO.getPaye();

}

   public void cafait(){
    caan=0.00;
       for (LignesFacturation facturation : lignesFacturationList) {
           caan = (facturation.getPrixUnitaire()*facturation.getQuantite())+caan;
           System.out.print("ttt"+caan);
       }

   }

    private void createLineModels() {
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Linear Chart");
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(12);
        yAxis.setMin(0);
        yAxis.setMax(12);
    }
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");

        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(10, 8);

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");

        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(10, 9);

        model.addSeries(series1);
        model.addSeries(series2);

        return model;
    }

    public LineChartModel getLineModel1() {
        return lineModel1;
    }

    public void setLineModel1(LineChartModel lineModel1) {
        this.lineModel1 = lineModel1;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Double getChargespayer() {
        return chargespayer;
    }

    public void setChargespayer(Double chargespayer) {
        this.chargespayer = chargespayer;
    }

    public Double getCaan() {
        cafait();
        return caan;
    }

    public void setCaan(Double caan) {

        this.caan = caan;
    }

    public LignesFacturation getLignesFacturation() {
        return lignesFacturation;
    }

    public void setLignesFacturation(LignesFacturation lignesFacturation) {
        this.lignesFacturation = lignesFacturation;
    }

    public List<LignesFacturation> getLignesFacturationList() {
        return lignesFacturationList;
    }

    public void setLignesFacturationList(List<LignesFacturation> lignesFacturationList) {
        this.lignesFacturationList = lignesFacturationList;
    }
}
