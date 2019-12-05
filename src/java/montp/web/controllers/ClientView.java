package montp.web.controllers;


import montp.data.dao.AdresseDAO;
import montp.data.dao.ClientDAO;
import montp.data.dao.UtilisateurDAO;
import montp.data.model.Adresse;
import montp.data.model.Client;
import montp.data.model.Utilisateur;
import montp.data.model.security.User;
import montp.locale.Messages;
import montp.services.ClientPaginator;
import montp.web.FacesTools;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.TransactionalException;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class ClientView implements Serializable {

    @Inject
    private ClientDAO res;
    private List<Client> rst;
    @Inject
    ClientPaginator clientPaginator;
    private Messages messages;
    private  Client client;
    private List<Client> clientList;
    @Inject
    private AdresseDAO adresseDAO;
    private Adresse adresse;
    @Inject
    private UtilisateurDAO utilisateurDAO;
    private Utilisateur utilisateur;
    private User user;
    @PostConstruct
    public void init() {
        rst = res.getAll();
        adresse =  new Adresse();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        utilisateur = utilisateurDAO.getByName(facesContext.getExternalContext().getRemoteUser());
        System.out.print("rr" + utilisateur.getNom());
    }


    public Messages getMessages() {
        return messages;
    }


    public void delete(Client client) {
        try {
            res.delete(client);
        } catch (TransactionalException txe) {
            FacesTools.addMessage(FacesMessage.SEVERITY_ERROR,
                messages.get("app.delete.error"),
                client);
        }
    }

    public void  create(){
        client = new Client();
    }


    public void save() {
        if (client.getId() == null) {
            client.setUtilisateur(utilisateur);
            res.insert(client);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Client créé");
        } else {
            res.update(client);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Modifications enregistrées");
        }
    }

    public void saveAdresse() {
        if (adresse.getId() == null) {
            adresseDAO.insert(adresse);
            client.setAdresse(adresse);
            res.update(client);

            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Adresse créé");
        } else {
            adresseDAO.update(adresse);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Modifications enregistrées");
        }
    }
    public Boolean isEmptyAdresse(Client client){
        return res.isEmptyAdresse(client);
    }

    public Client getClient(){

        return client;
    }

    public void setClient(Client client) {

        this.client = client;
    }

    public List<Client> getClients() {
        return res.getAll();
    }


    public void update(Client client) {
        try {

            res.update(client);
        } catch (TransactionalException txe) {
            FacesTools.addMessage(FacesMessage.SEVERITY_ERROR,
                "Impossible de metre à jour le Client %s car il fait partie il y a des resources de ce type",
                client);
        }
    }

    public ClientPaginator getClientPaginator() {
        return clientPaginator;
    }

    public void setClientPaginator(ClientPaginator clientPaginator) {
        this.clientPaginator = clientPaginator;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

}
