
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
public class NumericBadConsequence extends BadConsequence {
    private int nVisibleTreasures;
    private int nHiddenTreasures;

    /**
     * Constructores
     * @param t representar lo que dice un mal rollo.
     * @param l representar los niveles que se pierden.
     * @param nVisible representar el número de tesoros visibles que se pierden.
     * @param nHidden representar el número de tesoros ocultos que se pierden.
     */
    public NumericBadConsequence(String t, int l, int nVisible, int nHidden) {
        super(t, l);
        this.nVisibleTreasures = nVisible;
        this.nHiddenTreasures  = nHidden;
    }

    public BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h) {
        BadConsequence b = null;

        if ((this.nVisibleTreasures != 0) || (this.nHiddenTreasures != 0)) {
            int nV = 0,
                nH = 0;

            if (this.nVisibleTreasures >= v.size()) {
                nV = v.size();
            } else {
                nV = this.nVisibleTreasures;
            }

            if (this.nHiddenTreasures >= h.size()) {
                nH = h.size();
            } else {
                nH = this.nHiddenTreasures;
            }

            b = new NumericBadConsequence(this.text, this.levels, nV, nH);
        }

        return b;
    }
    @Override
    public void substractHiddenTreasure(Treasure t) {
        if (this.nHiddenTreasures != 0) {
            this.nHiddenTreasures--;
        }
    }
    @Override
    public void substractVisibleTreasure(Treasure t) {
        if (this.nVisibleTreasures != 0) {
            this.nVisibleTreasures--;
        }
    }

    @Override
    public boolean isEmpty() {
        boolean isEmpty = false;

        if (super.isEmpty() && (this.nVisibleTreasures == 0) && (this.nHiddenTreasures == 0)) {
            isEmpty = true;
        }

        return isEmpty;
    }

    public int getNHiddenTreasures() {
        return this.nHiddenTreasures;
    }

    public int getNVisibleTreasures() {
        return this.nVisibleTreasures;
    }
}
