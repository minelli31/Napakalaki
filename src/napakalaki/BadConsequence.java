
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
    private String                  text;
    private int                     levels;
    private int                     nVisibleTreasures;
    private int                     nHiddenTreasures;
    private boolean                 death;
    private ArrayList<TreasureKind> specificVisibleTreasures;
    private ArrayList<TreasureKind> specificHiddenTreasures;

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

    public BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h) {
        BadConsequence b;

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

            b = new BadConsequence(this.text, this.levels, nV, nH);
        } else {
            ArrayList<TreasureKind> sV = new ArrayList<TreasureKind>();
            ArrayList<TreasureKind> sH = new ArrayList<TreasureKind>();

            for (Treasure tv : v) {
                sV.add(tv.getType());
            }

            ArrayList<TreasureKind> intersectionV = new ArrayList(v);

            intersectionV.retainAll(intersectionV);

            for (Treasure th : h) {
                sH.add(th.getType());
            }

            ArrayList<TreasureKind> intersectionH = new ArrayList(h);

            intersectionH.retainAll(intersectionV);
            b = new BadConsequence(this.text, this.levels, intersectionV, intersectionH);
        }

        return b;
    }

    public void substractHiddenTreasure(Treasure t) {
        this.specificHiddenTreasures.remove(t.getType());

        if (this.nHiddenTreasures != 0) {
            this.nHiddenTreasures--;
        }
    }

    public void substractVisibleTreasure(Treasure t) {
        this.specificVisibleTreasures.remove(t.getType());

        if (this.nVisibleTreasures != 0) {
            this.nVisibleTreasures--;
        }
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

    public boolean getDeath() {
        return this.death;
    }

    public boolean isEmpty() {
        boolean isEmpty = false;

        if ((this.levels == 0)
                && (this.nVisibleTreasures == 0)
                && (this.nHiddenTreasures == 0)
                && this.specificHiddenTreasures.isEmpty()
                && this.specificVisibleTreasures.isEmpty()
                && (this.death == false)) {
            isEmpty = true;
        }

        return isEmpty;
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
}
