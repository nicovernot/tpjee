package montp.web.controllers;

import montp.data.model.security.Group;
import montp.data.model.security.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

import javax.inject.Named;
import java.io.Serializable;

@RequestScoped
@Named("login")
public class LoginView implements Serializable {
    private User user;
    private Group group;
    @PostConstruct
    public void init() {
      //  user = new User();
    }

    public User getUser(){
        return user;
    }
}
