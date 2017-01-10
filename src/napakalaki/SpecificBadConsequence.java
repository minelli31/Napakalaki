
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
public class SpecificBadConsequence extends BadConsequence {
    private ArrayList<TreasureKind> specificVisibleTreasures;
    private ArrayList<TreasureKind> specificHiddenTreasures;

    @SuppressWarnings("Convert2Diamond")
    public SpecificBadConsequence(String t, int l, ArrayList<TreasureKind> v, ArrayList<TreasureKind> h) {
        super(t, l);
        this.specificVisibleTreasures = v;
        this.specificHiddenTreasures  = h;
    }

    public BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h) {
        BadConsequence          b;
        ArrayList<TreasureKind> sV = new ArrayList<TreasureKind>();
        ArrayList<TreasureKind> sH = new ArrayList<TreasureKind>();

        for (Treasure tv : v) {
            sV.add(tv.getType());
        }

        ArrayList<TreasureKind> intersectionV = new ArrayList(sV);

        intersectionV.retainAll(this.specificVisibleTreasures);

        for (Treasure th : h) {
            sH.add(th.getType());
        }

        ArrayList<TreasureKind> intersectionH = new ArrayList(sH);

        intersectionH.retainAll(this.specificHiddenTreasures);
        b = new SpecificBadConsequence(this.text, this.levels, intersectionV, intersectionH);

        return b;
    }
    @Override
    public void substractHiddenTreasure(Treasure t) {
        this.specificHiddenTreasures.remove(t.getType());
    }
    @Override
    public void substractVisibleTreasure(Treasure t) {
        this.specificVisibleTreasures.remove(t.getType());
    }

    @Override
    public boolean isEmpty() {
        boolean isEmpty = false;

        if (super.isEmpty() && this.specificHiddenTreasures.isEmpty() && this.specificVisibleTreasures.isEmpty()) {
            isEmpty = true;
        }

        return isEmpty;
    }

    public ArrayList<TreasureKind> getSpecificHidden() {
        return this.specificHiddenTreasures;
    }

    public ArrayList<TreasureKind> getSpecificVisible() {
        return this.specificVisibleTreasures;
    }
}
