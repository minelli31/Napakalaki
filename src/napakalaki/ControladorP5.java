
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;

/**
 *
 * @author minelli
 */
public class ControladorP5 {
    private static ControladorP5 instance;

    private BadConsequence b;
    
    private ArrayList<BadConsequence> bcons = new ArrayList<BadConsequence>();
    
    private ControladorP5() {}

    public static ControladorP5 getInstance() {
        if (instance == null) {
            return new ControladorP5();
        } else {
            return instance;
        }
    }

    public BadConsequence getBadConsequence() {
        return b;
    }

    public void setBadConsequence(BadConsequence b) {
        this.b = b;
    }
    
    public void setDeath(){        
      
        
    }
    
}
