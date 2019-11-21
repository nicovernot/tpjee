package montp.web.controllers;

import montp.data.model.security.Group;
import montp.data.model.security.User;

import javax.enterprise.context.RequestScoped;

import javax.inject.Named;

@RequestScoped
@Named("login")
public class LoginView {
    private User user;
    private Group group;

    public User getUser(){
        return user;
    }
}
