
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
public class DeathBadConsequence extends NumericBadConsequence {
    private boolean death;

    /**
     * Constructores
     * @param t representar lo que dice un mal rollo.
     * @param death representar un mal rollo del tipo muerte.
     */
    @SuppressWarnings("Convert2Diamond")
    public DeathBadConsequence(String t, int l, int nVisible, int nHidden, boolean death) {
        super(t, l, nVisible, nHidden);
        this.death = death;
    }

    @Override
    public boolean isEmpty() {
        boolean isEmpty = false;

        if (super.isEmpty() && (this.death == false)) {
            isEmpty = true;
        }

        return isEmpty;
    }
    
    @Override
    public String toString() {
        String string = super.toString();

        if (this.death) {
            string += "\tDeath: Yes";
        } else {
            string += "\tDeath: No";
        }

        return string;
    }

    public boolean getDeath() {
        return this.death;
    }
}
