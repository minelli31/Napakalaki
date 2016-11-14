
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;

import static napakalaki.PruebaNapakalaki.monstruos;
import static napakalaki.PruebaNapakalaki.tamMonsters;

/**
 *
 * @author minelli
 */
@SuppressWarnings("FieldMayBeFinal")
public class Monster {
    private final String         name;
    private final int            combatLevel;
    private final Prize          prize;
    private final BadConsequence badConsequence;

    public Monster(String name, int combatLevel, BadConsequence badConsequence, Prize prize) {
        this.name           = name;
        this.combatLevel    = combatLevel;
        this.badConsequence = badConsequence;
        this.prize          = prize;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\tLevel: " + Integer.toString(this.combatLevel) + "\n"
               + this.badConsequence.toString();
    }

    public BadConsequence getBadConsequence() {
        return this.badConsequence;
    }

    public int getBadLevels() {
        return this.badConsequence.getLevels();
    }

    public String getBadText() {
        return this.badConsequence.getText();
    }

    public int getBadnHiddenTreasures() {
        return this.badConsequence.getNHiddenTreasures();
    }

    public int getBadnVisibleTreasures() {
        return this.badConsequence.getNVisibleTreasures();
    }

    public boolean getDeath() {
        return this.badConsequence.getDeath();
    }

    public int getLevel() {
        return this.combatLevel;
    }

    public int getLevelsGained() {
        return prize.getLevels();
    }

    public String getName() {
        return this.name;
    }

    public int getPrizeLevels() {
        return this.prize.getLevels();
    }

    public ArrayList<TreasureKind> getSpecificHiddenTreasures() {
        return this.badConsequence.getSpecificHidden();
    }

    public ArrayList<TreasureKind> getSpecificVisibleTreasures() {
        return this.badConsequence.getSpecificVisible();
    }

    public int getTreasuresGained() {
        return prize.getTreasures();
    }
}
