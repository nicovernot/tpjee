package montp.web.controllers;

import montp.data.dao.ResourceTypeDAO;
import montp.data.dao.UtilisateurDAO;
import montp.data.model.ResourceType;
import montp.data.model.Utilisateur;
import montp.data.model.security.User;
import montp.web.UserSession;

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
    @Inject
    private UtilisateurDAO utilisateurDAO;
@PostConstruct
    public void init(){
    userSession.init();
    FacesContext facesContext = FacesContext.getCurrentInstance();
    utilisateur = utilisateurDAO.getByName(facesContext.getExternalContext().getRemoteUser());
    System.out.print("rr" + utilisateur.getNom());
}


}
