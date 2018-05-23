package controller.common;

import persistence.IPersistence;
import persistence.Persistence;
import persistence.UserLogin;


public class Authenticator {
    IPersistence persist;

    public Authenticator() {
        this.persist = new Persistence();
    }


    public boolean Authenticate(String user, String pass){
        UserLogin userLogin = persist.getUserLogin(user);
        return userLogin.validatePassword(pass);
    }
}
