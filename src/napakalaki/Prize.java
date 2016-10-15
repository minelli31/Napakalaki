
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
public class Prize {

    private final int treasures;
    private final int levels;

    //Constructores
    public Prize(int treasures, int levels) {
        this.treasures = treasures;
        this.levels = levels;
    }

    //Consultores
    public int getTreasures() {
        return treasures;
    }

    public int getLevels() {
        return levels;
    }

    //Métodos auxiliares
    @Override
    public String toString() {
        return Integer.toString(treasures) + " tesoros y " + Integer.toString(levels) + " niveles.";
    }
}
