
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
public class BadConsequence {

    /**
     * Variables
     */
    private final String                  text;
    private final int                     levels;
    private final int                     nVisibleTreasures;
    private final int                     nHiddenTreasures;
    private final boolean                 death;
    private final ArrayList<TreasureKind> specificVisibleTreasures;
    private final ArrayList<TreasureKind> specificHiddenTreasures;

    /**
     * Constructores
     * @param t representar lo que dice un mal rollo.
     * @param death representar un mal rollo del tipo muerte.
     */
    @SuppressWarnings("Convert2Diamond")
    public BadConsequence(String t, boolean death) {
        this.text                     = t;
        this.levels                   = 0;
        this.nHiddenTreasures         = 0;
        this.nVisibleTreasures        = 0;
        this.specificHiddenTreasures  = new ArrayList<TreasureKind>();
        this.specificVisibleTreasures = new ArrayList<TreasureKind>();
        this.death                    = death;
    }

    /**
     *
     * @param t representar lo que dice un mal rollo.
     * @param l representar los niveles que se pierden.
     * @param v representar atributos de tipo lista de tesoros visibles que se pierden.
     * @param h representar atributos de tipo lista de tesoros ocultos que se pierden.
     */
    @SuppressWarnings("Convert2Diamond")
    public BadConsequence(String t, int l, ArrayList<TreasureKind> v, ArrayList<TreasureKind> h) {
        this.text                     = t;
        this.levels                   = l;
        this.specificVisibleTreasures = v;
        this.specificHiddenTreasures  = h;
        this.nHiddenTreasures         = 0;
        this.nVisibleTreasures        = 0;
        this.death                    = false;
    }

    /**
     * Constructores
     * @param t representar lo que dice un mal rollo.
     * @param l representar los niveles que se pierden.
     * @param nVisible representar el número de tesoros visibles que se pierden.
     * @param nHidden representar el número de tesoros ocultos que se pierden.
     */
    @SuppressWarnings("Convert2Diamond")
    public BadConsequence(String t, int l, int nVisible, int nHidden) {
        this.text                     = t;
        this.levels                   = l;
        this.nVisibleTreasures        = nVisible;
        this.nHiddenTreasures         = nHidden;
        this.death                    = false;
        this.specificHiddenTreasures  = new ArrayList<TreasureKind>();
        this.specificVisibleTreasures = new ArrayList<TreasureKind>();
    }

    @Override
    public String toString() {
        String string = "Bad consequence: " + this.text + "\tLevels: " + Integer.toString(this.levels);

        if (this.death) {
            string += "\tDeath: Yes";
        } else {
            string += "\tDeath: No";
        }

        return string;
    }

    public int getLevels() {
        return this.levels;
    }

    public int getNHiddenTreasures() {
        return this.nHiddenTreasures;
    }

    public int getNVisibleTreasures() {
        return this.nVisibleTreasures;
    }

    public ArrayList<TreasureKind> getSpecificHidden() {
        return this.specificHiddenTreasures;
    }

    public ArrayList<TreasureKind> getSpecificVisible() {
        return this.specificVisibleTreasures;
    }

    public String getText() {
        return this.text;
    }
    public boolean getDeath(){
        return this.death;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
