/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import javax.inject.Named;
import model.Person;
import database.Database;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author VictorPineda
 */
@Named(value = "userMB")
@SessionScoped
public class Login implements Serializable {

    /**
     * Creates a new instance of UserMB
     */
    private Person user;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Login() {
        createAccounts(); 
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public boolean login(String username, String password) {
        Person authUser = Database.AuthUser(username, password);
        if (authUser != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("Auth", true);
            setUser(authUser);
            
             CounterMB cm= (CounterMB)FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("Counter");
             if (cm== null)
             {
             cm = new CounterMB();
             }
             cm.setCounter();
            
            return true;
        }

        return false;

    }

    public String login() {
        boolean isLogin = login(user.getUsername(), user.getPassword());
        if (isLogin) {
            return "welcome.jsf?faces-redirect=true";
        } else {
            error = "Invalid Authentication";
            return "login.jsf?faces-redirect=true";
        }

    }

    public boolean createAccounts() {
        try {
            user = new Person();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public List<Person> getAccounts() {
        return Database.getAllUsers();
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        
        CounterMB cm= (CounterMB)FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("Counter");
        cm.setMinusCounter();

        return "login.jsf?faces-redirect=true";

    }

}
