package montp.web.controllers;


import montp.data.dao.ResourceTypeDAO;
import montp.data.model.ResourceType;
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
public class TypeResource implements Serializable {

    @Inject
    private ResourceTypeDAO res;
    private List<ResourceType> rst;

    @PostConstruct
    public void init() {
        rst = res.getAll();
    }
    public Collection<ResourceType> getRestyp() {

        return res.getAll();
    }



    public int getCoutrestype(){
        return res.getCount();
    }
}
