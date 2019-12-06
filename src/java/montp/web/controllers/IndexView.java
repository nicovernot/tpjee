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
import java.util.*;

@ViewScoped
@Named("index")
public class IndexView implements Serializable {
@Inject
    private UserSession userSession;
    private User user;
    private Utilisateur utilisateur;
    private LineChartModel lineModel1;
    private  Double chargespayer;
    private  Double chargesreelles;
    @Inject
    private UtilisateurDAO utilisateurDAO;
    @Inject
    LigneFacturationDAO ligneFacturationDAO;
    private Double caan;
    private LignesFacturation lignesFacturation;
    private List<LignesFacturation> lignesFacturationList;
    private HashMap<Integer, Double> caMois;

@PostConstruct
    public void init(){
    caMois = new HashMap<Integer, Double>();
    userSession.init();

    FacesContext facesContext = FacesContext.getCurrentInstance();
    utilisateur = utilisateurDAO.getByName(facesContext.getExternalContext().getRemoteUser());
    chargespayer = (utilisateur.getCa()*utilisateur.getTauxCharges())/100;
    System.out.print("indexrr" + utilisateur.getNom());
    lignesFacturationList = ligneFacturationDAO.getPaye();
    createLineModels();
}

   public void cafait(){
    caan=0.00;
       for (LignesFacturation facturation : lignesFacturationList) {
           caan = (facturation.getPrixUnitaire()*facturation.getQuantite())+caan;
           int mois = facturation.getFacture().getDatePaiement().getMonth();
           System.out.print("ttt"+mois);
           caMois.put(mois,facturation.getPrixUnitaire()*facturation.getQuantite());
       }
       System.out.print(caMois.toString()+caMois.size());
       chargesreelles = (caan*utilisateur.getTauxCharges())/100;
   }

    private void createLineModels() {
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Linear Chart");
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10000);
        yAxis.setMin(0);
        yAxis.setMax(10000);
    }
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
        LineChartSeries series3 = new LineChartSeries();
        series3.setLabel("Ca réalisé");
        for (LignesFacturation facturation : lignesFacturationList) {
            int mois = facturation.getFacture().getDatePaiement().getMonth();

            caMois.put(mois,facturation.getPrixUnitaire()*facturation.getQuantite());
        }
        for (int g:caMois.keySet()){
            series3.set(g,caMois.get(g).intValue());

        }
        System.out.print("size "+caMois.size()+utilisateur.getNom());;
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Ca attendu");
        int caprev = utilisateur.getCa().intValue()/12;
        for (int d=1;d<12;d++){
            System.out.print("size "+d+caprev);;
            series1.set(d, caprev);
        }


        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("charges attendues");
        int chprev = (int) (((utilisateur.getCa().intValue()*utilisateur.getTauxCharges())/100)/12);
        for (int d=1;d<12;d++){
            System.out.print("size "+d+chprev);;
            series2.set(d, chprev);
        }
        model.addSeries(series3);
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

    public Double getChargesreelles() {
        return chargesreelles;
    }

    public void setChargesreelles(Double chargesreelles) {
        this.chargesreelles = chargesreelles;
    }
}
