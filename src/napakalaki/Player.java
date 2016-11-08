
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author minelli
 */
public class Player {

    // Variables privadas
    public static final int     MAXLEVEL = 10;
    private boolean             dead;
    private boolean             canISteal;
    private String              name;
    private int                 level;
    private ArrayList<Treasure> visibleTreasures;
    private ArrayList<Treasure> hiddenTreasures;
    private BadConsequence      pendingBadConsequence;
    private Player              enemy;

    // Constructor
    public Player(String name) {
        this.name                  = name;
        this.dead                  = true;
        this.canISteal             = true;
        this.level                 = 1;
        this.hiddenTreasures       = new ArrayList<Treasure>();
        this.visibleTreasures      = new ArrayList<Treasure>();
        this.pendingBadConsequence = new BadConsequence("", 0, 0, 0);
    }

    private void applyBadConsequence(Monster m) {}

    private void applyPrize(Monster m) {}

    private void bringToLife() {}

    private boolean canMakeTreasureVisible(Treasure t) {
        return true;
    }

    public CombatResult combat(Monster m) {
        CombatResult comb = null;

        return comb;
    }

    private void decrementLevels(int l) {}

    private void dieIfNoTreasures() {}

    private int howManyVisibleTreasures(TreasureKind tkind) {
        return 1;
    }

    private void incrementLevels(int l) {}

    @Override
    public String toString() {
        return "Name: " + this.name + "\tLevel: " + this.level;
    }

    private int getCombatLevel() {
        return 1;
    }

    public boolean isDead() {
        return false;
    }

    public ArrayList<Treasure> getHiddenTreasures() {
        return this.hiddenTreasures;
    }

    public int getLevels() {
        return this.level;
    }

    public String getName() {
        return this.name;
    }

    private void setPendingBadConsequence(BadConsequence b) {}

    public ArrayList<Treasure> getVisibleTreasures() {
        return this.visibleTreasures;
    }
}
