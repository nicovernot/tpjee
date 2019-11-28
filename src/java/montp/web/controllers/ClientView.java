package montp.web.controllers;

import montp.data.dao.ClientDAO;
import montp.data.model.Client;
import montp.data.model.Client;
import montp.locale.Messages;
import montp.web.FacesTools;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.TransactionalException;
import java.util.Collection;
import java.util.List;

@ViewScoped
@Named
public class ClientView {

    @Inject
    private ClientDAO res;
    private List<Client> rst;
    private Messages messages;
    private  Client client;
    @PostConstruct
    public void init() {
        rst = res.getAll();
    }
    public Collection<Client> getRestyp() {
        return res.getAll();
    }

    // public String Mes(String cle) {
    //    return messages.get(cle);
    // }


    public Messages getMessages() {
        return messages;
    }


    public int getCoutrestype(){
        return res.getCount();
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
            res.insert(client);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Client créé");
        } else {
            res.update(client);
            FacesTools.addMessage(FacesMessage.SEVERITY_INFO,
                "Modifications enregistrées");
        }
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

}
