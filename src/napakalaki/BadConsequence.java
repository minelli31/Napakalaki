
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package napakalaki;

/**
 *
 * @author minelli
 */
public abstract class BadConsequence {

    /**
     * Variables
     */
    protected String text;
    protected int    levels;

    @SuppressWarnings("Convert2Diamond")
    public BadConsequence(String t, int l) {
        this.text   = t;
        this.levels = 0;
    }

    public abstract void substractHiddenTreasure(Treasure t);

    public abstract void substractVisibleTreasure(Treasure t);

    @Override
    public String toString() {
        String string = "Bad consequence: " + this.text + "\tLevels: " + Integer.toString(this.levels);

        return string;
    }

    public boolean isEmpty() {
        boolean isEmpty = false;

        if (this.levels == 0) {
            isEmpty = true;
        }

        return isEmpty;
    }

    public int getLevels() {
        return this.levels;
    }

    public String getText() {
        return this.text;
    }
}
