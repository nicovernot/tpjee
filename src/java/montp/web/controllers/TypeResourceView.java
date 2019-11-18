package montp.web.controllers;


import montp.data.dao.ResourceTypeDAO;
import montp.data.model.ResourceType;
import montp.locale.Messages;
import montp.web.FacesTools;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.TransactionalException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@ViewScoped
@Named("Typeresources")
public class TypeResourceView implements Serializable {

    @Inject
    private ResourceTypeDAO res;
    private List<ResourceType> rst;
    private Messages messages;
    @PostConstruct
    public void init() {
        rst = res.getAll();
    }
    public Collection<ResourceType> getRestyp() {

        return res.getAll();
    }

   // public String Mes(String cle) {
    //    return messages.get(cle);
   // }


    public Messages getMessages() {
        return messages;
    }

    public boolean canDelete(ResourceType resourceType) {
        return !res.isEmpty(resourceType);
    }

    public int getCoutrestype(){
        return res.getCount();
    }

    public void delete(ResourceType resourceType) {
        try {
            res.delete(resourceType);
        } catch (TransactionalException txe) {
            FacesTools.addMessage(FacesMessage.SEVERITY_ERROR,
                messages.get("app.delete.error"),
                resourceType);
        }
    }


}
