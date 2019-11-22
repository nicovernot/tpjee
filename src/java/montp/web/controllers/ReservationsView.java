package montp.web.controllers;

import montp.data.dao.ReservationDAO;
import montp.data.dao.ResourceDao;
import montp.data.model.Reservation;
import montp.data.model.Resource;
import montp.web.FacesTools;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@ViewScoped
@Named("reservations")
public class ReservationsView implements Serializable  {
    private ScheduleModel eventModel;

    private ScheduleModel lazyEventModel;

    private ScheduleEvent event = new DefaultScheduleEvent();

    HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    HttpServletResponse res = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
    String param;
    @Inject
    private Resource resource;

    private Reservation reservation;
    @Inject
    private ReservationDAO reservationDAO;
    @Inject
    private ResourceDao resourceDao;
    @PostConstruct
    public void init() {
        lazyEventModel = new LazyScheduleModel() {

            @Override
            public void loadEvents(Date start, Date end) {
                for (int i=1; i<=5; i++) {
                    Date random = Date.from(Instant.now().minus(Duration.ofDays(10)));
                    Date now = Date.from(Instant.now());
                    addEvent(new DefaultScheduleEvent("Lazy Event " + i, random, now));
                }
            }
        };
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }
    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", Date.from(Instant.now()),Date.from(Instant.now()));
    }
    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public void addEvent() {
        System.out.print(event.getEndDate().toString()+" "+event.getStartDate().toString()+event.getTitle());
        if(event.getId() == null)
            eventModel.addEvent(event);
        else
            eventModel.updateEvent(event);

        event = new DefaultScheduleEvent();
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    public void setLazyEventModel(ScheduleModel lazyEventModel) {
        this.lazyEventModel = lazyEventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public String getParam() {

        param= req.getParameter("productId");

        return param;
    }

    public Resource getResource() {
        int parid=1;
        try {
              parid =  Integer.parseInt(getParam().trim());

        }catch (NumberFormatException e){
            System.out.print(param+"exeption lors du passage du param vu reeservation");
        }
        FacesTools.addMessage(FacesMessage.SEVERITY_INFO,"resource Recuperé");
        return resource=resourceDao.get(parid);
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
