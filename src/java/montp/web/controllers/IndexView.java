package montp.web.controllers;

import montp.data.dao.ResourceTypeDAO;
import montp.data.model.ResourceType;
import montp.data.model.security.User;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named("index")
public class IndexView implements Serializable {

    @Inject
    private ResourceTypeDAO res;

    @PostConstruct
    public void init() {
       List<ResourceType> rst = res.getAll();
    }

}
