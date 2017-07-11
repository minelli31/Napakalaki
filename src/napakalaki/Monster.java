
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
    private String         name;
    private int            combatLevel;
    private Prize          prize;
    private BadConsequence badConsequence;
    private int            levelChangeAgainstCultistPlayer;

    public Monster(String name, int combatLevel, BadConsequence badConsequence, Prize prize) {
        this.name                            = name;
        this.combatLevel                     = combatLevel;
        this.badConsequence                  = badConsequence;
        this.prize                           = prize;
        this.levelChangeAgainstCultistPlayer = 0;
    }

    public Monster(String name, int combatLevel, BadConsequence badConsequence, Prize prize,
                   int levelChangeAgainstCultistPlayer) {
        this.name                            = name;
        this.combatLevel                     = combatLevel;
        this.badConsequence                  = badConsequence;
        this.prize                           = prize;
        this.levelChangeAgainstCultistPlayer = levelChangeAgainstCultistPlayer;
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
        return ((NumericBadConsequence) this.badConsequence).getNHiddenTreasures();
    }

    public int getBadnVisibleTreasures() {
        return ((NumericBadConsequence) this.badConsequence).getNVisibleTreasures();
    }

    public int getCombatLevel() {
        return combatLevel;
    }

    public int getCombatLevelAgainstCultistPlayer() {
        return this.levelChangeAgainstCultistPlayer + this.getLevel();
    }

    public boolean getDeath() {
        return ((DeathBadConsequence) this.badConsequence).getDeath();
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

    public Prize getPrize() {
        return prize;
    }

    public int getPrizeLevels() {
        return this.prize.getLevels();
    }

    public int getSpecialValue() {
        return this.getLevel() + this.levelChangeAgainstCultistPlayer;
    }

    public ArrayList<TreasureKind> getSpecificHiddenTreasures() {
        return ((SpecificBadConsequence) this.badConsequence).getSpecificHidden();
    }

    public ArrayList<TreasureKind> getSpecificVisibleTreasures() {
        return ((SpecificBadConsequence) this.badConsequence).getSpecificVisible();
    }

    public int getTreasuresGained() {
        return prize.getTreasures();
    }

}
