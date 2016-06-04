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
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author VictorPineda
 */
@Named(value = "userMB")
@SessionScoped
public class UserMB implements Serializable {

    /**
     * Creates a new instance of UserMB
     */
    private Person user = new Person();
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public UserMB() {
    }
    
    
    
    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }
    
    
    public String login()
    {
       Person authUser = Database.AuthUser(user.getUsername(),user.getPassword() );
       if (authUser!=null)
       {
          FacesContext context = FacesContext.getCurrentInstance();
          context.getExternalContext().getSessionMap().put("Auth", true);
         return "welcome.jsf?faces-redirect=true";
       }
       else
       {
           error = "Invalid Authentication";
          return "login.jsf";
       }
          

    }
    
}
