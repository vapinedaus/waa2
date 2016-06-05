/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author 984865
 */
@Named(value = "CounterMB")
@ApplicationScoped
public class CounterMB {

    
    private int counter;

    public CounterMB() {
          FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("Counter", this);
    }
    

    public int getCounter() {
        return counter;
    }

    public void setCounter() {
        this.counter = counter+1;
    }
    public void setMinusCounter() {
        this.counter = counter-1;
    }
    /**
     * Creates a new instance of Counter
     */

    
}
